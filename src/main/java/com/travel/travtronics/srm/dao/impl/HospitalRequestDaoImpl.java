package com.travel.travtronics.srm.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travel.travtronics.converter.HospitalRequestConverter;
import com.travel.travtronics.converter.HospitalResponseConverter;
import com.travel.travtronics.request.dto.HospitalRequestModel;
import com.travel.travtronics.request.dto.PackageHospitalDTO;
import com.travel.travtronics.request.dto.SrHospitalAddonsDto;
import com.travel.travtronics.request.dto.SrHospitalRoomsDto;
import com.travel.travtronics.srm.dao.CommonAsyncDao;
import com.travel.travtronics.srm.dao.HospitalRequestDao;
import com.travel.travtronics.srm.model.HolidayRequestLine;
import com.travel.travtronics.srm.model.SrHospitalAddons;
import com.travel.travtronics.srm.model.SrHospitalAddons.HsAddonFor;
import com.travel.travtronics.srm.model.SrHospitalLines;
import com.travel.travtronics.srm.model.SrHospitalPassengers;
import com.travel.travtronics.srm.model.SrHospitalRooms;
import com.travel.travtronics.srm.repository.HolidayRequestLineRepository;
import com.travel.travtronics.srm.repository.ServiceRequestLineRepository;
import com.travel.travtronics.srm.repository.SrHospitalAddonsRepository;
import com.travel.travtronics.srm.repository.SrHospitalLinesRepository;
import com.travel.travtronics.srm.repository.SrHospitalPassengersRepository;
import com.travel.travtronics.srm.repository.SrHospitalRoomsRepository;

import lombok.extern.slf4j.Slf4j;

@Repository("HospitalRequestDao")
@Slf4j
public class HospitalRequestDaoImpl implements HospitalRequestDao {

	@Autowired
	CommonAsyncDao commonAsyncDao;

	@Autowired
	SrHospitalLinesRepository srHospitalRepository;

	@Autowired
	SrHospitalPassengersRepository srPassengeerRepository;

	@Autowired
	SrHospitalRoomsRepository srRoomsRepository;

	@Autowired
	SrHospitalAddonsRepository srAddonsRepository;

	@Autowired
	ServiceRequestLineRepository seqenceGeneratorRepository;
	@Autowired
	HolidayRequestLineRepository holidayRequestLineRepository;

	@Override
	public Map<String, Object> hospitalRequestFromPackage(PackageHospitalDTO hospitalRequest, String token)
			throws Exception {
		HospitalRequestModel model = hospitalRequest.getRequestData();
		Long generateSeqenceNumber = seqenceGeneratorRepository
				.generateSeqenceNumber(model.getSrLine().getLineSrId().toString(), "66", 2);
		model.getSrLine().setId(generateSeqenceNumber.intValue());

		Optional<SrHospitalLines> hospitalLineValidation = srHospitalRepository
				.findByIdAndLineSrId(model.getSrLine().getId(), model.getSrLine().getLineSrId());

		if (hospitalLineValidation.isPresent()) {
			throw new Exception("invalid-request..sr-and-line-already-exists");

		}
		SrHospitalLines savedLine = srHospitalRepository
				.save(HospitalRequestConverter.convertHospitalLinesJsonToModel(model.getSrLine()));

		/*********** START Package line status Change *******************/
		if (savedLine.getLineSrId() > 0) {
			Optional<HolidayRequestLine> packageLine = holidayRequestLineRepository
					.findByRequestIdAndRequestLineId(Long.valueOf(savedLine.getLineSrId()), Long.valueOf(5501));
			if (packageLine.isPresent()) {
				packageLine.get().setLineStatusId(67); // 67 - WIP
				holidayRequestLineRepository.save(packageLine.get());
			}
		}
		/*********** END Package line status Change ********************/

		log.info("sr-hospital-lines-saved");

		List<SrHospitalRoomsDto> rooms = model.getSrRooms().stream().map(room -> {

			room.setRoomLineId(savedLine.getId());

			log.info("sr-hospital-lines-saved in sr-rooms");

			SrHospitalRooms savedRoom = srRoomsRepository.save(HospitalRequestConverter.convertRoomsJsonToModel(room));

			log.info("sr-hospital-rooms-saved in-db ");

			List<SrHospitalPassengers> saveAll = srPassengeerRepository
					.saveAll(room.getRoomPassengersInfo().stream().map(pass -> {

						pass.setPassengerRoomId(savedRoom.getId());
						pass.setPassengerLineId(savedLine.getId());

						log.info("sr-hospital-rooms-id-saved-passenger-info");
						return HospitalRequestConverter.convertPassengersJsonToModel(pass);

					}).collect(Collectors.toList()));

			log.info("sr-hospital-passengers-saved in-db ");

			return HospitalResponseConverter.convertPostRoomsModelToJson(savedRoom, saveAll);

		}).collect(Collectors.toList());

		HospitalRequestModel response = new HospitalRequestModel();
		response.setSrLine(HospitalResponseConverter.convertHospitalLinesModelToJson(savedLine));
		response.setSrRooms(rooms);

		Map<String, Object> hospitalResponse = new LinkedHashMap<>();

		hospitalResponse.put("requestData", response);

		commonAsyncDao.saveSrSummaryData(savedLine.getLineSrId(), savedLine.getId(), 6, 0, savedLine.getLineCreatedBy(),
				token);

		if (hospitalRequest.getAddonsData() != null && !hospitalRequest.getAddonsData().isEmpty()) {

			String addonNights = IntStream.rangeClosed(1, savedLine.getLineNoOfNights()).mapToObj(String::valueOf)
					.collect(Collectors.joining(","));

			List<String> roomIds = rooms.stream().map(room -> String.valueOf(room.getId()))
					.collect(Collectors.toList());
			List<SrHospitalAddonsDto> collectedAddonData = hospitalRequest.getAddonsData().stream().peek(a -> {
				a.setAddonSrId(savedLine.getLineSrId());
				a.setAddonLineId(savedLine.getId());
				a.setAddonNights(addonNights);
				a.setAddonRoomId(String.join(",", roomIds));

				a.setAddonPassengers(String.join(",",
						new HotelRequestDaoImpl().generateHotelAddonPassengersData(a, rooms.size(), "HOSPITAL")));
				a.setAddonFor(HsAddonFor.R);
			}).collect(Collectors.toList());

			List<SrHospitalAddonsDto> hospitalAddons = createHospitalAddons(collectedAddonData);
			hospitalResponse.put("addonData", hospitalAddons);

		}

		return hospitalResponse;

	}

	public List<SrHospitalAddonsDto> createHospitalAddons(List<SrHospitalAddonsDto> models) {

		List<SrHospitalAddons> saveAll = srAddonsRepository.saveAll(
				models.stream().map(HospitalRequestConverter::convertSrAddonsJsonToModel).collect(Collectors.toList()));

		List<SrHospitalAddonsDto> collectedAddons = saveAll.stream()
				.map(HospitalResponseConverter::convertSrAddonsModelToModel).collect(Collectors.toList());

		return collectedAddons;
	}

}

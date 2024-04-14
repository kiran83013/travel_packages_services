package com.travel.travtronics.srm.dao.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travel.travtronics.converter.HotelRequestConverter;
import com.travel.travtronics.converter.HotelResponseConverter;
import com.travel.travtronics.request.dto.HotelRequestModel;
import com.travel.travtronics.request.dto.PackageHotelDTO;
import com.travel.travtronics.request.dto.SrHospitalAddonsDto;
import com.travel.travtronics.request.dto.SrHotelAddonsDto;
import com.travel.travtronics.request.dto.SrHotelRoomsDto;
import com.travel.travtronics.response.dto.AttractionsRequiredPaxInfo;
import com.travel.travtronics.srm.dao.CommonAsyncDao;
import com.travel.travtronics.srm.dao.HotelRequestDao;
import com.travel.travtronics.srm.model.HolidayRequestLine;
import com.travel.travtronics.srm.model.SrHotelAddons;
import com.travel.travtronics.srm.model.SrHotelAddons.AddonFor;
import com.travel.travtronics.srm.model.SrHotelLines;
import com.travel.travtronics.srm.model.SrHotelPassengers;
import com.travel.travtronics.srm.model.SrHotelRooms;
import com.travel.travtronics.srm.repository.HolidayRequestLineRepository;
import com.travel.travtronics.srm.repository.ServiceRequestLineRepository;
import com.travel.travtronics.srm.repository.SrHotelAddonsRepository;
import com.travel.travtronics.srm.repository.SrHotelLinesRepository;
import com.travel.travtronics.srm.repository.SrHotelPassengersRepository;
import com.travel.travtronics.srm.repository.SrHotelRoomsRepository;

import lombok.extern.slf4j.Slf4j;

@Repository("HotelRequestDao")
@Slf4j
public class HotelRequestDaoImpl implements HotelRequestDao {

	@Autowired
	SrHotelLinesRepository srHotelRepository;

	@Autowired
	SrHotelPassengersRepository srPassengeerRepository;

	@Autowired
	SrHotelRoomsRepository srRoomsRepository;

	@Autowired
	SrHotelAddonsRepository srAddonsRepository;

	@Autowired
	ServiceRequestLineRepository seqenceGeneratorRepository;

	@Autowired
	HolidayRequestLineRepository holidayRequestLineRepository;

	@Autowired
	CommonAsyncDao commonAsyncDao;

	@Override
	public Map<String, Object> hotelRequestFromPackage(PackageHotelDTO hotelRequest, String token) throws Exception {

		HotelRequestModel model = hotelRequest.getRequestData();

		Long generateSeqenceNumber = seqenceGeneratorRepository
				.generateSeqenceNumber(model.getSrLine().getLineSrId().toString(), "88", 2);
		model.getSrLine().setId(generateSeqenceNumber.intValue());

		Optional<SrHotelLines> hotelLineValidation = srHotelRepository.findByIdAndLineSrId(model.getSrLine().getId(),
				model.getSrLine().getLineSrId());

		if (hotelLineValidation.isPresent()) {
			throw new Exception("invalid-request..sr-and-line-already-exists");
		}

		SrHotelLines savedLine = srHotelRepository
				.save(HotelRequestConverter.convertHotelLinesJsonToModel(model.getSrLine()));

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

		log.info("sr-hotel-lines-saved");

		List<SrHotelRoomsDto> rooms = model.getSrRooms().stream().map(room -> {

			room.setRoomLineId(savedLine.getId());

			log.info("sr-hotel-lines-saved in sr-rooms");

			SrHotelRooms savedRoom = srRoomsRepository.save(HotelRequestConverter.convertRoomsJsonToModel(room));

			log.info("sr-hotel-rooms-saved in-db ");

			List<SrHotelPassengers> saveAll = srPassengeerRepository
					.saveAll(room.getRoomPassengersInfo().stream().map(pass -> {

						pass.setPassengerRoomId(savedRoom.getId());
						pass.setPassengerLineId(savedLine.getId());

						log.info("sr-hotel-rooms-id-saved-passenger-info");
						return HotelRequestConverter.convertPassengersJsonToModel(pass);

					}).collect(Collectors.toList()));

			log.info("sr-hotel-passengers-saved in-db ");

			return HotelResponseConverter.convertPostRoomsModelToJson(savedRoom, saveAll);

		}).collect(Collectors.toList());
		HotelRequestModel response = new HotelRequestModel();
		response.setSrLine(HotelResponseConverter.convertHotelLinesModelToJson(savedLine));
		response.setSrRooms(rooms);

		Map<String, Object> hotelResponse = new LinkedHashMap<>();
		hotelResponse.put("requestData", response);

		if (hotelRequest.getAddonsData() != null && !hotelRequest.getAddonsData().isEmpty()) {

			String addonNights = IntStream.rangeClosed(1, savedLine.getLineNoOfNights()).mapToObj(String::valueOf)
					.collect(Collectors.joining(","));

			List<String> roomIds = rooms.stream().map(room -> String.valueOf(room.getId()))
					.collect(Collectors.toList());
			List<SrHotelAddonsDto> collectedAddonData = hotelRequest.getAddonsData().stream().peek(a -> {
				a.setAddonSrId(savedLine.getLineSrId());
				a.setAddonLineId(savedLine.getId());
				a.setAddonNights(addonNights);
				a.setAddonRoomId(String.join(",", roomIds));
				a.setAddonPassengers(String.join(",", generateHotelAddonPassengersData(a, rooms.size(), "HOTEL")));
				a.setAddonFor(AddonFor.R);
			}).collect(Collectors.toList());

			List<SrHotelAddonsDto> addons = createHotelAddons(collectedAddonData);
			hotelResponse.put("addonData", addons);
		}
		commonAsyncDao.saveSrSummaryData(savedLine.getLineSrId(), savedLine.getId(), 2, 0, savedLine.getLineCreatedBy(),
				token);
		return hotelResponse;
	}

	List<String> generateHotelAddonPassengersData(Object addonsData, Integer noOfRooms, String requestType) {

		List<AttractionsRequiredPaxInfo> paxDetails = new ArrayList<>();
		if (requestType.equals("HOTEL")) {

			SrHotelAddonsDto hotelAddons = (SrHotelAddonsDto) addonsData;

			paxDetails = hotelAddons.getPaxDetails();

		} else if (requestType.equals("HOSPITAL")) {

			SrHospitalAddonsDto hospitalAddons = (SrHospitalAddonsDto) addonsData;
			paxDetails = hospitalAddons.getPaxDetails();

		} else {

			log.info("unextpected condition occured");

		}

		List<String> addonpax = new ArrayList<>();

		Map<String, String> replacementMap = Map.of("ADT", "Adult", "CHD", "Child", "INF", "Infant");

		if (paxDetails != null && !paxDetails.isEmpty()) {

			Map<String, List<String>> paxTypeGroupedMap = paxDetails.stream().collect(
					Collectors.groupingBy(pax -> pax.getPaxType().substring(0, 3), Collectors.mapping(replacedPax -> {
						String key = replacedPax.getPaxType().substring(0, 3);
						String replacedPaxType = replacedPax.getPaxType().replace(key,
								replacementMap.getOrDefault(key, key));
						return replacedPaxType;

					}, Collectors.toList())));
			Stream.iterate(1, i -> i <= noOfRooms, i -> i + 1).forEach(room -> {
				// System.out.println("i:-" + room);

				paxTypeGroupedMap.keySet().stream().forEach(key -> {

					paxTypeGroupedMap.get(key).stream().map(eachPaxType -> {
						// System.out.println("key :" + key);

						// System.out.println("data :" + paxTypeGroupedMap.get(key));
						return "Room-" + String.valueOf(room) + "-" + eachPaxType;
					}).forEach(addonpax::add);

				});
			});
		}

		return addonpax;
	}

	public List<SrHotelAddonsDto> createHotelAddons(List<SrHotelAddonsDto> models) {

		List<SrHotelAddons> saveAll = srAddonsRepository.saveAll(
				models.stream().map(HotelRequestConverter::convertSrAddonsJsonToModel).collect(Collectors.toList()));

		List<SrHotelAddonsDto> collect = saveAll.stream().map(HotelResponseConverter::convertSrAddonsModelToModel)
				.collect(Collectors.toList());

		return collect;
	}

}

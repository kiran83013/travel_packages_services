package com.travel.travtronics.srm.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TimeZone;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.travtronics.converter.GroundTransportConverter;
import com.travel.travtronics.converter.HolidayPackageConverter;
import com.travel.travtronics.converter.PackageItenaryConverter;
import com.travel.travtronics.request.AttractionsRequest;
import com.travel.travtronics.request.GroundPaxRequest;
import com.travel.travtronics.request.GroundTransportRequest;
import com.travel.travtronics.request.HolidayRequest;
import com.travel.travtronics.request.SrAttractionsLinePaxRequest;
import com.travel.travtronics.request.SrAttractionsLinesRequest;
import com.travel.travtronics.request.dto.AnxRequestLine;
import com.travel.travtronics.request.dto.HolidayRoomsRequest;
import com.travel.travtronics.request.dto.HolidayServiceRequestHeader;
import com.travel.travtronics.request.dto.HolidayServiceRequestSegments;
import com.travel.travtronics.request.dto.HospitalRequestModel;
import com.travel.travtronics.request.dto.HotelRequestModel;
import com.travel.travtronics.request.dto.PackageAnxDTO;
import com.travel.travtronics.request.dto.PackageFlightDTO;
import com.travel.travtronics.request.dto.PackageHospitalDTO;
import com.travel.travtronics.request.dto.PackageHotelDTO;
import com.travel.travtronics.request.dto.PackageProductDTO;
import com.travel.travtronics.request.dto.PaxRequestModel;
import com.travel.travtronics.request.dto.RequestLinePaxDto;
import com.travel.travtronics.request.dto.ServiceRequestLineModel;
import com.travel.travtronics.request.dto.SrHospitalAddonsDto;
import com.travel.travtronics.request.dto.SrHospitalLinesDto;
import com.travel.travtronics.request.dto.SrHospitalPassengersDto;
import com.travel.travtronics.request.dto.SrHospitalRoomsDto;
import com.travel.travtronics.request.dto.SrHotelAddonsDto;
import com.travel.travtronics.request.dto.SrHotelLinesDto;
import com.travel.travtronics.request.dto.SrHotelPassengersDto;
import com.travel.travtronics.request.dto.SrHotelRoomsDto;
import com.travel.travtronics.response.HolidayResponse;
import com.travel.travtronics.response.dto.AddOnDetails;
import com.travel.travtronics.response.dto.AddonsRequiredPassengers;
import com.travel.travtronics.response.dto.AttractionNames;
import com.travel.travtronics.response.dto.AttractionsDetails;
import com.travel.travtronics.response.dto.AttractionsJsonDto;
import com.travel.travtronics.response.dto.AttractionsRequiredPassengers;
import com.travel.travtronics.response.dto.AttractionsRequiredPaxInfo;
import com.travel.travtronics.response.dto.FlightDetails;
import com.travel.travtronics.response.dto.HolidaySegmentResponse;
import com.travel.travtronics.response.dto.HospitalRoomsDetails;
import com.travel.travtronics.response.dto.HospitalsDetails;
import com.travel.travtronics.response.dto.HotelRoomsDetails;
import com.travel.travtronics.response.dto.HotelsDetails;
import com.travel.travtronics.response.dto.JsonAddOnType;
import com.travel.travtronics.response.dto.JsonAddOns;
import com.travel.travtronics.response.dto.PackageDetailsResponse;
import com.travel.travtronics.response.dto.PackageDetailsResponse.PackageItenaryResponse;
import com.travel.travtronics.response.dto.PackageHospitalsInfoDto;
import com.travel.travtronics.response.dto.PackageHospitalsInfoDto.HospitalRoomPassengersInfo;
import com.travel.travtronics.response.dto.PackageHospitalsInfoDto.HospitalRoomsLevelsData;
import com.travel.travtronics.response.dto.PackageHospitalsInfoDto.HsRoomChildAge;
import com.travel.travtronics.response.dto.PackageHotelsInfoDto;
import com.travel.travtronics.response.dto.PackageHotelsInfoDto.RoomChildAge;
import com.travel.travtronics.response.dto.PackageHotelsInfoDto.RoomPassengersInfo;
import com.travel.travtronics.response.dto.PackageHotelsInfoDto.RoomsLevelsData;
import com.travel.travtronics.response.dto.PackageItenaryGroupResponse;
import com.travel.travtronics.srm.dao.PackageRequestDao;
import com.travel.travtronics.srm.model.HolidayPackagePassengersModel;
import com.travel.travtronics.srm.model.HolidayPackageRoomsModel;
import com.travel.travtronics.srm.model.HolidayRequestLine;
import com.travel.travtronics.srm.model.HolidayRequestPax;
import com.travel.travtronics.srm.model.HolidayRequestSegments;
import com.travel.travtronics.srm.model.ServiceRequestLine;
import com.travel.travtronics.srm.model.ServiceRequestSegment;
import com.travel.travtronics.srm.model.SrHospitalLines.HsMarkUpType;
import com.travel.travtronics.srm.model.SrHotelLines.MarkUpType;
import com.travel.travtronics.srm.repository.HolidayPackagePassengersRepository;
import com.travel.travtronics.srm.repository.HolidayPackageRoomsRepository;
import com.travel.travtronics.srm.repository.HolidayRequestLineRepository;
import com.travel.travtronics.srm.repository.HolidayRequestPaxRepository;
import com.travel.travtronics.srm.repository.HolidayRequestSegmentsRepository;
import com.travel.travtronics.srm.repository.ServiceRequestRepository;
import com.travel.travtronics.util.NotFoundException;

import lombok.extern.slf4j.Slf4j;

@Repository("packageRequestDao")
@Slf4j
public class PackageRequestDaoImpl implements PackageRequestDao {

	@Autowired
	HolidayRequestLineRepository holidayRequestLineRepository;

	@Autowired
	HolidayRequestPaxRepository holidayRequestPaxRepository;

	@Autowired
	HolidayRequestSegmentsRepository holidayRequestSegmentsRepository;

	@Autowired
	HolidayPackageRoomsRepository roomsRepository;

	@Autowired
	HolidayPackagePassengersRepository roomPassengersRepository;

	@Autowired
	ServiceRequestRepository serviceRequestRepository;

	ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	@Transactional(rollbackFor = Exception.class)
	public ArrayList<HolidayResponse> packageRequest(HolidayRequest model) {
		return model.getServiceRequestSegment().stream().map(seg -> {
			return packageSaver.apply(model.getServiceRequestLine(), seg);
		}).collect(Collectors.toCollection(ArrayList::new));
	}

	private BiFunction<HolidayServiceRequestHeader, HolidayServiceRequestSegments, HolidayResponse> packageSaver = (
			line, seg) -> {
		log.info("------------start-package-request-----------------");

		Long requestLineId = holidayRequestLineRepository.generateSeqenceNumber(String.valueOf(line.getRequestId()),
				"55", 2);

		HolidayRequestLine holidayRequestLine = HolidayPackageConverter.createHolidayRequestLine(line, seg,
				requestLineId);

		HolidayRequestLine savedRequestLine = holidayRequestLineRepository.save(holidayRequestLine);

		HolidayRequestSegments segmentInfo = HolidayPackageConverter.createSegmentsEntity(seg, requestLineId);

		HolidayRequestSegments saveedSegmentInfo = holidayRequestSegmentsRepository.save(segmentInfo);

		log.info("------------start-package-request-pax-----------------");

		List<HolidayRequestPax> collectedPaxInfo = seg.getHolidayPersonList().stream()
				.map(pax -> HolidayPackageConverter.convertDtoToEntity(pax, requestLineId))
				.peek(holidayRequestPaxRepository::save).collect(Collectors.toList());
		log.info("------------start-package-request-pax-----------------");

		List<HolidayRoomsRequest> collectedRoomPassengerInfo = null;
		if (CollectionUtils.isNotEmpty(seg.getRoomsData())) {
			log.info("------start saving rooms-passenger-info-info");
			seg.getRoomsData().stream().map(room -> {
				HolidayPackageRoomsModel savedRoom = roomsRepository
						.save(HolidayPackageConverter.convertHolidayRoomJsonToEntity(room, requestLineId));
				log.info("room saved with id :-" + savedRoom.getId());

				List<HolidayPackagePassengersModel> collectedRoomPassengersInfo = HolidayPackageConverter
						.convertHolidayPassengersJsonToEntity(room.getRoomPassengersInfo(), savedRoom.getId(),
								requestLineId)
						.stream().map(roomPassengersRepository::save).collect(Collectors.toList());
				log.info("passenger information saved");
				return HolidayPackageConverter.convertPassengersRoomEntityToJson(savedRoom,
						collectedRoomPassengersInfo);

			}).collect(Collectors.toCollection(() -> new ArrayList<>()));
			log.info("------end saving rooms-passenger-info-info");
		}
		log.info("------------end-package-request-----------------");
		log.info("generating response for holiday models");
		return HolidayPackageConverter.generateResponseForHolidayInfo(
				HolidayPackageConverter.convertLineEntityToDto(savedRequestLine),
				HolidayPackageConverter.createSegmentsDtofromEntity(saveedSegmentInfo),
				HolidayPackageConverter.convertEntityToDto(collectedPaxInfo), collectedRoomPassengerInfo);
	};

	private Function<Map<String, Long>, HolidayResponse> packageRetriever = (packageInput) -> {

		long requestId = packageInput.get("srId").longValue();
		long requestLineId = packageInput.get("srLineId").longValue();
		HolidayRequestLine requestLine = holidayRequestLineRepository
				.findByRequestIdAndRequestLineId(requestId, requestLineId)
				.orElseThrow(() -> new NotFoundException(requestId + "-" + requestLineId, HolidayRequestLine.class));

		HolidayRequestSegments requestSegments = holidayRequestSegmentsRepository
				.findByRequestIdAndRequestLineId(requestId, requestLineId)
				.orElseGet(() -> new HolidayRequestSegments());
		List<HolidayRequestPax> paxInfo = holidayRequestPaxRepository
				.findByRequestIdAndRequestLineIdAndPaxIsDeletedIsNull(requestId, requestLineId);

		List<HolidayPackageRoomsModel> roomInfo = roomsRepository.findByRoomSrIdAndRoomLineId(requestId, requestLineId);
		List<HolidayRoomsRequest> collectedRoomPaxInfo = null;
		if (CollectionUtils.isNotEmpty(roomInfo)) {

			collectedRoomPaxInfo = roomInfo.stream().map(room -> {

				List<HolidayPackagePassengersModel> roomPaxsInfo = roomPassengersRepository
						.getRoomPaxsByRoomId(room.getId());

				return HolidayPackageConverter.convertPassengersRoomEntityToJson(room, roomPaxsInfo);

			}).collect(Collectors.toCollection(ArrayList::new));

		}
		log.info("generating response for holiday models");

		HolidaySegmentResponse reponseSegments = HolidayPackageConverter.createSegmentsDtofromEntity(requestSegments);

		return HolidayPackageConverter.generateResponseForHolidayInfo(
				HolidayPackageConverter.convertLineEntityToDto(requestLine), reponseSegments,
				HolidayPackageConverter.convertEntityToDto(paxInfo), collectedRoomPaxInfo);

	};

	@Override
	public List<HolidayResponse> packageRequest(Long requestId) throws Exception {

		List<Long> hotelRequest = holidayRequestLineRepository.findAllLineIdByRequestId(requestId);
		if (hotelRequest.isEmpty())
			throw new Exception("invalid service request received");
		return hotelRequest.stream().map(line -> {

			return this.packageRetriever.apply(Map.of("srId", requestId, "srLineId", line));

		}).filter(Objects::nonNull).collect(Collectors.toList());
	}

	public PackageDetailsResponse getPackageDetailsBySrId(Long requestId, Boolean isPlannerProduct) throws Exception {

		PackageDetailsResponse packageList = new PackageDetailsResponse();
		List<FlightDetails> flights = new ArrayList<>();
		List<HotelsDetails> hotelsData = new ArrayList<>();
		List<HospitalsDetails> hospitalData = new ArrayList<>();
		List<GroundTransportRequest> roadTransportData = new ArrayList<>();

		List<AttractionsDetails> attractionsData = new ArrayList<>();
		List<AddOnDetails> anxData = new ArrayList<>();

		List<HolidayRequestLine> packageLineInfo = holidayRequestLineRepository.findByRequestId(requestId);
		if (packageLineInfo.size() == 0) {
			throw new Exception("Invalid requestId, Not data found with given id");

		}

		List<HolidayRequestSegments> linesList = holidayRequestSegmentsRepository.findByRequestId(requestId);

		Map<Integer, String> checkinDates = new HashMap<>();

		String dpdate = "";
		Date isPlannerDate = null;
		int holidaycount = 0;
		Date isPlannerDateFlight = null;
		int holidaycountFlight = 0;
		for (HolidayRequestSegments line : linesList) {

			List<AddOnDetails> flightAddons = new ArrayList<>();
			List<AddOnDetails> hotelsAddons = new ArrayList<>();
			List<AddOnDetails> hospitalsAddons = new ArrayList<>();
			List<AddOnDetails> ancillaries = new ArrayList<>();

			SimpleDateFormat dpformatter = new SimpleDateFormat("yyyy-MM-dd");
			dpformatter.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
			/*
			 * here dept date is empty at planner package product we will pick creation
			 * dates
			 */
			int index = linesList.indexOf(line);
			if (isPlannerProduct && index == 0) {
				dpdate = dpformatter.format(line.getCreatedDate());
				isPlannerDate = line.getCreatedDate();
				holidaycount = Integer.valueOf(line.getHolidayDays());
			} else if (isPlannerProduct && index > 0) {

				Date segmtDate = DateUtils.addDays(isPlannerDate, holidaycount - 1);
				dpdate = dpformatter.format(segmtDate);
				isPlannerDate = segmtDate;
				holidaycount = Integer.valueOf(line.getHolidayDays());
			} else
				dpdate = dpformatter.format(line.getDepatureDate());

			checkinDates.put(line.getRequestLineId().intValue(), dpdate);

			Optional<HolidayRequestLine> requestInfo = holidayRequestLineRepository
					.findByRequestIdAndRequestLineId(line.getRequestId(), line.getRequestLineId());

			if (requestInfo.isPresent() && requestInfo.get().getAddons() != null
					&& !requestInfo.get().getAddons().isEmpty() && requestInfo.get().getAddons().size() > 0) {
				List<Map<Object, Object>> addOns = requestInfo.get().getAddons();

				ObjectMapper lineAddonsMapper = new ObjectMapper();
				List<JsonAddOns> lineAddonsInfo = lineAddonsMapper
						.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
						.setSerializationInclusion(Include.NON_NULL).setSerializationInclusion(Include.NON_EMPTY)
						.convertValue(addOns, new TypeReference<List<JsonAddOns>>() {
						});

				System.out.println(lineAddonsInfo);
				for (JsonAddOns addon : lineAddonsInfo) {
					if (addon.getAddOnType() == null) {
						continue;
					}
					AddOnDetails thisAddon = new AddOnDetails();

					JsonAddOnType addonData = addon.getAddOnType();

					// System.out.println(addon.getRequiredPassenger().toString());

					thisAddon.setAddOnId(addonData.getId());
					thisAddon.setAddOnName(addonData.getName());
					thisAddon.setAddOnCode(addonData.getCode());
					thisAddon.setAddOnDescription(addonData.getDescription());
					thisAddon.setAddOnType(addonData.getAddonType());
					thisAddon.setRemarks(addon.getRemarks());
					thisAddon.setExtraCost(addon.getExtraCost());
					// System.out.println(addon.getRequiredPassenger().getPax().toString());
					if (Objects.nonNull(addon.getRequiredPassenger())) {
						AddonsRequiredPassengers arp = addon.getRequiredPassenger();
						if (arp != null && Objects.nonNull(arp.getPassengers())) {
							System.out.println(arp.getAll());
							// if (Objects.nonNull(arp.getAll()) && arp.getAll() == true) {
							thisAddon.setPaxCount(arp.getPassengers().size());
							thisAddon.setPaxDetails(arp.getPassengers());
							// }
						}
					}
					thisAddon.setDaysList(Collections.singletonList(dpdate));
					if (Objects.nonNull(addonData.getAddonType()) && addonData.getAddonType().equalsIgnoreCase("F")) {
						flightAddons.add(thisAddon);
					}
					if (Objects.nonNull(addonData.getAddonType()) && addonData.getAddonType().equalsIgnoreCase("H")) {
						hotelsAddons.add(thisAddon);
					}
					if (Objects.nonNull(addonData.getAddonType()) && addonData.getAddonType().equalsIgnoreCase("HS")) {
						hospitalsAddons.add(thisAddon);
					}
					if (Objects.nonNull(addonData.getAddonType()) && addonData.getAddonType().equalsIgnoreCase("A")) {

						thisAddon.setDynamicTabData(addon.getDynamicTabData());
						ancillaries.add(thisAddon);
					}
				}

			}

			Integer adtCnt = 0, chdCnt = 0, infCnt = 0;
			if (requestInfo.isPresent()) {
				adtCnt = requestInfo.get().getNoofAdt();
				chdCnt = requestInfo.get().getNoofChd();
				infCnt = requestInfo.get().getNoofInf();
			}

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
			String deptDate;

			if (isPlannerProduct && index == 0) {
				deptDate = dpformatter.format(line.getCreatedDate());
				isPlannerDateFlight = line.getCreatedDate();
				holidaycountFlight = Integer.valueOf(line.getHolidayDays());
			} else if (isPlannerProduct && index > 0) {
				Date segmtDate = DateUtils.addDays(isPlannerDateFlight, holidaycountFlight - 1);
				deptDate = dpformatter.format(segmtDate);
				isPlannerDateFlight = segmtDate;
				deptDate = dpformatter.format(segmtDate);
				holidaycountFlight = Integer.valueOf(line.getHolidayDays());
			} else
				deptDate = dpformatter.format(line.getDepatureDate());

			if (Objects.nonNull(requestInfo.get().getTransportFlag()) && !requestInfo.get().getTransportFlag()
					&& line.getModeOfTransport().equalsIgnoreCase("flightClass")) {
				FlightDetails flight = new FlightDetails();

				flight.setLineId(line.getRequestLineId());
				flight.setSegmentBoardPoint(line.getFromCode());
				flight.setSegmentBoardCityOrAirport(line.getFromAirportOrCityName());

				flight.setSegmentDepartDate(dpdate);
				flight.setSegmentDepartTime(line.getDepatureTime());
				flight.setSegmentDepartTerminal(0);
				flight.setSegmentOffPoint(line.getToCode());
				flight.setSegmentArrivalCityOrAirport(line.getToAirportOrCityName());
				flight.setSegmentAirlineMarketing(line.getAirlineCode());
				flight.setSegmentClassDesignator(line.getClassName());
				flight.setSegmentRbdCode(line.getRbd());
				flight.setAdtCount(adtCnt);
				flight.setChdCount(chdCnt);
				flight.setInfCount(infCnt);
				flight.setAddOns(flightAddons);
				flight.setAncillaries(ancillaries);
				flight.setValidateCarrier(line.getValidateCarrier());
				flight.setBudgetFrom(line.getBudgetFrom());
				flight.setBudgetTo(line.getBudgetTo());
				flight.setFlexAirLineCode(line.getFlexAirLineCode());
				flight.setFlexFromCode(line.getFlexFromCode());
				flight.setFlexToCode(line.getFlexAirLineCode());

				List<HolidayRequestPax> thisPaxList = holidayRequestPaxRepository
						.findByRequestIdAndRequestLineIdAndPaxIsDeletedIsNull(line.getRequestId(),
								line.getRequestLineId());
				if (thisPaxList != null && thisPaxList.size() > 0) {
					flight.setPaxCount(thisPaxList.size());
					flight.setPaxInfo(thisPaxList);
				}

				flights.add(flight);

			}

			if (Objects.nonNull(requestInfo.get().getTransportFlag()) && !requestInfo.get().getTransportFlag()
					&& List.of("bus", "car", "train").contains(line.getModeOfTransport().toLowerCase())) {
				/*
				 * instantiate ground transport type pair
				 */
				Map<String, ImmutablePair<Integer, String>> modeOfTransportMap = Map.of("train",
						new ImmutablePair<Integer, String>(10, "Train"), "bus",
						new ImmutablePair<Integer, String>(11, "Bus"), "car",
						new ImmutablePair<Integer, String>(9, "Car"));

				GroundTransportRequest groundTransportDetails = new GroundTransportRequest();

				groundTransportDetails.setDepartureCity(line.getFromAirportOrCityName());
				groundTransportDetails.setDepartureCountry(line.getFromCountryName());

				String departureLocation = List
						.of(line.getFromCode(), line.getFromAirportOrCityName(), line.getFromCountryName()).stream()
						.filter(Objects::nonNull).reduce((loc1, loc2) -> loc1 + "," + loc2).orElse("");
				groundTransportDetails.setDepartureLocation(departureLocation);
				groundTransportDetails.setDepartureDate(deptDate);

				groundTransportDetails.setArrivalCity(line.getToAirportOrCityName());
				groundTransportDetails.setArrivalCountry(line.getToCountryName());

				String arrivalLocation = List
						.of(line.getToCode(), line.getToAirportOrCityName(), line.getToCountryName()).stream()
						.filter(Objects::nonNull).reduce((loc1, loc2) -> loc1 + "," + loc2).orElse("");

				groundTransportDetails.setArrivalLocation(arrivalLocation);

				groundTransportDetails.setNoOfAdults(adtCnt);
				groundTransportDetails.setNoOfChilds(chdCnt);
				groundTransportDetails.setNoOfInfants(infCnt);

				String returnDate = LocalDate.parse(deptDate).plusDays(Integer.valueOf(line.getHolidayDays()))
						.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

				System.out.println(deptDate + "===================>" + returnDate);

				groundTransportDetails.setReturnDate(returnDate);

				ImmutablePair<Integer, String> modeOfTransportType = modeOfTransportMap
						.get(line.getModeOfTransport().toLowerCase());

				groundTransportDetails.setType(modeOfTransportType.getLeft());
				groundTransportDetails.setTypeName(modeOfTransportType.getRight());

				roadTransportData.add(groundTransportDetails);
			}
			String pdate = deptDate;

			Integer holidaysCount = Integer.valueOf(line.getHolidayDays()) - 1;
			LocalDate fromDate = LocalDate.parse(pdate);
			Date checkoutDate = java.sql.Date.valueOf(fromDate.plusDays(holidaysCount));

			if (holidaysCount > 0) {
				System.out.println("H count: " + holidaysCount);

				List<HotelsDetails> hotelsListData = new ArrayList<HotelsDetails>();
				if (requestInfo.isPresent() && requestInfo.get().getHotelSelectionData() != null) {

					List<Map<String, Object>> hotelInfoJson = requestInfo.get().getHotelSelectionData();
					ObjectMapper hotelsInfoJsonMapper = new ObjectMapper();
					List<PackageHotelsInfoDto> packageHotelsInfoList = hotelsInfoJsonMapper
							.setSerializationInclusion(Include.NON_NULL).setSerializationInclusion(Include.NON_EMPTY)
							.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
							.convertValue(hotelInfoJson, new TypeReference<List<PackageHotelsInfoDto>>() {
							});

					System.out.println("==============HT================" + packageHotelsInfoList.size());

					System.out.println("==============HT================" + packageHotelsInfoList);

					if (isPlannerProduct) {

						List<PackageHotelsInfoDto> packageHotelsInfoPlannerProduct = packageHotelsInfoList.stream()
								.peek(eachHotel -> {

									List<String> hotelDays = new ArrayList<>();

									if (eachHotel.getHotelDate() != null) {

										for (String dayNo : eachHotel.getHotelDate()) {

											Long parsedDayNo = Long.valueOf(dayNo);

											// System.out.println("parsedDay Hotelcount: " + parsedDayNo);

											String formatedHotelDate = fromDate.plusDays(parsedDayNo - 1)
													.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
											hotelDays.add(formatedHotelDate);

											eachHotel.setHotelDate(hotelDays);
										}
									}

								}).collect(Collectors.toList());
						hotelsListData = convertPackagesHotelRoomsJsonDataToHotelsList(holidaysCount, requestInfo, line,
								hotelsAddons, ancillaries, packageHotelsInfoPlannerProduct);

					} else {

						hotelsListData = convertPackagesHotelRoomsJsonDataToHotelsList(holidaysCount, requestInfo, line,
								hotelsAddons, ancillaries, packageHotelsInfoList);
					}
				}

				hotelsData.addAll(hotelsListData);
			}
			List<HospitalsDetails> hospitalsListData = new ArrayList<HospitalsDetails>();
			if (requestInfo.isPresent() && requestInfo.get().getHospitalSelectionData() != null) {

				List<Map<String, Object>> hospitalInfoJson = requestInfo.get().getHospitalSelectionData();
				ObjectMapper hospitalsInfoJsonMapper = new ObjectMapper();
				List<PackageHospitalsInfoDto> packageHospitalsInfoList = hospitalsInfoJsonMapper
						.setSerializationInclusion(Include.NON_NULL).setSerializationInclusion(Include.NON_EMPTY)
						.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
						.convertValue(hospitalInfoJson, new TypeReference<List<PackageHospitalsInfoDto>>() {
						});

				System.out.println("=============HS=================" + packageHospitalsInfoList.size());
				if (isPlannerProduct) {
					List<PackageHospitalsInfoDto> packageHospitalsPlannerInfoList = packageHospitalsInfoList.stream()
							.peek(eachHospital -> {

								List<String> hospitalDates = new ArrayList<>();
								if (eachHospital.getHospitalDate() != null) {
									for (String dayNo : eachHospital.getHospitalDate()) {

										Long parsedDayNo = Long.valueOf(dayNo);
										// System.out.println("parsedDay Hospitalcount: " + parsedDayNo);
										String formatedHotelDate = fromDate.plusDays(parsedDayNo - 1)
												.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
										hospitalDates.add(formatedHotelDate);

										eachHospital.setHospitalDate(hospitalDates);
									}
								}

							}).collect(Collectors.toList());

					hospitalsListData = convertPackagesHospitalRoomsJsonDataToHospitalsList(holidaysCount, requestInfo,
							line, hospitalsAddons, ancillaries, packageHospitalsPlannerInfoList);

				} else {
					hospitalsListData = convertPackagesHospitalRoomsJsonDataToHospitalsList(holidaysCount, requestInfo,
							line, hospitalsAddons, ancillaries, packageHospitalsInfoList);
				}

				hospitalData.addAll(hospitalsListData);
			}

		}
		packageList.setFlight(flights);
		packageList.setHotels(hotelsData);
		packageList.setHospitals(hospitalData);

		for (

		HolidayRequestLine pack : packageLineInfo) {
			String deptDate = "";
			Integer holidaysCount = 1;
			Map<Integer, String> hdays = new HashMap<>();
			if (pack.getHolidaysCount() != null && pack.getHolidaysCount() > 0) {
				deptDate = checkinDates.get(pack.getRequestLineId().intValue());
				holidaysCount = pack.getHolidaysCount();
				Date hotelCheckInDate = new Date();
				try {
					hotelCheckInDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(deptDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (int co = 1; co <= holidaysCount; co++) {
					Calendar c = Calendar.getInstance();
					c.setTime(hotelCheckInDate);
					c.add(Calendar.DATE, co - 1);
					Date checkoutDate = c.getTime();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
					formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
					String pdate = formatter.format(checkoutDate);
					hdays.put(co, pdate);
				}
			}

			List<HolidayRequestPax> paxList = holidayRequestPaxRepository.findByRequestId(pack.getRequestId());
			packageList.setPaxInfo(paxList);

			if (pack.getAttractions() != null && !pack.getAttractions().isEmpty() && pack.getAttractions().size() > 0) {
				List<Map<String, Object>> attractions = pack.getAttractions();

				ObjectMapper objectMapper = new ObjectMapper();
				List<AttractionsJsonDto> attrsInfo = objectMapper.setSerializationInclusion(Include.NON_NULL)
						.setSerializationInclusion(Include.NON_EMPTY)
						.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
						.convertValue(attractions, new TypeReference<List<AttractionsJsonDto>>() {
						});

				for (AttractionsJsonDto attr : attrsInfo) {
					if (attr.getAttractionName() == null) {
						continue;
					}
					List<String> holidays = new ArrayList<String>();
					if (attr.getAttractionDay() != null && !attr.getAttractionDay().isEmpty()
							&& attr.getAttractionDay().size() > 0) {
						for (Integer hday : attr.getAttractionDay()) {
							String hDate = hdays.get(hday);
							holidays.add(hDate);
						}
					}

					for (AttractionNames names : attr.getAttractionName()) {

						AttractionsDetails attrData = new AttractionsDetails();

						attrData.setAttractionID(names.getActivityID());
						attrData.setAttractionName(names.getActivityName());
						attrData.setCity(names.getCity());
						attrData.setRemarks(attr.getRemarks());
						attrData.setCountry(names.getCountry());
						attrData.setLocation(names.getLocation());
						if (attr.getAttractionsRequiredPassenger() != null) {
							AttractionsRequiredPassengers arp = attr.getAttractionsRequiredPassenger();
							if (arp != null && arp.getPax() != null) {
								if (arp != null && arp.getAll() != null && arp.getAll() == true
										&& arp.getPax() != null) {
									attrData.setPaxCount(arp.getPax().size());
									attrData.setPaxDetails(arp.getPax());
								}
							}
						}
						attrData.setDaysList(holidays);

						attractionsData.add(attrData);
					}
				}
			}

			if (pack.getAddons() != null && !pack.getAddons().isEmpty() && pack.getAddons().size() > 0) {
				List<Map<Object, Object>> addOns = pack.getAddons();

				ObjectMapper addonsMapper = new ObjectMapper();
				List<JsonAddOns> addonsInfo = addonsMapper
						.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
						.setSerializationInclusion(Include.NON_NULL).setSerializationInclusion(Include.NON_EMPTY)
						.convertValue(addOns, new TypeReference<List<JsonAddOns>>() {
						});

				for (JsonAddOns addon : addonsInfo) {
					if (addon.getAddOnType() == null) {
						continue;
					}
					AddOnDetails thisAddon = new AddOnDetails();

					JsonAddOnType addonData = addon.getAddOnType();

					thisAddon.setAddOnId(addonData.getId());
					thisAddon.setAddOnName(addonData.getName());
					thisAddon.setAddOnCode(addonData.getCode());
					thisAddon.setAddOnDescription(addonData.getDescription());
					thisAddon.setAddOnType(addonData.getAddonType());
					thisAddon.setRemarks(addon.getRemarks());
					thisAddon.setExtraCost(addon.getExtraCost());
					if (addon.getRequiredPassenger() != null) {
						AddonsRequiredPassengers arp = addon.getRequiredPassenger();
						if (arp != null && arp.getPassengers() != null) {
							if (arp != null && arp.getPassengers() != null) {
								thisAddon.setPaxCount(arp.getPassengers().size());
								thisAddon.setPaxDetails(arp.getPassengers());
							}
						}
					}
					thisAddon.setDaysList(Collections.singletonList(dpdate));
					if (Objects.nonNull(addonData.getAddonType()) && addonData.getAddonType().equalsIgnoreCase("A")) {

						thisAddon.setDynamicTabData(addon.getDynamicTabData());
						anxData.add(thisAddon);
					}
				}
			}

		}
		if (attractionsData.size() > 0 && attractionsData.isEmpty() == false) {
			packageList.setAttractions(attractionsData);
		}
		if (anxData.size() > 0 && anxData.isEmpty() == false) {
			packageList.setAncillaries(anxData);
		}

		if (roadTransportData.size() > 0 && roadTransportData.isEmpty() == false) {

			packageList.setGroundTransportData(roadTransportData);

		}
		return packageList;
	}

	public List<HospitalsDetails> convertPackagesHospitalRoomsJsonDataToHospitalsList(Integer holidaysCount,
			Optional<HolidayRequestLine> requestInfo, HolidayRequestSegments line, List<AddOnDetails> hospitalsAddons,
			List<AddOnDetails> ancillaries, List<PackageHospitalsInfoDto> packageHospitalsInfoList) {

		List<HospitalsDetails> hospitalsData = new ArrayList<>();

		Integer adtCnt = 0, chdCnt = 0, infCnt = 0;
		if (requestInfo.isPresent()) {
			adtCnt = requestInfo.get().getNoofAdt();
			chdCnt = requestInfo.get().getNoofChd();
			infCnt = requestInfo.get().getNoofInf();
		}

		if (packageHospitalsInfoList != null && packageHospitalsInfoList.size() > 0) {

			for (PackageHospitalsInfoDto hospital : packageHospitalsInfoList) {

				if (hospital.getHospitalDate() == null || hospital.getHospitalDate().isEmpty()) {
					continue;
				} else {
					if (hospital.getHospitalDate().size() > 1) {
						Collections.sort(hospital.getHospitalDate());
					}
				}

				List<HospitalRoomsDetails> roomsInfoList = new ArrayList<HospitalRoomsDetails>();
				List<HolidayPackagePassengersModel> roomPaxes = new ArrayList<HolidayPackagePassengersModel>();
				List<HolidayRequestPax> hospitalPax = new ArrayList<HolidayRequestPax>();
				String roomsNames = "";
				for (HospitalRoomsLevelsData rooms : hospital.getRoomLevelsData()) {
					if (hospital.getRoomLevelsData().indexOf(rooms) > 1) {
						roomsNames = roomsNames + ", " + rooms.getRoomName();
					} else {
						roomsNames = rooms.getRoomName();
					}
					HospitalRoomsDetails thisRoom = new HospitalRoomsDetails();
					if (rooms.getRoomName() != null) {
						thisRoom.setRoomName(rooms.getRoomName());
					}
					if (rooms.getRoomNumber() != null) {
						thisRoom.setRoomNumber(Integer.valueOf(rooms.getRoomNumber()));
					}
					if (rooms.getRoomType() != null) {
						thisRoom.setRoomType(rooms.getRoomType());
					}
					if (rooms.getRoomAdultCount() != null) {
						thisRoom.setRoomAdultCount(Integer.valueOf(rooms.getRoomAdultCount()));
					}
					if (rooms.getRoomChildCount() != null) {
						thisRoom.setRoomChildCount(Integer.valueOf(rooms.getRoomChildCount()));
					}
					if (rooms.getRoomInfantCount() != null) {
						thisRoom.setRoomInfantCount(Integer.valueOf(rooms.getRoomInfantCount()));
					}
					if (rooms.getRoomLevelchildAge() != null) {
						String childAges = rooms.getRoomLevelchildAge().stream().map(HsRoomChildAge::getRoomChildAges)
								.collect(Collectors.joining(","));
						thisRoom.setRoomChildAges(childAges);
					}
					if (rooms.getRoomInfantAges() != null) {
						thisRoom.setRoomInfantAges(rooms.getRoomInfantAges());
					}
					roomsInfoList.add(thisRoom);

					if (rooms.getRoomPassengersInfo() != null && rooms.getRoomPassengersInfo().size() > 0) {
						for (HospitalRoomPassengersInfo roompac : rooms.getRoomPassengersInfo()) {
							HolidayPackagePassengersModel pac = new HolidayPackagePassengersModel();
							pac.setId(roompac.getPaxId());
							pac.setPassengerFirstName(roompac.getFirstName());
							pac.setPassengerLastName(roompac.getLastName());
							pac.setPassengerCountry(roompac.getIssuedCountry());
							pac.setPassengerEmail(roompac.getEmail());
							pac.setPassengerPhone(roompac.getPhone());
							pac.setPassengerPaxId(roompac.getPaxId());
							pac.setPassengerRoomId(Integer.valueOf(rooms.getRoomNumber()));
							pac.setPassengerLineId(roompac.getRequestLineId());
							roomPaxes.add(pac);

							HolidayRequestPax hospitalPac = new HolidayRequestPax();
							hospitalPac.setFirstName(roompac.getFirstName());
							hospitalPac.setLastName(roompac.getLastName());
							hospitalPac.setAssign(roompac.getAssign());
							hospitalPac.setDob(roompac.getDob());
							if (roompac.getIssuedCountry() != null) {
								hospitalPac.setIssuedCountry(Long.valueOf(roompac.getIssuedCountry()));
							}
							hospitalPac.setEmail(roompac.getEmail());
							hospitalPac.setPhone(roompac.getPhone());
							if (roompac.getPaxId() != null) {
								hospitalPac.setPaxId(Long.valueOf(roompac.getPaxId()));
							}
							if (roompac.getRequestId() != null) {
								hospitalPac.setRequestId(Long.valueOf(roompac.getRequestId()));
							}
							if (roompac.getRequestLineId() != null) {
								hospitalPac.setRequestLineId(Long.valueOf(roompac.getRequestLineId()));
							}
							hospitalPax.add(hospitalPac);
						}
					}
				}

				HospitalsDetails hospitals = new HospitalsDetails();
				hospitals.setLineId(line.getRequestLineId());
				hospitals.setRoomName(roomsNames);
				hospitals.setHospitalName(hospital.getHospitalName());
				hospitals.setHospitalCountryCode(hospital.getDestinationCode());
				hospitals.setHospitalCountryName(hospital.getCityName());
				hospitals.setHospitalCityName(hospital.getCityName());
				hospitals.setHospitalAddress(hospital.getCityName());
				hospitals.setNoOfDays(hospital.getHospitalDate().size());
				hospitals.setCheckInDate(java.sql.Date.valueOf(hospital.getHospitalDate().get(0)));
				hospitals.setCheckOutDate(java.sql.Date
						.valueOf(hospital.getHospitalDate().get(hospital.getHospitalDate().size() - 1).toString()));
				hospitals.setRoomsCount(hospital.getHospitalRoomCount());
				hospitals.setBudgetFrom(0L);
				hospitals.setBudgetTo(0L);
				hospitals.setTeamLeader(line.getTeamLeader());
				hospitals.setAdtCount(adtCnt);
				hospitals.setChdCount(chdCnt);
				hospitals.setInfCount(infCnt);
				hospitals.setAddOns(hospitalsAddons);
				hospitals.setAncillaries(ancillaries);
				if (hospitalPax != null && hospitalPax.size() > 0) {
					hospitals.setPaxCount(hospitalPax.size());
					hospitals.setPaxInfo(hospitalPax);
				}
				if (roomPaxes != null && roomPaxes.size() > 0) {
					hospitals.setRoomPaxInfo(roomPaxes);
				}
				hospitals.setRoomsInfo(roomsInfoList);
				hospitalsData.add(hospitals);
			}
		}
		return hospitalsData;
	}

	public List<HotelsDetails> convertPackagesHotelRoomsJsonDataToHotelsList(Integer holidaysCount,
			Optional<HolidayRequestLine> requestInfo, HolidayRequestSegments line, List<AddOnDetails> hotelsAddons,
			List<AddOnDetails> ancillaries, List<PackageHotelsInfoDto> packageHotelsInfoList) {

		List<HotelsDetails> hotelsData = new ArrayList<>();

		Integer adtCnt = 0, chdCnt = 0, infCnt = 0;
		if (requestInfo.isPresent()) {
			adtCnt = requestInfo.get().getNoofAdt();
			chdCnt = requestInfo.get().getNoofChd();
			infCnt = requestInfo.get().getNoofInf();
		}

		if (packageHotelsInfoList != null && packageHotelsInfoList.size() > 0) {

			for (PackageHotelsInfoDto hotel : packageHotelsInfoList) {
				if (hotel.getHotelDate() == null || hotel.getHotelDate().isEmpty()) {
					continue;
				} else {
					if (hotel.getHotelDate().size() > 1) {
						Collections.sort(hotel.getHotelDate());
					}
				}

				List<HotelRoomsDetails> roomsInfoList = new ArrayList<HotelRoomsDetails>();
				List<HolidayPackagePassengersModel> roomPaxes = new ArrayList<HolidayPackagePassengersModel>();
				List<HolidayRequestPax> hotelPax = new ArrayList<HolidayRequestPax>();
				String roomsNames = "";
				for (RoomsLevelsData rooms : hotel.getRoomLevelsData()) {
					if (hotel.getRoomLevelsData().indexOf(rooms) > 1) {
						roomsNames = roomsNames + ", " + rooms.getRoomName();
					} else {
						roomsNames = rooms.getRoomName();
					}
					HotelRoomsDetails thisRoom = new HotelRoomsDetails();
					if (rooms.getRoomName() != null) {
						thisRoom.setRoomName(rooms.getRoomName());
					}
					if (rooms.getRoomNumber() != null) {
						thisRoom.setRoomNumber(Integer.valueOf(rooms.getRoomNumber()));
					}
					if (rooms.getRoomType() != null) {
						thisRoom.setRoomType(rooms.getRoomType());
					}
					if (rooms.getRoomAdultCount() != null) {
						thisRoom.setRoomAdultCount(Integer.valueOf(rooms.getRoomAdultCount()));
					}
					if (rooms.getRoomChildCount() != null) {
						thisRoom.setRoomChildCount(Integer.valueOf(rooms.getRoomChildCount()));
					}
					if (rooms.getRoomInfantCount() != null) {
						thisRoom.setRoomInfantCount(Integer.valueOf(rooms.getRoomInfantCount()));
					}
					if (rooms.getRoomLevelchildAge() != null) {
						String childAges = rooms.getRoomLevelchildAge().stream().map(RoomChildAge::getRoomChildAges)
								.collect(Collectors.joining(","));
						thisRoom.setRoomChildAges(childAges);
					}
					if (rooms.getRoomInfantAges() != null) {
						thisRoom.setRoomInfantAges(rooms.getRoomInfantAges());
					}
					roomsInfoList.add(thisRoom);

					if (rooms.getRoomPassengersInfo() != null && rooms.getRoomPassengersInfo().size() > 0) {
						for (RoomPassengersInfo roompac : rooms.getRoomPassengersInfo()) {
							HolidayPackagePassengersModel pac = new HolidayPackagePassengersModel();
							pac.setId(roompac.getPaxId());
							pac.setPassengerFirstName(roompac.getFirstName());
							pac.setPassengerLastName(roompac.getLastName());
							pac.setPassengerCountry(roompac.getIssuedCountry());
							pac.setPassengerEmail(roompac.getEmail());
							pac.setPassengerPhone(roompac.getPhone());
							pac.setPassengerPaxId(roompac.getPaxId());
							pac.setPassengerRoomId(Integer.valueOf(rooms.getRoomNumber()));
							pac.setPassengerLineId(roompac.getRequestLineId());
							roomPaxes.add(pac);

							HolidayRequestPax hotelPac = new HolidayRequestPax();
							hotelPac.setFirstName(roompac.getFirstName());
							hotelPac.setLastName(roompac.getLastName());
							hotelPac.setAssign(roompac.getAssign());
							hotelPac.setDob(roompac.getDob());
							if (roompac.getIssuedCountry() != null) {
								hotelPac.setIssuedCountry(Long.valueOf(roompac.getIssuedCountry()));
							}
							hotelPac.setEmail(roompac.getEmail());
							hotelPac.setPhone(roompac.getPhone());
							if (roompac.getPaxId() != null) {
								hotelPac.setPaxId(Long.valueOf(roompac.getPaxId()));
							}
							if (roompac.getRequestId() != null) {
								hotelPac.setRequestId(Long.valueOf(roompac.getRequestId()));
							}
							if (roompac.getRequestLineId() != null) {
								hotelPac.setRequestLineId(Long.valueOf(roompac.getRequestLineId()));
							}
							hotelPax.add(hotelPac);
						}
					}
				}

				HotelsDetails hotels = new HotelsDetails();
				hotels.setLineId(line.getRequestLineId());
				hotels.setRoomName(roomsNames);
				hotels.setHotelName(hotel.getHotelName());
				hotels.setHotelCountryCode(hotel.getDestinationCode());
				hotels.setHotelCountryName(hotel.getCityName());
				hotels.setHotelCityName(hotel.getCityName());
				hotels.setHotelAddress(hotel.getCityName());
				hotels.setNoOfDays(hotel.getHotelDate().size());
				hotels.setCheckInDate(java.sql.Date.valueOf(hotel.getHotelDate().get(0)));
				hotels.setCheckOutDate(
						java.sql.Date.valueOf(hotel.getHotelDate().get(hotel.getHotelDate().size() - 1).toString()));
				hotels.setRoomsCount(hotel.getHotelRoomCount());
				hotels.setBudgetFrom(0L);
				hotels.setBudgetTo(0L);
				hotels.setTeamLeader(line.getTeamLeader());
				hotels.setAdtCount(adtCnt);
				hotels.setChdCount(chdCnt);
				hotels.setInfCount(infCnt);
				hotels.setAddOns(hotelsAddons);
				hotels.setAncillaries(ancillaries);
				if (hotelPax != null && hotelPax.size() > 0) {
					hotels.setPaxCount(hotelPax.size());
					hotels.setPaxInfo(hotelPax);
				}
				if (roomPaxes != null && roomPaxes.size() > 0) {
					hotels.setRoomPaxInfo(roomPaxes);
				}
				hotels.setRoomsInfo(roomsInfoList);
				hotelsData.add(hotels);
			}
		}
		return hotelsData;
	}

	public Map<String, Object> packageItenaryData(Long requestId) throws Exception {

		Integer productId = serviceRequestRepository.findProductByRequest(requestId)
				.orElseThrow(() -> new Exception(String.format("Sr Information Not Found with %d", requestId)));
		PackageDetailsResponse packageItenaryData = null;
		try {
			packageItenaryData = getPackageDetailsBySrId(requestId, productId.equals(13) ? true : false);
		} catch (Exception e) {

			e.printStackTrace();
		}

		PackageItenaryResponse packageItenaryResponse = packageItenaryData.generatePackageItenaryResponse();

		/*
		 * gets dates information
		 */
		List<LocalDate> datesList = PackageItenaryConverter.generateDatesListFromPackageJson(packageItenaryResponse);

		List<PackageItenaryGroupResponse> srPackageDataByProduct = PackageItenaryConverter
				.groupSrPackageDataByProductDate(packageItenaryResponse);
		List<PackageItenaryGroupResponse> finalPackageData = PackageItenaryConverter
				.generateFinalSrPackageData(srPackageDataByProduct, datesList);

		List<PackageItenaryGroupResponse> srSortedByDate = finalPackageData.stream()
				.sorted(Comparator.comparing(PackageItenaryGroupResponse::getDate)).collect(Collectors.toList());

		Map<String, Integer> packageHeaderMap = new LinkedHashMap<>();
		packageHeaderMap.put("totalNoOfDays", datesList.size());
		packageHeaderMap.put("totalNoOfFlights", packageItenaryResponse.getFlight().size());
		packageHeaderMap.put("totalNoOfHotels", packageItenaryResponse.getHotel().size());
		packageHeaderMap.put("totalNoOfHospitals", packageItenaryResponse.getHospital().size());
		packageHeaderMap.put("totalNoOfAttractions", packageItenaryResponse.getAttractions().size());
		packageHeaderMap.put("totalNoOfAncillaries", packageItenaryResponse.getAncillary().size());
		packageHeaderMap.put("totalNoOfGroundTransport", packageItenaryResponse.getGroundTranport().size());

		PackageItenaryConverter.configureHotels(srSortedByDate);
		PackageItenaryConverter.configureHospitals(srSortedByDate);

		Map<String, Object> packageResponse = new LinkedHashMap<>();
		packageResponse.put("packageHeader", packageHeaderMap);
		packageResponse.put("packageLines", srSortedByDate);
		packageResponse.put("packagePaxDetails", packageItenaryData.getPaxInfo());
		return packageResponse;

	}

	@Override
	public PackageProductDTO confirmPackageItenaryData(Long srId, Long loggedInUserId) throws Exception {
		Integer productId = serviceRequestRepository.findProductByRequest(srId)
				.orElseThrow(() -> new Exception(String.format("Sr Information Not Found with %d", srId)));

		PackageDetailsResponse packageItenaryData = getPackageDetailsBySrId(srId, productId.equals(13) ? true : false);

		/*
		 * prepare data for flight
		 */

		List<PackageFlightDTO> preparedRequestDataForFlight = new ArrayList<PackageFlightDTO>();
		if (packageItenaryData.getFlight() != null && packageItenaryData.getFlight().size() > 0) {
			preparedRequestDataForFlight = prepareRequestDataForFlight(packageItenaryData.getFlight(),
					packageItenaryData.getPaxInfo(), srId, loggedInUserId);
		}

		/*
		 * prepare data for anx
		 */
		List<PackageAnxDTO> preparedRequestDataForAnx = new ArrayList<PackageAnxDTO>();
		if (packageItenaryData.getAncillaries() != null && packageItenaryData.getAncillaries().size() > 0) {
			preparedRequestDataForAnx = prepareRequestDataForAnx(packageItenaryData.getAncillaries(),
					packageItenaryData.getPaxInfo(), srId, loggedInUserId);
		}

		/*
		 * prepare data for attraction
		 */
		List<AttractionsRequest> preparedRequestDataForAttractions = new ArrayList<AttractionsRequest>();
		if (packageItenaryData.getAttractions() != null && packageItenaryData.getAttractions().size() > 0) {
			preparedRequestDataForAttractions = prepareRequestDataForAttractions(packageItenaryData.getAttractions(),
					packageItenaryData.getPaxInfo(), srId, loggedInUserId);
		}
		/*
		 * prepare date for hotel
		 */
		List<PackageHotelDTO> preparedRequestDataForHotel = new ArrayList<PackageHotelDTO>();
		if (packageItenaryData.getHotels() != null && packageItenaryData.getHotels().size() > 0) {
			preparedRequestDataForHotel = prepareRequestDataForHotel(packageItenaryData.getHotels(),
					packageItenaryData.getPaxInfo(), srId, loggedInUserId);
		}

		/*
		 * prepare date for hospital
		 */
		List<PackageHospitalDTO> preparedRequestDataForHospital = new ArrayList<PackageHospitalDTO>();
		if (packageItenaryData.getHospitals() != null && packageItenaryData.getHospitals().size() > 0) {
			preparedRequestDataForHospital = prepareRequestDataForHospital(packageItenaryData.getHospitals(),
					packageItenaryData.getPaxInfo(), srId, loggedInUserId);
		}

		/*
		 * prepare date for gt
		 */

		List<GroundTransportRequest> preparedRequestDataForGroundTransport = new ArrayList<>();
		if (packageItenaryData.getGroundTransportData() != null
				&& packageItenaryData.getGroundTransportData().size() > 0) {

			preparedRequestDataForGroundTransport = prepareRequestDataForGroundRequest(
					packageItenaryData.getGroundTransportData(), loggedInUserId, srId, packageItenaryData.getPaxInfo());
		}

		PackageProductDTO requestModel = new PackageProductDTO();
		requestModel.setFlight(preparedRequestDataForFlight);
		requestModel.setHotel(preparedRequestDataForHotel);
		requestModel.setHospital(preparedRequestDataForHospital);
		requestModel.setAnx(preparedRequestDataForAnx);
		requestModel.setAttraction(preparedRequestDataForAttractions);

		requestModel.setGroundTransport(preparedRequestDataForGroundTransport);

		return requestModel;
	}

	public List<GroundTransportRequest> prepareRequestDataForGroundRequest(List<GroundTransportRequest> requestInfo,
			Long loggedUserId, Long srId, List<HolidayRequestPax> paxInfo) {

		return requestInfo.stream().peek(eachRequest -> {
			eachRequest.setRequestId(srId.intValue());
			eachRequest.setCreatedBy(loggedUserId.intValue());
			if (paxInfo != null && !paxInfo.isEmpty()) {

				List<GroundPaxRequest> pax = GroundTransportConverter.convertListHolidayPaxToListGroundPax(paxInfo,
						loggedUserId);
				eachRequest.setPax(pax);
			} else {
				eachRequest.setPax(Collections.emptyList());
			}
		}).collect(Collectors.toList());

	}

	private List<PackageHospitalDTO> prepareRequestDataForHospital(List<HospitalsDetails> hospitals,
			List<HolidayRequestPax> paxInfo, Long srId, Long loggedInUserId) {

		return hospitals.stream().map(eachHospital -> {
			PackageHospitalDTO hospitalJson = new PackageHospitalDTO();

			HospitalRequestModel requestModel = new HospitalRequestModel();

			SrHospitalLinesDto srLine = new SrHospitalLinesDto();

			srLine.setLineLatitude("");
			srLine.setLineLongitude("");

			srLine.setLineRadius("");
			srLine.setLineSrId(srId.intValue());
			srLine.setLineCountry("");
			srLine.setLineCity("");
			srLine.setLineLocation(eachHospital.getHospitalCityName() + "," + eachHospital.getHospitalCountryName() + ""
					+ "(" + eachHospital.getHospitalCountryCode() + ")");
			srLine.setLineHospitalName(eachHospital.getHospitalName() != null ? eachHospital.getHospitalName() : "");
			srLine.setLinePropertyType("");

			srLine.setLineMealType("");
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

			srLine.setLineCheckInDate(simpleDateFormat.format(eachHospital.getCheckInDate()));
			srLine.setLineCheckOutDate(simpleDateFormat.format(eachHospital.getCheckOutDate()));

			int totalDays = eachHospital.getNoOfDays() != null ? eachHospital.getNoOfDays() : 0;
			srLine.setLineNoOfNights(totalDays - 1);
			srLine.setLineRoomCount(eachHospital.getRoomsCount() != null && eachHospital.getRoomsCount() != 0
					? eachHospital.getRoomsCount()
					: 1);
			srLine.setLineCountryResidency("");
			srLine.setLineNationality("");
			srLine.setLineRatings(
					eachHospital.getHospitalRating() != null ? Integer.valueOf(eachHospital.getHospitalRating()) : 0);

			srLine.setLineMarkUpType(HsMarkUpType.P);
			srLine.setLineMarkupAmount(null);
			srLine.setLineMarkupPercentage(null);

			srLine.setLineAdultCount(eachHospital.getAdtCount());
			srLine.setLineChildCount(eachHospital.getChdCount());
			srLine.setLineInfantCount(eachHospital.getInfCount());
			srLine.setLineTotalDays(totalDays);
			srLine.setLineSearchType("Normal");
			srLine.setLineAddonsRequired(0);

			srLine.setLineApis("");
			srLine.setLineCreatedBy(loggedInUserId.intValue());
			srLine.setLineCreatedDevice("chrome");
			srLine.setLineCreatedIp("0000.00.00.00");

			srLine.setLpoAmount(null);
			srLine.setLpoDate(null);
			srLine.setLpoNumber(null);
			srLine.setLineCreatedDate(simpleDateFormat.format(new Date()));

			requestModel.setSrLine(srLine);

			List<SrHospitalRoomsDto> collectedRooms = eachHospital.getRoomsInfo().stream().map(eachRoom -> {

				SrHospitalRoomsDto srRoom = new SrHospitalRoomsDto();

				srRoom.setRoomSrId(srId.intValue());
				srRoom.setRoomNumber(eachRoom.getRoomNumber());
				srRoom.setRoomAddonsRequired(0);

				srRoom.setRoomAdultCount(eachRoom.getRoomAdultCount());
				srRoom.setRoomInfantCount(eachRoom.getRoomInfantCount());
				srRoom.setRoomChildCount(eachRoom.getRoomChildCount());

				srRoom.setRoomChildAges(eachRoom.getRoomChildAges());
				srRoom.setRoomInfantAges(eachRoom.getRoomInfantAges());
				srRoom.setRoomStatus(0);
				srRoom.setRoomCreatedBy(loggedInUserId.intValue());
				srRoom.setRoomCreatedDate(simpleDateFormat.format(new Date()));
				srRoom.setRoomCreatedDevice("chrome");
				srRoom.setRoomCreatedIp("0000.00.00.00");

				List<SrHospitalPassengersDto> collectedRoomPaxInfo = paxInfo.stream().map(roomPax -> {
					SrHospitalPassengersDto roomPaxData = new SrHospitalPassengersDto();
					roomPaxData.setPassengerSrId(srId.intValue());
					roomPaxData.setPassengerStatus(0);
					roomPaxData.setPassengerAddonsRequired(0);
					roomPaxData.setPassengerPaxId(roomPax.getPaxId() != null ? roomPax.getPaxId().intValue() : 0);
					roomPaxData.setPassengerFirstName(roomPax.getFirstName() != null ? roomPax.getFirstName() : "");
					roomPaxData.setPassengerLastName(roomPax.getLastName() != null ? roomPax.getLastName() : "");
					roomPaxData.setPassengerDob(roomPax.getDob() != null ? roomPax.getDob() : null);
					roomPaxData.setPassengerNationality(
							roomPax.getNationality() != null ? roomPax.getNationality().intValue() : 0);
					roomPaxData.setPassengerCoutry(
							roomPax.getIssuedCountry() != null ? roomPax.getIssuedCountry().intValue() : 0);
					roomPaxData.setPassengerEmail(roomPax.getEmail() != null ? roomPax.getEmail() : "");

					roomPaxData.setPassengerPhone(roomPax.getPhone() != null ? roomPax.getPhone() : "");
					roomPaxData.setPassengerType(roomPax.getPaxType() != null ? roomPax.getPaxType().intValue() : 0);
					roomPaxData.setPassengerAttr1(roomPax.getPaxCode() != null ? roomPax.getPaxCode() : "");
					roomPaxData.setPassengerCreatedBy(loggedInUserId.intValue());
					roomPaxData.setPassengerCreatedDate(simpleDateFormat.format(new Date()));
					roomPaxData.setPassengerTitle(roomPax.getPrefix() != null ? roomPax.getPrefix() : "");
					roomPaxData.setPassengerCreatedDevice("chrome");
					roomPaxData.setPassengerCreatedIp("0000.00.00.00");
					return roomPaxData;
				}).collect(Collectors.toList());

				srRoom.setRoomPassengersInfo(collectedRoomPaxInfo);

				return srRoom;

			}).collect(Collectors.toList());

			requestModel.setSrRooms(collectedRooms);

			hospitalJson.setRequestData(requestModel);

			List<SrHospitalAddonsDto> addons = new ArrayList<>();

			if (eachHospital.getAddOns() != null && !eachHospital.getAddOns().isEmpty()) {

				eachHospital.getAddOns().stream().map(addon -> {
					SrHospitalAddonsDto addonData = new SrHospitalAddonsDto();
					addonData.setAddonTitle(addon.getAddOnName() != null ? addon.getAddOnName() : "");
					addonData.setAddonPassengerId("0");
					addonData.setAddonWithBooking(0);

					addonData.setAddonCount(addon.getPaxCount() != null ? addon.getPaxCount() : 0);
					addonData.setAddonRemarks(addon.getRemarks() != null ? addon.getRemarks() : "");

					addonData.setAddonRequired(0);

					addonData.setAddonExtraCost(
							addon.getExtraCost() != null ? BooleanUtils.toInteger(addon.getExtraCost()) : 0);

					addonData.setAddonStatus(0);
					addonData.setAddonSubTypeId(0);
					addonData.setAddonTypeId(0);

					addonData.setAddonCreatedBy(loggedInUserId.intValue());
					addonData.setAddonCreatedDate(simpleDateFormat.format(new Date()));

					addonData.setAddonCreatedDevice("chrome");
					addonData.setAddonCreatedIp("0000.00.00");

					if (addon.getPaxDetails() != null && !addon.getPaxDetails().isEmpty()) {
						addonData.setPaxDetails(addon.getPaxDetails());

					}

					return addonData;
				}).forEach(addons::add);
			}

			hospitalJson.setAddonsData(addons);
			return hospitalJson;

		}).collect(Collectors.toList());
	}

	private List<PackageHotelDTO> prepareRequestDataForHotel(List<HotelsDetails> hotels,
			List<HolidayRequestPax> paxInfo, Long srId, Long loggedInUserId) {

		return hotels.stream().map(eachHOtel -> {
			PackageHotelDTO hotelJson = new PackageHotelDTO();

			HotelRequestModel requestModel = new HotelRequestModel();

			SrHotelLinesDto srLine = new SrHotelLinesDto();

			srLine.setLineLatitude("");
			srLine.setLineLongitude("");

			srLine.setLineRadius("");
			srLine.setLineSrId(srId.intValue());
			srLine.setLineCountry("");
			srLine.setLineCity("");
			srLine.setLineLocation(eachHOtel.getHotelCityName() + "," + eachHOtel.getHotelCountryName() + "" + "("
					+ eachHOtel.getHotelCountryCode() + ")");
			srLine.setLineHotelName(eachHOtel.getHotelName() != null ? eachHOtel.getHotelName() : "");
			srLine.setLinePropertyType("");

			srLine.setLineMealType("");
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

			srLine.setLineCheckInDate(simpleDateFormat.format(eachHOtel.getCheckInDate()));
			srLine.setLineCheckOutDate(simpleDateFormat.format(eachHOtel.getCheckOutDate()));

			int totalDays = eachHOtel.getNoOfDays() != null ? eachHOtel.getNoOfDays() : 0;
			srLine.setLineNoOfNights(totalDays - 1);
			srLine.setLineRoomCount(
					eachHOtel.getRoomsCount() != null && eachHOtel.getRoomsCount() != 0 ? eachHOtel.getRoomsCount()
							: 1);
			srLine.setLineCountryResidency("");
			srLine.setLineNationality("");
			srLine.setLineRatings(eachHOtel.getHotelRating() != null ? Integer.valueOf(eachHOtel.getHotelRating()) : 0);

			srLine.setLineMarkUpType(MarkUpType.P);
			srLine.setLineMarkupAmount(null);
			srLine.setLineMarkupPercentage(null);

			srLine.setLineAdultCount(eachHOtel.getAdtCount());
			srLine.setLineChildCount(eachHOtel.getChdCount());
			srLine.setLineInfantCount(eachHOtel.getInfCount());
			srLine.setLineTotalDays(totalDays);
			srLine.setLineSearchType("Normal");
			srLine.setLineAddonsRequired(0);

			srLine.setLineApis("");
			srLine.setLineCreatedBy(loggedInUserId.intValue());
			srLine.setLineCreatedDevice("chrome");
			srLine.setLineCreatedIp("0000.00.00.00");

			srLine.setLpoAmount(null);
			srLine.setLpoDate(null);
			srLine.setLpoNumber(null);
			srLine.setLineCreatedDate(simpleDateFormat.format(new Date()));

			requestModel.setSrLine(srLine);

			List<SrHotelRoomsDto> collectedRooms = eachHOtel.getRoomsInfo().stream().map(eachRoom -> {

				SrHotelRoomsDto srRoom = new SrHotelRoomsDto();

				srRoom.setRoomSrId(srId.intValue());
				srRoom.setRoomNumber(eachRoom.getRoomNumber());
				srRoom.setRoomAddonsRequired(0);

				srRoom.setRoomAdultCount(eachRoom.getRoomAdultCount());
				srRoom.setRoomInfantCount(eachRoom.getRoomInfantCount());
				srRoom.setRoomChildCount(eachRoom.getRoomChildCount());

				srRoom.setRoomChildAges(eachRoom.getRoomChildAges());
				srRoom.setRoomInfantAges(eachRoom.getRoomInfantAges());
				srRoom.setRoomStatus(0);
				srRoom.setRoomCreatedBy(loggedInUserId.intValue());
				srRoom.setRoomCreatedDate(simpleDateFormat.format(new Date()));
				srRoom.setRoomCreatedDevice("chrome");
				srRoom.setRoomCreatedIp("0000.00.00.00");

				List<SrHotelPassengersDto> collectedRoomPaxInfo = paxInfo.stream().map(roomPax -> {
					SrHotelPassengersDto roomPaxData = new SrHotelPassengersDto();
					roomPaxData.setPassengerSrId(srId.intValue());
					roomPaxData.setPassengerStatus(0);
					roomPaxData.setPassengerAddonsRequired(0);
					roomPaxData.setPassengerPaxId(roomPax.getPaxId() != null ? roomPax.getPaxId().intValue() : 0);
					roomPaxData.setPassengerFirstName(roomPax.getFirstName() != null ? roomPax.getFirstName() : "");
					roomPaxData.setPassengerLastName(roomPax.getLastName() != null ? roomPax.getLastName() : "");
					roomPaxData.setPassengerDob(roomPax.getDob() != null ? roomPax.getDob() : null);
					roomPaxData.setPassengerNationality(
							roomPax.getNationality() != null ? roomPax.getNationality().intValue() : 0);
					roomPaxData.setPassengerCoutry(
							roomPax.getIssuedCountry() != null ? roomPax.getIssuedCountry().intValue() : 0);
					roomPaxData.setPassengerEmail(roomPax.getEmail() != null ? roomPax.getEmail() : "");

					roomPaxData.setPassengerPhone(roomPax.getPhone() != null ? roomPax.getPhone() : "");
					roomPaxData.setPassengerType(roomPax.getPaxType() != null ? roomPax.getPaxType().intValue() : 0);
					roomPaxData.setPassengerAttr1(roomPax.getPaxCode() != null ? roomPax.getPaxCode() : "");
					roomPaxData.setPassengerCreatedBy(loggedInUserId.intValue());
					roomPaxData.setPassengerCreatedDate(simpleDateFormat.format(new Date()));
					roomPaxData.setPassengerTitle(roomPax.getPrefix() != null ? roomPax.getPrefix() : "");
					roomPaxData.setPassengerCreatedDevice("chrome");
					roomPaxData.setPassengerCreatedIp("0000.00.00.00");
					return roomPaxData;
				}).collect(Collectors.toList());

				srRoom.setRoomPassengersInfo(collectedRoomPaxInfo);

				return srRoom;

			}).collect(Collectors.toList());

			requestModel.setSrRooms(collectedRooms);

			hotelJson.setRequestData(requestModel);

			List<SrHotelAddonsDto> addons = new ArrayList<>();

			if (eachHOtel.getAddOns() != null && !eachHOtel.getAddOns().isEmpty()) {

				eachHOtel.getAddOns().stream().map(addon -> {
					SrHotelAddonsDto addonData = new SrHotelAddonsDto();
					addonData.setAddonTitle(addon.getAddOnName() != null ? addon.getAddOnName() : "");
					addonData.setAddonPassengerId("0");
					addonData.setAddonWithBooking(0);

					addonData.setAddonCount(addon.getPaxCount() != null ? addon.getPaxCount() : 0);
					addonData.setAddonRemarks(addon.getRemarks() != null ? addon.getRemarks() : "");

					addonData.setAddonRequired(0);

					addonData.setAddonExtraCost(
							addon.getExtraCost() != null ? BooleanUtils.toInteger(addon.getExtraCost()) : 0);

					addonData.setAddonStatus(0);
					addonData.setAddonSubTypeId(0);
					addonData.setAddonTypeId(0);

					addonData.setAddonCreatedBy(loggedInUserId.intValue());
					addonData.setAddonCreatedDate(simpleDateFormat.format(new Date()));

					addonData.setAddonCreatedDevice("chrome");
					addonData.setAddonCreatedIp("0000.00.00");

					if (addon.getPaxDetails() != null && !addon.getPaxDetails().isEmpty()) {
						addonData.setPaxDetails(addon.getPaxDetails());

					}
					return addonData;
				}).forEach(addons::add);
			}

			hotelJson.setAddonsData(addons);
			return hotelJson;

		}).collect(Collectors.toList());
	}

	public List<AttractionsRequest> prepareRequestDataForAttractions(List<AttractionsDetails> attractions,
			List<HolidayRequestPax> paxInfo, Long srId, Long loggedInUserId) {

		return attractions.stream().map(eachAttr -> {

			AttractionsRequest attr = new AttractionsRequest();

			attr.setAttractionRequestId(srId);
			attr.setAttractionCreatedBy(loggedInUserId.intValue());
			attr.setAttractionCreatedDevice("chrome");
			attr.setAttractionCreatedIp("0000.000.000");

			attr.setAttractionName(eachAttr.getAttractionName());
			attr.setAttractionDescription("");

			List<SrAttractionsLinesRequest> lines = new ArrayList<>();

			SrAttractionsLinesRequest eachAttrLine = new SrAttractionsLinesRequest();

			eachAttrLine.setAttractionId(eachAttr.getAttractionID());
			eachAttrLine.setAttractionLineCity(eachAttr.getCity() != null ? eachAttr.getCity() : "city");

			eachAttrLine.setAttractionLineCountry(eachAttr.getCountry() != null ? eachAttr.getCountry() : "");

			eachAttrLine.setAttractionLineDate(eachAttr.getDaysList().get(0));
			eachAttrLine.setAttractionLineDay(0);
			eachAttrLine.setAttractionLineLocation(eachAttr.getLocation() != null ? eachAttr.getLocation() : "");
			eachAttrLine.setAttractionLineName(eachAttr.getAttractionName());
			eachAttrLine.setAttractionLinePaxCount(eachAttr.getPaxCount() != null ? eachAttr.getPaxCount() : 0);

			if (paxInfo != null && !paxInfo.isEmpty() && eachAttr.getPaxDetails() != null
					&& !eachAttr.getPaxDetails().isEmpty()) {
				List<SrAttractionsLinePaxRequest> generateAttractionPaxData = generateAttractionPaxData(paxInfo,
						eachAttr.getPaxDetails());

				System.out.println("-------------------------->" + generateAttractionPaxData.size());
				eachAttrLine.setPassengers(generateAttractionPaxData);
			} else {
				eachAttrLine.setPassengers(Collections.emptyList());
			}

			lines.add(eachAttrLine);
			attr.setLines(lines);
			return attr;

		}).collect(Collectors.toList());

	}

	private List<SrAttractionsLinePaxRequest> generateAttractionPaxData(List<HolidayRequestPax> paxInfo,
			List<AttractionsRequiredPaxInfo> paxDetails) {

		List<SrAttractionsLinePaxRequest> finalPax = new ArrayList<>();

		/*
		 * flrst level paxGrouping for holiday Pax
		 */
		Map<String, List<HolidayRequestPax>> groupedPaxTypeData = paxInfo.stream()
				.collect(Collectors.groupingBy(HolidayRequestPax::getPaxCode,
						Collectors.collectingAndThen(Collectors.toList(),
								paxList -> paxList.stream().sorted(Comparator.comparing(HolidayRequestPax::getAssign))
										.collect(Collectors.toList()))));

		/*
		 * second level grouping for selected pax from UI
		 */

		Map<String, Set<Integer>> groupedSelectedPaxData = paxDetails.stream()
				.collect(Collectors.groupingBy(p -> p.getPaxType().substring(0, 3),
						Collectors.mapping(AttractionsRequiredPaxInfo::getPaxNo, Collectors.toSet())));

		/*
		 * consolidate both grouped Objects and assign pax for products(ancillary and
		 * attractions)
		 */

		groupedSelectedPaxData.keySet().stream().forEach(selectedKey -> {

			if (selectedKey.equalsIgnoreCase("ADT")) {

				Set<Integer> paxNos = groupedSelectedPaxData.get(selectedKey);

				List<HolidayRequestPax> paxAdtInfo = groupedPaxTypeData.get("Adult");
				if (paxAdtInfo != null && !paxAdtInfo.isEmpty()) {
					paxAdtInfo.stream().filter(eachAdt -> paxNos.contains(eachAdt.getAssign())).map(p -> {
						SrAttractionsLinePaxRequest paxAttr = new SrAttractionsLinePaxRequest();

						paxAttr.setAttractionLinePassengerDob(p.getDob() != null ? p.getDob() : null);
						paxAttr.setAttractionLinePassengerEmail(p.getEmail() != null ? p.getEmail() : "");
						paxAttr.setAttractionLinePassengerFristName(p.getFirstName() != null ? p.getFirstName() : "");
						paxAttr.setAttractionLinePassengerLastName(p.getLastName() != null ? p.getLastName() : "");
						paxAttr.setAttractionLinePassengerPhone(p.getPhone() != null ? p.getPhone() : "");
						paxAttr.setAttractionLinePassengerTitle(p.getPrefix() != null ? p.getPrefix() : "");
						paxAttr.setAttractionLinePassengerType(p.getPaxCode());
						paxAttr.setAttractionLinePaxId(p.getPaxId() != null ? p.getPaxId().intValue() : 0);
						return paxAttr;
					}).forEach(finalPax::add);
				}
			}

			if (selectedKey.equalsIgnoreCase("INF")) {
				Set<Integer> paxNos = groupedSelectedPaxData.get(selectedKey);
				List<HolidayRequestPax> infList = groupedPaxTypeData.get("Infant");
				if (infList != null && !infList.isEmpty()) {
					infList.stream().filter(eachAdt -> paxNos.contains(eachAdt.getAssign())).map(p -> {
						SrAttractionsLinePaxRequest paxAttr = new SrAttractionsLinePaxRequest();

						paxAttr.setAttractionLinePassengerDob(p.getDob() != null ? p.getDob() : null);
						paxAttr.setAttractionLinePassengerEmail(p.getEmail() != null ? p.getEmail() : "");
						paxAttr.setAttractionLinePassengerFristName(p.getFirstName() != null ? p.getFirstName() : "");
						paxAttr.setAttractionLinePassengerLastName(p.getLastName() != null ? p.getLastName() : "");
						paxAttr.setAttractionLinePassengerPhone(p.getPhone() != null ? p.getPhone() : "");
						paxAttr.setAttractionLinePassengerTitle(p.getPrefix() != null ? p.getPrefix() : "");
						paxAttr.setAttractionLinePassengerType(p.getPaxCode());
						paxAttr.setAttractionLinePaxId(p.getPaxId() != null ? p.getPaxId().intValue() : 0);
						return paxAttr;
					}).forEach(finalPax::add);
				}

			}

			if (selectedKey.equalsIgnoreCase("CHD")) {
				Set<Integer> paxNos = groupedSelectedPaxData.get(selectedKey);
				List<HolidayRequestPax> chdlist = groupedPaxTypeData.get("Child");
				if (chdlist != null && !chdlist.isEmpty()) {
					chdlist.stream().filter(eachAdt -> paxNos.contains(eachAdt.getAssign())).map(p -> {
						SrAttractionsLinePaxRequest paxAttr = new SrAttractionsLinePaxRequest();

						paxAttr.setAttractionLinePassengerDob(p.getDob() != null ? p.getDob() : null);
						paxAttr.setAttractionLinePassengerEmail(p.getEmail() != null ? p.getEmail() : "");
						paxAttr.setAttractionLinePassengerFristName(p.getFirstName() != null ? p.getFirstName() : "");
						paxAttr.setAttractionLinePassengerLastName(p.getLastName() != null ? p.getLastName() : "");
						paxAttr.setAttractionLinePassengerPhone(p.getPhone() != null ? p.getPhone() : "");
						paxAttr.setAttractionLinePassengerTitle(p.getPrefix() != null ? p.getPrefix() : "");
						paxAttr.setAttractionLinePassengerType(p.getPaxCode());
						paxAttr.setAttractionLinePaxId(p.getPaxId() != null ? p.getPaxId().intValue() : 0);

						return paxAttr;
					}).forEach(finalPax::add);
				}

			}
		});

		return finalPax;

	}

	private List<PackageAnxDTO> prepareRequestDataForAnx(List<AddOnDetails> ancillaries,
			List<HolidayRequestPax> paxInfo, Long srId, Long loggedInUserId) {

		return ancillaries.stream().map(each -> {

			PackageAnxDTO anx = new PackageAnxDTO();

			AnxRequestLine requestData = new AnxRequestLine();

			RequestLinePaxDto generatePaxDataInfo = new RequestLinePaxDto();

			requestData.setAnxLineTypeId(each.getAddOnId());
			requestData.setAnxLineType(each.getAddOnName());
			requestData.setAnxLineRequestId(srId.intValue());
			requestData.setLoggedInUserId(loggedInUserId.intValue());

			if (each.getDynamicTabData() != null && !each.getDynamicTabData().isEmpty()) {

				Map<String, Object> anxLineJson = new HashMap<>();

				if (each.getDynamicTabData().isObject()) {
					log.debug("{-isObject--}", each.getDynamicTabData());
					anxLineJson = mapper.convertValue(each.getDynamicTabData(), Map.class);
				}
				if (each.getDynamicTabData().isArray()) {

					log.debug("{-isArray-}", each.getDynamicTabData());
					List<Map<String, Object>> listData = mapper.convertValue(each.getDynamicTabData(), List.class);
					anxLineJson = listData.get(0);

				}
				System.out.println(
						"---------------------------anxLineJson---------------------------------->" + anxLineJson);
				requestData.setAnxLineJson(anxLineJson);
			} else {
				requestData.setAnxLineJson(Collections.emptyMap());
			}

			if (paxInfo != null && !paxInfo.isEmpty() && each.getPaxDetails() != null
					&& !each.getPaxDetails().isEmpty()) {
				generatePaxDataInfo = generateAnxPaxDataInfo(srId, loggedInUserId, paxInfo, each.getPaxDetails());
			}
			if (Objects.nonNull(each.getPaxDetails()) && !each.getPaxDetails().isEmpty()) {
				requestData.setAnxLineAdtCount(Math.toIntExact(
						each.getPaxDetails().stream().filter(p -> p.getPaxType().startsWith("ADT")).count()));
				requestData.setAnxLineChdCount(Math.toIntExact(
						each.getPaxDetails().stream().filter(p -> p.getPaxType().startsWith("CHD")).count()));

				requestData.setAnxLineInfCount(Math.toIntExact(
						each.getPaxDetails().stream().filter(p -> p.getPaxType().startsWith("INF")).count()));

			} else {
				requestData.setAnxLineAdtCount(0);
				requestData.setAnxLineChdCount(0);

				requestData.setAnxLineInfCount(0);
			}

			anx.setRequestData(requestData);

			anx.setPaxData(generatePaxDataInfo);
			return anx;

		}).collect(Collectors.toList());
	}

	private RequestLinePaxDto generateAnxPaxDataInfo(Long srId, Long loggedInUserId, List<HolidayRequestPax> paxInfo,
			List<AttractionsRequiredPaxInfo> paxDetails) {
		RequestLinePaxDto pax = new RequestLinePaxDto();
		pax.setCreatedBy(loggedInUserId);
		pax.setUpdatedBy(0L);

		List<PaxRequestModel> finalPax = new ArrayList<>();
		/*
		 * flrst level paxGrouping for holiday Pax
		 */
		Map<String, List<HolidayRequestPax>> groupedPaxTypeData = paxInfo.stream()
				.collect(Collectors.groupingBy(HolidayRequestPax::getPaxCode,
						Collectors.collectingAndThen(Collectors.toList(),
								paxList -> paxList.stream().sorted(Comparator.comparing(HolidayRequestPax::getAssign))
										.collect(Collectors.toList()))));

		/*
		 * second level grouping for selected pax from UI
		 */

		System.out.println("================================>" + paxDetails);
		Map<String, Set<Integer>> groupedSelectedPaxData = paxDetails.stream()
				.collect(Collectors.groupingBy(p -> p.getPaxType().substring(0, 3),
						Collectors.mapping(AttractionsRequiredPaxInfo::getPaxNo, Collectors.toSet())));

		/*
		 * consolidate both grouped Objects and assign pax for products(ancillary and
		 * attractions)
		 */
		groupedSelectedPaxData.keySet().stream().forEach(selectedKey -> {

			if (selectedKey.equalsIgnoreCase("ADT")) {

				Set<Integer> paxNos = groupedSelectedPaxData.get(selectedKey);

				List<HolidayRequestPax> adtList = groupedPaxTypeData.get("Adult");

				if (adtList != null && !adtList.isEmpty()) {

					adtList.stream().filter(eachAdt -> paxNos.contains(eachAdt.getAssign())).map(p -> {
						PaxRequestModel paxData = new PaxRequestModel();

						paxData.setCreatedDate(new Date());
						paxData.setEmail(p.getEmail() != null ? p.getEmail() : "");
						paxData.setDob(p.getDob() != null ? p.getDob() : null);
						paxData.setFirstName(p.getFirstName() != null ? p.getFirstName() : "");
						paxData.setLastName(p.getLastName() != null ? p.getLastName() : "");
						paxData.setIssuedCountry(p.getIssuedCountry() != null ? p.getIssuedCountry() : 0L);
						paxData.setNationality(p.getNationality() != null ? p.getNationality() : 0L);
						paxData.setPassport(p.getPassport() != null ? p.getPassport() : "");
						paxData.setPassportExpiredDate(
								p.getPassportExpiredDate() != null ? p.getPassportExpiredDate() : null);
						paxData.setPassportIssueDate(
								p.getPassportIssueDate() != null ? p.getPassportIssueDate() : null);
						paxData.setPaxId(p.getPaxId() != null ? p.getPaxId() : 0L);
						paxData.setPaxType(p.getPaxType() != null ? p.getPaxType() : 0L);
						paxData.setPhone(p.getPhone() != null ? p.getPhone() : "");
						return paxData;
					}).forEach(finalPax::add);
				}

			}
			if (selectedKey.equalsIgnoreCase("INF")) {
				Set<Integer> paxNos = groupedSelectedPaxData.get(selectedKey);
				List<HolidayRequestPax> Inflist = groupedPaxTypeData.get("Infant");
				if (Inflist != null && !Inflist.isEmpty()) {
					Inflist.stream().filter(eachAdt -> paxNos.contains(eachAdt.getAssign())).map(p -> {
						PaxRequestModel paxData = new PaxRequestModel();

						paxData.setCreatedDate(new Date());
						paxData.setEmail(p.getEmail() != null ? p.getEmail() : "");
						paxData.setDob(p.getDob() != null ? p.getDob() : null);
						paxData.setFirstName(p.getFirstName() != null ? p.getFirstName() : "");
						paxData.setLastName(p.getLastName() != null ? p.getLastName() : "");
						paxData.setIssuedCountry(p.getIssuedCountry() != null ? p.getIssuedCountry() : 0L);
						paxData.setNationality(p.getNationality() != null ? p.getNationality() : 0L);
						paxData.setPassport(p.getPassport() != null ? p.getPassport() : "");
						paxData.setPassportExpiredDate(
								p.getPassportExpiredDate() != null ? p.getPassportExpiredDate() : null);
						paxData.setPassportIssueDate(
								p.getPassportIssueDate() != null ? p.getPassportIssueDate() : null);
						paxData.setPaxId(p.getPaxId() != null ? p.getPaxId() : 0L);
						paxData.setPaxType(p.getPaxType() != null ? p.getPaxType() : 0L);
						paxData.setPhone(p.getPhone() != null ? p.getPhone() : "");
						return paxData;
					}).forEach(finalPax::add);
				}

			}
			if (selectedKey.equalsIgnoreCase("CHD")) {
				Set<Integer> paxNos = groupedSelectedPaxData.get(selectedKey);
				List<HolidayRequestPax> chdlist = groupedPaxTypeData.get("Child");
				if (chdlist != null && !chdlist.isEmpty()) {
					chdlist.stream().filter(eachAdt -> paxNos.contains(eachAdt.getAssign())).map(p -> {
						PaxRequestModel paxData = new PaxRequestModel();

						paxData.setCreatedDate(new Date());
						paxData.setEmail(p.getEmail() != null ? p.getEmail() : "");
						paxData.setDob(p.getDob() != null ? p.getDob() : null);
						paxData.setFirstName(p.getFirstName() != null ? p.getFirstName() : "");
						paxData.setLastName(p.getLastName() != null ? p.getLastName() : "");
						paxData.setIssuedCountry(p.getIssuedCountry() != null ? p.getIssuedCountry() : 0L);
						paxData.setNationality(p.getNationality() != null ? p.getNationality() : 0L);
						paxData.setPassport(p.getPassport() != null ? p.getPassport() : "");
						paxData.setPassportExpiredDate(
								p.getPassportExpiredDate() != null ? p.getPassportExpiredDate() : null);
						paxData.setPassportIssueDate(
								p.getPassportIssueDate() != null ? p.getPassportIssueDate() : null);
						paxData.setPaxId(p.getPaxId() != null ? p.getPaxId() : 0L);
						paxData.setPaxType(p.getPaxType() != null ? p.getPaxType() : 0L);
						paxData.setPhone(p.getPhone() != null ? p.getPhone() : "");
						return paxData;
					}).forEach(finalPax::add);
				}

			}

		});

		/*
		 * List<PaxRequestModel> collectedPax = paxInfo.stream().map(p -> {
		 * PaxRequestModel paxData = new PaxRequestModel();
		 * 
		 * paxData.setCreatedDate(new Date()); paxData.setEmail(p.getEmail() != null ?
		 * p.getEmail() : ""); paxData.setDob(p.getDob() != null ? p.getDob() : null);
		 * paxData.setFirstName(p.getFirstName() != null ? p.getFirstName() : "");
		 * paxData.setLastName(p.getLastName() != null ? p.getLastName() : "");
		 * paxData.setIssuedCountry(p.getIssuedCountry() != null ? p.getIssuedCountry()
		 * : 0L); paxData.setNationality(p.getNationality() != null ? p.getNationality()
		 * : 0L); paxData.setPassport(p.getPassport() != null ? p.getPassport() : "");
		 * paxData.setPassportExpiredDate(p.getPassportExpiredDate() != null ?
		 * p.getPassportExpiredDate() : null);
		 * paxData.setPassportIssueDate(p.getPassportIssueDate() != null ?
		 * p.getPassportIssueDate() : null); paxData.setPaxId(p.getPaxId() != null ?
		 * p.getPaxId() : 0L); paxData.setPaxType(p.getPaxType() != null ?
		 * p.getPaxType() : 0L); paxData.setPhone(p.getPhone() != null ? p.getPhone() :
		 * ""); return paxData;
		 * 
		 * }).collect(Collectors.toList());
		 */

		pax.setPaxData(finalPax);

		return pax;

	}

	public List<PackageFlightDTO> prepareRequestDataForFlight(List<FlightDetails> flight,
			List<HolidayRequestPax> paxInfo, Long srId, Long loggedInUserId) {

		ServiceRequestLineModel flightRequest = new ServiceRequestLineModel();

		ServiceRequestLine serviceRequestLine = new ServiceRequestLine();

		/*
		 * generate flight request line
		 */
		serviceRequestLine.setRequestId(srId);
		serviceRequestLine.setNoofADT(flight.get(0).getAdtCount());
		serviceRequestLine.setNoofCHD(flight.get(0).getChdCount());
		serviceRequestLine.setNoofINF(flight.get(0).getInfCount());
		serviceRequestLine.setLPoDate(null);
		serviceRequestLine.setLpoAmount("0.0");
		serviceRequestLine.setTripTypeId(11L);
		serviceRequestLine.setTypeOfFlight("11");
		serviceRequestLine.setCreatedBy(loggedInUserId);
		serviceRequestLine.setCreatedDate(new Date());

		// List<AddOnDetails> flightAddons = new ArrayList<>();

		List<Map<Object, Object>> flightAddons = new ArrayList<>();
		List<ServiceRequestSegment> serviceRequestSegment = new ArrayList<>();

		flight.stream().forEach(flightSegments -> {
			ServiceRequestSegment eachSegment = new ServiceRequestSegment();

			eachSegment.setRequestID(srId);

			String fromCode = flightSegments.getSegmentBoardPoint() != null ? flightSegments.getSegmentBoardPoint()
					: "";
			eachSegment.setFromCode(fromCode);

			eachSegment.setFromCountryName(fromCode);

			eachSegment.setFromAirportOrCityName(flightSegments.getSegmentBoardCityOrAirport() != null
					? flightSegments.getSegmentBoardCityOrAirport()
					: "");

			String toCode = flightSegments.getSegmentOffPoint() != null ? flightSegments.getSegmentOffPoint() : "";
			eachSegment.setToCode(toCode);

			eachSegment.setToCountryName(toCode);

			eachSegment.setToAirportOrCityName(flightSegments.getSegmentArrivalCityOrAirport() != null
					? flightSegments.getSegmentArrivalCityOrAirport()
					: "");

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

			if (flightSegments.getSegmentDepartDate() != null) {

				Date flightDeptDate = null;
				try {
					flightDeptDate = simpleDateFormat.parse(flightSegments.getSegmentDepartDate());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				eachSegment.setDepatureDate(flightDeptDate);

			}

			eachSegment.setClassName(
					flightSegments.getSegmentClassDesignator() != null ? flightSegments.getSegmentClassDesignator()
							: "");

			eachSegment.setRbd(flightSegments.getSegmentRbdCode() != null ? flightSegments.getSegmentRbdCode() : "");
			if (flightSegments.getValidateCarrier() != null)
				eachSegment.setValidateCarrier(flightSegments.getValidateCarrier().booleanValue());
			eachSegment.setBudgetFrom(flightSegments.getBudgetFrom());
			eachSegment.setBudgetTo(flightSegments.getBudgetTo());

			eachSegment.setFlexAirLineCode(flightSegments.getFlexAirLineCode());
			eachSegment.setFlexFromCode(flightSegments.getFlexFromCode());
			eachSegment.setFlexToCode(flightSegments.getFlexToCode());
			eachSegment.setTransitPointCode(Collections.emptyList());
			eachSegment.setExcludePointCode(Collections.emptyList());

			eachSegment.setFlexDepature(Collections.emptyList());
			eachSegment.setFlexReturn(Collections.emptyList());
			eachSegment.setFlexClassName(Collections.emptyList());

			eachSegment.setAirlineCode(
					flightSegments.getSegmentAirlineMarketing() != null ? flightSegments.getSegmentAirlineMarketing()
							: "");

			serviceRequestSegment.add(eachSegment);

			if (Objects.nonNull(flightSegments.getAddOns()) && !flightSegments.getAddOns().isEmpty()) {
				List<Map<Object, Object>> addons = convertAddonDetailsToMap(flightSegments.getAddOns(), fromCode,
						toCode);
				addons.forEach(flightAddons::add);
			}

		});

		serviceRequestLine.setAddons(flightAddons.stream().filter(Objects::nonNull).collect(Collectors.toList()));
		flightRequest.setServiceRequestLine(serviceRequestLine);
		flightRequest.setServiceRequestSegment(serviceRequestSegment);

		RequestLinePaxDto generatePaxDataInfo = generateFlightPaxDataInfo(srId, loggedInUserId, paxInfo);

		PackageFlightDTO flightData = new PackageFlightDTO();

		flightData.setRequestData(flightRequest);
		flightData.setPaxData(generatePaxDataInfo);
		return List.of(flightData);

	}

	public RequestLinePaxDto generateFlightPaxDataInfo(Long srId, Long loggedInUserId,
			List<HolidayRequestPax> paxInfo) {
		RequestLinePaxDto pax = new RequestLinePaxDto();
		pax.setCreatedBy(loggedInUserId);
		pax.setUpdatedBy(0L);

		List<PaxRequestModel> collectedPax = paxInfo.stream().map(p -> {
			PaxRequestModel paxData = new PaxRequestModel();

			paxData.setCreatedDate(new Date());
			paxData.setEmail(p.getEmail() != null ? p.getEmail() : "");
			paxData.setDob(p.getDob() != null ? p.getDob() : null);
			paxData.setFirstName(p.getFirstName() != null ? p.getFirstName() : "");
			paxData.setLastName(p.getLastName() != null ? p.getLastName() : "");
			paxData.setIssuedCountry(p.getIssuedCountry() != null ? p.getIssuedCountry() : 0L);
			paxData.setNationality(p.getNationality() != null ? p.getNationality() : 0L);
			paxData.setPassport(p.getPassport() != null ? p.getPassport() : "");
			paxData.setPassportExpiredDate(p.getPassportExpiredDate() != null ? p.getPassportExpiredDate() : null);
			paxData.setPassportIssueDate(p.getPassportIssueDate() != null ? p.getPassportIssueDate() : null);
			paxData.setPaxId(p.getPaxId() != null ? p.getPaxId() : 0L);
			paxData.setPaxType(p.getPaxType() != null ? p.getPaxType() : 0L);
			paxData.setPhone(p.getPhone() != null ? p.getPhone() : "");
			return paxData;

		}).collect(Collectors.toList());

		pax.setPaxData(collectedPax);
		return pax;
	}

	private List<Map<Object, Object>> convertAddonDetailsToMap(List<AddOnDetails> addOns, String fromCode,
			String toCode) {

		return addOns.stream().map(addOnDetails -> {

			Map<Object, Object> addonObject = new HashMap<Object, Object>();

			addonObject.put("addOnType", Map.of("id", addOnDetails.getAddOnId() != null ? addOnDetails.getAddOnId() : 0,
					"name", addOnDetails.getAddOnName() != null ? addOnDetails.getAddOnName() : "NA"));

			addonObject.put("remarks", addOnDetails.getRemarks() != null ? addOnDetails.getRemarks() : "NA");
			addonObject.put("required", addOnDetails.getRequired());
			addonObject.put("extraCost", addOnDetails.getExtraCost());
			addonObject.put("requiredPassenger", Map.of("all", true, "passengers",
					addOnDetails.getPaxDetails() != null ? addOnDetails.getPaxDetails() : Collections.emptyList()));

			String route = fromCode.concat(" - ").concat(toCode);
			addonObject.put("requiredRoute", Map.of("all", true, "routes", Arrays.asList(
					Map.of("route", route, "routeNo", 0, "bindLableRoute", route, "selectedAllGroup", "Select All"))));

			return addonObject;

		}).collect(Collectors.toList());

	}
}

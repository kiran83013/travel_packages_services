package com.travel.travtronics.response.dto;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.travel.travtronics.converter.ItenarySrConverter;
import com.travel.travtronics.request.GroundTransportRequest;
import com.travel.travtronics.srm.model.HolidayPackagePassengersModel;
import com.travel.travtronics.srm.model.HolidayRequestPax;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class PackageDetailsResponse {

	private List<FlightDetails> flight;

	private List<HotelsDetails> hotels;

	private List<HospitalsDetails> hospitals;

	private List<AttractionsDetails> attractions;

	private List<AddOnDetails> ancillaries;

	List<GroundTransportRequest> groundTransportData;

	private List<HolidayRequestPax> paxInfo;

	@Getter
	@Setter
	public static class FlightItenary {
		private Long lineId;

		private String segmentBoardPoint;

		private String segmentBoardCityOrAirport;

		private LocalDate segmentDepartDate;

		private String segmentDepartTime;

		private Integer segmentDepartTerminal;

		private String segmentOffPoint;

		private String segmentArrivalCityOrAirport;

		private Date segmentArrivalDate;

		private String segmentArrivalTime;

		private Integer segmentArrivalTerminal;

		private String segmentAirlineOperating;

		private String segmentAirlineMarketing;

		private String segmentAirlineNo;

		private String segmentClassDesignator;

		private String segmentRbdCode;

		private String segmentNumOfStops;

		private Integer adtCount;

		private Integer chdCount;

		private Integer infCount;

		private Integer paxCount;

		private List<AddOnDetails> addOns;

		private List<AddOnDetails> ancillaries;

		private List<HolidayRequestPax> paxInfo;

	}

	@Getter
	@Setter
	public static class HotelItenary {

		private Long lineId;

		private String hotelName;

		private String hotelCode;

		private String roomNumber;

		private String roomName;

		private String roomType;

		private String hotelCountryCode;

		private String hotelCountryName;

		private String hotelCityCode;

		private String hotelCityName;

		private String hotelStar;

		private String hotelRating;

		private String hotelAddress;

		private String hotelDescription;

		private String hotelPhone;

		private LocalDate checkInDate;

		private LocalDate checkOutDate;

		private Long budgetFrom;

		private Long budgetTo;

		private String teamLeader;

		private Integer roomsCount;

		private Integer noOfDays;

		private Integer adtCount;

		private Integer chdCount;

		private Integer infCount;

		private Integer paxCount;

		private Integer roomAdtCount;

		private Integer roomChdCount;

		private Integer roomInfCount;

		private String roomChildAges;

		private String roomInfantAges;

		private List<HotelRoomsDetails> roomsInfo;

		private List<AddOnDetails> addOns;

		private List<AddOnDetails> ancillaries;

		private List<HolidayRequestPax> paxInfo;

		private List<HolidayPackagePassengersModel> roomPaxInfo;

	}

	@Getter
	@Setter

	public static class HospitalItenary {

		private Long lineId;

		private String hospitalName;

		private String hospitalCode;

		private String roomNumber;

		private String roomName;

		private String roomType;

		private String hospitalCountryCode;

		private String hospitalCountryName;

		private String hospitalCityCode;

		private String hospitalCityName;

		private String hospitalStar;

		private String hospitalRating;

		private String hospitalAddress;

		private String hospitalDescription;

		private String hospitalPhone;

		private LocalDate checkInDate;

		private LocalDate checkOutDate;

		private Long budgetFrom;

		private Long budgetTo;

		private String teamLeader;

		private Integer roomsCount;

		private Integer noOfDays;

		private Integer adtCount;

		private Integer chdCount;

		private Integer infCount;

		private Integer paxCount;

		private Integer roomAdtCount;

		private Integer roomChdCount;

		private Integer roomInfCount;

		private String roomChildAges;

		private String roomInfantAges;

		private List<HospitalRoomsDetails> roomsInfo;

		private List<AddOnDetails> addOns;

		private List<AddOnDetails> ancillaries;

		private List<HolidayRequestPax> paxInfo;

		private List<HolidayPackagePassengersModel> roomPaxInfo;

	}

	@Getter
	@Setter
	public static class AttractionItenary {
		private String attractionName;

		private Integer attractionID;

		private String city;

		private String country;

		private String location;

		private Integer paxCount;

		private LocalDate date;
		private String remarks;

	}

	@Getter
	@Setter
	public static class AnxItenary {
		private Integer addOnId;

		private String addOnName;

		private String addOnCode;

		private String addOnDescription;

		private String addOnType;

		private String remarks;

		private Boolean extraCost;

		private Integer paxCount;

		private List<AttractionsRequiredPaxInfo> paxDetails;

		private LocalDate date;

		private JsonNode dynamicTabData;

	}

	@Getter
	@Setter
	public static class GroundTransportItenary {
		private String departureCity;

		private String departureLocation;

		private String departureCountry;

		private String departureDate;

		private String arrivalCity;

		private String arrivalLocation;

		private String arrivalCountry;

		private String returnDate;

		private Integer noOfAdults;

		private Integer noOfChilds;

		private Integer noOfInfants;

		private Integer type;

		private String typeName;

		private List<Map<Object, Object>> specialRequests;

		private String vehicleType;

		private LocalDate date;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class PackageItenaryResponse {

		private List<FlightItenary> flight;
		private List<HotelItenary> hotel;
		private List<HospitalItenary> hospital;
		private List<AttractionItenary> attractions;
		private List<AnxItenary> ancillary;
		private List<GroundTransportItenary> groundTranport;
	}

	public PackageItenaryResponse generatePackageItenaryResponse() {

		List<FlightDetails> flightData = this.getFlight() != null ? this.flight : Collections.emptyList();
		List<HotelsDetails> hotelsData = this.getHotels() != null ? this.hotels : Collections.emptyList();
		List<HospitalsDetails> hospitalsData = this.getHospitals() != null ? this.hospitals : Collections.emptyList();
		List<AttractionsDetails> attractionsData = this.getAttractions() != null ? this.getAttractions()
				: Collections.emptyList();
		List<AddOnDetails> anxData = this.getAncillaries() != null ? this.getAncillaries() : Collections.emptyList();
		List<GroundTransportRequest> groundTransport = this.getGroundTransportData() != null
				? this.getGroundTransportData()
				: Collections.emptyList();

		List<FlightItenary> flightParsedData = flightData.stream()
				.filter(flight -> Objects.nonNull(flight.getSegmentDepartDate())).map(f -> {
					FlightItenary flightItenary = new FlightItenary();
					BeanUtils.copyProperties(f, flightItenary, "segmentDepartDate");

					LocalDate segmentDepartDate = LocalDate.parse(f.getSegmentDepartDate(),
							com.travel.travtronics.converter.ItenarySrConverter.formatter);
					flightItenary.setSegmentDepartDate(segmentDepartDate);

					return flightItenary;
				}).collect(Collectors.toList());

		List<HotelItenary> hotelParsedData = hotelsData.stream()
				.filter(hotel -> Objects.nonNull(hotel.getCheckInDate()) && Objects.nonNull(hotel.getCheckOutDate()))
				.map(h -> {

					HotelItenary hotelItenary = new HotelItenary();
					BeanUtils.copyProperties(h, hotelItenary, "checkInDate", "checkOutDate");

					LocalDate parsedCheckInDate = LocalDate
							.parse(new SimpleDateFormat("yyyy-MM-dd").format(h.getCheckInDate()));
					LocalDate parsedCheckOutDate = LocalDate
							.parse(new SimpleDateFormat("yyyy-MM-dd").format(h.getCheckOutDate()));
					hotelItenary.setCheckInDate(parsedCheckInDate);
					hotelItenary.setCheckOutDate(parsedCheckOutDate);
					return hotelItenary;

				}).collect(Collectors.toList());

		List<HospitalItenary> hospitalParsedData = hospitalsData.stream().filter(
				hospital -> Objects.nonNull(hospital.getCheckInDate()) && Objects.nonNull(hospital.getCheckOutDate()))
				.map(h -> {

					HospitalItenary hospitalItenary = new HospitalItenary();
					BeanUtils.copyProperties(h, hospitalItenary, "checkInDate", "checkOutDate");

					LocalDate parsedCheckInDate = LocalDate
							.parse(new SimpleDateFormat("yyyy-MM-dd").format(h.getCheckInDate()));
					LocalDate parsedCheckOutDate = LocalDate
							.parse(new SimpleDateFormat("yyyy-MM-dd").format(h.getCheckOutDate()));
					hospitalItenary.setCheckInDate(parsedCheckInDate);
					hospitalItenary.setCheckOutDate(parsedCheckOutDate);
					return hospitalItenary;

				}).collect(Collectors.toList());

		List<AttractionItenary> parsedAttrData = attractionsData.stream().map(attr -> {

			List<String> daysList = attr.getDaysList();

			return daysList.stream().filter(Objects::nonNull).map(day -> {
				AttractionItenary attractionItenary = new AttractionItenary();
				BeanUtils.copyProperties(attr, attractionItenary, "daysList");
				LocalDate segmentDepartDate = LocalDate.parse(day, ItenarySrConverter.formatter);
				attractionItenary.setDate(segmentDepartDate);
				return attractionItenary;
			}).collect(Collectors.toList());

		}).flatMap(List::stream).collect(Collectors.toList());

		List<AnxItenary> parsedAnxData = anxData.stream().filter(Objects::nonNull).map(anx -> {

			List<String> daysList = anx.getDaysList();

			System.out.println("=======ANX=====" + daysList);

			return daysList.stream().map(day -> {
				AnxItenary anxItenary = new AnxItenary();
				BeanUtils.copyProperties(anx, anxItenary, "daysList");
				LocalDate segmentDepartDate = LocalDate.parse(day, ItenarySrConverter.formatter);
				anxItenary.setDate(segmentDepartDate);
				return anxItenary;
			}).collect(Collectors.toList());

		}).flatMap(List::stream).collect(Collectors.toList());

		List<GroundTransportItenary> parsedTransportData = groundTransport.stream().filter(Objects::nonNull)
				.map(tranport -> {

					// List<String> daysList = tranport.getDaysList();

					String departureDate = tranport.getDepartureDate();

					LocalDate parsedDate = LocalDate.parse(departureDate, ItenarySrConverter.formatter);

					GroundTransportItenary groundTransportItenary = new GroundTransportItenary();
					BeanUtils.copyProperties(tranport, groundTransportItenary);

					groundTransportItenary.setDate(parsedDate);
					return groundTransportItenary;

				}).collect(Collectors.toList());

		PackageItenaryResponse packageItenaryResponse = new PackageItenaryResponse(flightParsedData, hotelParsedData,
				hospitalParsedData, parsedAttrData, parsedAnxData, parsedTransportData);

		return packageItenaryResponse;
	}
}

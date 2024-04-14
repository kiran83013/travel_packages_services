package com.travel.travtronics.converter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.travel.travtronics.response.dto.PackageDetailsResponse.AnxItenary;
import com.travel.travtronics.response.dto.PackageDetailsResponse.AttractionItenary;
import com.travel.travtronics.response.dto.PackageDetailsResponse.FlightItenary;
import com.travel.travtronics.response.dto.PackageDetailsResponse.GroundTransportItenary;
import com.travel.travtronics.response.dto.PackageDetailsResponse.HospitalItenary;
import com.travel.travtronics.response.dto.PackageDetailsResponse.HotelItenary;
import com.travel.travtronics.response.dto.PackageDetailsResponse.PackageItenaryResponse;

import com.travel.travtronics.response.dto.PackageItenaryGroupResponse;

public class PackageItenaryConverter {

	public static List<LocalDate> generateDatesListFromPackageJson(PackageItenaryResponse packageItenaryResponse) {

		Set<LocalDate> datesSet = new HashSet<>();

		if (Objects.nonNull(packageItenaryResponse.getFlight())) {
			packageItenaryResponse.getFlight().forEach(f -> datesSet.add(f.getSegmentDepartDate()));

		}
		if (Objects.nonNull(packageItenaryResponse.getAttractions())) {
			packageItenaryResponse.getAttractions().forEach(attr -> datesSet.add(attr.getDate()));
		}
		if (Objects.nonNull(packageItenaryResponse.getAncillary())) {
			packageItenaryResponse.getAncillary().forEach(anx -> datesSet.add(anx.getDate()));
		}
		if (Objects.nonNull(packageItenaryResponse.getGroundTranport())) {
			packageItenaryResponse.getGroundTranport().forEach(transport -> datesSet.add(transport.getDate()));
		}

		if (Objects.nonNull(packageItenaryResponse.getHotel())) {

			packageItenaryResponse.getHotel().forEach(hotel -> {

				List<LocalDate> datesList = ItenarySrConverter
						.generateDatesBetwenTwoCheckinDates(hotel.getCheckInDate(), hotel.getCheckOutDate());
				datesList.forEach(d -> datesSet.add(d));
			});
		}

		if (Objects.nonNull(packageItenaryResponse.getHospital())) {

			packageItenaryResponse.getHospital().forEach(hospital -> {

				List<LocalDate> datesList = ItenarySrConverter
						.generateDatesBetwenTwoCheckinDates(hospital.getCheckInDate(), hospital.getCheckOutDate());
				datesList.forEach(d -> datesSet.add(d));
			});
		}

		LocalDate minDate = datesSet.stream().min(Comparator.comparing(LocalDate::toEpochDay)).get();
		LocalDate maxDate = datesSet.stream().max(Comparator.comparing(LocalDate::toEpochDay)).get();

		return ItenarySrConverter.generateDatesBetwenTwoCheckinDates(minDate, maxDate);

	}

	public static List<PackageItenaryGroupResponse> groupSrPackageDataByProductDate(
			PackageItenaryResponse itenaryResponse) {
		List<Object> productObjects = new ArrayList<>();

		itenaryResponse.getFlight().forEach(f -> productObjects.add(f));
		itenaryResponse.getHotel().forEach(h -> productObjects.add(h));
		itenaryResponse.getHospital().forEach(hs -> productObjects.add(hs));
		itenaryResponse.getAncillary().forEach(anx -> productObjects.add(anx));
		itenaryResponse.getAttractions().forEach(attr -> productObjects.add(attr));
		itenaryResponse.getGroundTranport().forEach(transport -> productObjects.add(transport));

		Map<LocalDate, List<Object>> groupProductsByDate = groupProductsByDate(productObjects);

		return convertPackageItenaryResponse(groupProductsByDate);
	}

	private static Map<LocalDate, List<Object>> groupProductsByDate(List<Object> productObjects) {
		return productObjects.stream().collect(Collectors.groupingBy(productItem -> {

			if (productItem instanceof FlightItenary) {
				return ((FlightItenary) productItem).getSegmentDepartDate();
			} else if (productItem instanceof HotelItenary) {
				return ((HotelItenary) productItem).getCheckInDate();
			} else if (productItem instanceof HospitalItenary) {
				return ((HospitalItenary) productItem).getCheckInDate();
			} else if (productItem instanceof AttractionItenary) {
				return ((AttractionItenary) productItem).getDate();
			} else if (productItem instanceof AnxItenary) {
				return ((AnxItenary) productItem).getDate();
			} else if (productItem instanceof GroundTransportItenary) {
				return ((GroundTransportItenary) productItem).getDate();
			} else {
				throw new IllegalArgumentException("Unknown productItem type: " + productItem.getClass());
			}

		}));
	}

	// Convert the grouped product items into the desired JSON format
	public static List<PackageItenaryGroupResponse> convertPackageItenaryResponse(
			Map<LocalDate, List<Object>> groupedProdcutItems) {

		List<PackageItenaryGroupResponse> itenaryResponse = new ArrayList<>();

		groupedProdcutItems.keySet().stream().forEach(key -> {
			List<FlightItenary> flight = new ArrayList<>();
			List<HotelItenary> hotel = new ArrayList<>();
			List<HospitalItenary> hospital = new ArrayList<>();
			List<AttractionItenary> attraction = new ArrayList<>();
			List<AnxItenary> ancillary = new ArrayList<>();
			List<GroundTransportItenary> groundTransportItenary = new ArrayList<>();

			groupedProdcutItems.get(key).stream().forEach(productItem -> {

				if (productItem instanceof FlightItenary) {
					flight.add(((FlightItenary) productItem));
				} else if (productItem instanceof HotelItenary) {
					hotel.add(((HotelItenary) productItem));
				} else if (productItem instanceof HospitalItenary) {
					hospital.add(((HospitalItenary) productItem));
				} else if (productItem instanceof AttractionItenary) {
					attraction.add(((AttractionItenary) productItem));
				} else if (productItem instanceof AnxItenary) {
					ancillary.add(((AnxItenary) productItem));
				} else if (productItem instanceof GroundTransportItenary) {
					groundTransportItenary.add(((GroundTransportItenary) productItem));
				} else {
					throw new IllegalArgumentException("Unknown productItem type: " + productItem.getClass());
				}
			});

			PackageItenaryGroupResponse eachKey = new PackageItenaryGroupResponse();

			eachKey.setDate(key);
			eachKey.setAncillary(ancillary);
			eachKey.setAttractions(attraction);
			eachKey.setHotel(hotel);
			eachKey.setHospital(hospital);
			eachKey.setFlight(flight);
			eachKey.setGroundTransport(groundTransportItenary);
			itenaryResponse.add(eachKey);

		});

		return itenaryResponse;

	}

	public static List<PackageItenaryGroupResponse> generateFinalSrPackageData(List<PackageItenaryGroupResponse> srData,
			List<LocalDate> datesList) {

		datesList.stream().filter(datePredicate -> srData.stream().noneMatch(p -> p.getDate().equals(datePredicate)))
				.forEach(date -> srData.add(createEmptyItenary(date)));

		return srData;
	}

	private static PackageItenaryGroupResponse createEmptyItenary(LocalDate date) {
		PackageItenaryGroupResponse emptyItenary = new PackageItenaryGroupResponse();

		emptyItenary.setDate(date);
		emptyItenary.setAncillary(Collections.emptyList());
		emptyItenary.setAttractions(Collections.emptyList());
		emptyItenary.setFlight(Collections.emptyList());
		emptyItenary.setHotel(Collections.emptyList());
		emptyItenary.setHospital(Collections.emptyList());
		emptyItenary.setGroundTransport(Collections.emptyList());
		return emptyItenary;
	}
	public static void configureHotels(List<PackageItenaryGroupResponse> finalSrPackageResponse) {

		List<HotelItenary> collectedHotels = finalSrPackageResponse.stream()
				.filter(sr -> CollectionUtils.isNotEmpty(sr.getHotel())).map(PackageItenaryGroupResponse::getHotel)
				.flatMap(List::stream).distinct().collect(Collectors.toList());

		collectedHotels.stream().forEach(h -> {

			List<LocalDate> dates = ItenarySrConverter.generateDatesBetwenTwoCheckinDates(h.getCheckInDate(),
					h.getCheckOutDate());

			Predicate<PackageItenaryGroupResponse> datePredicate = p -> dates.contains(p.getDate());

			List<PackageItenaryGroupResponse> dateMatchedSr = finalSrPackageResponse.stream().filter(datePredicate)
					.collect(Collectors.toList());

			for (PackageItenaryGroupResponse sr : dateMatchedSr) {

				if (!sr.getHotel().contains(h)) {

					List<HotelItenary> collectedHotelInfo = Arrays.asList(List.of(h), sr.getHotel()).stream()
							.flatMap(List::stream).collect(Collectors.toList());
					sr.setHotel(collectedHotelInfo);
				}

			}
		});

	}

	public static void configureHospitals(List<PackageItenaryGroupResponse> finalSrPackageResponse) {

		List<HospitalItenary> collectedHospitals = finalSrPackageResponse.stream()
				.filter(sr -> CollectionUtils.isNotEmpty(sr.getHospital()))
				.map(PackageItenaryGroupResponse::getHospital).flatMap(List::stream).distinct()
				.collect(Collectors.toList());

		collectedHospitals.stream().forEach(h -> {

			List<LocalDate> dates = ItenarySrConverter.generateDatesBetwenTwoCheckinDates(h.getCheckInDate(),
					h.getCheckOutDate());

			Predicate<PackageItenaryGroupResponse> datePredicate = p -> dates.contains(p.getDate());

			List<PackageItenaryGroupResponse> dateMatchedSr = finalSrPackageResponse.stream().filter(datePredicate)
					.collect(Collectors.toList());

			for (PackageItenaryGroupResponse sr : dateMatchedSr) {

				if (!sr.getHospital().contains(h)) {

					List<HospitalItenary> collectedHospitalInfo = Arrays.asList(List.of(h), sr.getHospital()).stream()
							.flatMap(List::stream).collect(Collectors.toList());
					sr.setHospital(collectedHospitalInfo);
				}

			}
		});

	}
}

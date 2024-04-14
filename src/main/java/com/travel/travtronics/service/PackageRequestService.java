package com.travel.travtronics.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travel.travtronics.request.GroundTransportRequest;
import com.travel.travtronics.request.HolidayRequest;
import com.travel.travtronics.request.dto.PackageProductDTO;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.HolidayResponse;
import com.travel.travtronics.response.dto.PackageDetailsResponse;
import com.travel.travtronics.response.dto.SrAttractionsResponse;
import com.travel.travtronics.srm.dao.AnxRequestDao;
import com.travel.travtronics.srm.dao.AttrRequestDao;
import com.travel.travtronics.srm.dao.FlightRequestDao;
import com.travel.travtronics.srm.dao.GTRequestDao;
import com.travel.travtronics.srm.dao.HospitalRequestDao;
import com.travel.travtronics.srm.dao.HotelRequestDao;
import com.travel.travtronics.srm.dao.PackageRequestDao;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PackageRequestService {

	@Autowired
	PackageRequestDao packageRequestDao;

	@Autowired
	AnxRequestDao anxRequestDao;

	@Autowired
	AttrRequestDao attrRequestDao;

	@Autowired
	FlightRequestDao flightRequestDao;

	@Autowired
	GTRequestDao gTRequestDao;

	@Autowired
	HospitalRequestDao hospitalRequestDao;

	@Autowired
	HotelRequestDao hotelRequestDao;

	public ResponseEntity<?> createPackageRequest(@Valid HolidayRequest model) {

		ArrayList<HolidayResponse> holidayResponseInfo = packageRequestDao.packageRequest(model);

		return ResponseEntity.status(HttpStatus.CREATED).body(holidayResponseInfo);
	}

	public ResponseEntity<?> getPackageRequest(Long requestId) throws Exception {

		List<HolidayResponse> packageRequestData = packageRequestDao.packageRequest(requestId);

		return ResponseEntity.status(HttpStatus.OK).body(packageRequestData);

	}

	public ResponseEntity<?> getPackageItenaryInfo(Long requestId) throws Exception {

		Map<String, Object> packageItenaryData = packageRequestDao.packageItenaryData(requestId);

		return ResponseEntity.ok(new APIResponse(HttpStatus.OK.value(), "package itenary details",
				Collections.singletonList(packageItenaryData)));
	}

	public ResponseEntity<?> savePackageRequestData(Long srId, Long loggedInUserId, String authorizationHeader)
			throws Exception {

		PackageProductDTO confirmedPackageItenaryData = packageRequestDao.confirmPackageItenaryData(srId,
				loggedInUserId);
		Map<String, Object> savePackageRequestData = savePackageRequestData(confirmedPackageItenaryData,
				authorizationHeader);
		return ResponseEntity
				.ok(new APIResponse(HttpStatus.OK.value(), "package request deatils", List.of(savePackageRequestData)));
	}

	private Map<String, Object> savePackageRequestData(PackageProductDTO confirmedPackageItenaryData,
			String authorizationHeader) {
		/*
		 * flight request saving
		 */
		Map<String, Object> responseData = new LinkedHashMap<>();

		if (confirmedPackageItenaryData.getFlight() != null && confirmedPackageItenaryData.getFlight().size() > 0) {

			List<Map<String, Object>> flightData = new ArrayList<Map<String, Object>>();
			confirmedPackageItenaryData.getFlight().stream().forEach(flight -> {
				try {
					Map<String, Object> flightRequestFromPackage = flightRequestDao.flightRequestFromPackage(flight,
							authorizationHeader);

					flightData.add(flightRequestFromPackage);
					// responseData.put("flight", flightRequestFromPackage);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			responseData.put("flight", flightData);
		}

		/*
		 * hotel request saving
		 */

		if (confirmedPackageItenaryData.getHotel() != null && confirmedPackageItenaryData.getHotel().size() > 0) {
			List<Map<String, Object>> hotelData = new ArrayList<Map<String, Object>>();
			confirmedPackageItenaryData.getHotel().stream().forEach(hotel -> {
				try {
					Map<String, Object> hotelRequestFromPackage = hotelRequestDao.hotelRequestFromPackage(hotel,
							authorizationHeader);
					hotelData.add(hotelRequestFromPackage);
				} catch (Exception e) {

					e.printStackTrace();
				}
			});
			responseData.put("hotel", hotelData);
		}

		/*
		 * ancillary request saving
		 */

		if (confirmedPackageItenaryData.getAnx() != null && confirmedPackageItenaryData.getAnx().size() > 0) {
			List<Map<String, Object>> anxData = new ArrayList<Map<String, Object>>();
			confirmedPackageItenaryData.getAnx().stream().forEach(ancillary -> {

				try {
					Map<String, Object> anxRequestFromPackage = anxRequestDao.anxRequestFromPackage(ancillary,
							authorizationHeader);
					anxData.add(anxRequestFromPackage);
					responseData.put("ancillary", anxData);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			responseData.put("ancillary", anxData);
		}

		/*
		 * attraction request saving
		 */

		if (confirmedPackageItenaryData.getAttraction() != null
				&& confirmedPackageItenaryData.getAttraction().size() > 0) {
			List<SrAttractionsResponse> attrData = new ArrayList<SrAttractionsResponse>();
			confirmedPackageItenaryData.getAttraction().stream().forEach(attraction -> {

				try {
					SrAttractionsResponse attrRequestFromPackage = attrRequestDao.attrRequestFromPackage(attraction,
							authorizationHeader);

					attrData.add(attrRequestFromPackage);
				} catch (Exception e) {

					e.printStackTrace();
				}
			});
			responseData.put("attraction", attrData);
		}

		/*
		 * hospial request saving
		 */
		if (confirmedPackageItenaryData.getHospital() != null && confirmedPackageItenaryData.getHospital().size() > 0) {

			List<Map<String, Object>> hospitalData = new ArrayList<Map<String, Object>>();

			confirmedPackageItenaryData.getHospital().stream().forEach(hospital -> {
				try {
					Map<String, Object> hospitalRequestFromPackage = hospitalRequestDao
							.hospitalRequestFromPackage(hospital, authorizationHeader);

					hospitalData.add(hospitalRequestFromPackage);

				} catch (Exception e) {

					e.printStackTrace();
				}
			});
			responseData.put("hospital", hospitalData);
		}

		/*
		 * ground transport request saving
		 */

		if (confirmedPackageItenaryData.getGroundTransport() != null
				&& confirmedPackageItenaryData.getGroundTransport().size() > 0) {

			List<GroundTransportRequest> gtRequestFromPackage = gTRequestDao
					.gtRequestFromPackage(confirmedPackageItenaryData.getGroundTransport());

			responseData.put("grounndTransport", gtRequestFromPackage);

		}
		return responseData;
	}

}

package com.travel.travtronics.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.travel.travtronics.packages.converter.PkgBookingsConverter;
import com.travel.travtronics.pkgbookings.model.BookingPackageScheduleModel;
import com.travel.travtronics.pkgbookings.model.BookingPackageSchedulePriceModel;
import com.travel.travtronics.pkgbookings.model.BookingPassengersModel;
import com.travel.travtronics.pkgbookings.model.BookingsModel;
import com.travel.travtronics.pkgbookings.repository.BookingPackageSchedulePriceRepository;
import com.travel.travtronics.pkgbookings.repository.BookingPackageScheduleRepository;
import com.travel.travtronics.pkgbookings.repository.BookingPassengersRepository;
import com.travel.travtronics.pkgbookings.repository.BookingsRepository;
import com.travel.travtronics.pkgbookings.request.AddBookingRequest;
import com.travel.travtronics.pkgbookings.response.PkgBookingResponse;
import com.travel.travtronics.response.APIResponse;

@Service
public class PackageBookingsService {
	
	ZonedDateTime instance = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	String currentDateTime = formatter.format(instance); // 15-02-2022 12:43

	DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	String currentDate = formatter2.format(instance); // 15-02-2022
	
	@Autowired
	ProcedureService procedureService;
	
	@Autowired
	BookingsRepository bookingsRepository;
	
	@Autowired
	BookingPackageSchedulePriceRepository bookingPackageSchedulePriceRepository;
	
	@Autowired
	BookingPackageScheduleRepository bookingPackageScheduleRepository;
	
	@Autowired
	BookingPassengersRepository bookingPassengersRepository;
	
	@Transactional(rollbackFor = Exception.class)
	public APIResponse createPackageBooking(AddBookingRequest requestData) {
		
		PkgBookingResponse bkReponse = new PkgBookingResponse();
		
		if(requestData == null) {
			return new APIResponse(HttpStatus.BAD_REQUEST.value(), "Failed, Invalid Request data.",
					Collections.emptyList());
		}		
		try {
			
			bkReponse = createPackageBookingProcess(requestData);
			
			if(bkReponse.getBookingId() != null && bkReponse.getBookingId() > 0) {	
				
				return new APIResponse(HttpStatus.OK.value(), "Successfully booking created.", Collections.singletonList(bkReponse));
				
			}else {
				return new APIResponse(HttpStatus.BAD_REQUEST.value(), "Failed, Booking not created.", Collections.emptyList());
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to Create Booking.", Collections.emptyList());
		}
		
	}

	@Transactional(rollbackFor = Exception.class)
	public PkgBookingResponse createPackageBookingProcess(AddBookingRequest requestData) {

		PkgBookingResponse bkReponse = new PkgBookingResponse();

		try {
			BookingsModel bkModelData = PkgBookingsConverter.convertAddBookingRequestToBookingModel(requestData);
	
			if (bkModelData.getBookingReferenceNumber() == null || bkModelData.getBookingReferenceNumber().isEmpty()) {
	
				String pkgRefrence = "TTPKGOF" + DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDate.now());
				String bookingReferenceNumber = procedureService.generateProductSequenceNumber("PKG", pkgRefrence, 4);
				bkModelData.setBookingReferenceNumber(bookingReferenceNumber);
			}
	
			BookingsModel bookingData = bookingsRepository.save(bkModelData);
	
			if (bookingData != null && bookingData.getBookingId() > 0) {
	
				BeanUtils.copyProperties(bookingData, bkReponse);
	
				Integer addedBy = requestData.getAddedBy() != null ? requestData.getAddedBy() : 0;
				String device = requestData.getAddedDevice() != null ? requestData.getAddedDevice() : "";
				String ip = requestData.getAddedIp() != null ? requestData.getAddedIp() : "";
				Long bkId = bookingData.getBookingId();
	
				BookingPackageScheduleModel thisPkgSchd = PkgBookingsConverter
						.convertPkgSchedulesRequestToBookingPackageScheduleModel(requestData.getPkgSchedules());
				thisPkgSchd.setBookingId(bkId);
				thisPkgSchd.setAddedBy(addedBy);
				thisPkgSchd.setAddedDevice(device);
				thisPkgSchd.setAddedIp(ip);
				thisPkgSchd.setAddedDate(currentDate);
				
				if (thisPkgSchd != null && thisPkgSchd.getBookingId() > 0) {
					BookingPackageScheduleModel savedPkgSchedules = bookingPackageScheduleRepository.save(thisPkgSchd);
					bkReponse.setPkgSchedules(savedPkgSchedules);
				}
	
				List<BookingPackageSchedulePriceModel> pkgSchedulesPrices = new ArrayList<BookingPackageSchedulePriceModel>();
	
				pkgSchedulesPrices = requestData.getPkgSchedulePrice().stream().map(pkgSchPrice -> {
	
					BookingPackageSchedulePriceModel pkgSchdsPrice = PkgBookingsConverter
							.convertPkgSchedulePriceRequestToBookingPackageSchedulePriceModel(pkgSchPrice);
					pkgSchdsPrice.setBookingId(bkId);
					pkgSchdsPrice.setAddedBy(addedBy);
					pkgSchdsPrice.setAddedDevice(device);
					pkgSchdsPrice.setAddedIp(ip);
					pkgSchdsPrice.setAddedDate(currentDate);
	
					return pkgSchdsPrice;
	
				}).collect(Collectors.toList());
				if (pkgSchedulesPrices != null && pkgSchedulesPrices.size() > 0) {
					List<BookingPackageSchedulePriceModel> savedPkgSchedulesPrices = bookingPackageSchedulePriceRepository
							.saveAll(pkgSchedulesPrices);
					if (savedPkgSchedulesPrices.size() > 0) {
						bkReponse.setPkgSchedulePrice(savedPkgSchedulesPrices);
					}
				}
	
				List<BookingPassengersModel> pkgBkPaxes = new ArrayList<BookingPassengersModel>();
	
				pkgBkPaxes = requestData.getPkgBookingPaxs().stream().map(pkgBkPax -> {
	
					BookingPassengersModel thisPax = PkgBookingsConverter
							.convertPkgBookingPaxRequestToBookingPassengersModel(pkgBkPax);
					thisPax.setBookingId(bkId);
					thisPax.setAddedBy(addedBy);
					thisPax.setAddedDevice(device);
					thisPax.setAddedIp(ip);
					thisPax.setAddedDate(currentDate);
	
					return thisPax;
	
				}).collect(Collectors.toList());
				if (pkgBkPaxes != null && pkgBkPaxes.size() > 0) {
					List<BookingPassengersModel> savedPkgBkPaxes = bookingPassengersRepository.saveAll(pkgBkPaxes);
					if (savedPkgBkPaxes.size() > 0) {
						bkReponse.setPkgBookingPaxs(savedPkgBkPaxes);
					}
				}
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return null;
		}

		return bkReponse;
	}

	public APIResponse getPackageBookingInfo(Long bookingId) {
		
		PkgBookingResponse bkReponse = new PkgBookingResponse();
		
		Optional<BookingsModel> bookingInfo = bookingsRepository.findById(bookingId);
		if(bookingInfo.isPresent()) {
			
			BeanUtils.copyProperties(bookingInfo.get(), bkReponse);
			
			BookingPackageScheduleModel bookingschedules = bookingPackageScheduleRepository.findByBookingId(bookingId);
			
			bkReponse.setPkgSchedules(bookingschedules);
			
			List<BookingPackageSchedulePriceModel> bookingschedulePrice = bookingPackageSchedulePriceRepository.findByBookingId(bookingId);
			
			bkReponse.setPkgSchedulePrice(bookingschedulePrice);
			
			
			List<BookingPassengersModel> bookingPaxs = bookingPassengersRepository.findByBookingId(bookingId);
			
			bkReponse.setPkgBookingPaxs(bookingPaxs);
			
			return new APIResponse(HttpStatus.OK.value(), "Booking details.", Collections.singletonList(bkReponse));
			
		}else {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), "Not found any booking with provided id.", Collections.emptyList());
		}
	}

}

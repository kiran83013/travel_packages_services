package com.travel.travtronics.service;

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

import com.travel.travtronics.converter.PkgsPriceSavingConverter;
import com.travel.travtronics.request.PkgHotelSupplierInfoRequest;
import com.travel.travtronics.request.PkgPriceHeaderMappingRequest;
import com.travel.travtronics.request.PkgPriceHotelRequest;
import com.travel.travtronics.request.PkgScheduleHeaderLineRequest;
import com.travel.travtronics.request.PkgSchedulePriceFlightRequest;
import com.travel.travtronics.request.PkgScheduleSegmentFlightRequest;
import com.travel.travtronics.request.PkgSubHotelRequest;
import com.travel.travtronics.request.PkgSupplierFlightRequest;
import com.travel.travtronics.request.PkgSupplierHotelRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.FlightSegmentsPriceDataReponse;
import com.travel.travtronics.response.PkgAllPriceOptionsResponse;
import com.travel.travtronics.response.PkgAllPriceOptionsResponse.FlightPriceOptions;
import com.travel.travtronics.response.PkgAllPriceOptionsResponse.HotelPriceOptions;
import com.travel.travtronics.response.PkgHeaderLinesResponse;
import com.travel.travtronics.response.PkgScheduleHeaderHotelReponse;
import com.travel.travtronics.response.PkgScheduleHeaderLinesResponse;
import com.travel.travtronics.response.PkgSchedulePriceFlightResponse;
import com.travel.travtronics.response.PkgSchedulePriceHotelResponse;
import com.travel.travtronics.response.PkgScheduleSegmentFlightResponse;
import com.travel.travtronics.response.PkgScheduleSubHotelResponse;
import com.travel.travtronics.response.PkgScheduleSupplierFlightResponse;
import com.travel.travtronics.response.PkgScheduleSupplierHotelResponse;
import com.travel.travtronics.srm.model.PackageScheduleHeaderLineModel;
import com.travel.travtronics.srm.model.PackageScheduleHeaderModel;
import com.travel.travtronics.srm.model.PackageSchedulePriceFlightModel;
import com.travel.travtronics.srm.model.PackageSchedulePriceHotelModel;
import com.travel.travtronics.srm.model.PackageScheduleSegmentFlightModel;
import com.travel.travtronics.srm.model.PackageScheduleSubHotelModel;
import com.travel.travtronics.srm.model.PackageScheduleSupplierFlightModel;
import com.travel.travtronics.srm.model.PackageScheduleSupplierHotelModel;
import com.travel.travtronics.srm.repository.PackageScheduleHeaderLineRepository;
import com.travel.travtronics.srm.repository.PackageScheduleHeaderRepository;
import com.travel.travtronics.srm.repository.PackageSchedulePriceFlightRepository;
import com.travel.travtronics.srm.repository.PackageSchedulePriceHotelRepository;
import com.travel.travtronics.srm.repository.PackageScheduleSegmentFlightRepository;
import com.travel.travtronics.srm.repository.PackageScheduleSubHotelRepository;
import com.travel.travtronics.srm.repository.PackageScheduleSupplierFlightRepository;
import com.travel.travtronics.srm.repository.PackageScheduleSupplierHotelRepository;

@Service
public class PackagePriceConfigServcie {
	
	ZonedDateTime instance = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	String currentDateTime = formatter.format(instance); // 15-02-2022 12:43

	DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	String currentDate = formatter2.format(instance); // 15-02-2022
	
	@Autowired
	PackageScheduleHeaderRepository pkgScheduleHeaderRepository;
	
	@Autowired
	PackageScheduleHeaderLineRepository pkgScheduleHeaderLineRepository;
	
	@Autowired
	PackageScheduleSupplierHotelRepository pkgScheduleSupplierHotelRepository;
	
	@Autowired
	PackageScheduleSubHotelRepository pkgScheduleSubHotelRepository;
	
	@Autowired
	PackageSchedulePriceHotelRepository pkgSchedulePriceHotelRepository;
	
	@Autowired
	PackageScheduleSupplierFlightRepository PkgScheduleSupplierFlightRepository;
	
	@Autowired
	PackageScheduleSegmentFlightRepository PkgScheduleSegmentFlightRepository;
	
	@Autowired
	PackageSchedulePriceFlightRepository PkgSchedulePriceFlightRepository;
	
	public APIResponse saveHotelPackagePriceData(List<PkgSupplierHotelRequest> requestData) {
		
	    if (requestData == null || requestData.isEmpty()) {
	        return new APIResponse(HttpStatus.BAD_REQUEST.value(), "Failed, Invalid data.", Collections.emptyList());
	    }

	    try {
	        List<PackageScheduleSupplierHotelModel> suppliersData = requestData.stream()
	                .map(PkgsPriceSavingConverter::convertHotelSplrRequestToHtlSplrModel)
	                .collect(Collectors.toList());
	        List<PackageScheduleSupplierHotelModel> savedSplrs = pkgScheduleSupplierHotelRepository.saveAll(suppliersData);

	        List<PkgSupplierHotelRequest> responseData = new ArrayList<PkgSupplierHotelRequest>();
	        
	        for (int i = 0; i < suppliersData.size(); i++) {
	        	PackageScheduleSupplierHotelModel savedSplr = savedSplrs.get(i);
	        	PkgSupplierHotelRequest requestSplr = requestData.get(i);
	        	requestSplr.setId(savedSplr.getId());
	        	
	        	for (int k = 0; k < requestSplr.getSupbkgprcoption().size(); k++) {
                    PkgSubHotelRequest requestSubHotel = requestSplr.getSupbkgprcoption().get(k);
                    PackageScheduleSubHotelModel subHotelModel = PkgsPriceSavingConverter.convertSubHotelRequestToSubHtlModel(savedSplr.getId(), requestSubHotel);
                    PackageScheduleSubHotelModel savedSubHotel = pkgScheduleSubHotelRepository.save(subHotelModel);
                    requestSubHotel.setId(savedSubHotel.getId());
                    requestSubHotel.setOptionId(savedSplr.getId());

                    for (int l = 0; l < requestSubHotel.getSupbkgprcoptionprice().size(); l++) {
                        PkgPriceHotelRequest requestPrice = requestSubHotel.getSupbkgprcoptionprice().get(l);
                        PackageSchedulePriceHotelModel priceModel = PkgsPriceSavingConverter.convertSubHotelRequestToSubHtlModel(savedSubHotel.getId(), requestPrice);
                        PackageSchedulePriceHotelModel savedPrice = pkgSchedulePriceHotelRepository.save(priceModel);
                        requestPrice.setId(savedPrice.getId());
                        requestPrice.setSubOptionId(savedSubHotel.getId());
                    }
                }
	        	responseData.add(requestSplr);
	        }

	        return new APIResponse(HttpStatus.OK.value(), "Hotel Package price data.", responseData);
	    } catch (Exception e) {
	        return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to save data.", Collections.emptyList());
	    }
	}
	
	public APIResponse getHotelPackagePriceDataByHeaderId(Long splrId) {
		
	    Optional<PackageScheduleSupplierHotelModel> splrDataOptional = pkgScheduleSupplierHotelRepository.findById(splrId);
	    if (splrDataOptional.isEmpty()) {
	        return new APIResponse(HttpStatus.NOT_FOUND.value(), "Data not found", null);
	    }

	    PackageScheduleSupplierHotelModel supplier = splrDataOptional.get();
	    
	    PkgScheduleSupplierHotelResponse supplierResponse = new PkgScheduleSupplierHotelResponse();
	    
	    BeanUtils.copyProperties(supplier, supplierResponse);

	    List<PackageScheduleSubHotelModel> subHotels = pkgScheduleSubHotelRepository.findByOptionId(supplier.getId());
	    
	    List<PkgScheduleSubHotelResponse> subHotelResponses = new ArrayList<>();
	    
	    for (PackageScheduleSubHotelModel subHotel : subHotels) {
	        PkgScheduleSubHotelResponse subHotelResponse = new PkgScheduleSubHotelResponse();
	        BeanUtils.copyProperties(subHotel, subHotelResponse);

	        List<PackageSchedulePriceHotelModel> prices = pkgSchedulePriceHotelRepository.findBySubOptionId(subHotel.getId());
	        List<PkgSchedulePriceHotelResponse> priceResponses = new ArrayList<>();
	        for (PackageSchedulePriceHotelModel price : prices) {
	            PkgSchedulePriceHotelResponse priceResponse = new PkgSchedulePriceHotelResponse();
	            BeanUtils.copyProperties(price, priceResponse);
	            priceResponses.add(priceResponse);
	        }

	        subHotelResponse.setSupbkgprcoptionprice(priceResponses);
	        subHotelResponses.add(subHotelResponse);
	    }

	    supplierResponse.setSupbkgprcoption(subHotelResponses);

	    return new APIResponse(HttpStatus.OK.value(), "Hotel Package price data.", Collections.singletonList(supplierResponse));
	}

	public APIResponse packagePriceHeaderMapping(List<PkgPriceHeaderMappingRequest> requestData) {
		
		if (requestData == null || requestData.isEmpty()) {
	        return new APIResponse(HttpStatus.BAD_REQUEST.value(), "Failed, Invalid data.", Collections.emptyList());
	    }
		
		try {
	        List<PackageScheduleHeaderModel> headersData = requestData.stream()
	                .map(PkgsPriceSavingConverter::convertHotelPkgHeaderRequestToHtlHModel)
	                .collect(Collectors.toList());
	        List<PackageScheduleHeaderModel> headersSavedData = pkgScheduleHeaderRepository.saveAll(headersData);

	        List<PkgPriceHeaderMappingRequest> responseData = new ArrayList<PkgPriceHeaderMappingRequest>();
	        
	        for (int i = 0; i < headersSavedData.size(); i++) {
	        	PackageScheduleHeaderModel savedHeaderInfo = headersSavedData.get(i);
	        	PkgPriceHeaderMappingRequest requestHeader = requestData.get(i);
	        	requestHeader.setId(savedHeaderInfo.getId());
	        	
	        	for (int k = 0; k < requestHeader.getHeaderLines().size(); k++) {
	        		
	        		PkgScheduleHeaderLineRequest requestHeaderLine = requestHeader.getHeaderLines().get(k);
	        		System.out.println("step-1: "+requestHeaderLine.getLineGroupId()+" -- "+requestHeaderLine.getLineOptionId());
	        		PackageScheduleHeaderLineModel lineModel = PkgsPriceSavingConverter.convertHeadlineRequestToHeaderLinesModel(savedHeaderInfo.getId(), requestHeaderLine, 
	        				requestHeader.getAddedBy(), requestHeader.getAddedDeviceInfo(), requestHeader.getAddedIp());
	        		PackageScheduleHeaderLineModel savedLine = pkgScheduleHeaderLineRepository.save(lineModel);
	        		System.out.println("step-3: "+savedLine.getLineGroupId()+" -- "+savedLine.getLineOptionId());
	        		requestHeaderLine.setId(savedLine.getId());
	        		requestHeaderLine.setHeaderId(savedHeaderInfo.getId());
                }
	        	responseData.add(requestHeader);
	        }

	        return new APIResponse(HttpStatus.OK.value(), "Hotel Package price data.", responseData);
	    } catch (Exception e) {
	        return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to save data.", Collections.emptyList());
	    }
	}
	
	public APIResponse getPackagePriceHeaderMappingData(Long headerId) {
		
		Optional<PackageScheduleHeaderModel> headerDataOptional = pkgScheduleHeaderRepository.findById(headerId);
	    if (headerDataOptional.isEmpty()) {
	        return new APIResponse(HttpStatus.NOT_FOUND.value(), "Data not found", null);
	    }
	    
	    PackageScheduleHeaderModel header = headerDataOptional.get();
	    
	    PkgScheduleHeaderHotelReponse finalResponse = new PkgScheduleHeaderHotelReponse();
	    
	    BeanUtils.copyProperties(header, finalResponse);

	    List<PackageScheduleHeaderLineModel> linesData = pkgScheduleHeaderLineRepository.findByHeaderId(header.getId());
	    
        List<PkgScheduleHeaderLinesResponse> linesResponses = new ArrayList<>();
        
        for (PackageScheduleHeaderLineModel line : linesData) {
        	
        	PkgScheduleHeaderLinesResponse lineResponse = new PkgScheduleHeaderLinesResponse();
            BeanUtils.copyProperties(line, lineResponse);
            linesResponses.add(lineResponse);
        }
	    finalResponse.setHeaderLines(linesResponses);

	    return new APIResponse(HttpStatus.OK.value(), "Package header and lines data.", Collections.singletonList(finalResponse));
	    
	}
	
	public APIResponse saveFlightPackagePriceData(List<PkgSupplierFlightRequest> requestData) {
		
		if (requestData == null || requestData.isEmpty()) {
	        return new APIResponse(HttpStatus.BAD_REQUEST.value(), "Failed, Invalid data.", Collections.emptyList());
	    }

	    try {
	        List<PackageScheduleSupplierFlightModel> suppliersData = requestData.stream()
	                .map(PkgsPriceSavingConverter::convertFlightSplrRequestToFltSplrModel)
	                .collect(Collectors.toList());
	        List<PackageScheduleSupplierFlightModel> savedSplrs = PkgScheduleSupplierFlightRepository.saveAll(suppliersData);

	        List<PkgSupplierFlightRequest> responseData = new ArrayList<PkgSupplierFlightRequest>();
	        
	        for (int i = 0; i < suppliersData.size(); i++) {
	        	PackageScheduleSupplierFlightModel savedSplr = savedSplrs.get(i);
	        	PkgSupplierFlightRequest requestSplr = requestData.get(i);
	        	requestSplr.setId(savedSplr.getId());
	        	
	        	for (int k = 0; k < requestSplr.getSegmentsData().size(); k++) {
	        		PkgScheduleSegmentFlightRequest requestSegs = requestSplr.getSegmentsData().get(k);
	        		PackageScheduleSegmentFlightModel segmentsModel = PkgsPriceSavingConverter.convertSegmentsRequestToSegmentsModel(savedSplr.getId(), requestSegs, 
	        				requestSplr.getAddedBy(), requestSplr.getAddedDevice(), requestSplr.getAddedIp());
	        		PackageScheduleSegmentFlightModel savedsegs = PkgScheduleSegmentFlightRepository.save(segmentsModel);
	        		requestSegs.setId(savedsegs.getId());
	        		requestSegs.setSupplierFlightId(savedSplr.getId());
                }
	        	for (int l = 0; l < requestSplr.getPriceLines().size(); l++) {
                	PkgSchedulePriceFlightRequest requestPrice = requestSplr.getPriceLines().get(l);
                    PackageSchedulePriceFlightModel priceModel = PkgsPriceSavingConverter.convertFlightPriceRequestToFlightPriceModel(savedSplr.getId(), requestPrice,
                    		requestSplr.getAddedBy(), requestSplr.getAddedDevice(), requestSplr.getAddedIp());
                    PackageSchedulePriceFlightModel savedPrice = PkgSchedulePriceFlightRepository.save(priceModel);
                    requestPrice.setId(savedPrice.getId());
                    requestPrice.setSupplierFlightId(savedSplr.getId());
                }
	        	responseData.add(requestSplr);
	        }

	        return new APIResponse(HttpStatus.OK.value(), "Flight Package price data.", responseData);
	    } catch (Exception e) {
	        return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to save data.", Collections.emptyList());
	    }
	}

	public APIResponse getFlightPackagePriceDataByHeaderId(Long splrHeaderId) {
		
		Optional<PackageScheduleSupplierFlightModel> splrDataOptional = PkgScheduleSupplierFlightRepository.findById(splrHeaderId);
	    if (splrDataOptional.isEmpty()) {
	        return new APIResponse(HttpStatus.NOT_FOUND.value(), "Data not found", null);
	    }

	    PackageScheduleSupplierFlightModel supplier = splrDataOptional.get();
	    
	    PkgScheduleSupplierFlightResponse supplierResponse = new PkgScheduleSupplierFlightResponse();	    
	    BeanUtils.copyProperties(supplier, supplierResponse);

	    FlightSegmentsPriceDataReponse segPriceData = flightSegmentsPriceBySplrId(supplier.getId());
	    
	    if(segPriceData.getSegmentsData() != null) {
	    	supplierResponse.setSegmentsData(segPriceData.getSegmentsData());
	    }
	    if(segPriceData.getPriceLines() != null) {
	    	supplierResponse.setPriceLines(segPriceData.getPriceLines());
	    };

	    return new APIResponse(HttpStatus.OK.value(), "Flight Package price data.", Collections.singletonList(supplierResponse));
	}
	
	public APIResponse getPkgAllPriceOptions(Long scheduleId) {
		
		PkgAllPriceOptionsResponse allPriceLinesData = new PkgAllPriceOptionsResponse();
		
		List<PackageScheduleSupplierFlightModel> flightSplrs = PkgScheduleSupplierFlightRepository.getSplrPriceOptionsListBySchdId(scheduleId);
		
		if(flightSplrs.size() > 0) {
			
			List<FlightPriceOptions> flightPrices = new ArrayList<PkgAllPriceOptionsResponse.FlightPriceOptions>();
			flightPrices = flightSplrs.stream().map(supplier -> {
				
				FlightPriceOptions thisPrice = new FlightPriceOptions();
				thisPrice.setSrId(supplier.getServiceRequestId());
				thisPrice.setSrLineId(supplier.getServiceRequestLineId());
				List<PkgScheduleSupplierFlightResponse> splrPrices = getFlightPriceOptionsData(scheduleId, supplier.getServiceRequestId(), 
						supplier.getServiceRequestLineId(), true);
				thisPrice.setOptions(splrPrices);				
				return thisPrice;				
				
			}).collect(Collectors.toList());	
			
			allPriceLinesData.setFlight(flightPrices);
		}
		
		List<PackageScheduleSupplierHotelModel> hotelSplrs = pkgScheduleSupplierHotelRepository.getSplrPriceOptionsListBySchdId(scheduleId);
		if(hotelSplrs.size() > 0) {
			
			List<HotelPriceOptions> hotelPrices = new ArrayList<PkgAllPriceOptionsResponse.HotelPriceOptions>();
			hotelPrices = hotelSplrs.stream().map(supplier -> {
				
				HotelPriceOptions thisPrice = new HotelPriceOptions();
				thisPrice.setSrId(supplier.getServiceRequestId());
				thisPrice.setSrLineId(supplier.getServiceRequestLineId());
				List<PkgScheduleSupplierHotelResponse> splrPrices = getHotelPriceOptionsData(scheduleId, supplier.getServiceRequestId(), 
						supplier.getServiceRequestLineId(), true);
				thisPrice.setOptions(splrPrices);				
				return thisPrice;				
				
			}).collect(Collectors.toList());
			
			allPriceLinesData.setHotels(hotelPrices);
		}
		
		return new APIResponse(HttpStatus.OK.value(), "Package price data.", Collections.singletonList(allPriceLinesData));
	}
	
	public APIResponse getPkgAllPriceOptionsBySrSrLine(Long scheduleId, Long srId, Long srLineId) {
		
		List<FlightPriceOptions> flightPrices = new ArrayList<PkgAllPriceOptionsResponse.FlightPriceOptions>();
		List<PackageScheduleSupplierFlightModel> flightSplrs = PkgScheduleSupplierFlightRepository.getSplrPriceOptionsListBySchdIdSrSrLine(scheduleId, srId, srLineId);		
		if(flightSplrs.size() > 0) {			
			flightPrices = flightSplrs.stream().map(supplier -> {
				
				FlightPriceOptions thisPrice = new FlightPriceOptions();
				thisPrice.setSrId(supplier.getServiceRequestId());
				thisPrice.setSrLineId(supplier.getServiceRequestLineId());
				List<PkgScheduleSupplierFlightResponse> splrPrices = getFlightPriceOptionsData(scheduleId, supplier.getServiceRequestId(), 
						supplier.getServiceRequestLineId(), true);
				thisPrice.setOptions(splrPrices);				
				return thisPrice;				
				
			}).collect(Collectors.toList());	
			
		}
		
		List<HotelPriceOptions> hotelPrices = new ArrayList<PkgAllPriceOptionsResponse.HotelPriceOptions>();
		List<PackageScheduleSupplierHotelModel> hotelSplrs = pkgScheduleSupplierHotelRepository.getSplrPriceOptionsListBySchdIdSrSrLine(scheduleId, srId, srLineId);
		if(hotelSplrs.size() > 0) {
			hotelPrices = hotelSplrs.stream().map(supplier -> {
				
				HotelPriceOptions thisPrice = new HotelPriceOptions();
				thisPrice.setSrId(supplier.getServiceRequestId());
				thisPrice.setSrLineId(supplier.getServiceRequestLineId());
				List<PkgScheduleSupplierHotelResponse> splrPrices = getHotelPriceOptionsData(scheduleId, supplier.getServiceRequestId(), 
						supplier.getServiceRequestLineId(), true);
				thisPrice.setOptions(splrPrices);				
				return thisPrice;				
				
			}).collect(Collectors.toList());
			
		}
		
		if(flightPrices.size() > 0) {
			return new APIResponse(HttpStatus.OK.value(), "Package price data.", flightPrices);
		}else if(hotelPrices.size() > 0) {
			return new APIResponse(HttpStatus.OK.value(), "Package price data.", hotelPrices);
		}else {
			return new APIResponse(HttpStatus.OK.value(), "No data found.", Collections.emptyList());
		}
	}
	
	private List<PkgScheduleSupplierFlightResponse> getFlightPriceOptionsData(Long scheduleId, Integer srId, Integer srLineId, Boolean priceDataFirstNode){
		
		List<PkgScheduleSupplierFlightResponse> priceList = new ArrayList<PkgScheduleSupplierFlightResponse>();		
		
		List<PackageScheduleSupplierFlightModel> splrDataList = PkgScheduleSupplierFlightRepository.getSplrPriceOptionsList(srId, srLineId, scheduleId);
		if (splrDataList != null && splrDataList.size() > 0) {

			final boolean[] isFirstNode = {true};
			
			priceList = splrDataList.stream().map(supplier -> {

				PkgScheduleSupplierFlightResponse supplierResponse = new PkgScheduleSupplierFlightResponse();	    
			    BeanUtils.copyProperties(supplier, supplierResponse);
			    
			    if (isFirstNode[0] && priceDataFirstNode) { //here will return data for first node only
				    FlightSegmentsPriceDataReponse segPriceData = flightSegmentsPriceBySplrId(supplier.getId());
				    
				    if(segPriceData.getSegmentsData() != null) {
				    	supplierResponse.setSegmentsData(segPriceData.getSegmentsData());
				    }
				    if(segPriceData.getPriceLines() != null) {
				    	supplierResponse.setPriceLines(segPriceData.getPriceLines());
				    }
				    isFirstNode[0] = false;
			    }
			    
				return supplierResponse;
			}).collect(Collectors.toList());
		}
	    
	    return priceList;
	}
	
	private FlightSegmentsPriceDataReponse flightSegmentsPriceBySplrId(Long supplierId) {
		
		FlightSegmentsPriceDataReponse segmentsPriceData = new FlightSegmentsPriceDataReponse();
		
		List<PackageScheduleSegmentFlightModel> segmentsList = PkgScheduleSegmentFlightRepository.findBySupplierFlightId(supplierId);	   
		if(segmentsList != null && segmentsList.size() > 0) {
		    List<PkgScheduleSegmentFlightResponse> segsResponses = new ArrayList<PkgScheduleSegmentFlightResponse>();	    
		    for (PackageScheduleSegmentFlightModel seg : segmentsList) {
		    	PkgScheduleSegmentFlightResponse thisSegResponse = new PkgScheduleSegmentFlightResponse();
		        BeanUtils.copyProperties(seg, thisSegResponse);
		        segsResponses.add(thisSegResponse);
		    }
		    segmentsPriceData.setSegmentsData(segsResponses);	
		}
	    
	    List<PackageSchedulePriceFlightModel> pricesList = PkgSchedulePriceFlightRepository.findBySupplierFlightId(supplierId);
	    if(pricesList != null && pricesList.size() > 0) {
	        List<PkgSchedulePriceFlightResponse> priceResponses = new ArrayList<>();
	        for (PackageSchedulePriceFlightModel price : pricesList) {
	        	PkgSchedulePriceFlightResponse priceResponse = new PkgSchedulePriceFlightResponse();
	            BeanUtils.copyProperties(price, priceResponse);
	            priceResponses.add(priceResponse);
	        }
	        segmentsPriceData.setPriceLines(priceResponses);
	    }
	    
	    return segmentsPriceData;
	    
	}
	
	private List<PkgScheduleSupplierHotelResponse> getHotelPriceOptionsData(Long scheduleId, Integer srId, Integer srLineId, Boolean priceDataFirstNode){
		
		List<PkgScheduleSupplierHotelResponse> priceList = new ArrayList<PkgScheduleSupplierHotelResponse>();		
		
		List<PackageScheduleSupplierHotelModel> splrDataList = pkgScheduleSupplierHotelRepository.getSplrPriceOptionsList(srId, srLineId, scheduleId);
		
		if (splrDataList != null && splrDataList.size() > 0) {

			final boolean[] isFirstNode = {true};
			
			priceList = splrDataList.stream().map(supplier -> {

				PkgScheduleSupplierHotelResponse supplierResponse = new PkgScheduleSupplierHotelResponse();

				BeanUtils.copyProperties(supplier, supplierResponse);
				
				if (isFirstNode[0] && priceDataFirstNode) { //here will return data for first node only
					
					List<PkgScheduleSubHotelResponse> subHotelResponses = hotelPriceLines(supplier.getId());
							
					supplierResponse.setSupbkgprcoption(subHotelResponses);
					
					isFirstNode[0] = false;
				}

				return supplierResponse;
				
			}).collect(Collectors.toList());
		}
		
		return priceList;
	}
	
	private List<PkgScheduleSubHotelResponse> hotelPriceLines(Long hotelSplrId) {
		
		List<PkgScheduleSubHotelResponse> subHotelPriceResponses = new ArrayList<>();
		
		List<PackageScheduleSubHotelModel> subHotels = pkgScheduleSubHotelRepository.findByOptionId(hotelSplrId);

		for (PackageScheduleSubHotelModel subHotel : subHotels) {
			PkgScheduleSubHotelResponse subHotelResponse = new PkgScheduleSubHotelResponse();
			BeanUtils.copyProperties(subHotel, subHotelResponse);

			List<PackageSchedulePriceHotelModel> prices = pkgSchedulePriceHotelRepository
					.findBySubOptionId(subHotel.getId());
			List<PkgSchedulePriceHotelResponse> priceResponses = new ArrayList<>();
			for (PackageSchedulePriceHotelModel price : prices) {
				PkgSchedulePriceHotelResponse priceResponse = new PkgSchedulePriceHotelResponse();
				BeanUtils.copyProperties(price, priceResponse);
				priceResponses.add(priceResponse);
			}

			subHotelResponse.setSupbkgprcoptionprice(priceResponses);
			subHotelPriceResponses.add(subHotelResponse);
		}
		
		return subHotelPriceResponses;
	}
	
	
	/************************* Individual Hotel Supplier and hotel pricing line saving and get services *********************/
	public APIResponse savePkgHotelSupplierData(List<PkgHotelSupplierInfoRequest> requestData) {
		
		if (requestData == null || requestData.isEmpty()) {
	        return new APIResponse(HttpStatus.BAD_REQUEST.value(), "Failed, Invalid data.", Collections.emptyList());
	    }
		
		List<PackageScheduleSupplierHotelModel> suppliersData = requestData.stream()
                .map(PkgsPriceSavingConverter::convertPkgHotelSplrRequestToHtlSplrModel)
                .collect(Collectors.toList());
		
		List<PackageScheduleSupplierHotelModel> savedSplrsData = pkgScheduleSupplierHotelRepository.saveAll(suppliersData);
		
		return new APIResponse(HttpStatus.OK.value(), "Hotel Package Supplier data.", savedSplrsData);
	}

	public APIResponse getPkgHotelSupplierData(Long id) {
		
		Optional<PackageScheduleSupplierHotelModel> hotelSplrData = pkgScheduleSupplierHotelRepository.findById(id);
		
		if(hotelSplrData.isPresent()) {
			return new APIResponse(HttpStatus.OK.value(), "Hotel Package Supplier data.", Collections.singletonList(hotelSplrData));
		}else {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), "No data found.", Collections.emptyList());
		}
	}

	public APIResponse savePkgHotelSupplierPricingData(Long supplierHotelId, List<PkgSubHotelRequest> requestData) {
		
		if (supplierHotelId == null || supplierHotelId < 0) {
	        return new APIResponse(HttpStatus.BAD_REQUEST.value(), "Failed, Invalid supplierHotelId data.", Collections.emptyList());
	    }
		
		if (requestData == null || requestData.isEmpty()) {
	        return new APIResponse(HttpStatus.BAD_REQUEST.value(), "Failed, Invalid data.", Collections.emptyList());
	    }
		
		Optional<PackageScheduleSupplierHotelModel> hotelSplrData = pkgScheduleSupplierHotelRepository.findById(supplierHotelId);
		
		if(hotelSplrData.isPresent()) {			
			List<PkgSubHotelRequest> responseData = new ArrayList<PkgSubHotelRequest>();
			
			List<PackageScheduleSubHotelModel> subHotelModel = requestData.stream()
	                .map( subHotel -> {
	                	PackageScheduleSubHotelModel thisModel = PkgsPriceSavingConverter.convertSubHotelRequestToSubHtlModel(supplierHotelId, subHotel);
	                	return thisModel;
	                }).collect(Collectors.toList());
			
	        List<PackageScheduleSubHotelModel> savedSubHtl = pkgScheduleSubHotelRepository.saveAll(subHotelModel);
	        
	        for (int i = 0; i < requestData.size(); i++) {
	        	PackageScheduleSubHotelModel savedHotel = savedSubHtl.get(i);
	        	PkgSubHotelRequest requestHotel = requestData.get(i);
	        	requestHotel.setId(savedHotel.getId());
	        	
	        	List<PkgPriceHotelRequest> priceList = new ArrayList<PkgPriceHotelRequest>();
	        	for (int l = 0; l < requestHotel.getSupbkgprcoptionprice().size(); l++) {
                    PkgPriceHotelRequest requestPrice = requestHotel.getSupbkgprcoptionprice().get(l);
                    PackageSchedulePriceHotelModel priceModel = PkgsPriceSavingConverter.convertSubHotelRequestToSubHtlModel(savedHotel.getId(), requestPrice);
                    PackageSchedulePriceHotelModel savedPrice = pkgSchedulePriceHotelRepository.save(priceModel);
                    requestPrice.setId(savedPrice.getId());
                    requestPrice.setSubOptionId(savedHotel.getId());
                    priceList.add(requestPrice);
	        	}
	        	requestHotel.setSupbkgprcoptionprice(priceList);
	        	responseData.add(requestHotel);
	        }	        
	        return new APIResponse(HttpStatus.OK.value(), "Hotel Package price data.", responseData);
		}else {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), "Invalid supplierHotelId, Not found in the system.", Collections.emptyList());
		}
	}

	public APIResponse getPkgHotelSupplierSubHotelPricingData(Long supplierSubHotelId) {
		
		Optional<PackageScheduleSubHotelModel> subHotels = pkgScheduleSubHotelRepository.findById(supplierSubHotelId);
	    if (subHotels.isEmpty()) {
	        return new APIResponse(HttpStatus.NOT_FOUND.value(), "Data not found", null);
	    }
		
	    PkgScheduleSubHotelResponse supplierSubResponse = new PkgScheduleSubHotelResponse();
	    
	    PackageScheduleSubHotelModel subHotelInfo = subHotels.get();
	    BeanUtils.copyProperties(subHotelInfo, supplierSubResponse);
	    
	    List<PackageSchedulePriceHotelModel> prices = pkgSchedulePriceHotelRepository.findBySubOptionId(subHotelInfo.getId());
        List<PkgSchedulePriceHotelResponse> priceResponses = new ArrayList<>();
        for (PackageSchedulePriceHotelModel price : prices) {
            PkgSchedulePriceHotelResponse priceResponse = new PkgSchedulePriceHotelResponse();
            BeanUtils.copyProperties(price, priceResponse);
            priceResponses.add(priceResponse);
        }
        supplierSubResponse.setSupbkgprcoptionprice(priceResponses);
	    
	    return new APIResponse(HttpStatus.OK.value(), "Hotel Package price data.", Collections.singletonList(supplierSubResponse));
	}

	public APIResponse getPackagePriceHeadersData(Long scheduleId, Long srId, Long srLineId) {
		
		List<PkgHeaderLinesResponse> headerDataResponse = new ArrayList<PkgHeaderLinesResponse>();
		
		List<PackageScheduleHeaderModel> headerData = pkgScheduleHeaderRepository.getHeaderDataBySchIdSrSrlineId(scheduleId, srId, srLineId);
		
		if(headerData != null && headerData.size() > 0) {
			
			headerDataResponse = headerData.stream().map( header -> {
				
				PkgHeaderLinesResponse thisHeader = new PkgHeaderLinesResponse();
				BeanUtils.copyProperties(header, thisHeader);
				
				List<PackageScheduleHeaderLineModel> linesData = pkgScheduleHeaderLineRepository.getLinesDataByHeaderId(header.getId());
				List<PkgScheduleHeaderLinesResponse> linesResponseData = new ArrayList<PkgScheduleHeaderLinesResponse>();
				if(linesData != null && linesData.size() > 0) {
					linesData.stream().forEach( line -> {
						PkgScheduleHeaderLinesResponse thisLine = new PkgScheduleHeaderLinesResponse();
						BeanUtils.copyProperties(line, thisLine);
						
						linesResponseData.add(thisLine);
					});
					thisHeader.setLinesData(linesResponseData);
				}				
				return thisHeader;
				
			}).collect(Collectors.toList());
		}
		if(headerDataResponse.size() >0) {
			return new APIResponse(HttpStatus.OK.value(), "Package schedule header and lines data.", headerDataResponse);
		}else {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), "Not found any package schedule header and lines data.", Collections.emptyList());
		}
	}

	
	

}

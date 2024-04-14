package com.travel.travtronics.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.travtronics.pkgbookings.request.AddBookingRequest;
import com.travel.travtronics.pkgbookings.request.PkgBookingPaxRequest;
import com.travel.travtronics.pkgbookings.request.PkgSchedulePriceRequest;
import com.travel.travtronics.pkgbookings.request.PkgSchedulesRequest;
import com.travel.travtronics.pkgbookings.response.PkgBookingResponse;
import com.travel.travtronics.request.AddPkgQuoteRequest;
import com.travel.travtronics.request.QuoteFulfillmentRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.EservicePricingResponse;
import com.travel.travtronics.srm.dao.CommonAsyncDao;
import com.travel.travtronics.srm.model.CustomerContactInfoModel;
import com.travel.travtronics.srm.model.HolidayRequestLine;
import com.travel.travtronics.srm.model.HolidayRequestPax;
import com.travel.travtronics.srm.model.HolidayRequestSegments;
import com.travel.travtronics.srm.model.MedInfo;
import com.travel.travtronics.srm.model.QuotesHeaderModel;
import com.travel.travtronics.srm.model.QuotesHeaderRepository;
import com.travel.travtronics.srm.model.QuotesPaxDetailsModel;
import com.travel.travtronics.srm.model.QuotesPkgPricing;
import com.travel.travtronics.srm.model.QuotesSegmentsInfoModel;
import com.travel.travtronics.srm.model.ServiceRequest;
import com.travel.travtronics.srm.model.SrPackagePriceLineBreakupModel;
import com.travel.travtronics.srm.model.SrPackagePriceLineModel;
import com.travel.travtronics.srm.model.TravelPackageSchedule;
import com.travel.travtronics.srm.repository.HolidayPackagePassengersRepository;
import com.travel.travtronics.srm.repository.HolidayPackageRoomsRepository;
import com.travel.travtronics.srm.repository.HolidayRequestLineRepository;
import com.travel.travtronics.srm.repository.HolidayRequestPaxRepository;
import com.travel.travtronics.srm.repository.HolidayRequestSegmentsRepository;
import com.travel.travtronics.srm.repository.MedInfoRepository;
import com.travel.travtronics.srm.repository.QuotesPaxDetailsRepository;
import com.travel.travtronics.srm.repository.QuotesPkgPricingRepository;
import com.travel.travtronics.srm.repository.QuotesSegmentsInfoRepository;
import com.travel.travtronics.srm.repository.ServiceRequestRepository;
import com.travel.travtronics.srm.repository.SrPackagePriceLineBreakupRepository;
import com.travel.travtronics.srm.repository.SrPackagePriceLineRepository;
import com.travel.travtronics.srm.repository.TravelPackageScheduleRepository;

@Service
public class PackageQuotesService {
	
	ZonedDateTime instance = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	String currentDateTime = formatter.format(instance); // 15-02-2022 12:43
	
	@Autowired
	ServiceRequestRepository srRepository;
	
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
	QuotesHeaderRepository quotesHeaderRepository;
	
	@Autowired
	MedInfoRepository medInfoRepository;
	
	@Autowired
	TravelPackageScheduleRepository travelPackageScheduleRepository;
		
	@Autowired
	ServiceTypePricingService serviceTypePricingService;
	
	@Autowired
	SrPackagePriceLineRepository srPackagePriceLineRepository;
	
	@Autowired
	SrPackagePriceLineBreakupRepository srPackagePriceLineBreakupRepository;
	
	@Autowired
	QuotesPkgPricingRepository quotesPkgPricingRepository;
	
	@Autowired
	CommonAsyncDao commonAsyncDao;
	
	@Autowired
	QuotesSegmentsInfoRepository quotesSegmentsInfoRepository;
	
	@Autowired
	QuotesPaxDetailsRepository quotesPaxDetailsRepository;

	@Autowired
	PackageBookingsService packageBookingsService;

	@Transactional(rollbackFor = Exception.class)
	public APIResponse addPackageQuote(AddPkgQuoteRequest requestData) {

		List<Integer> quoteIds = new ArrayList<Integer>();

		if (requestData == null) {
			return new APIResponse(HttpStatus.BAD_REQUEST.value(), "Failed, Invalid Request data.",
					Collections.emptyList());
		}
		
		String chdAges = "";
		if(requestData.getChildrenAge() != null) {
			ObjectMapper mapper = new ObjectMapper();
	        try {
	        	chdAges = mapper.writeValueAsString(requestData.getChildrenAge());
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	        }
		}
		Integer adtCount = requestData.getAdultCount() != null ? requestData.getAdultCount() : 0;
		Integer chdCount = requestData.getChildrenCount() != null ? requestData.getChildrenCount() : 0;
		Integer infCount = requestData.getInfantCount() != null ? requestData.getInfantCount() : 0;
		String requiredDate = requestData.getRequiredDate() != null ? requestData.getRequiredDate() : "";
		Integer scheduledDatesId = requestData.getScheduledDatesId() != null ? requestData.getScheduledDatesId() : 0;
		Integer customerId = 0;
		Integer contactId = 0;

		try {
			// package_configuration_schedule
			Optional<TravelPackageSchedule> scheduleInfo = travelPackageScheduleRepository
					.findById(requestData.getScheduleId());
			if (scheduleInfo != null && scheduleInfo.isPresent()) {
				
				String deviceInfo = requestData.getDeviceInfo() != null ? requestData.getDeviceInfo() : "";
				String ipAddress = requestData.getIpAddress() != null ? requestData.getIpAddress() : "";
				Integer addedBy = requestData.getAddedBy() != null ? requestData.getAddedBy() : 0;
				Long leadId = requestData.getLeadId() != null ? requestData.getLeadId() : (long) 0;

				Long schConfigId = requestData.getScheduleId(); 
				Long schId = scheduleInfo.get().getPackageConfigurationId();
				
				Optional<MedInfo> pkgConfigurationHeader = medInfoRepository.findById(schId);
				if (pkgConfigurationHeader.isPresent()) {
					
					String maildId = requestData.getContactMailId();
					String phoneNo = requestData.getContactMobileNo();
					String firstName = requestData.getContactName();
					
					Long srId = pkgConfigurationHeader.get().getSrId();
					Optional<ServiceRequest> srInfo = srRepository.findByRequestId(srId);
					if (srInfo != null && srInfo.isPresent()) {
						if(requestData.getContactMailId() != null && requestData.getContactMobileNo() != null) {
							customerId = srInfo.get().getCustomerId().intValue();
							CustomerContactInfoModel contactInfo = commonAsyncDao.validateContact(maildId, phoneNo, firstName, "", customerId);
							if(contactInfo != null) {
								contactId = contactInfo.getId();
							}
						}
						
						ServiceRequest newSrModeldata = new ServiceRequest();
						BeanUtils.copyProperties(srInfo.get(), newSrModeldata);
						newSrModeldata.setCopyFrom(srId.intValue());
						newSrModeldata.setRequestId((long) 0);
						newSrModeldata.setContactId(contactId.longValue());

						ServiceRequest newSrInfo = srRepository.save(newSrModeldata);
						if (newSrInfo != null && newSrInfo.getRequestId() > 0) {

							Long newSrId = newSrInfo.getRequestId();
							
							Long pkgLineId = (long) 0;
							List<HolidayRequestLine> pkgLines = holidayRequestLineRepository.findByRequestId(srId);
							if (pkgLines != null && pkgLines.size() > 0) {

								pkgLineId = pkgLines.get(0).getRequestLineId();
								
								pkgLines.stream().forEach(oldPkg -> {
									
									HolidayRequestLine newPkgSr = new HolidayRequestLine();
									BeanUtils.copyProperties(oldPkg, newPkgSr);
									newPkgSr.setLineUuid(newSrId + "-" + oldPkg.getRequestLineId());
									newPkgSr.setRequestId(newSrId);

									HolidayRequestLine newPkgSrInfo = holidayRequestLineRepository.save(newPkgSr);
									if (newPkgSrInfo != null && newPkgSrInfo.getLineUuid() != null
											&& !newPkgSrInfo.getLineUuid().isEmpty()) {

										// PaxLines
										List<HolidayRequestPax> pkgPaxLines = holidayRequestPaxRepository
												.findByRequestIdAndPaxIsDeletedIsNull(newSrId);
										if (pkgLines != null && pkgLines.size() > 0) {
											pkgPaxLines.stream().forEach(oldPax -> {
												HolidayRequestPax newPkgPax = new HolidayRequestPax();
												BeanUtils.copyProperties(oldPax, newPkgPax);
												newPkgPax.setRequestLinePaxId((long) 0);
												holidayRequestPaxRepository.save(newPkgPax);
											});
										}

										// Segments
										List<HolidayRequestSegments> pkgSegsLines = holidayRequestSegmentsRepository
												.findByRequestId(newSrId);
										if (pkgSegsLines != null && pkgSegsLines.size() > 0) {
											pkgSegsLines.stream().forEach(oldSegs -> {
												HolidayRequestSegments newPkgseg = new HolidayRequestSegments();
												BeanUtils.copyProperties(newPkgseg, newPkgseg);
												newPkgseg.setRequestSegmentId((long) 0);
												holidayRequestSegmentsRepository.save(newPkgseg);
											});
										}
									}
								});
								// Adding quote -- quotesHeaderRepository								
								QuotesHeaderModel quoteInfo = new QuotesHeaderModel();
								quoteInfo.setCustmerId(customerId);
								quoteInfo.setContactId(contactId);
								quoteInfo.setSupplierId(0);
								quoteInfo.setSrId(newSrId.intValue());
								quoteInfo.setSrLineId(pkgLineId.intValue());
								quoteInfo.setProductId(newSrInfo.getProductId());
								quoteInfo.setSeqNo(1);
								quoteInfo.setStatusId(30); //30
								quoteInfo.setStatusCode("QTSASSIGN"); //QTSASSIGN
								quoteInfo.setChannel("Offline");
								quoteInfo.setRemarks("Quote added from pkg");
								quoteInfo.setRecordStatus(1);
								quoteInfo.setLeadId(leadId.intValue());
								quoteInfo.setScheduleId(schConfigId.intValue()); //schId.intValue()
								quoteInfo.setAdtCount(adtCount);
								quoteInfo.setChdCount(chdCount);
								quoteInfo.setInfCount(infCount);
								quoteInfo.setChdAges(chdAges);
								if(requiredDate.isEmpty() == false) {
									quoteInfo.setRequiredDate(requiredDate);
								}
								quoteInfo.setFareRules(schId + "##" + leadId);
								quoteInfo.setCreatedBy(addedBy);
								quoteInfo.setCreatedDate(currentDateTime);
								quoteInfo.setSubmittedBy(addedBy);
								quoteInfo.setSubmittedDate(currentDateTime);
								
								QuotesHeaderModel savedQuote = quotesHeaderRepository.save(quoteInfo);
								if (savedQuote != null && savedQuote.getId() > 0) {
									quoteIds.add(savedQuote.getId());
									
									String pkgName = ""; 
									String scheduleName = "";
									String schdFromDate = "";
									String schdToDate = "";
									Map<String, Object> schdInfo = quotesSegmentsInfoRepository.getSchduleInfo(schConfigId);
									if(schdInfo != null && schdInfo.isEmpty() == false) {
										pkgName = schdInfo.get("pkgName").toString();
										scheduleName = schdInfo.get("scheduleName").toString();
										/*
										Map<String, String> schdDateInfo = quotesSegmentsInfoRepository.getSchduleDatesInfo(scheduledDatesId);
										if(schdDateInfo != null && schdDateInfo.isEmpty() == false) {
											schdFromDate = schdInfo.get("schdFromDate");
											schdToDate = schdInfo.get("schdToDate");
										}
										*/
									}
									System.out.println(pkgName+"---------------pkgName------scheduleName------------------"+scheduleName);
									
									QuotesSegmentsInfoModel segData = new QuotesSegmentsInfoModel();
									segData.setHeaderId(savedQuote.getId());
									segData.setRefNo(scheduledDatesId);
									segData.setDepDate(schdFromDate);
									segData.setArrDate(schdToDate);
									segData.setHotelAnxName(pkgName);
									segData.setHotelAnxCode(pkgName);
									segData.setRoomOrServiceName(scheduleName);
									segData.setRoomOrServiceType(scheduleName);
									segData.setRoomOrServiceNo(schConfigId.intValue());
									segData.setFareRules("Pkg data - "+schId+"##"+schConfigId);
									segData.setCreatedBy(addedBy);
									segData.setCreatedDate(currentDateTime);
									segData.setCreatedBy(addedBy);
									segData.setRecordStatus(1);
									quotesSegmentsInfoRepository.save(segData);
									
									List<QuotesPaxDetailsModel> paxList = new ArrayList<QuotesPaxDetailsModel>();
									if(adtCount != null && adtCount > 0) {
										IntStream.range(0, adtCount)
									    .mapToObj(i -> {
									        QuotesPaxDetailsModel paxData = new QuotesPaxDetailsModel();
									    	paxData.setHeaderId(savedQuote.getId());
									    	paxData.setPaxType("ADT");
									    	paxData.setFirstName("ADT-"+(i+1));
									    	paxData.setCreatedBy(addedBy);
									    	paxData.setCreatedDate(currentDateTime);
									    	paxData.setCreatedBy(addedBy);
									    	paxData.setRecordStatus(1);
									        return paxData;
									    })
									    .forEach(paxList::add);
									}
									if(adtCount != null && adtCount > 0) {
										IntStream.range(0, chdCount)
										    .mapToObj(c -> {
										    	QuotesPaxDetailsModel paxData = new QuotesPaxDetailsModel();
										    	paxData.setHeaderId(savedQuote.getId());
										    	paxData.setPaxType("CHD");
										    	paxData.setFirstName("CHD-"+(c+1));
										    	paxData.setCreatedBy(addedBy);
										    	paxData.setCreatedDate(currentDateTime);
										    	paxData.setCreatedBy(addedBy);
										    	paxData.setRecordStatus(1);
										        return paxData;
										    })
										    .forEach(paxList::add);
									}
									if(paxList != null && paxList.size() > 0) {
										quotesPaxDetailsRepository.saveAll(paxList);
									}
								}
								
								//prepare price here
								Double totalPkgPrice = (double) 0;
								Long serviceHeaderId = requestData.getPriceHeaderId();
								Map<String, Object> priceQualifiers = requestData.getPriceSelected();
								List<EservicePricingResponse> pricingLines = serviceTypePricingService.getPriceInfoRangeData(serviceHeaderId, priceQualifiers);
								System.out.println("----------------------pricingLines---------------------------"+pricingLines.size());
								if(pricingLines != null && pricingLines.size() > 0) {
									
									SrPackagePriceLineModel pkgPriceLineModel = new SrPackagePriceLineModel();
									pkgPriceLineModel.setRequestId(newSrId);
									pkgPriceLineModel.setSrPackageLineId(pkgLineId.intValue());
									pkgPriceLineModel.setScheduledId(schId.intValue());
									pkgPriceLineModel.setScheduleDateId(scheduledDatesId);
									pkgPriceLineModel.setServiceTypeHeaderId(serviceHeaderId.intValue());
									pkgPriceLineModel.setProductId(newSrInfo.getProductId());
									pkgPriceLineModel.setAdtCount(adtCount);
									pkgPriceLineModel.setChdCount(chdCount);
									pkgPriceLineModel.setInfCount(infCount);
									pkgPriceLineModel.setChdAges(chdAges);
									if(requiredDate.isEmpty() == false) {
										pkgPriceLineModel.setRequiredDate(requiredDate);
									}
									pkgPriceLineModel.setRecordStatus(1);
									pkgPriceLineModel.setCreatedBy(addedBy);
									pkgPriceLineModel.setQuoteId(savedQuote.getId().longValue());
									pkgPriceLineModel.setCreatedDate(currentDateTime);
									pkgPriceLineModel.setCreatedDevice(deviceInfo);
									pkgPriceLineModel.setCreatedIp(ipAddress);
									
									SrPackagePriceLineModel savePkgSrLine = srPackagePriceLineRepository.save(pkgPriceLineModel);
									
									List<SrPackagePriceLineBreakupModel> lines = new ArrayList<SrPackagePriceLineBreakupModel>();
									List<QuotesPkgPricing> quotePriceList = new ArrayList<QuotesPkgPricing>();
									
									for(EservicePricingResponse price : pricingLines) {
										
										SrPackagePriceLineBreakupModel line = new SrPackagePriceLineBreakupModel();
										
										line.setSrPackagePriceLineId(savePkgSrLine.getId());
										line.setPricingHeaderId(price.getPriceHeahderId());
										line.setPricingLineId(price.getPriceLineId());
										line.setItemId(price.getItemId());
										line.setItemName(price.getItemName());
										line.setItemCode(price.getItemCode());
										if(price.getInputValue() != null) {
											line.setQuantity(Integer.parseInt(price.getInputValue().toString()));
										}
										line.setBasePrice(price.getItemPrice());											
										line.setTotalPrice(price.getTotalPrice());
										line.setAddedBy(addedBy);
										line.setAddedDate(currentDateTime);
										line.setAddedIp(ipAddress);
										line.setAddedDevice(deviceInfo);
										
										lines.add(line);
										
										QuotesPkgPricing quotePrices = new QuotesPkgPricing();
										
										quotePrices.setQuoteHeaderId(savedQuote.getId());
										if(serviceHeaderId != null) {
											quotePrices.setServiceTypeHeaderId(serviceHeaderId.intValue());
										}
										if(price.getPriceHeahderId() != null) {
											quotePrices.setPricingHeaderId(price.getPriceHeahderId().intValue());
										}
										if(price.getPriceLineId() != null) {
											quotePrices.setPricingLineId(price.getPriceLineId().intValue());
										}
										quotePrices.setItemId(price.getItemId());
										quotePrices.setItemName(price.getItemName());
										quotePrices.setItemCode(price.getItemCode());
										if(price.getInputValue() != null) {
											quotePrices.setQuantity(Integer.parseInt(price.getInputValue().toString()));
										}
										quotePrices.setBase(price.getItemPrice());											
										quotePrices.setTotalPrice(price.getTotalPrice());
										quotePrices.setAddedBy(addedBy);
										quotePrices.setAddedDate(currentDateTime);
										quotePrices.setAddedIp(ipAddress);
										quotePrices.setAddedDevice(deviceInfo);
										
										quotePriceList.add(quotePrices);
										
										totalPkgPrice = totalPkgPrice+price.getTotalPrice();
									}
									
									System.out.println("-----------Final---------->"+lines.size());
									
									srPackagePriceLineBreakupRepository.saveAll(lines);
									
									quotesPkgPricingRepository.saveAll(quotePriceList);
									
									savePkgSrLine.setTotalPrice(totalPkgPrice);
									srPackagePriceLineRepository.save(savePkgSrLine);
									
								}
							}
						}
					}
					if(quoteIds != null && quoteIds.size() > 0 && contactId > 0) {
						sendQuoteMailToCustomer(quoteIds.get(0), requestData.getScheduleId().intValue());
					}
					return new APIResponse(HttpStatus.OK.value(), "Successfully Quote added.", quoteIds);
				}else {
					return new APIResponse(HttpStatus.NOT_FOUND.value(), "Failed, Not found package configuration header with scheduleId", Collections.emptyList());
				}
				
			}else {
				return new APIResponse(HttpStatus.NOT_FOUND.value(), "Failed, Not found scheduleId", Collections.emptyList());
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to quote adding. ",
					Collections.emptyList());
		}
	}

	public void sendQuoteMailToCustomer(Integer quoteId, Integer schId) {
		
		Integer srId = 0;
		String toMailId = "";
		String subject = "";
		String contactName = "";
		String scheduleName = "";
		String scheduleContent = "";
		String srCreatedDate = "";
		String srCreatedDateTimeLocal = "";
		
		Optional<QuotesHeaderModel> quoteInfo = quotesHeaderRepository.findById(quoteId);
		if(quoteInfo != null && quoteInfo.isPresent()) {
			srId = quoteInfo.get().getSrId();
			Map<String, Object> srInfo = quotesHeaderRepository.getContactInfoBySrId(quoteInfo.get().getSrId());
			if(srInfo != null && srInfo.isEmpty() == false) {
				contactName = srInfo.get("contactName").toString();
				toMailId = srInfo.get("mailId").toString();
				
				srCreatedDate = srInfo.get("createdDate").toString();
				DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		        LocalDateTime dateTime = LocalDateTime.parse(srCreatedDate, inputFormatter);

		        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm a");
		        srCreatedDateTimeLocal = dateTime.format(outputFormatter);
			}
			
			Optional<TravelPackageSchedule> scheduleInfo = travelPackageScheduleRepository.findById(schId.longValue());
			if(scheduleInfo != null && scheduleInfo.isPresent()) {
				scheduleName = scheduleInfo.get().getScheduleName();
				scheduleContent = scheduleInfo.get().getPackageScheduleTextEditor();
			}
			
			String quoteRefNo = quoteId+"-"+quoteInfo.get().getSrId()+"-"+quoteInfo.get().getSrLineId()+"-"+schId;
			
			subject = "Kindly view quote for "+scheduleName+" (Booking ID: "+quoteRefNo+")";
			
			String mailBodyContent = "Hi "+contactName+", <br/>Kindly view quote for "+scheduleName+" (Booking ID: "+quoteRefNo+")";
			if(scheduleContent.isEmpty() == false) {
				mailBodyContent += scheduleContent;
			}
			
			Map<String, String> replaceContents = new HashMap<String, String>();
			replaceContents.put("##CUSTNAME##", contactName);
			replaceContents.put("##SRID##", srId.toString());
			replaceContents.put("##CONTCTNAME##", contactName);
			replaceContents.put("##SRDATETIME##", srCreatedDateTimeLocal);
			replaceContents.put("<br>","");
			replaceContents.put("<br/>","");
			
			mailBodyContent = templateVariablesValuesMerge(replaceContents, mailBodyContent);
			
			// ============================== START SENDING MAIL ============================================
			Future<ResponseEntity<String>> mailReponse = null;
			if (srId > 0 && toMailId.isEmpty() == false) {
				String mailCc = "pampinchandi@gmail.com"; //"raasuko@gmail.com";
				String mailBcc = "mnrajusw@gmail.com";
				try {
					mailReponse = commonAsyncDao.sendHtmlEmail(toMailId, mailCc, mailBcc, subject, mailBodyContent);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}			
		}
	}	
	
	public String templateVariablesValuesMerge(Map<String, String> variableValues, String TemplateString) {

		if (TemplateString.isEmpty() == false && variableValues != null && variableValues.isEmpty() == false) {

			for (Map.Entry<String, String> values : variableValues.entrySet()) {
				System.out.println(values.getKey()); // value to be replaced
				System.out.println(values.getValue()); // replacable string
				TemplateString = TemplateString.replaceAll(values.getKey(), values.getValue());
			}
		}

		return TemplateString;
	}

	public APIResponse quoteFullfillment(QuoteFulfillmentRequest requestData) {
		
		Long quoteId = requestData.getQuoteId() != null ? requestData.getQuoteId() : 0;
		Integer addedBy = requestData.getAddedBy() != null ? requestData.getAddedBy() : 0;
		String device = requestData.getDeviceInfo() != null ? requestData.getDeviceInfo() : "";
		String ip = requestData.getIpAddress() != null ? requestData.getIpAddress() : "";
		
		Optional<QuotesHeaderModel> headerInfo = quotesHeaderRepository.findById(quoteId);
		if(headerInfo != null && headerInfo.isPresent()) {
			
			if(headerInfo.get().getStatusCode().equalsIgnoreCase("QTSAPPROVED")) { //33
				
				AddBookingRequest bkRequest = new AddBookingRequest();
				
				bkRequest.setBookingId((long) 0);
				bkRequest.setProductId(headerInfo.get().getProductId());
				bkRequest.setBookingChannel("Offline");
				bkRequest.setBookingStatusId(30);
				bkRequest.setBookingStatusName("Confirmed");
				bkRequest.setBookingServiceRequestId(headerInfo.get().getSrId());
				bkRequest.setBookingServiceRequestLineId(headerInfo.get().getSrLineId());
				bkRequest.setQuoteId(headerInfo.get().getId());
				bkRequest.setSupplierId(11);
				bkRequest.setSupplierReferenceDate(currentDateTime);
				bkRequest.setCustomerId(headerInfo.get().getCustmerId());
				bkRequest.setCustomerContactId(headerInfo.get().getContactId());
				bkRequest.setUserId(addedBy);
				bkRequest.setLiabilityOwnerId(addedBy);
				bkRequest.setCurrency("AED");
				bkRequest.setAddedBy(addedBy);
				bkRequest.setAddedDevice(device);
				bkRequest.setAddedIp(ip);
				
				Map<String, Object> schdInfo = quotesSegmentsInfoRepository.getSchduleInfo(headerInfo.get().getScheduleId().longValue());
				
				Integer pkgId = Integer.parseInt(schdInfo.get("pkgId").toString());
				Integer schId = Integer.parseInt(schdInfo.get("schId").toString());
				Integer serviceTypeHeaderId = Integer.parseInt(schdInfo.get("serviceTypeHeaderId").toString());
				
				PkgSchedulesRequest thisSchedule = new PkgSchedulesRequest();
				thisSchedule.setBookingPackageId(pkgId);
				thisSchedule.setBookingScheduleId(schId);
				thisSchedule.setPackageName(schdInfo.get("pkgName").toString());
				thisSchedule.setScheduleName(schdInfo.get("scheduleName").toString());
				thisSchedule.setScheduleDetails(schdInfo.get("scheduleDesc").toString());
				thisSchedule.setScheduleEditorData(schdInfo.get("scheduleEditorContent").toString());
				thisSchedule.setScheduleServiceTypeId(serviceTypeHeaderId);
				thisSchedule.setBookingPackageScheduleStatus(1);
				
				bkRequest.setPkgSchedules(thisSchedule); 
				
				List<PkgSchedulePriceRequest> pkgSchdRequest = new ArrayList<PkgSchedulePriceRequest>();
				List<QuotesPkgPricing> quotePrices = quotesPkgPricingRepository.findByQuoteHeaderId(quoteId);
				if(quotePrices != null && quotePrices.size() > 0) {		
					
					quotePrices.stream().forEach( qprice -> {
						PkgSchedulePriceRequest thisPrice = new PkgSchedulePriceRequest();
						thisPrice.setBookingPackageId(pkgId);
						thisPrice.setBookingScheduleId(schId);
						thisPrice.setScheduleServiceTypeId(serviceTypeHeaderId);
						thisPrice.setPriceItemHeaderId(qprice.getPricingHeaderId());
						thisPrice.setPriceItemId(qprice.getItemId());
						thisPrice.setPriceItemCode(qprice.getItemCode());
						thisPrice.setPriceItemName(qprice.getItemName());
						thisPrice.setPriceItemDescription(qprice.getItemName());
						thisPrice.setBookingPackageSchedulePriceStatus(1);
						thisPrice.setBaseCurrency("AED");
						thisPrice.setCurrency("AED");
						if(qprice.getBase() != null && qprice.getBase() > 0) {
							thisPrice.setBase(new BigDecimal(qprice.getBase()));
							bkRequest.setBase(bkRequest.getBase().add(thisPrice.getBase()));
						}
						if(qprice.getTax() != null && qprice.getTax() > 0) {
							thisPrice.setTax(new BigDecimal(qprice.getTax()));
							bkRequest.setTax(bkRequest.getTax().add(thisPrice.getTax()));
						}
						thisPrice.setTaxData("");
						thisPrice.setCommissionAmount(new BigDecimal(0));
						thisPrice.setCommissionPercentage(new BigDecimal(0));
						thisPrice.setCommissionPercentageToAmount(new BigDecimal(0));
						thisPrice.setExtraCommissionAmount(new BigDecimal(0));
						thisPrice.setExtraCommissionPercentage(new BigDecimal(0));
						thisPrice.setExtraCommissionPercentageToAmount(new BigDecimal(0));
						thisPrice.setSupplierTotal(new BigDecimal(0));
						thisPrice.setM1(new BigDecimal(0));
						thisPrice.setM1TemplateId((long)0);
					    thisPrice.setM1TemplateData("");
					    thisPrice.setM2(new BigDecimal(0));
					    thisPrice.setM2ReasonData("");
					    thisPrice.setD1(new BigDecimal(0));
					    thisPrice.setD2(new BigDecimal(0));
					    if(qprice.getTotalPrice() != null) {
					    	thisPrice.setGrandTotal(new BigDecimal(qprice.getTotalPrice()));
					    	bkRequest.setGrandTotal(bkRequest.getGrandTotal().add(thisPrice.getGrandTotal()));
					    }
					    pkgSchdRequest.add(thisPrice);
					});
					bkRequest.setPkgSchedulePrice(pkgSchdRequest);
				}
				List<QuotesPaxDetailsModel> quotePax = quotesPaxDetailsRepository.findByHeaderId(quoteId.intValue());
				List<PkgBookingPaxRequest> pkgSchdPaxRequest = new ArrayList<PkgBookingPaxRequest>();
				if(quotePax != null && quotePax.size() > 0) {					
					quotePax.stream().forEach( qpax -> {
						
						PkgBookingPaxRequest thisPax = new PkgBookingPaxRequest();
						thisPax.setBookingReferenceNumber("");
						thisPax.setCustomerTravelUserId(0);
						thisPax.setPaxType(qpax.getPaxType());
						thisPax.setTitle(qpax.getPaxTitle());
						thisPax.setFirstName(qpax.getFirstName());
						thisPax.setMiddleName("");
						thisPax.setLastSurName(qpax.getLastName());
					    thisPax.setDob(qpax.getDob());
					    thisPax.setAge(0);
					    thisPax.setMobile(qpax.getPhoneNumber());
					    thisPax.setEmail(qpax.getMailId());
					    thisPax.setPassengerLead(0);
					    thisPax.setPassengerStatus(1);
					    
					    pkgSchdPaxRequest.add(thisPax);
					});
					bkRequest.setPkgBookingPaxs(pkgSchdPaxRequest);
				}
				
				PkgBookingResponse bookingInfo = packageBookingsService.createPackageBookingProcess(bkRequest);
				
				if(bookingInfo != null && bookingInfo.getBookingReferenceNumber().isEmpty() == false) {
					
					QuotesHeaderModel qHeaderData = headerInfo.get();
					qHeaderData.setStatusId(34);
					qHeaderData.setStatusCode("QTSFULLFILLED");
					qHeaderData.setFullfilledBy(addedBy);
					qHeaderData.setFullfilledDate(currentDateTime);					
					quotesHeaderRepository.save(qHeaderData);
					
					Map<String, Object> bookingDetails = new HashMap<String, Object>();
					bookingDetails.put("bookingId", bookingInfo.getBookingId());
					bookingDetails.put("bookingRefNo", bookingInfo.getBookingReferenceNumber());
					bookingDetails.put("supplierRefNo", bookingInfo.getSupplierReferenceId());
					
					return new APIResponse(HttpStatus.OK.value(), "Successfully quote fulfillment created.", Collections.singletonList(bookingDetails));
				}else {
					return new APIResponse(HttpStatus.OK.value(), "Failed, This quoted is not fulfillment.", Collections.emptyList());
				}
				
			}else {
				return new APIResponse(HttpStatus.OK.value(), "Failed, This quoted is not not eligible fulfillment.", Collections.emptyList());
			}
		}else {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), "Not found any quote with provided id.", Collections.emptyList());
		}
	}
	

}

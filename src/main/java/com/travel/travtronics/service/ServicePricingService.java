package com.travel.travtronics.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.travtronics.bpf.model.FeildLangPriceItem;
import com.travel.travtronics.bpf.model.FeildLanguageLinesModel;
import com.travel.travtronics.bpf.model.FieldLangPriceTaxCategory;
import com.travel.travtronics.bpf.model.InputTypeConfig;
import com.travel.travtronics.bpf.model.Item;
import com.travel.travtronics.bpf.model.Item.EntityImport;
import com.travel.travtronics.bpf.model.PricingHeader;
import com.travel.travtronics.bpf.model.PricingLines;
import com.travel.travtronics.bpf.model.ServicePricing;
import com.travel.travtronics.bpf.repository.FeildLangPriceItemRepository;
import com.travel.travtronics.bpf.repository.FeildLanguageLinesRepository;
import com.travel.travtronics.bpf.repository.FieldLangPriceTaxCategoryRepository;
import com.travel.travtronics.bpf.repository.InputTypeConfigRepository;
import com.travel.travtronics.bpf.repository.ItemRepository;
import com.travel.travtronics.bpf.repository.PricingHeaderRepository;
import com.travel.travtronics.bpf.repository.PricingLineRepository;
import com.travel.travtronics.bpf.repository.ServicePricingRepository;
import com.travel.travtronics.bpf.repository.TaxHeaderRepository;
import com.travel.travtronics.converter.ServicePricingConverter;
import com.travel.travtronics.request.ServicePricingRequest;
import com.travel.travtronics.request.dto.TaxTemplateDto;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.EservicePricingResponse;
import com.travel.travtronics.response.PriceHearderTemplate;
import com.travel.travtronics.response.PriceLineResponseModel;
import com.travel.travtronics.response.ServicePriceFinalResponse;
import com.travel.travtronics.response.ServicePricingInformationResponse;
import com.travel.travtronics.util.Status;

@Service
public class ServicePricingService {

	@Autowired
	ServicePricingRepository servicePricingRepository;

	@Autowired
	PricingLineRepository pricingLineRepository;

	@Autowired
	PricingHeaderRepository pricingHeaderRepository;

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	TaxHeaderRepository taxHeaderRepository;

	@Autowired
	InputTypeConfigRepository inputTypeConfigRepository;

	@Autowired
	FeildLangPriceItemRepository feildItemPriceRepository;

	@Autowired
	FeildLanguageLinesRepository feildLangRepository;

	@Autowired
	FieldLangPriceTaxCategoryRepository feildLangTaxCtegoryRepository;
	
	@Autowired
	RestTemplate restTemplate;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	public APIResponse createPricing(List<ServicePricing> pricing) {
		try {
			List<ServicePricing> save = servicePricingRepository.saveAll(pricing);
			return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), save);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public APIResponse GetById(Long headerId) {
		List<ServicePricing> opt = servicePricingRepository.findByHeaderIdAndStatus(headerId, Status.Active);
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), opt);

	}

	public APIResponse saveAndUpdate(List<ServicePricingRequest> pricing) {
		List<ServicePricing> list = servicePricingRepository.saveAll(
				pricing.stream().map(ServicePricingConverter::convertPricingToModel).collect(Collectors.toList()));
		logger.info("lines-modified");
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
	}

	public APIResponse getPricing(Long headerId) {

		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), fetchResponseModel(headerId));
	}

	public List<ServicePriceFinalResponse> fetchResponseModel(Long headerId) {

		return servicePricingRepository.findByHeaderIdAndStatus(headerId, Status.Active).stream().map(sp -> {

			List<PriceLineResponseModel> collect = pricingLineRepository
					.findAllByHeaderIdAndStatus(sp.getPriceList(), Status.Active).stream().map(line -> {

						Optional<Item> findById = itemRepository.findById(line.getItem());

						Item item = findById.isPresent() ? findById.get() : null;

						return ServicePricingConverter.convertPriceLineModelToResponse(line, item);
					}).collect(Collectors.toList());

			Optional<PricingHeader> header = pricingHeaderRepository.findById(sp.getPriceList());

			PricingHeader headerModel = header.isPresent() ? header.get() : null;

			return ServicePricingConverter.convertModelToServicePriceFinalResponse(sp,
					ServicePricingConverter.convertHeaderLineModelToDto(headerModel, collect));

		}).collect(Collectors.toList());

	}

	// getting pricing information for service type
	public APIResponse getPriceInformation(Long serviceHeaderId) {
		List<ServicePricingInformationResponse> priceInformation = new ArrayList<>();
		// Get pricing header id by using serviceType id
		List<ServicePricing> servicePrices = servicePricingRepository.findByHeaderIdAndStatus(serviceHeaderId,
				Status.Active);
		servicePrices.stream().map(price -> {
			// getting price lines using price header id
			List<PricingLines> pricingLines = pricingLineRepository.findAllByHeaderIdAndStatus(price.getPriceList(),
					Status.Active);
			List<ServicePricingInformationResponse> ServicePricings = convertServicePricingInformationResponse(
					pricingLines);
			ServicePricings.stream().map(sprice -> priceInformation.add(sprice)).collect(Collectors.toList());
			return price;
		}).collect(Collectors.toList());
		if (priceInformation.size() > 0) {
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), priceInformation);
		} else {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), "No data found for this request.", priceInformation);
		}
	}

	private List<ServicePricingInformationResponse> convertServicePricingInformationResponse(
			List<PricingLines> pricingLines) {
		List<ServicePricingInformationResponse> ServicePricings = new ArrayList<>();
		pricingLines.stream().map(price -> {
			ServicePricingInformationResponse servicePricing = new ServicePricingInformationResponse();
			servicePricing.setCharge(price.getCharge());
			Optional<Item> item = itemRepository.findById(price.getItem());
			if (item.isPresent())
				servicePricing.setItem(item.get().getName());

			if (Objects.nonNull(price.getField()) && price.getQualifier().equals(EntityImport.Yes)) {
				InputTypeConfig filed = inputTypeConfigRepository.findByConfigId(price.getField());
				servicePricing.setField(filed.getName());
				servicePricing.setOperator(Objects.nonNull(price.getOperator()) ? price.getOperator() : null);
				servicePricing.setFieldValue(Objects.nonNull(price.getValue()) ? price.getValue() : null);
			}
			// ServicePricing.setItem(item);
			ServicePricings.add(servicePricing);
			return servicePricing;
		}).collect(Collectors.toList());
		return ServicePricings;
	}

	private List<ServicePricingInformationResponse> convertServicePricingLocalizationInformationResponse(
			List<PricingLines> pricingLines, String langCode) {

		return pricingLines.stream().map(localizationPrice -> {
			ServicePricingInformationResponse servicePricing = new ServicePricingInformationResponse();
			servicePricing.setCharge(localizationPrice.getCharge());
			Optional<FeildLangPriceItem> localizationItemPrice = feildItemPriceRepository.getLocalizationItemPrice(
					localizationPrice.getOrganizationId().intValue(), localizationPrice.getItem().intValue(), langCode);

			if (localizationItemPrice.isPresent())
				servicePricing.setItem(localizationItemPrice.get().getItemName());
			else
				servicePricing.setItem(itemRepository.getItemInfoById(localizationPrice.getItem()).get().getName());
			if (Objects.nonNull(localizationPrice.getField())
					&& localizationPrice.getQualifier().equals(EntityImport.Yes)) {

				Optional<FeildLanguageLinesModel> feildInfo = feildLangRepository.findByOrgIdAndLangCodeAndFieldId(
						localizationPrice.getOrganizationId().intValue(), langCode, localizationPrice.getField());
				servicePricing.setField(feildInfo.isPresent() ? feildInfo.get().getName()
						: inputTypeConfigRepository.getFeildName(localizationPrice.getField()).get());
				servicePricing.setOperator(
						Objects.nonNull(localizationPrice.getOperator()) ? localizationPrice.getOperator() : null);
				servicePricing.setFieldValue(
						Objects.nonNull(localizationPrice.getValue()) ? localizationPrice.getValue() : null);
			}

			return servicePricing;
		}).collect(Collectors.toList());

	}

	public APIResponse getPriceInfoRangeData(Long serviceHeaderId, String qualifiers, Integer pricingType,
			Boolean iacFlag) {

		List<EservicePricingResponse> priceInfo = new ArrayList<>();

		logger.info("Post params:");
		System.out.println(qualifiers);

		/*
		 * for (String key : jsonObject.keySet()) { System.out.println(key);
		 * System.out.println(jsonObject.get(key)); }
		 */

		// Get all header lines by id
		List<ServicePricing> servicePrices = servicePricingRepository
				.findByHeaderIdAndServicePriceTypeAndStatus(serviceHeaderId, pricingType, Status.Active);

		if (servicePrices.size() == 0) {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), "Header Id is not found in the system.",
					new ArrayList<>());
		} else {
			for (ServicePricing price : servicePrices) {

				// Get all price lines by price header id
				List<PriceHearderTemplate> lineInfo = pricingLineRepository.getPriceLineItemsInfo(price.getPriceList());

				if (lineInfo.size() > 0) {

					ObjectMapper mapper = new ObjectMapper();
					Map<String, Object> jsonObject = null;
					if (!qualifiers.isEmpty()) {
						try {
							jsonObject = (Map<String, Object>) mapper.readValue(qualifiers, Map.class);
						} catch (JsonMappingException e1) {
							e1.printStackTrace();
						} catch (JsonProcessingException e1) {
							e1.printStackTrace();
						}
					}

					String inputFieldValue = new String();
					for (PriceHearderTemplate line : lineInfo) {

						if ((iacFlag.equals(false) || iacFlag == false) && line.getIacflag().equals("Yes")) {// Process
																												// IAC
																												// flag
																												// only
																												// if
																												// FALSE
							continue;
						}

						if (iacFlag == true && line.getIacflag().equals("No")) { // Process IAC flag only if TRUE
							continue;
						}

						if (line.getQualifier().equals("Yes")) {

							if (qualifiers.isEmpty() || qualifiers.isBlank()) {
								continue;
							}

							System.out.println("FID:-> " + line.getField());

							String fieldName = "";
							InputTypeConfig fieldInfo = inputTypeConfigRepository.findByConfigId(line.getField());
							if (fieldInfo.getConfigId() > 0) {
								fieldName = String.valueOf(fieldInfo.getName());
							}
							System.out.println("FName:-> " + fieldName);
							if (fieldName.isEmpty()) {
								continue;
							} else {

								System.out.println(jsonObject);
								System.out.println(jsonObject.containsKey(fieldName));

								if (jsonObject.containsKey(fieldName) == false) {
									continue;
								} else {
									// Checking qualifier conditions
									inputFieldValue = String.valueOf(jsonObject.get(fieldName));

									System.out.println("Start Q Checking: -----> ");
									System.out.println(inputFieldValue);
									System.out.println(line.getOperator());
									System.out.println(line.getValue());

									if ((line.getOperator().isEmpty() || line.getOperator().isBlank())
											|| (line.getValue().isEmpty() || line.getValue().isBlank())) {
										continue;
									} else {
										try {

											String jsonString = line.getValue();
											/*
											 * for range
											 */
											String toOperator = line.getToOperator();
											String toValue = line.getToValue();

											JsonNode rootNode = mapper.readTree(jsonString);
											JsonNode objKey = rootNode.path("key");

											System.out.println("key : " + objKey);

											if ((StringUtils.isEmpty(toOperator) || StringUtils.isEmpty(toValue))
													&& (Objects.isNull(line.getIsRange()) || !line.getIsRange())) {
												boolean condition = evaluateOperator(inputFieldValue, objKey.asText(),
														line.getOperator());
												System.out.println("Q Value: --> ");
												System.out.println(condition);
												if (!condition) {
													continue;
												}
											} else {

												JsonNode rootToNode = mapper.readTree(toValue);
												JsonNode objToKey = rootToNode.path("key");
												System.out.println("toKey : " + objToKey);

												boolean condition = evaluateOperatorForRange(inputFieldValue,
														objKey.asText(), line.getOperator(), inputFieldValue,
														objToKey.asText(), toOperator);
												System.out.println("Q Value: --> ");
												System.out.println(condition);
												if (!condition) {
													continue;
												}
											}

										} catch (JsonParseException e) {
											e.printStackTrace();
										} catch (JsonMappingException e) {
											e.printStackTrace();
										} catch (IOException e) {
											e.printStackTrace();
										}

										/*
										 * boolean condition = evaluateOperator(inputFieldValue, line.getValue(),
										 * line.getOperator()); System.out.println("Q Value: --> ");
										 * System.out.println(condition); if (!condition) { continue; }
										 */

									}
								}
							}
						}

						// Get Tax lines by tax header id
						List<TaxTemplateDto> taxLines = taxHeaderRepository
								.getTaxLinesInfoByTaxCategory(line.getTaxCatId(), line.getOrganizationId());
						EservicePricingResponse priceData = new EservicePricingResponse();

						System.out.println("pbuom:" + line.getPbouom() + "--" + inputFieldValue);

						System.out.println("isAPI:" + line.getIsApi() + "--" + inputFieldValue);

						System.out.println("priceLineId" + line.getPriceLineId());
						if (Objects.nonNull(line.getPbouom()) && line.getPbouom()) {

							priceData = ServicePricingConverter.convertPropertiesListDataToPropertiesResponse(line,
									taxLines, Objects.nonNull(inputFieldValue) ? Double.valueOf(inputFieldValue) : 0.0,
									false);

						}

						else if (Objects.nonNull(line.getIsApi()) && line.getIsApi()) {

							/*
							 * calling internal api to aquire api result
							 */
							String calculatedPrice = callInternalPriceApi(line.getItemPrice(), jsonObject,
									line.getApi(), serviceHeaderId, pricingType);

							priceData = ServicePricingConverter.convertPropertiesListDataToPropertiesResponse(line,
									taxLines, Objects.nonNull(calculatedPrice) ? Double.valueOf(calculatedPrice) : 0.0,
									true);

						}

						else {
							priceData = ServicePricingConverter.convertPriceListDataToPriceResponse(line, taxLines);
						}
						priceInfo.add(priceData);
					}
				}
			}
		}
		if (priceInfo.size() > 0) {
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), priceInfo);
		} else {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), "No data found in the system.", new ArrayList<>());
		}
	}

	public APIResponse getPriceInfoData(Long serviceHeaderId, String qualifiers, Integer pricingType, Boolean iacFlag) {

		List<EservicePricingResponse> priceInfo = new ArrayList<>();

		logger.info("Post params:");
		System.out.println(qualifiers);

		/*
		 * for (String key : jsonObject.keySet()) { System.out.println(key);
		 * System.out.println(jsonObject.get(key)); }
		 */

		// Get all header lines by id
		List<ServicePricing> servicePrices = servicePricingRepository
				.findByHeaderIdAndServicePriceTypeAndStatus(serviceHeaderId, pricingType, Status.Active);

		if (servicePrices.size() == 0) {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), "Header Id is not found in the system.",
					new ArrayList<>());
		} else {
			for (ServicePricing price : servicePrices) {

				// Get all price lines by price header id
				List<PriceHearderTemplate> lineInfo = pricingLineRepository.getPriceLineItemsInfo(price.getPriceList());

				if (lineInfo.size() > 0) {

					ObjectMapper mapper = new ObjectMapper();
					Map<String, Object> jsonObject = null;
					if (!qualifiers.isEmpty()) {
						try {
							jsonObject = (Map<String, Object>) mapper.readValue(qualifiers, Map.class);
						} catch (JsonMappingException e1) {
							e1.printStackTrace();
						} catch (JsonProcessingException e1) {
							e1.printStackTrace();
						}
					}

					for (PriceHearderTemplate line : lineInfo) {

						if ((iacFlag.equals(false) || iacFlag == false) && line.getIacflag().equals("Yes")) {// Process
																												// IAC
																												// flag
																												// only
																												// if
																												// FALSE
							continue;
						}

						if (iacFlag == true && line.getIacflag().equals("No")) { // Process IAC flag only if TRUE
							continue;
						}

						if (line.getQualifier().equals("Yes")) {

							if (qualifiers.isEmpty() || qualifiers.isBlank()) {
								continue;
							}

							System.out.println("FID:-> " + line.getField());

							String fieldName = "";
							InputTypeConfig fieldInfo = inputTypeConfigRepository.findByConfigId(line.getField());
							if (fieldInfo.getConfigId() > 0) {
								fieldName = String.valueOf(fieldInfo.getName());
							}
							System.out.println("FName:-> " + fieldName);
							if (fieldName.isEmpty()) {
								continue;
							} else {

								System.out.println(jsonObject);
								System.out.println(jsonObject.containsKey(fieldName));

								if (jsonObject.containsKey(fieldName) == false) {
									continue;
								} else {
									// Checking qualifier conditions
									String inputFieldValue = String.valueOf(jsonObject.get(fieldName));

									System.out.println("Start Q Checking: -----> ");
									System.out.println(inputFieldValue);
									System.out.println(line.getOperator());
									System.out.println(line.getValue());

									if ((line.getOperator().isEmpty() || line.getOperator().isBlank())
											|| (line.getValue().isEmpty() || line.getValue().isBlank())) {
										continue;
									} else {
										try {

											String jsonString = line.getValue();
											JsonNode rootNode = mapper.readTree(jsonString);
											JsonNode objKey = rootNode.path("key");

											System.out.println("key : " + objKey);
											boolean condition = evaluateOperator(inputFieldValue, objKey.asText(),
													line.getOperator());
											System.out.println("Q Value: --> ");
											System.out.println(condition);
											if (!condition) {
												continue;
											}

										} catch (JsonParseException e) {
											e.printStackTrace();
										} catch (JsonMappingException e) {
											e.printStackTrace();
										} catch (IOException e) {
											e.printStackTrace();
										}

										/*
										 * boolean condition = evaluateOperator(inputFieldValue, line.getValue(),
										 * line.getOperator()); System.out.println("Q Value: --> ");
										 * System.out.println(condition); if (!condition) { continue; }
										 */

									}
								}
							}
						}

						// Get Tax lines by tax header id
						List<TaxTemplateDto> taxLines = taxHeaderRepository
								.getTaxLinesInfoByTaxCategory(line.getTaxCatId(), line.getOrganizationId());

						EservicePricingResponse priceData = ServicePricingConverter
								.convertPriceListDataToPriceResponse(line, taxLines);

						priceInfo.add(priceData);
					}
				}
			}
		}

		if (priceInfo.size() > 0) {
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), priceInfo);
		} else {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), "No data found in the system.", new ArrayList<>());
		}
	}

	public boolean evaluateOperator(String inputX, String inputY, String operator) {
		System.out.println("Start evaluateOperator Checking: -----> ");
		System.out.println(inputX + "-->" + inputY + " --- " + operator);
		if (operator.equals("==")) {

			return inputY.equals(inputX);

		} else if (operator.equals("!=")) {

			return inputY.equals(inputX) == false;

		} else if (operator.equals(">=")) { // Greater than equal to

			return (Double.valueOf(inputX) >= Double.valueOf(inputY));

		} else if (operator.equals("<=")) { // Less than equal to

			return (Double.valueOf(inputX) <= Double.valueOf(inputY));

		} else if (operator.equals(">")) { // Greater than equal to

			double inputXVal = Double.parseDouble(inputX);
			double inputYVal = Double.parseDouble(inputY);

			return (inputXVal > inputYVal);

		} else if (operator.equals("<")) { // Less than equal to

			return (Double.valueOf(inputX) < Double.valueOf(inputY));

		}

		return false;

	}

	public boolean evaluateOperatorForRange(String fromInputX, String fromInputY, String fromOperator, String toInputX,
			String toInputY, String toOperator) {
		System.out.println("Start evaluateOperator Range Checking: -----> ");
		System.out.println(fromInputX + "-->" + fromInputY + " --- " + fromOperator);
		System.out.println(toInputX + "-->" + toInputY + " --- " + toOperator);

		if (fromOperator.equals(">") && toOperator.equals("<")) {
			return (Double.valueOf(fromInputX) > Double.valueOf(fromInputY))
					&& (Double.valueOf(toInputX) < Double.valueOf(toInputY));
		} else if (fromOperator.equals(">=") && toOperator.equals("<=")) {
			return (Double.valueOf(fromInputX) >= Double.valueOf(fromInputY))
					&& (Double.valueOf(toInputX) <= Double.valueOf(toInputY));
		} else if (fromOperator.equals(">") && toOperator.equals("<=")) {
			return (Double.valueOf(fromInputX) > Double.valueOf(fromInputY))
					&& (Double.valueOf(toInputX) <= Double.valueOf(toInputY));
		} else if (fromOperator.equals(">=") && toOperator.equals("<")) {
			return (Double.valueOf(fromInputX) >= Double.valueOf(fromInputY))
					&& (Double.valueOf(toInputX) < Double.valueOf(toInputY));
		}

		return false;
	}

	public APIResponse feildLocalizationPriceInfo(Long serviceHeaderId, String langCode, Integer pricingType) {

		List<ServicePricingInformationResponse> collectedPriceInfo = servicePricingRepository
				.findByHeaderIdAndStatusAndServicePriceType(serviceHeaderId, Status.Active, pricingType).stream()
				.map(price -> {
					List<PricingLines> pricingLines = pricingLineRepository
							.findAllByHeaderIdAndStatus(price.getPriceList(), Status.Active);

					if (langCode.equalsIgnoreCase("en"))
						return convertServicePricingInformationResponse(pricingLines);
					else
						return convertServicePricingLocalizationInformationResponse(pricingLines, langCode);
				}).flatMap(List::stream).collect(Collectors.toCollection(ArrayList::new));

		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), collectedPriceInfo);

	}

	public APIResponse feildLocaLizationPricingData(Long serviceHeaderId, String qualifiers, String langCode,
			Integer pricingType, Boolean iacFlag) {

		@SuppressWarnings("unchecked")
		List<EservicePricingResponse> priceResponseInfo = (List<EservicePricingResponse>) getPriceInfoData(
				serviceHeaderId, qualifiers, pricingType, iacFlag).getData();

		List<EservicePricingResponse> collectedPriceInfoResponse = priceResponseInfo.stream().map(response -> {

			if (!langCode.equalsIgnoreCase("en")) {
				Optional<FeildLangPriceItem> localizationItemPrice = feildItemPriceRepository
						.getLocalizationItemPrice(response.getOrganizationId(), response.getItemId(), langCode);

				response.setItemName(localizationItemPrice.isPresent() ? localizationItemPrice.get().getItemName()
						: response.getItemName());

				if (CollectionUtils.isEmpty(response.getTaxBreakup())) {
					response.setTaxBreakup(response.getTaxBreakup());
				} else {
					response.getTaxBreakup().stream().map(tax -> {

						Optional<FieldLangPriceTaxCategory> taxCategoryLocalizationInfo = feildLangTaxCtegoryRepository
								.getTaxCategoryLocalizationInfo(response.getOrganizationId(), tax.getTaxCategoryId(),
										langCode);

						tax.setTaxCategoryName(taxCategoryLocalizationInfo.isPresent()
								? taxCategoryLocalizationInfo.get().getTaxCategoryName()
								: feildLangTaxCtegoryRepository.getTaxCategoryName(tax.getTaxCategoryId()).get());
						return tax;
					}).collect(Collectors.toList());
				}

			} else {

				if (CollectionUtils.isEmpty(response.getTaxBreakup())) {
					response.setTaxBreakup(response.getTaxBreakup());
				} else {
					response.getTaxBreakup().stream().map(tax -> {

						Optional<String> taxCategoryName = feildLangTaxCtegoryRepository
								.getTaxCategoryName(tax.getTaxCategoryId());
						tax.setTaxCategoryName(taxCategoryName.get());
						return tax;
					}).collect(Collectors.toList());
				}

			}
			return response;
		}).collect(Collectors.toCollection(ArrayList::new));

		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), collectedPriceInfoResponse);
	}

	public APIResponse getSrPriceRangeInfo(Long serviceHeaderId, Integer pricingType) {

		double startingPrice = 0;
		double maxPrice = 0;
		double variablePrice = 0;

		Map<String, Object> priceInfo = new HashMap<>();

		// Get all header lines by id
		List<ServicePricing> servicePrices = servicePricingRepository
				.findByHeaderIdAndServicePriceTypeAndStatus(serviceHeaderId, pricingType, Status.Active);

		if (servicePrices.size() == 0) {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), "Header Id is not found in the system.",
					new ArrayList<>());
		} else {

			for (ServicePricing price : servicePrices) {

				// Get all price lines by price header id
				List<PriceHearderTemplate> lineInfo = pricingLineRepository.getPriceLineItemsInfo(price.getPriceList());

				if (lineInfo.size() > 0) {

					for (PriceHearderTemplate line : lineInfo) {

						List<TaxTemplateDto> taxLines = taxHeaderRepository
								.getTaxLinesInfoByTaxCategory(line.getTaxCatId(), line.getOrganizationId());

						EservicePricingResponse priceData = ServicePricingConverter
								.convertPriceListDataToPriceResponse(line, taxLines);

						if (line.getQualifier().equals("Yes")) {
							// variablePrice = variablePrice+line.getItemPrice();

							variablePrice = variablePrice + priceData.getTotalPrice();
						} else {

							startingPrice = startingPrice + priceData.getTotalPrice();
						}

					}
				}
			}
			maxPrice = startingPrice + variablePrice;

			priceInfo.put("minprice", startingPrice);
			priceInfo.put("maxprice", maxPrice);
		}
		if (priceInfo.size() > 0) {
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), Collections.singletonList(priceInfo));
		} else {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), "No data found in the system.", new ArrayList<>());
		}
	}

	public APIResponse getPropertyPrice(Long serviceHeaderId, String qualifiers, String langCode, Integer pricingType,
			Boolean iacFlag) {

		@SuppressWarnings("unchecked")
		List<EservicePricingResponse> priceResponseInfo = (List<EservicePricingResponse>) getPriceInfoRangeData(
				serviceHeaderId, qualifiers, pricingType, iacFlag).getData();

		List<EservicePricingResponse> collectedPriceInfoResponse = priceResponseInfo.stream().map(response -> {

			if (!langCode.equalsIgnoreCase("en")) {
				Optional<FeildLangPriceItem> localizationItemPrice = feildItemPriceRepository
						.getLocalizationItemPrice(response.getOrganizationId(), response.getItemId(), langCode);

				response.setItemName(localizationItemPrice.isPresent() ? localizationItemPrice.get().getItemName()
						: response.getItemName());

				if (CollectionUtils.isEmpty(response.getTaxBreakup())) {
					response.setTaxBreakup(response.getTaxBreakup());
				} else {
					response.getTaxBreakup().stream().map(tax -> {

						Optional<FieldLangPriceTaxCategory> taxCategoryLocalizationInfo = feildLangTaxCtegoryRepository
								.getTaxCategoryLocalizationInfo(response.getOrganizationId(), tax.getTaxCategoryId(),
										langCode);

						tax.setTaxCategoryName(taxCategoryLocalizationInfo.isPresent()
								? taxCategoryLocalizationInfo.get().getTaxCategoryName()
								: feildLangTaxCtegoryRepository.getTaxCategoryName(tax.getTaxCategoryId()).get());
						return tax;
					}).collect(Collectors.toList());
				}

			} else {

				if (CollectionUtils.isEmpty(response.getTaxBreakup())) {
					response.setTaxBreakup(response.getTaxBreakup());
				} else {
					response.getTaxBreakup().stream().map(tax -> {

						Optional<String> taxCategoryName = feildLangTaxCtegoryRepository
								.getTaxCategoryName(tax.getTaxCategoryId());
						tax.setTaxCategoryName(taxCategoryName.get());
						return tax;
					}).collect(Collectors.toList());
				}

			}
			return response;
		}).collect(Collectors.toCollection(ArrayList::new));

		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), collectedPriceInfoResponse);
	}

	

	public String callInternalPriceApi(Double charge, Map<String, Object> qualifiers, String apiUrl,
			Long serviceHeaderId, Integer pricingType) {

		System.out.println("calling an api with qualifiers" + qualifiers);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl).queryParam("charge", charge)
				.queryParam("serviceHeaderId", serviceHeaderId).queryParam("pricingType", pricingType);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity<>(qualifiers, headers);

		ResponseEntity<String> exchange = restTemplate.exchange(builder.build().toString(), HttpMethod.POST, entity,
				String.class);

		return exchange.getBody();

	}
}

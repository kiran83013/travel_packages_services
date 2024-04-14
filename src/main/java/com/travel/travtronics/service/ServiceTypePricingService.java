package com.travel.travtronics.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.travtronics.bpf.model.InputTypeConfig;
import com.travel.travtronics.bpf.model.ServicePricing;
import com.travel.travtronics.bpf.repository.InputTypeConfigRepository;
import com.travel.travtronics.bpf.repository.PricingLineRepository;
import com.travel.travtronics.bpf.repository.ServicePricingRepository;
import com.travel.travtronics.bpf.repository.TaxHeaderRepository;
import com.travel.travtronics.converter.ServicePricingConverter;
import com.travel.travtronics.request.dto.TaxTemplateDto;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.EservicePricingResponse;
import com.travel.travtronics.response.PriceHearderTemplate;
import com.travel.travtronics.util.Status;

@Service
public class ServiceTypePricingService {
	
	@Autowired
	ServicePricingRepository servicePricingRepository;
	
	@Autowired
	PricingLineRepository pricingLineRepository;
	
	@Autowired
	InputTypeConfigRepository inputTypeConfigRepository;
	
	@Autowired
	TaxHeaderRepository taxHeaderRepository;
	
	public APIResponse getItemsPrice(Long serviceHeaderId, Map<String, Object> qualifiers) {
		
		List<EservicePricingResponse> priceResponseInfo = getPriceInfoRangeData(serviceHeaderId, qualifiers);

		List<EservicePricingResponse> collectedPriceInfoResponse = priceResponseInfo.stream().map(response -> {

			response.setItemName(response.getItemName());

			if (CollectionUtils.isEmpty(response.getTaxBreakup())) {
				response.setTaxBreakup(response.getTaxBreakup());
			} else {
				response.getTaxBreakup().stream().map(tax -> {
					Optional<String> taxName = servicePricingRepository.getTaxCategoryName(tax.getTaxCategoryId());
					if(taxName != null && taxName.isPresent()) {
						tax.setTaxCategoryName(taxName.get());
					}
					return tax;
				}).collect(Collectors.toList());
			}

			return response;
			
		}).collect(Collectors.toCollection(ArrayList::new));

		if(collectedPriceInfoResponse != null && collectedPriceInfoResponse.size() > 0) {
			
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), collectedPriceInfoResponse);
		}else {
			
			return new APIResponse(HttpStatus.NOT_FOUND.value(), "Not found any price data", Collections.emptyList());
		}
	}
	
	public List<EservicePricingResponse> getPriceInfoRangeData(Long serviceHeaderId, Map<String, Object> qualifiers) {
		
		Integer pricingType = 3;
		Boolean iacFlag = false;

		List<EservicePricingResponse> priceInfo = new ArrayList<>();

		// Get all header lines by id
		List<ServicePricing> servicePrices = servicePricingRepository
				.findByHeaderIdAndServicePriceTypeAndStatus(serviceHeaderId, pricingType, Status.Active);

		if (servicePrices.size() == 0) {
			return priceInfo;
		} else {
			for (ServicePricing price : servicePrices) {

				// Get all price lines by price header id
				List<PriceHearderTemplate> lineInfo = pricingLineRepository.getPriceLineItemsInfo(price.getPriceList());

				if (lineInfo.size() > 0) {

					ObjectMapper mapper = new ObjectMapper();
					Map<String, Object> jsonObject = null;
					if (!qualifiers.isEmpty()) {
						jsonObject = qualifiers;
					}

					String inputFieldName = new String();
					String inputFieldValue = new String();
					
					for (PriceHearderTemplate line : lineInfo) {

						if ((iacFlag.equals(false) || iacFlag == false) && line.getIacflag().equals("Yes")) {// Process IAC flag only if FALSE
							continue;
						}

						if (iacFlag == true && line.getIacflag().equals("No")) { // Process IAC flag only if TRUE
							continue;
						}

						if (line.getQualifier().equals("Yes")) {

							if (qualifiers.isEmpty()) {
								continue;
							}

							System.out.println("FID:-> " + line.getField());

							String fieldName = "";
							InputTypeConfig fieldInfo = inputTypeConfigRepository.findByConfigId(line.getField());
							if (fieldInfo.getConfigId() > 0) {
								fieldName = String.valueOf(fieldInfo.getName());
								inputFieldName = fieldName;
							}
							System.out.println("FName:-> " + fieldName);
							if (fieldName.isEmpty()) {
								continue;
							} else {

								System.out.println("-----fieldName----->"+fieldName);
								System.out.println(jsonObject);
								//System.out.println(jsonObject.containsKey(fieldName));

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
											/** for range*/
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

							priceData = ServicePricingConverter.convertPropertiesListDataToPropertiesResponse(inputFieldName, line,
									taxLines, Objects.nonNull(inputFieldValue) ? Double.valueOf(inputFieldValue) : 0.0,
									false);
							priceData.setServicTypeHeaderId(serviceHeaderId);
							priceData.setPriceHeahderId(price.getPriceList());
						} else {
							priceData = ServicePricingConverter.convertPriceListDataToPriceResponse(inputFieldName, line, taxLines);
							priceData.setServicTypeHeaderId(serviceHeaderId);
							priceData.setPriceHeahderId(price.getPriceList());
						}
						priceInfo.add(priceData);
					}
				}
			}
		}
		return priceInfo;
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

	public List<Map<String, Object>> getPriceLinesForSchdulePriceLinking(Long srtypeId) {
		
		return pricingLineRepository.getPriceLinesForSchdulePriceLink(srtypeId);
	}

	public APIResponse GetById(Long headerId) {
		List<ServicePricing> opt = servicePricingRepository.findByHeaderIdAndStatus(headerId, Status.Active);
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), opt);

	}

}

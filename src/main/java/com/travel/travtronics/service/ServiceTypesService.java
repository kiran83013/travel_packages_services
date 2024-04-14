package com.travel.travtronics.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.travtronics.bpf.model.FeildLanguageLinesModel;
import com.travel.travtronics.bpf.model.FieldLanguageServiceTypes;
import com.travel.travtronics.bpf.model.InputTypeConfig;
import com.travel.travtronics.bpf.model.ServiceTypeLines;
import com.travel.travtronics.bpf.repository.FeildLanguageLinesRepository;
import com.travel.travtronics.bpf.repository.FieldLanguageServiceTypesRepository;
import com.travel.travtronics.bpf.repository.InputTypeConfigRepository;
import com.travel.travtronics.bpf.repository.ServiceTypesLineRepository;
import com.travel.travtronics.bpf.repository.ServiceTypesRepository;
import com.travel.travtronics.converter.InputTypeConfigConverter;
import com.travel.travtronics.converter.ServiceTypeConveter;
import com.travel.travtronics.dtos.ServiceTypeHeaderResponse;
import com.travel.travtronics.request.FeildFormDataRequest;
import com.travel.travtronics.request.dto.ServiceTypeLinesFetchModel;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.ConfigResponse;
import com.travel.travtronics.response.DependentFieldData;
import com.travel.travtronics.response.EservicePricingResponse;
import com.travel.travtronics.response.FeildLineMappedResponseDto;
import com.travel.travtronics.response.FeildLinesResponseDto;
import com.travel.travtronics.response.FinalServiceTypesHeaderFormDataResponse;
import com.travel.travtronics.srm.model.TravelPackageSchedule;
import com.travel.travtronics.srm.repository.TravelPackageScheduleRepository;
import com.travel.travtronics.util.Status;

@Service
public class ServiceTypesService {

	@Autowired
	ServiceTypesRepository serviceTypeHeaderRepository;

	@Autowired
	ServiceTypesLineRepository serviceLineRepository;

	@Autowired
	InputTypeConfigRepository configRepository;

	@Autowired
	FieldLanguageServiceTypesRepository feildLangSrTypeRepository;

	@Autowired
	FeildLanguageLinesRepository feildLangLinesRepository;

	@Autowired
	TravelPackageScheduleRepository travelPackageScheduleRepository;

	@Autowired
	ServiceTypePricingService serviceTypePricingService;

	public APIResponse getServiceHeaderTypes(String organizationId) {

		List<Map<String, String>> serviceTypesList = serviceTypeHeaderRepository.findAllByList(organizationId);

		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), serviceTypesList);
	}

	public ResponseEntity<?> getServiceTypeLines(Long headerId) {

		List<ServiceTypeLinesFetchModel> collect = serviceLineRepository
				.findAllByHeaderIdAndStatus(headerId, Status.Active).stream()
				.map(ServiceTypeConveter::convertServiceTypeModelToJson).peek(model -> {
					model.setConigModel(fetchInputConfigWithType(model.getField()));

				}).collect(Collectors.toList());

		collect.replaceAll(echline -> {

			FeildLinesResponseDto convertedFormJson = convertFormDataTOJson(echline.getFormData());

			List<DependentFieldData> dependentfeild = convertedFormJson.getDependentFields();

			if (Objects.nonNull(dependentfeild)) {

				dependentfeild.replaceAll(eachdeptfeild -> {

					Integer dependentField = eachdeptfeild.getDependentField();

					if (Objects.nonNull(dependentField)) {
						ConfigResponse fetchInputConfigWithType = fetchInputConfigWithType(dependentField);
						eachdeptfeild.setInputConfigResponse(fetchInputConfigWithType);

					}
					try {
						String fromData = new ObjectMapper().writeValueAsString(convertedFormJson);
						echline.setFormData(fromData);

					} catch (JsonProcessingException e) {
						echline.setFormData("{}");
						e.printStackTrace();
					}
					return eachdeptfeild;
				});
			}

			return echline;

		});
		return new ResponseEntity<>(Map.of("message", HttpStatus.OK.name(), "status", HttpStatus.OK.value(), "data",
				collect, "errors", List.of()), HttpStatus.OK);

	}

	public ResponseEntity<?> getServiceTypeLinesByIsPricing(Long headerId, Long isPricing) {
		List<ServiceTypeLinesFetchModel> collect = serviceLineRepository
				.findAllByHeaderIdAndStatusAndIsPricing(headerId, Status.Active, isPricing).stream()
				.map(ServiceTypeConveter::convertServiceTypeModelToJson).peek(model -> {

					model.setConigModel(fetchInputConfigWithType(model.getField()));

				}).collect(Collectors.toList());

		collect.replaceAll(echline -> {

			FeildLinesResponseDto convertedFormJson = convertFormDataTOJson(echline.getFormData());

			List<DependentFieldData> dependentfeild = convertedFormJson.getDependentFields();

			if (Objects.nonNull(dependentfeild)) {

				dependentfeild.replaceAll(eachdeptfeild -> {

					Integer dependentField = eachdeptfeild.getDependentField();

					if (Objects.nonNull(dependentField)) {
						ConfigResponse fetchInputConfigWithType = fetchInputConfigWithType(dependentField);
						eachdeptfeild.setInputConfigResponse(fetchInputConfigWithType);

					}
					try {
						String fromData = new ObjectMapper().writeValueAsString(convertedFormJson);
						echline.setFormData(fromData);

					} catch (JsonProcessingException e) {
						echline.setFormData("{}");
						e.printStackTrace();
					}
					return eachdeptfeild;
				});
			}

			return echline;

		});
		return new ResponseEntity<>(Map.of("message", HttpStatus.OK.name(), "status", HttpStatus.OK.value(), "data",
				collect, "errors", List.of()), HttpStatus.OK);
	}

	private FeildLinesResponseDto convertFormDataTOJson(String formData) {

		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		try {
			return mapper.readValue(formData, FeildLinesResponseDto.class);
		} catch (JsonMappingException e) {

			e.printStackTrace();
			return new FeildLinesResponseDto();
		} catch (JsonProcessingException e) {

			e.printStackTrace();

			return new FeildLinesResponseDto();
		}

	}

	public ConfigResponse fetchInputConfigWithType(Integer configId) {
		ConfigResponse response = new ConfigResponse();
		InputTypeConfig configModel = configRepository.findByConfigId(configId != null ? configId : 0);
		response = InputTypeConfigConverter
				.convertModelToResponse(configModel != null ? configModel : new InputTypeConfig());
		String findByTypeId = configRepository.findByTypeId(response.getType() != null ? response.getType() : 0);
		response.setTypeName(findByTypeId != null ? findByTypeId : null);
		Optional<String> serviceUrl = configRepository.findByServiceUrl(response.getServiceId());
		Optional<Boolean> isExternalUrl = configRepository.findByIsExternalUrl(response.getServiceId());
		if (serviceUrl.isPresent()) {
			response.setService(serviceUrl.get());
		}
		if (isExternalUrl.isPresent()) {
			response.setIsExternalUrl(isExternalUrl.get());
		}

		return response;
	}

	public APIResponse getServiceTypeHeaderAndLinesFromDataByHeadeId(Long scheduleId) {

		Optional<TravelPackageSchedule> pkgConfScheduleInfo = travelPackageScheduleRepository.findById(scheduleId);

		if (pkgConfScheduleInfo == null || pkgConfScheduleInfo.isPresent() == false) {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), "No schedule data found in the system.",
					Collections.emptyList());
		}
		if (pkgConfScheduleInfo.get().getSrTypeLink() == null || pkgConfScheduleInfo.get().getSrTypeLink() <= 0) {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), "Not linked any sr types to this schedule",
					Collections.emptyList());
		}

		Long headerId = pkgConfScheduleInfo.get().getSrTypeLink().longValue();
		String langCode = "";

		FinalServiceTypesHeaderFormDataResponse response = new FinalServiceTypesHeaderFormDataResponse();

		Optional<ServiceTypeHeaderResponse> headerInfo = serviceTypeHeaderRepository.findByServiceTypeId(headerId);

		if (!headerInfo.isPresent()) {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), "No data found in the system.",
					Collections.EMPTY_LIST);
		} else {
			Optional<FieldLanguageServiceTypes> srFeildTypeInfo = feildLangSrTypeRepository
					.findByOrgIdAndServiceTypeIdAndLangCode(Integer.valueOf(headerInfo.get().getOrganizationId()),
							headerInfo.get().getId().intValue(), langCode);

			Optional<String> serviceUrl = Optional.empty();
			Optional<Boolean> isExternalUrl = Optional.empty();
			if (serviceTypeHeaderRepository != null && response.getLinesData() != null
					&& !response.getLinesData().isEmpty()) {
				String serviceData = response.getLinesData().get(0).getService();
				serviceUrl = serviceTypeHeaderRepository.findByServiceUrl(serviceData);
				isExternalUrl = serviceTypeHeaderRepository.findByIsExternalUrl(serviceData);
			}
			if (serviceUrl.isPresent()) {
				response.getLinesData().get(0).setServiceUrl(serviceUrl.get());
			}
			if (isExternalUrl.isPresent() && response.getLinesData().size() > 1) {
				response.getLinesData().get(1).setIsExternalUrl(isExternalUrl.get());
			}

			if (srFeildTypeInfo.isPresent() && !langCode.equalsIgnoreCase("en")) {
				headerInfo.get().setName(srFeildTypeInfo.get().getName());
				headerInfo.get().setInstructions(srFeildTypeInfo.get().getInstructions());
			}
			response.setHeaderInfo(headerInfo.get());
		}

		List<ServiceTypeLines> serviceLines = serviceLineRepository.findAllByHeaderIdAndStatus(headerId, Status.Active);
		if (!serviceLines.isEmpty()) {

			

			List<FeildFormDataRequest> collectedJsonInfo = serviceLines.stream()
					.map(model -> new FeildFormDataRequest(model.getFormData(), model.getFieldOrder()))
					.collect(Collectors.toList());

			List<FeildLineMappedResponseDto> jsonResponse = new ArrayList<>();

			collectedJsonInfo.stream().forEach(json -> {
				
				Map<String, Object> fieldsData = new HashMap<String, Object>();

				ServiceTypeLines lines = new ServiceTypeLines();

				FeildLineMappedResponseDto mappedJsonInfo = new FeildLineMappedResponseDto();

				try {
					mappedJsonInfo = new ObjectMapper()
							.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
							.readValue(json.getJson(), FeildLineMappedResponseDto.class);

					List<Map<String, Object>> formInput = mappedJsonInfo.getFormInput();
					if (CollectionUtils.isNotEmpty(formInput)) {

						List<Map<String, Object>> preparedmapformInput = formInput.stream().map(input -> {

							@SuppressWarnings("unchecked")
							Map<String, Object> feildObject = (Map<String, Object>) input.get("field");
							return feildObject;

						}).collect(Collectors.toList());

						mappedJsonInfo.setFormInput(preparedmapformInput);
					}

					List<DependentFieldData> dependentfeild = mappedJsonInfo.getDependentFields();
					if (dependentfeild != null) {
						List<DependentFieldData> collectedDeptFieldsInfo = dependentfeild.stream()
								.map(eachdeptfeild -> {
									Integer dependentField = eachdeptfeild.getDependentField();

									if (Objects.nonNull(dependentField)) {

										ConfigResponse fetchInputConfigWithType = fetchInputConfigWithType(
												dependentField);
										eachdeptfeild.setInputConfigResponse(fetchInputConfigWithType);
									}

									return eachdeptfeild; // Return the modified DependentFieldData
								}).collect(Collectors.toList());

						mappedJsonInfo.setDependentFields(collectedDeptFieldsInfo);

					}
					String fromData = new ObjectMapper().writeValueAsString(mappedJsonInfo);
					lines.setFormData(fromData);
				} catch (JsonMappingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (JsonProcessingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				Optional<FeildLanguageLinesModel> feildLineInfo = feildLangLinesRepository
						.findByOrgIdAndLangCodeAndFieldId(mappedJsonInfo.getOrganizationId(), langCode,
								mappedJsonInfo.getConfigId());
				mappedJsonInfo.setFeildOrder(json.getJsonOrder());

				if (feildLineInfo.isPresent() && !langCode.equalsIgnoreCase("en")) {
					mappedJsonInfo.getUi().setHint(feildLineInfo.get().getHint());
					mappedJsonInfo.getUi().setInfo(feildLineInfo.get().getInfo());
					mappedJsonInfo.getUi().setPlaceholder(feildLineInfo.get().getPlaceHolder());
					mappedJsonInfo.getUi().setLabel(feildLineInfo.get().getLabel());
				}

				System.out.println("--------------------------------------field Name ------------------------------------------->"+mappedJsonInfo.getName());
				fieldsData.put(mappedJsonInfo.getName(), 1);

				List<EservicePricingResponse> priceInfo = serviceTypePricingService.getPriceInfoRangeData(headerId,fieldsData);
				if (priceInfo != null && priceInfo.size() > 0) {
					mappedJsonInfo.setPriceInfo(priceInfo.get(0));
				}

				jsonResponse.add(mappedJsonInfo);

			});
			response.setLinesData(jsonResponse);
		}

		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), Collections.singletonList(response));

	}

	public APIResponse priceLinesForSchedulePriceLinking(Long srtypeId) {

		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		List<Map<String, Object>> priceLinesForSchdulePriceLinking = serviceTypePricingService
				.getPriceLinesForSchdulePriceLinking(srtypeId);
		List<Map<String, Object>> finalResponse = new ArrayList<>();

		priceLinesForSchdulePriceLinking.forEach(each -> {
			Map<String, Object> response = new LinkedHashMap<>();

			response.put("priceHeaderId", each.get("price_header_id"));
			response.put("item", each.get("item"));
			response.put("priceHeaderName", each.get("price_header_name"));
			response.put("field", each.get("field"));
			response.put("fieldName", each.get("field_name"));

			try {
				Map ui = mapper.readValue(each.get("ui").toString(), Map.class);
				response.put("ui", ui);
			} catch (JsonMappingException e) {
				response.put("ui", Map.of());
				e.printStackTrace();
			} catch (JsonProcessingException e) {

				e.printStackTrace();
				response.put("ui", Map.of());
			}

			finalResponse.add(response);

		});

		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), finalResponse);
	}

}

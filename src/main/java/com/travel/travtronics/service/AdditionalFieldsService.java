package com.travel.travtronics.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.travtronics.bpf.model.InputTypeConfig;
import com.travel.travtronics.bpf.repository.AdditionalFieldsRepository;
import com.travel.travtronics.bpf.repository.InputTypeConfigRepository;
import com.travel.travtronics.converter.InputTypeConfigConverter;
import com.travel.travtronics.converter.ServiceTypeConveter;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.AdditionalFieldsResponse;
import com.travel.travtronics.response.ConfigResponse;
import com.travel.travtronics.response.DependentFieldData;
import com.travel.travtronics.response.FeildLinesResponseDto;
import com.travel.travtronics.util.Status;

@Service
public class AdditionalFieldsService {

	@Autowired
	AdditionalFieldsRepository additionalFieldsRepository;

	@Autowired
	InputTypeConfigRepository configRepository;

	Logger logger = LoggerFactory.getLogger(this.getClass());

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

	public APIResponse getadditionalFieldsByHeaderId(Long headerId, Long isPricing) {

		List<AdditionalFieldsResponse> collect = additionalFieldsRepository
				.findAllByHeaderIdAndStatus(headerId, Status.Active).stream()
				.map(ServiceTypeConveter::convertAdditionalFieldModelToJson).peek(model -> {
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
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), collect);
	}

	public ConfigResponse fetchInputConfigWithType(Integer configId) {
		ConfigResponse response = new ConfigResponse();
		InputTypeConfig configModel = configRepository.findByConfigId(configId != null ? configId : 0);
		response = InputTypeConfigConverter
				.convertModelToResponse(configModel != null ? configModel : new InputTypeConfig());
		String findByTypeId = configRepository.findByTypeId(response.getType() != null ? response.getType() : 0);
		response.setTypeName(findByTypeId != null ? findByTypeId : null);
		return response;
	}
}
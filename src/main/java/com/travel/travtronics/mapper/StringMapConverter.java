package com.travel.travtronics.mapper;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StringMapConverter implements AttributeConverter<Map<String, Object>, String> {

	private static final ObjectMapper mapper = new ObjectMapper();

	@Override
	public String convertToDatabaseColumn(Map<String, Object> data) {
		if (null == data || data.isEmpty()) {
			return "{}";
		}
		try {
			return mapper.writeValueAsString(data);
		} catch (JsonProcessingException e) {

			e.printStackTrace();
			return "";
		}
	}

	@Override
	public Map<String, Object> convertToEntityAttribute(String dbData) {

		if (null == dbData || dbData.equals("{}") || dbData.isBlank()) {
			return new HashMap<>();
		}
		try {
			Map<String, Object> anxJson = mapper.readValue(dbData, new TypeReference<Map<String, Object>>() {
			});
			return removeSpacesFromKeys(anxJson);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return Collections.emptyMap();
		} catch (JsonProcessingException e) {

			e.printStackTrace();
			return Collections.emptyMap();
		}

	}

	private static Map<String, Object> removeSpacesFromKeys(Map<String, Object> anxJson) {
		Map<String, Object> modifiedMap = new HashMap<>();
		for (Map.Entry<String, Object> entry : anxJson.entrySet()) {
			String modifiedKey = entry.getKey().replaceAll("\\s+", "");
			if (entry.getValue() instanceof Map) {
				modifiedMap.put(modifiedKey, removeSpacesFromKeys((Map<String, Object>) entry.getValue()));
			} else {
				modifiedMap.put(modifiedKey, entry.getValue());
			}
		}
		return modifiedMap;
	}

}

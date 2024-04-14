package com.travel.travtronics.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MapListConverter implements AttributeConverter<List<Map<Object, Object>>, String> {

	ObjectMapper mapper = new ObjectMapper();

	@Override
	public String convertToDatabaseColumn(List<Map<Object, Object>> stringList) {

		try {
			if (stringList == null || stringList.isEmpty())
				return "[]";
			return mapper.writeValueAsString(stringList);
		} catch (Exception e) {

			return "[]";
		}
	}

	@Override
	public List<Map<Object, Object>> convertToEntityAttribute(String string) {

		try {
			if (string == null || string.isBlank())
				return Collections.emptyList();
			return mapper.readValue(string, new TypeReference<List<Map<Object, Object>>>() {
			});
		} catch (Exception e) {

			return Collections.emptyList();
		}
	}

}

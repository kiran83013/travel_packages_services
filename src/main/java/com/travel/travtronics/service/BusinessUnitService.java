package com.travel.travtronics.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.travel.travtronics.bpf.repository.BusinessUnitRepository;
import com.travel.travtronics.response.APIResponse;

@Service
public class BusinessUnitService {

	@Autowired
	BusinessUnitRepository bunitRepository;

	public APIResponse getAll(Long organizationId) {
		try {
			List<Map<String, String>> list = bunitRepository.findByList(organizationId);
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), new ArrayList<>());
		}
	}

}

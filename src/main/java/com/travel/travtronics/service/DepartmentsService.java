package com.travel.travtronics.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.travel.travtronics.bpf.repository.DepartmentsRepository;
import com.travel.travtronics.response.APIResponse;

@Service
public class DepartmentsService {

	@Autowired
	DepartmentsRepository departmentsRepository;

	public APIResponse list(Long organizationId) {
		try {
			List<Map<String, String>> list = departmentsRepository.findAllList(organizationId);
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}

	}

}

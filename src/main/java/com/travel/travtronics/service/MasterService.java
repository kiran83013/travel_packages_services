package com.travel.travtronics.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.travel.travtronics.bpf.repository.MasterDao;
import com.travel.travtronics.converter.MasterConverter;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.MasterResponse;
import com.travel.travtronics.util.QueryConst;

@Service
public class MasterService {

	private final MasterDao masterDao;

	public MasterService(MasterDao masterDao) {
		super();
		this.masterDao = masterDao;

	}

	public APIResponse getAll(String tableName) {
		String sql = String.format(QueryConst.GET_ALL, tableName);
		List<MasterResponse> responses = masterDao.getAll(sql).stream().map(MasterConverter::convertModelToJson)
				.collect(Collectors.toList());
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), responses);
	}

}

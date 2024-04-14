package com.travel.travtronics.bpf.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.travel.travtronics.bpf.model.MasterModel;
import com.travel.travtronics.request.dto.MasterRowMapper;

@Component
public class MasterDao {

	@Autowired
	private final NamedParameterJdbcTemplate jdbcTemplate;

	public MasterDao(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<MasterModel> getAll(String sql) {
		return jdbcTemplate.query(sql, new MasterRowMapper());
	}

}

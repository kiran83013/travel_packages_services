package com.travel.travtronics.request.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.travel.travtronics.bpf.model.MasterModel;

public class MasterRowMapper implements RowMapper<MasterModel> {

	@Override
	public MasterModel mapRow(ResultSet rs, int rowNum) throws SQLException {

		MasterModel model = new MasterModel();

		model.setId(rs.getInt("ID"));
		model.setName(rs.getString("name"));
		model.setCode(rs.getString("code"));
		model.setStatus(rs.getBoolean("status"));
		model.setDescription(rs.getString("description"));
		model.setCreatedBy(rs.getInt("created_by"));
		model.setUpdatedBy(rs.getInt("updated_by"));
		model.setCreatedDate(rs.getString("created_date"));
		model.setUpdatedDate(rs.getString("updated_date"));
		return model;

	}

}

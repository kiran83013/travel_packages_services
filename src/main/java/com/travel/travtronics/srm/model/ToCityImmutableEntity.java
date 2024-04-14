package com.travel.travtronics.srm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;

import org.hibernate.annotations.Subselect;
import org.springframework.data.annotation.Immutable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

@Immutable
@Subselect(value = "SELECT\r\n" + "to_cities.`id`,\r\n" + "to_cities.`header_id` as header_id,\r\n"
		+ "airports.`City` AS city_name,\r\n" + "airports.Code AS city_code,\r\n" + "airports.ID AS city_id\r\n"
		+ "FROM\r\n" + "`srm`.`package_config_to_cites` to_cities\r\n"
		+ "JOIN `masters_srm`.`master_airports` airports ON airports.`Code`=to_cities.`city_code` AND airports.`Status`=1 AND airports.`Type`='C' ")
@Getter
@Entity
public class ToCityImmutableEntity {

	@Id
	@Column(name = "id", insertable = false, updatable = false)
	private Long id;

	@Column(name = "header_id", insertable = false, updatable = false)
	private Long headerId;

	@Column(name = "city_name", insertable = false, updatable = false)
	private String cityName;
	@Column(name = "city_code", insertable = false, updatable = false)
	private String cityCode;

	@Column(name = "city_id", insertable = false, updatable = false)
	private Long cityId;

}

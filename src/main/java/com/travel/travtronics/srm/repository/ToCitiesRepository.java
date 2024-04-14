package com.travel.travtronics.srm.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.srm.model.ToCities;

public interface ToCitiesRepository extends JpaRepository<ToCities, Long>{

	@Query(value = "SELECT ma.ID AS cityId, ma.City AS cityName, ma.Code AS cityCode\r\n" + 
			"FROM srm.package_config_to_cites AS pcpt\r\n" + 
			"LEFT JOIN masters_srm.master_airports AS ma ON pcpt.city_code = ma.Code\r\n" + 
			"WHERE ma.Type = \"C\"\r\n" + 
			"AND\r\n" + 
			"pcpt.header_id = ?1",nativeQuery = true)
	List<Map<String, String>> findByHeaderId(Long id);

	Optional<ToCities> findByHeaderIdAndCityCode(Long id, String code);

}

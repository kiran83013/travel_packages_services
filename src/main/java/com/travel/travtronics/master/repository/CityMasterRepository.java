package com.travel.travtronics.master.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.travel.travtronics.master.model.CitiesMaster;

public interface CityMasterRepository extends JpaRepository<CitiesMaster, Long> {

	@Query(value = "SELECT * FROM masters_srm.master_cities WHERE CityCode IN (:cityCodes) AND status = 1", nativeQuery = true)
	List<CitiesMaster> getCtyListByCodes(@Param("cityCodes") List<String> cityCodes);

}

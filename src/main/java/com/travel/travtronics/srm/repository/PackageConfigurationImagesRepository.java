package com.travel.travtronics.srm.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.travel.travtronics.srm.model.PackageConfigurationImages;
import com.travel.travtronics.util.QueryConst;

public interface PackageConfigurationImagesRepository extends CrudRepository<PackageConfigurationImages, Long> {

	List<PackageConfigurationImages> findByPackageId(Long packageId);

	@Query(value = QueryConst.GET_SR_INFO_BY_ID_QRY, nativeQuery = true)
	Map<String, Object> getSrInfoById(Long srId);

}

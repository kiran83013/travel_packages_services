package com.travel.travtronics.srm.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.travel.travtronics.srm.model.MedInfo;
import com.travel.travtronics.util.QueryConst;

public interface MedInfoRepository extends JpaRepository<MedInfo, Long> {

	@Query(value = "SELECT * FROM package_configuration_header AS pch WHERE pch.id = ?1", nativeQuery = true)
	Optional<MedInfo> findByIdList(Long id);
	
	
	Optional<MedInfo> findById(Long id);

//	List<MedInfo> search(String medName, Date validFrom, Date validTo, Long createdBy, Long updatedBy,
//			String primaryTags, String secondaryTags);

	@Query(value = "SELECT * FROM package_configuration_header m WHERE "
			+ "(:medName is null or m.med_name = :medName) " + "and (:validFrom is null or m.valid_from >= :validFrom) "
			+ "and (:validTo is null or m.valid_to <= :validTo) "
			+ "and (:createdBy is null or m.created_by = :createdBy) "
			+ "and (:updatedBy is null or m.updated_by = :updatedBy) "
			+ "and (:medCategory is null or m.med_category = :medCategory) "
			+ "and (:medType is null or m.med_type = :medType)"
			+ "and (:recordStatus is null or m.record_status = :recordStatus)", nativeQuery = true)
	List<MedInfo> search(@Param("medName") String medName, @Param("validFrom") Date validFrom,
			@Param("validTo") Date validTo, @Param("createdBy") Long createdBy, @Param("updatedBy") Long updatedBy,
			@Param("medCategory") Long medCategory, @Param("medType") Long medType, @Param("recordStatus") String recordStatus);

	@Query(value = QueryConst.GET_SR_INFO_BY_ID_QRY, nativeQuery = true)
	Map<String, Object> getSrInfoById(Long srId);


}

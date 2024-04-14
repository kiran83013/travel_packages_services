package com.travel.travtronics.bpf.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.bpf.model.GenerateSetupMaster;

public interface GenerateSetupMasterRepository extends JpaRepository<GenerateSetupMaster, Long> {
	@Query(value = "SELECT mgs.id,mgs.name,mgs.code,mgs.description,mgs.org_id AS orgId,mgs.category_id AS categoryId,mgs.created_by AS createdBy,mgs.created_date AS createdDate, mgs.updated_by AS updatedBy,\r\n"
			+ "mgs.updated_date AS updatedDate,mgs.status,mc.name AS categoryName,mo.Name AS organizationName  FROM master_general_setup mgs\r\n"
			+ "LEFT JOIN  master_category mc ON mc.category_id  = mgs.category_id\r\n"
			+ "LEFT JOIN  master_organization mo ON mo.OrganizationId = mgs.org_id WHERE mgs.org_id =?1 AND mgs.category_id=?2", nativeQuery = true)
	List<Map<String, String>> findByOrgIdAndCategoryId(Long orgId, Long categoryId);

}

package com.travel.travtronics.bpf.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.bpf.model.Departments;

public interface DepartmentsRepository extends JpaRepository<Departments, Long> {



	@Query(value = "SELECT md.DepartmentId AS departmentId,\r\n" + "md.AccCode AS accCode,\r\n"
			+ "md.Attr1 AS attr1,\r\n" + "md.Attr10 AS attr10,\r\n" + "md.Attr11 AS attr11,\r\n"
			+ "md.Attr12 AS attr12,\r\n" + "md.Attr13 AS attr13,\r\n" + "md.Attr14 AS attr14,\r\n"
			+ "md.Attr15 AS attr15,\r\n" + "md.Attr16 AS attr16,\r\n" + "md.Attr17 AS attr17,\r\n"
			+ "md.Attr18 AS attr18,\r\n" + "md.Attr19 AS attr19,\r\n" + "md.Attr2 AS attr2,\r\n"
			+ "md.Attr20 AS attr20,\r\n" + "md.Attr3 AS attr3,\r\n" + "md.Attr4 AS attr4,\r\n"
			+ "md.Attr5 AS attr5,\r\n" + "md.Attr6 AS attr6,\r\n" + "md.Attr7 AS attr7,\r\n" + "md.Attr8 AS attr8,\r\n"
			+ "md.Attr9 AS attr9,\r\n" + "md.Code AS code,\r\n" + "md.CreatedBy AS createdBy,\r\n"
			+ "md.CreatedDate AS createdDtae,\r\n" + "md.Description AS description,\r\n" + "md.EndDate AS endDate,\r\n"
			+ "md.Name AS name,\r\n" + "md.OrganizationId AS organizationId,\r\n" + "mo.Name AS organizationName,\r\n"
			+ "md.StartDate AS startDate,\r\n" + "md.Status AS status,\r\n" + "md.UpdatedBy AS updatedBy,\r\n"
			+ "md.UpdatedDate AS updatedDate FROM bpf.master_departments AS md\r\n"
			+ "INNER JOIN bpf.master_organization mo ON mo.OrganizationId = md.OrganizationId And md.Status='Active' And mo.OrganizationId =?1 ", nativeQuery = true)
	List<Map<String, String>> findAllList(Long organizationId);


}

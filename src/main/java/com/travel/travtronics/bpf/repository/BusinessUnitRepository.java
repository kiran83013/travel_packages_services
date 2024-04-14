package com.travel.travtronics.bpf.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.bpf.model.BusinessUnit;

public interface BusinessUnitRepository extends JpaRepository<BusinessUnit, Long> {

	

	@Query(value = "SELECT bu.BusinessUnitId AS businessUnitId, bu.AccCode AS accCode, bu.Name AS name, bu.OrganizationId AS organizationId, \r\n"
			+ "bu.StartDate AS startDate, bu.Status AS status, bu.CreatedBy AS createdBy, bu.CreatedDate AS createdDate, bu.UpdatedBy AS updatedBy,\r\n"
			+ "bu.UpdatedDate AS updatedDate,bu.Code AS code, bu.Description AS description, bu.EndDate AS endDate,mo.Name AS organizationName,\r\n"
			+ "bu.Attr1 AS attr1, bu.Attr2 AS attr2, bu.Attr3 AS attr3, bu.Attr4 AS attr4, bu.Attr5 AS attr5, bu.Attr6 AS attr6, bu.Attr7 AS attr7,\r\n"
			+ "bu.Attr8 AS attr8, bu.Attr9 AS attr9, bu.Attr10 AS attr10, bu.Attr11 AS attr11, bu.Attr12 AS attr12, bu.Attr13 AS attr13, bu.Attr14 AS attr14,\r\n"
			+ "bu.Attr15 AS attr15, bu.Attr16 AS attr16, bu.Attr17 AS attr17, bu.Attr18 AS attr18, bu.Attr19 AS attr19,  bu.Attr20 AS attr20 FROM bpf.business_unit bu\r\n"
			+ "LEFT JOIN bpf.master_organization mo ON mo.OrganizationId = bu.OrganizationId WHERE bu.OrganizationId =?1", nativeQuery = true)
	List<Map<String, String>> findByList(Long organizationId);


}

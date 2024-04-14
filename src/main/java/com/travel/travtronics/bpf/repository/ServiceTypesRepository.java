package com.travel.travtronics.bpf.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.travel.travtronics.bpf.model.ServiceTypesHeader;
import com.travel.travtronics.dtos.ServiceTypeHeaderResponse;

public interface ServiceTypesRepository extends JpaRepository<ServiceTypesHeader, Long> {

	@Query(value = "SELECT st.ID AS id, st.CreatedBy AS createdBy, st.CreatedDate AS createdDate, \r\n"
			+ "st.UpdatedBy AS updatedBy, st.UpdatedDate AS updatedDate, \r\n"
			+ "st.DepartmentId AS departmentId, st.Description AS description,\r\n"
			+ "st.Status AS status, st.name, md.Name AS departmentName,\r\n"
			+ "st.OrganizationId AS organizationId, mo.name AS organizationName,\r\n"
			+ "st.ParentId AS parentId, mp.name AS parentName, st.Is_Pricing AS isPricing, st.Receipt_Confirm_Template AS receiptConfirmTemplate\r\n"
			+ "FROM bpf.service_types_header st\r\n"
			+ "LEFT JOIN bpf.master_departments md ON md.DepartmentId= st.DepartmentId\r\n"
			+ "LEFT JOIN bpf.master_organization mo ON mo.OrganizationId = st.OrganizationId\r\n"
			+ "LEFT JOIN bpf.master_parent mp ON mp.ID= st.ParentId\r\n"
			+ "WHERE st.Status='Active' AND st.organizationId = ?1", nativeQuery = true)
	List<Map<String, String>> findAllByList(String organizationId);
	
	@Query(nativeQuery = true)
	Optional<ServiceTypeHeaderResponse> findByServiceTypeId(Long id);
	
	@Query(value = "SELECT esr.ServiceUrl AS serviceUrl FROM bpf.e_service_register esr WHERE esr.ID =:service", nativeQuery = true)
	Optional<String> findByServiceUrl(@Param("service") String string);

	@Query(value = "SELECT esr.IsExternalUrl AS isExternalUrl FROM bpf.e_service_register esr WHERE esr.ID =:service", nativeQuery = true)
	Optional<Boolean> findByIsExternalUrl(@Param("service") String service);

}

package com.travel.travtronics.bpf.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.travel.travtronics.bpf.model.InputTypeConfig;


public interface InputTypeConfigRepository extends JpaRepository<InputTypeConfig, Integer> {

	InputTypeConfig findByConfigId(Integer configId);

	@Query(value = "SELECT * FROM input_type_config WHERE ID=?1", nativeQuery = true)
	Optional<InputTypeConfig> getInputTypeInfoById(Integer fieldId);

	@Query(value = "SELECT NAME FROM master_fieldtype WHERE ID=?1", nativeQuery = true)
	String findByTypeId(Integer id);

	List<InputTypeConfig> findAllByOrganizationId(Long organizationId);

	Page<InputTypeConfig> findByOrganizationId(Long organizationId, Pageable pageable);

	@Query(value = "SELECT l.Name AS TypeName  FROM  master_fieldtype  l WHERE l.Id=?1", nativeQuery = true)
	Optional<String> getTypeName(Integer id);

	@Query("select c.name from InputTypeConfig c where c.configId=?1")
	Optional<String> getFeildName(Integer feildId);

	@Query(value = "SELECT esr.ServiceUrl AS serviceUrl FROM  e_service_register esr WHERE esr.ID =:serviceId",nativeQuery = true)
	Optional<String> findByServiceUrl(@Param("serviceId") Long serviceId);

	@Query(value = "SELECT esr.IsExternalUrl AS isExternalUrl FROM  e_service_register esr WHERE esr.ID =:serviceId",nativeQuery = true)
	Optional<Boolean> findByIsExternalUrl(@Param("serviceId") Long serviceId);

	@Query(value = "SELECT esr.ServiceUrl AS serviceUrl, esr.IsExternalUrl AS isExternalUrl FROM  e_service_register esr WHERE esr.ID =:serviceId",nativeQuery = true)
	List<Object[]> findByServiceIdAndIsExternalUrl(@Param("serviceId") Long serviceId);

}

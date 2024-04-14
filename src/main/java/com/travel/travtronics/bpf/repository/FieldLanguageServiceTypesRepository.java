package com.travel.travtronics.bpf.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.bpf.model.FieldLanguageServiceTypes;

public interface FieldLanguageServiceTypesRepository extends JpaRepository<FieldLanguageServiceTypes, Integer> {

	@Query(value = "SELECT service_types_header.ID AS service_type_id,\r\n"
			+ "service_types_header.Name AS actual_name,field_language_service_types.lang_name,\r\n"
			+ "field_language_service_types.name,field_language_service_types.created_by,\r\n"
			+ "field_language_service_types.created_date,field_language_service_types.updated_by,\r\n"
			+ "service_types_header.Instructions actual_instructions,field_language_service_types.`instructions`,\r\n"
			+ "field_language_service_types.updated_date,field_language_service_types.`id`,field_language_service_types.`header_id` FROM service_types_header\r\n"
			+ "LEFT JOIN field_language_service_types ON service_types_header.ID = field_language_service_types.service_type_id AND lang_id = ?1 AND field_language_service_types.org_id = ?2\r\n"
			+ "WHERE 1=1 AND service_types_header.organizationId = ?2", countQuery = "SELECT \r\n" + "COUNT(*)\r\n"
					+ "FROM service_types_header\r\n"
					+ "LEFT JOIN field_language_service_types ON service_types_header.ID = field_language_service_types.service_type_id AND lang_id = ?1 AND field_language_service_types.org_id = ?2\r\n"
					+ "WHERE 1=1 \r\n" + "AND service_types_header.organizationId = ?2", nativeQuery = true)
	Page<Map<String, Object>> getAllSrTypeInfo(Integer langId, Integer orgId, Pageable page);

	@Query(value = "SELECT service_types_header.ID AS service_type_id,\r\n"
			+ "service_types_header.Name AS actual_name,field_language_service_types.lang_name,\r\n"
			+ "field_language_service_types.name,field_language_service_types.created_by,\r\n"
			+ "field_language_service_types.created_date,field_language_service_types.updated_by,\r\n"
			+ "service_types_header.Instructions actual_instructions,field_language_service_types.`instructions`,\r\n"
			+ "field_language_service_types.updated_date,field_language_service_types.`id`,field_language_service_types.`header_id` FROM service_types_header\r\n"
			+ "LEFT JOIN field_language_service_types ON service_types_header.ID = field_language_service_types.service_type_id AND lang_id = ?1 AND field_language_service_types.org_id = ?2\r\n"
			+ "WHERE 1=1 AND service_types_header.organizationId = ?2 AND  field_language_service_types.lang_name IS  NULL", countQuery = "SELECT COUNT(1) FROM service_types_header\r\n"
					+ "LEFT JOIN field_language_service_types ON service_types_header.ID = field_language_service_types.service_type_id AND lang_id =?1 AND field_language_service_types.org_id = ?2\r\n"
					+ "WHERE 1=1 AND service_types_header.organizationId = ?2 AND  field_language_service_types.lang_name IS  NULL", nativeQuery = true)
	Page<Map<String, Object>> getAllNonExistsSrTypeInfo(Integer langId, Integer orgId, Pageable page);

	@Query(value = "SELECT service_types_header.ID AS service_type_id,\r\n"
			+ "service_types_header.Name AS actual_name,field_language_service_types.lang_name,\r\n"
			+ "field_language_service_types.name,field_language_service_types.created_by,\r\n"
			+ "field_language_service_types.created_date,field_language_service_types.updated_by,\r\n"
			+ "service_types_header.Instructions actual_instructions,field_language_service_types.`instructions`,\r\n"
			+ "field_language_service_types.updated_date,field_language_service_types.`id`,field_language_service_types.`header_id` FROM service_types_header\r\n"
			+ "LEFT JOIN field_language_service_types ON service_types_header.ID = field_language_service_types.service_type_id AND lang_id = ?1 AND field_language_service_types.org_id = ?2\r\n"
			+ "WHERE 1=1 AND service_types_header.organizationId = ?2 AND  field_language_service_types.lang_name IS NOT NULL", countQuery = "SELECT \r\n"
					+ "COUNT(*)\r\n" + "FROM service_types_header\r\n"
					+ "LEFT JOIN field_language_service_types ON service_types_header.ID = field_language_service_types.service_type_id AND lang_id = ?1 AND field_language_service_types.org_id = ?2\r\n"
					+ "WHERE 1=1 \r\n" + "AND service_types_header.organizationId = ?2\r\n"
					+ "AND field_language_service_types.lang_name IS NOT NULL", nativeQuery = true)
	Page<Map<String, Object>> getAllExistsSrTypeInfo(Integer langId, Integer orgId, Pageable page);

	@Query(value = "SELECT service_types_header.ID AS service_type_id,\r\n"
			+ "service_types_header.Name AS actual_name,field_language_service_types.lang_name,\r\n"
			+ "field_language_service_types.name,field_language_service_types.created_by,\r\n"
			+ "field_language_service_types.created_date,field_language_service_types.updated_by,\r\n"
			+ "service_types_header.Instructions actual_instructions,field_language_service_types.`instructions`,\r\n"
			+ "field_language_service_types.updated_date,field_language_service_types.`id`,field_language_service_types.`header_id` FROM service_types_header\r\n"
			+ "LEFT JOIN field_language_service_types ON service_types_header.ID = field_language_service_types.service_type_id AND lang_id = ?1 AND field_language_service_types.org_id = ?2\r\n"
			+ "WHERE 1=1 AND service_types_header.organizationId = ?2 AND  field_language_service_types.lang_name IS NULL", nativeQuery = true)
	List<Map<String, Object>> getAllNonExistsSrTypeList(Integer langId, Integer orgId);

	Optional<FieldLanguageServiceTypes> findByOrgIdAndServiceTypeIdAndLangCode(Integer orgId, Integer serviceTypeId,
			String lanCode);
}

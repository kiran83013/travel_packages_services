package com.travel.travtronics.bpf.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.bpf.model.TaxSlabHeader;
import com.travel.travtronics.request.dto.TaxTemplateDto;

public interface TaxHeaderRepository extends JpaRepository<TaxSlabHeader, Long> {
	List<TaxSlabHeader> findByTaxCategory(Long id);

	@Query(value = "SELECT tsh.TaxCategory AS taxCategoryId, tsl.TaxType AS taxId, mtt.name AS taxName, mtt.code AS taxCode, \r\n"
			+ "mtt.description AS taxDescription,tsl.BudgetFrom AS budgetFrom,tsl.BudgetTo AS budgetTo, \r\n"
			+ "tsl.SlabPercentage AS slabPercentage,tsl.SlabAmount AS slabAmount,tsh.OrganizationId AS \r\n"
			+ "organizationId,mo.Name AS organizationName FROM tax_slab_header tsh INNER JOIN tax_slab_lines tsl ON\r\n"
			+ "tsl.TaxSlabHeaderId = tsh.ID INNER JOIN master_tax_types mtt ON mtt.ID = tsl.TaxType\r\n"
			+ "LEFT JOIN master_organization AS mo ON mo.OrganizationId = tsh.OrganizationId \r\n"
			+ "WHERE tsh.TaxCategory = ?1 AND \r\n" + "tsh.OrganizationId=?2 AND \r\n"
			+ "tsh.Status = 'Active'", nativeQuery = true)
	List<TaxTemplateDto> getTaxLinesInfoByTaxCategory(Integer taxCategoryId, Integer orgId);

	List<TaxSlabHeader> findAllByOrganizationId(Long organizationId);

	@Query("select count(*) from TaxSlabHeader h where h.organizationId=?1")
	Long countByOrganizationId(Long organizationId);

	@Query("select count(*) from TaxSlabHeader h")
	Long countAll();

	Page<TaxSlabHeader> findByOrganizationId(Long organizationId, Pageable pageable);

}

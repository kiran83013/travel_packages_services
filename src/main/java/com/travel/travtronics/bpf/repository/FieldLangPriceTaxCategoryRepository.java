package com.travel.travtronics.bpf.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.travel.travtronics.bpf.model.FieldLangPriceTaxCategory;

public interface FieldLangPriceTaxCategoryRepository extends CrudRepository<FieldLangPriceTaxCategory, Integer> {

	@Query(value = "SELECT \r\n"
			+ "`master_tax_category`.`ID` AS tax_category_id,`master_tax_category`.`name` AS actual_name,\r\n"
			+ "`field_language_price_tax_category`.`lang_name`,`field_language_price_tax_category`.`tax_category_name`,\r\n"
			+ "`field_language_price_tax_category`.`created_by`,`field_language_price_tax_category`.`created_date`,\r\n"
			+ "`field_language_price_tax_category`.`updated_by`,`field_language_price_tax_category`.`updated_date`,"
			+ "`field_language_price_tax_category`.`id`,`field_language_price_tax_category`.`header_id`\r\n"
			+ "FROM `master_tax_category`\r\n"
			+ "LEFT JOIN `tax_slab_header` ON `tax_slab_header`.`TaxCategory`=`master_tax_category`.`ID`\r\n"
			+ "LEFT JOIN `field_language_price_tax_category` ON `field_language_price_tax_category`.`tax_category_id`=`tax_slab_header`.`TaxCategory`\r\n"
			+ "AND lang_id=?1 AND `field_language_price_tax_category`.`org_id`=?2\r\n"
			+ "WHERE 1=1 AND `tax_slab_header`.`OrganizationId`=?2 AND `field_language_price_tax_category`.`lang_name` IS NULL", nativeQuery = true)
	List<Map<String, Object>> getNullTaxCategoryList(Integer langId, Integer orgId);

	@Query(value = "SELECT \r\n"
			+ "`master_tax_category`.`ID` AS tax_category_id,`master_tax_category`.`name` AS actual_name,\r\n"
			+ "`field_language_price_tax_category`.`lang_name`,`field_language_price_tax_category`.`tax_category_name`,\r\n"
			+ "`field_language_price_tax_category`.`created_by`,`field_language_price_tax_category`.`created_date`,\r\n"
			+ "`field_language_price_tax_category`.`updated_by`,`field_language_price_tax_category`.`updated_date`"
			+ ",`field_language_price_tax_category`.`id`,`field_language_price_tax_category`.`header_id`\r\n"
			+ "FROM `master_tax_category`\r\n"
			+ "LEFT JOIN `tax_slab_header` ON `tax_slab_header`.`TaxCategory`=`master_tax_category`.`ID`\r\n"
			+ "LEFT JOIN `field_language_price_tax_category` ON `field_language_price_tax_category`.`tax_category_id`=`tax_slab_header`.`TaxCategory`\r\n"
			+ "AND lang_id=?1 AND `field_language_price_tax_category`.`org_id`=?2\r\n"
			+ "WHERE 1=1 AND `tax_slab_header`.`OrganizationId`=?2 AND `field_language_price_tax_category`.`lang_name` IS NULL", countQuery = "SELECT \r\n"
					+ "COUNT(1)\r\n" + "FROM `master_tax_category`\r\n"
					+ "LEFT JOIN `tax_slab_header` ON `tax_slab_header`.`TaxCategory`=`master_tax_category`.`ID`\r\n"
					+ "LEFT JOIN `field_language_price_tax_category` ON `field_language_price_tax_category`.`tax_category_id`=`tax_slab_header`.`TaxCategory`\r\n"
					+ "AND lang_id=?1 AND `field_language_price_tax_category`.`org_id`=?2\r\n"
					+ "WHERE 1=1 AND `tax_slab_header`.`OrganizationId`=?2 AND `field_language_price_tax_category`.`lang_name` IS NULL", nativeQuery = true)
	Page<Map<String, Object>> getNullTaxCategoryPagination(Integer langId, Integer orgId, Pageable page);

	@Query(value = "SELECT \r\n"
			+ "`master_tax_category`.`ID` AS tax_category_id,`master_tax_category`.`name` AS actual_name,\r\n"
			+ "`field_language_price_tax_category`.`lang_name`,`field_language_price_tax_category`.`tax_category_name`,\r\n"
			+ "`field_language_price_tax_category`.`created_by`,`field_language_price_tax_category`.`created_date`,\r\n"
			+ "`field_language_price_tax_category`.`updated_by`,`field_language_price_tax_category`.`updated_date`,"
			+ "`field_language_price_tax_category`.`id`,`field_language_price_tax_category`.`header_id`\r\n"
			+ "FROM `master_tax_category`\r\n"
			+ "LEFT JOIN `tax_slab_header` ON `tax_slab_header`.`TaxCategory`=`master_tax_category`.`ID`\r\n"
			+ "LEFT JOIN `field_language_price_tax_category` ON `field_language_price_tax_category`.`tax_category_id`=`tax_slab_header`.`TaxCategory`\r\n"
			+ "AND lang_id=?1 AND `field_language_price_tax_category`.`org_id`=?2\r\n"
			+ "WHERE 1=1 AND `tax_slab_header`.`OrganizationId`=?2", countQuery = "SELECT \r\n" + "COUNT(1)\r\n"
					+ "FROM `master_tax_category`\r\n"
					+ "LEFT JOIN `tax_slab_header` ON `tax_slab_header`.`TaxCategory`=`master_tax_category`.`ID`\r\n"
					+ "LEFT JOIN `field_language_price_tax_category` ON `field_language_price_tax_category`.`tax_category_id`=`tax_slab_header`.`TaxCategory`\r\n"
					+ "AND lang_id=?1 AND `field_language_price_tax_category`.`org_id`=?2\r\n"
					+ "WHERE 1=1 AND `tax_slab_header`.`OrganizationId`=?2", nativeQuery = true)
	Page<Map<String, Object>> getAllTaxCategoryPagination(Integer langId, Integer orgId, Pageable page);

	@Query(value = "SELECT \r\n"
			+ "`master_tax_category`.`ID` AS tax_category_id,`master_tax_category`.`name` AS actual_name,\r\n"
			+ "`field_language_price_tax_category`.`lang_name`,`field_language_price_tax_category`.`tax_category_name`,\r\n"
			+ "`field_language_price_tax_category`.`created_by`,`field_language_price_tax_category`.`created_date`,\r\n"
			+ "`field_language_price_tax_category`.`updated_by`,`field_language_price_tax_category`.`updated_date` ,"
			+ "`field_language_price_tax_category`.`id`,`field_language_price_tax_category`.`header_id`\r\n"
			+ "FROM `master_tax_category`\r\n"
			+ "LEFT JOIN `tax_slab_header` ON `tax_slab_header`.`TaxCategory`=`master_tax_category`.`ID`\r\n"
			+ "LEFT JOIN `field_language_price_tax_category` ON `field_language_price_tax_category`.`tax_category_id`=`tax_slab_header`.`TaxCategory`\r\n"
			+ "AND lang_id=?1 AND `field_language_price_tax_category`.`org_id`=?2\r\n"
			+ "WHERE 1=1 AND `tax_slab_header`.`OrganizationId`=?2 AND `field_language_price_tax_category`.`lang_name` IS NOT NULL", countQuery = "SELECT \r\n"
					+ "COUNT(1)\r\n" + "FROM `master_tax_category`\r\n"
					+ "LEFT JOIN `tax_slab_header` ON `tax_slab_header`.`TaxCategory`=`master_tax_category`.`ID`\r\n"
					+ "LEFT JOIN `field_language_price_tax_category` ON `field_language_price_tax_category`.`tax_category_id`=`tax_slab_header`.`TaxCategory`\r\n"
					+ "AND lang_id=?1 AND `field_language_price_tax_category`.`org_id`=?2\r\n"
					+ "WHERE 1=1 AND `tax_slab_header`.`OrganizationId`=?2 AND `field_language_price_tax_category`.`lang_name` IS NOT  NULL", nativeQuery = true)
	Page<Map<String, Object>> getNotNUllTaxCategoryPagination(Integer langId, Integer orgId, Pageable page);

	@Query(value = "SELECT tax.name FROM master_tax_category tax WHERE tax.ID=?1", nativeQuery = true)
	Optional<String> getTaxCategoryName(Integer id);

	@Query("select tax from FieldLangPriceTaxCategory tax where tax.orgId=?1 and tax.taxCategoryId=?2 and tax.langCode=?3")
	Optional<FieldLangPriceTaxCategory> getTaxCategoryLocalizationInfo(int orgId, Integer taxCategoryId,
			String langCode);

}

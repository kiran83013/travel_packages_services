package com.travel.travtronics.bpf.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.travel.travtronics.bpf.model.FeildLangPriceItem;

public interface FeildLangPriceItemRepository extends CrudRepository<FeildLangPriceItem, Integer> {

	@Query(value = "SELECT `item`.`ID` AS item_id,`item`.`Name` AS actual_name,`field_language_price_item`.`lang_name`,\r\n"
			+ "`field_language_price_item`.`item_name`,`field_language_price_item`.`created_by`,`field_language_price_item`.`created_date`,\r\n"
			+ "`field_language_price_item`.`updated_by`,`field_language_price_item`.`updated_date`,`field_language_price_item`.`id`,`field_language_price_item`.`header_id`\r\n"
			+ "FROM `item`\r\n"
			+ "LEFT JOIN `field_language_price_item` ON `field_language_price_item`.`item_id`=`item`.`ID` AND lang_id=?1 AND `field_language_price_item`.`org_id`=?2\r\n"
			+ "WHERE 1=1 AND `item`.`OrganizationId`=?2 AND `field_language_price_item`.`lang_name` IS NULL", nativeQuery = true)
	List<Map<String, Object>> getNullPriceList(Integer langId, Integer orgId);

	@Query(value = "SELECT `item`.`ID` AS item_id,`item`.`Name` AS actual_name,`field_language_price_item`.`lang_name`,\r\n"
			+ "`field_language_price_item`.`item_name`,`field_language_price_item`.`created_by`,`field_language_price_item`.`created_date`,\r\n"
			+ "`field_language_price_item`.`updated_by`,`field_language_price_item`.`updated_date`,`field_language_price_item`.`id`,`field_language_price_item`.`header_id`\r\n"
			+ "FROM `item`\r\n"
			+ "LEFT JOIN `field_language_price_item` ON `field_language_price_item`.`item_id`=`item`.`ID` AND lang_id=?1 AND `field_language_price_item`.`org_id`=?2\r\n"
			+ "WHERE 1=1 AND `item`.`OrganizationId`=?2 AND `field_language_price_item`.`lang_name` IS NULL", countQuery = "SELECT COUNT(1)\r\n"
					+ "FROM `item`\r\n"
					+ "LEFT JOIN `field_language_price_item` ON `field_language_price_item`.`item_id`=`item`.`ID` AND lang_id=?1 AND `field_language_price_item`.`org_id`=?2\r\n"
					+ "WHERE 1=1 AND `item`.`OrganizationId`=?2 AND `field_language_price_item`.`lang_name` IS NULL", nativeQuery = true)
	Page<Map<String, Object>> getNullPriceListPagination(Integer langId, Integer orgId, Pageable page);

	@Query(value = "SELECT `item`.`ID` AS item_id,`item`.`Name` AS actual_name,`field_language_price_item`.`lang_name`,\r\n"
			+ "`field_language_price_item`.`item_name`,`field_language_price_item`.`created_by`,`field_language_price_item`.`created_date`,\r\n"
			+ "`field_language_price_item`.`updated_by`,`field_language_price_item`.`updated_date`,`field_language_price_item`.`id`,`field_language_price_item`.`header_id`\r\n"
			+ "FROM `item`\r\n"
			+ "LEFT JOIN `field_language_price_item` ON `field_language_price_item`.`item_id`=`item`.`ID` AND lang_id=?1 AND `field_language_price_item`.`org_id`=?2\r\n"
			+ "WHERE 1=1 AND `item`.`OrganizationId`=?2 ", countQuery = "SELECT COUNT(1)\r\n" + "FROM `item`\r\n"
					+ "LEFT JOIN `field_language_price_item` ON `field_language_price_item`.`item_id`=`item`.`ID` AND lang_id=?1 AND `field_language_price_item`.`org_id`=?2\r\n"
					+ "WHERE 1=1 AND `item`.`OrganizationId`=?2", nativeQuery = true)
	Page<Map<String, Object>> getAllPriceListPagination(Integer langId, Integer orgId, Pageable page);

	@Query(value = "SELECT `item`.`ID` AS item_id,`item`.`Name` AS actual_name,`field_language_price_item`.`lang_name`,\r\n"
			+ "`field_language_price_item`.`item_name`,`field_language_price_item`.`created_by`,`field_language_price_item`.`created_date`,\r\n"
			+ "`field_language_price_item`.`updated_by`,`field_language_price_item`.`updated_date`,`field_language_price_item`.`id`,`field_language_price_item`.`header_id`\r\n"
			+ "FROM `item`\r\n"
			+ "LEFT JOIN `field_language_price_item` ON `field_language_price_item`.`item_id`=`item`.`ID` AND lang_id=?1 AND `field_language_price_item`.`org_id`=?2\r\n"
			+ "WHERE 1=1 AND `item`.`OrganizationId`=?2 AND `field_language_price_item`.`lang_name` IS NOT NULL", countQuery = "SELECT COUNT(1)\r\n"
					+ "FROM `item`\r\n"
					+ "LEFT JOIN `field_language_price_item` ON `field_language_price_item`.`item_id`=`item`.`ID` AND lang_id=?1 AND `field_language_price_item`.`org_id`=?2\r\n"
					+ "WHERE 1=1 AND `item`.`OrganizationId`=?2 AND `field_language_price_item`.`lang_name` IS NOT  NULL", nativeQuery = true)
	Page<Map<String, Object>> getNotNullPriceListPagination(Integer langId, Integer orgId, Pageable page);

	@Query("select i from FeildLangPriceItem i where i.orgId=?1 and i.itemId=?2 and i.langCode=?3")
	Optional<FeildLangPriceItem> getLocalizationItemPrice(int orgId, Integer itemId, String langCode);

}

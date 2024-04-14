package com.travel.travtronics.bpf.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.bpf.model.PricingLines;
import com.travel.travtronics.response.PriceHearderTemplate;
import com.travel.travtronics.util.Status;

public interface PricingLineRepository extends JpaRepository<PricingLines, Long> {

	List<PricingLines> findAllByHeaderIdAndStatus(Long id, Status active);

	// PricingLines findByHeaderId(Long priceList);

	@Query(value = "SELECT pl.ID AS priceLineId,\r\n" + "i.ID AS itemId, i.Name AS itemName,\r\n"
			+ "i.Description AS itemDesc, pl.Charge AS itemPrice,\r\n"
			+ "i.TaxCategory AS taxCatId, pl.Field AS `field`,\r\n" + "pl.Api AS api,pl.IsApi AS isApi,\r\n"
			+ "pl.Operator AS operator, pl.Value AS `value`,pl.`ToOperator` AS toOperator,\r\n"
			+ "pl.`ToValue` AS toValue, pl.Percentage AS percentage,\r\n"
			+ "pl.Qualifier AS qualifier, pl.OrganizationId AS organizationId,\r\n"
			+ "mo.Name AS organizationName, pl.IsAdditinalCharge AS iacflag,\r\n"
			+ "pl.PBOUOM AS pbouom, pl.UmoValue AS umoValue,i.UnitofMeasurement,pl.IsRange AS isRange \r\n"
			+ "FROM pricing_lines pl JOIN item i ON i.ID = pl.Item LEFT JOIN \r\n"
			+ "master_organization AS mo ON mo.OrganizationId = pl.OrganizationId \r\n"
			+ "WHERE pl.TemplateHeaderId =?1 AND pl.Status = 'Active'", nativeQuery = true)
	List<PriceHearderTemplate> getPriceLineItemsInfo(Long id);

	@Query(value = "SELECT * FROM pricing_lines WHERE TemplateHeaderId = ?1 AND IsAdditinalCharge = 'Yes' AND STATUS = 'Active'", nativeQuery = true)
	List<PricingLines> getCharablePriceLinesByHeaderId(Long Id);

	@Query(value = "SELECT pl.ID AS id, pl.TemplateHeaderId AS headerId,ph.Name AS priceheaderName, pl.Item  AS item, item.`Name` AS  itemName, sth.Name AS serviceTypeHeaderName, pl.Field AS field, pl.TaxSlab AS taxSlab , pl.Api AS api, pl.Charge AS charge,\r\n"
			+ " pl.Operator AS operator, pl.Value AS value, pl.ToOperator AS toOperator, pl.ToValue AS toValue, pl.Percentage AS percentage, pl.PortalVisible AS portalVisible,\r\n"
			+ " pl.Statutory AS statutory, pl.Qualifier AS qualifier, pl.FieldDependent  AS fieldDependent, pl.VariableFrequency AS variableFrequency, pl.Mandatory AS mandatory,\r\n"
			+ " pl.IsAdditinalCharge AS isAdditinalCharge, pl.IsChargeOveride AS isChargeOveride, pl.StartDate AS startDate, pl.EndDate AS endDate,pl.Attr1 AS attr1, pl.Attr2 AS attr2,\r\n"
			+ " pl.Attr3 AS attr3, pl.CreatedBy AS createdBy, pl.UpdatedBy AS updatedBy, pl.CreatedDate AS createdDate, pl.UpdatedDate AS updatedDate,pl.Status AS status,\r\n"
			+ " pl.OrganizationId AS organizationId, pl.PBOUOM AS pbouom, pl.UmoValue AS umoValue, pl.IsApi AS isApi, pl.IsRange AS isRange, pl.PriceTemplateType AS priceTemplateType\r\n"
			+ " FROM pricing_lines pl\r\n" + " LEFT JOIN item ON item.ID = pl.Item\r\n"
			+ " LEFT JOIN pricing_header ph ON  ph.`ID`  = pl.`TemplateHeaderId`\r\n"
			+ " LEFT JOIN service_types_header sth ON   sth.ID = ph.ServiceTypeHeaderId \r\n"
			+ "WHERE pl.TemplateHeaderId = ?1 AND pl.OrganizationId = ?2 AND pl.Status = 'Active' ", nativeQuery = true)
	List<Map<String, String>> findByHeaderIdAndOrganizationsId(Long headerId, Long organizationId);

	@Query(value = "SELECT\n" + "price_header.`ID` price_header_id,\n" + "price_header.`Name` AS price_header_name,\n"
			+ "price_line.`Item` AS item,\n" + "price_line.`Field` AS `field`,\n"
			+ "field_config.`name` AS field_name,\n" + "field_config.`ui`,sr_type.`Name` AS sr_type_name\n" + "FROM\n"
			+ "`bpf`.`pricing_header` price_header\n"
			+ "JOIN `bpf`.`pricing_lines` price_line ON price_line.`TemplateHeaderId`=price_header.`ID`\n"
			+ "JOIN `bpf`.`input_type_config` field_config ON field_config.`ID`=price_line.`Field`\n"
			+ "LEFT JOIN `bpf`.`service_types_header` sr_type ON sr_type.`ID`=price_header.`ServiceTypeHeaderId`\n"
			+ "WHERE\n" + "price_header.`ServiceTypeHeaderId`=?1", nativeQuery = true)
	List<Map<String, Object>> getPriceLinesForSchdulePriceLink(Long srtypeId);
}

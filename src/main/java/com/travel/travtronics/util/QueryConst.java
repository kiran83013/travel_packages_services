package com.travel.travtronics.util;

public class QueryConst {
	
	public static final String GET_SCHEDULE_DATES_INFO_BY_SCHD_DATE_ID_QRY = "SELECT start_date AS schdFromDate, end_date AS schdToDate \r\n"
			+ "FROM srm.package_configuration_schedule_dates\r\n"
			+ "WHERE record_status = 'Active' AND id= ?1";
	
	public static final String GET_SCHEDULE_INFO_BY_SCHD_CONFIG_ID_QRY = "SELECT pcs.id AS schId, pcs.schedule_name AS scheduleName, pch.id AS pkgId, pch.`med_name` AS pkgName, pcs.sr_type_link AS serviceTypeHeaderId,\r\n"
			+ "pcs.package_schedule_text_editor AS scheduleEditorContent, pcs.description AS scheduleDesc  \r\n"
			+ "FROM `srm`.`package_configuration_schedule` pcs \r\n"
			+ "LEFT JOIN `srm`.`package_configuration_header` pch  ON pcs.package_configuration_id = pch.`id` \r\n"
			+ "WHERE pcs.id=?1";

	public static final String ITINERARY_LIST_QUERY = "SELECT pih.ITINERARYHEADERID AS id, pih.Name AS itineraryName, IF(pih.StartDate = '0000-00-00', NULL, pih.StartDate) AS startDate, \r\n"
			+ "IF(pih.EndDate = '0000-00-00', NULL, pih.EndDate) AS endDate, \r\n"
			+ "IF( a.username IS NULL, b.username,  a.username) AS agentName, pih.CreatedDate AS dateaction\r\n"
			+ "FROM package_itineraryheader pih \r\n"
			+ "LEFT JOIN a3m_account a ON a.id=pih.UpdatedBy \r\n"
			+ "LEFT JOIN a3m_account b ON b.id=pih.CreatedBy \r\n"
			+ "WHERE 1 = 1 \r\n"
			+ "AND (:itineraryName is NULL OR pih.Name LIKE :itineraryName%)";
	
	public static final String ITINERARY_INFO_QUERY = "SELECT * FROM package_itineraryheader WHERE ITINERARYHEADERID = ?1";
	
	public static final String ITINERARY_LINES_LIST_QUERY = "SELECT pkgi.*, pih.Name AS itineraryHeaderName, pa.ActivityName \r\n"
			+ "FROM package_itineraryheader pih \r\n"
			+ "LEFT JOIN package_itinerary pkgi ON pkgi.ITINERARYHEADERID = pih.ITINERARYHEADERID \r\n"
			+ "LEFT JOIN package_activity pa ON FIND_IN_SET(pa.ACTIVITYID, pkgi.ItineraryActivities) > 0 \r\n"
			+ "WHERE 1 = 1 \r\n"
			+ "AND pih.ITINERARYHEADERID = ?1 ";
	
	public static final String ITEMS_LIST_QUERY = "SELECT pkgi.ITEMID AS itemId, pkgi.ItemCode AS itemCode, pkgi.ItemName AS itemName, \r\n"
			+ "IF(pkgi.ItemStartDate = '0000-00-00', NULL, pkgi.ItemStartDate) AS itemStartDate, \r\n"
			+ "IF(pkgi.ItemEndDate = '0000-00-00', NULL, pkgi.ItemEndDate) AS itemEndDate, \r\n"
			+ "pu.UomName AS uomName,IF( a.username IS NULL, b.username, a.username) AS agentName, pkgi.CreatedDate AS itemCreatedDate \r\n"
			+ "FROM package_items pkgi \r\n"
			+ "LEFT JOIN a3m_account a ON a.id = pkgi.UpdatedBy \r\n"
			+ "LEFT JOIN a3m_account b ON b.id = pkgi.CreatedBy \r\n"
			+ "LEFT JOIN package_uom pu ON pu.UOMID = pkgi.ITEMUOM \r\n"
			+ "WHERE 1 = 1 \r\n"
			+ "AND pkgi.Status = 'A' \r\n"
			+ "AND (:itemName IS NULL OR pkgi.ItemName LIKE :itemName%)";
	
	
	public static final String QUOTES_LIST_REPORTS = "SELECT qh.id, qh.sr_id AS srId, qh.sr_line_id AS srLineId, qh.custmer_id AS custmerId, ci.BusinessName AS customerName, qh.contact_id AS contactId, \n"
			+ "CONCAT(cc.FirstName, ' ', cc.LastName) AS contactName, \n"
			+ "qh.status_id AS statusId, qh.status_code AS statusCode, COUNT(qh.sr_line_id) AS linesCount, qh.created_by AS createdBy, CONCAT(ui.first_name, ' ', ui.last_name) AS agentName, \n"
			+ "qh.created_date AS createdDate, \n"
			+ "CONCAT(su.first_name, ' ', su.last_name) AS submittedName, qh.submitted_by AS submittedBy, qh.submitted_date AS submittedDate, \n"
			+ "CONCAT(au.first_name, ' ', au.last_name) AS approvedName, qh.approved_by, qh.approved_date, \n"
			+ "CONCAT(fu.first_name, ' ', fu.last_name) AS fullfilledName, qh.fullfilled_by, qh.fullfilled_date \n"
			+ "FROM quotes_header qh \n"
			+ "INNER JOIN sr_request s ON s.REQUESTID = qh.sr_id \n"
			+ "LEFT JOIN customer_info ci ON ci.CUSTOMERID = qh.custmer_id \n"
			+ "LEFT JOIN customer_contact cc ON cc.ID = qh.contact_id \n"
			+ "LEFT JOIN user_info ui ON ui.user_id = qh.created_by \n"
			+ "LEFT JOIN user_info au ON au.user_id = qh.approved_by \n"
			+ "LEFT JOIN user_info su ON su.user_id = qh.submitted_by \n"
			+ "LEFT JOIN user_info fu ON fu.user_id = qh.fullfilled_by \n"
			+ "WHERE qh.status_code = :statucCode AND \n"
			+ "(:custmerId = 0 or qh.custmer_id = :custmerId) AND \n"
			+ "(:contactId = 0 or qh.contact_id = :contactId) AND \n"
			+ "(:srId = 0 or qh.sr_id = :srId) AND \n"
			+ "(:srCreationDate is null or DATE_FORMAT(s.CreatedDate,'%Y-%m-%d') = :srCreationDate) AND \n"
			+ "(:quoteCreationDate is null or DATE_FORMAT(qh.created_date,'%Y-%m-%d') = :quoteCreationDate) \n"
			+ "GROUP BY qh.sr_id, qh.sr_line_id, qh.status_code ORDER BY qh.id DESC";
	
	public static final String QUOTES_LIST_REPORTS_LIMIT = QUOTES_LIST_REPORTS+" LIMIT :recordsCount";
	
	public static final String GET_SR_INFO_BY_ID_QRY = "SELECT sr.CUSTOMERCONTACTID AS contactId, sr.CUSTOMERID AS customerId, sr.CreatedDate AS createdDate, \r\n"
			+ "sr.CreatedBy AS createdBy, sr.ProductId AS productId, mp.Name AS productName \r\n"
			+ "FROM srm.sr_request sr \r\n"
			+ "JOIN masters_srm.master_products mp ON mp.ID = sr.ProductId \r\n"
			+ "WHERE sr.REQUESTID = ?1";

	public static final String GET_ALL = "SELECT * FROM bpf. %s";
	
	public static final String GET_CONTACT_INFO_BY_SRID_QRY = "SELECT sr.CUSTOMERCONTACTID AS contactId, sr.CUSTOMERID AS customerId, DATE_FORMAT(sr.CreatedDate, '%Y-%m-%d %H:%i:%s') AS createdDate, \r\n"
			+ "sr.CreatedBy AS createdBy, CONCAT(cc.FirstName, ' ', cc.LastName) AS contactName, cc.CustomerId AS customerId, cc.PrimaryEmail AS mailId, \r\n"
			+ "cc.SecondaryPhoneNumber AS phoneno \r\n"
			+ "FROM sr_request sr \r\n"
			+ "LEFT JOIN srm.customer_contact cc ON cc.ID = sr.CUSTOMERCONTACTID \r\n"
			+ "WHERE sr.REQUESTID = ?1";
	
	public static final String GET_SERVICE_TYPE_INFO_DETAILS = "SELECT\n" + "service_type.ID,\n"
			+ "service_type.`CreatedBy`,\n" + "service_type.`CreatedDate`,\n" + "service_type.`UpdatedBy`,\n"
			+ "service_type.`UpdatedDate`,\n" + "service_type.`OrganizationId`,\n" + "service_type.`DepartmentId`,\n"
			+ "service_type.`ServiceTypeGroup`,\n"
			+ "service_type.`Name`,service_type.`DesktopEnabled`,service_type.`MobileEnabled`,\n"
			+ "service_type.`IsDynamicForm`,service_type.`FormUrl`,service_type.`Status`,service_type.`Description`,service_type.`PreValidations`,\n"
			+ "service_type.`Instructions`,service_type.`IsProcess`,service_type.`IsParent`,service_type.`ServiceClass`,service_type.`ServiceCategory`,\n"
			+ "service_type.`ServiceType`"
			//+ ",service_type.`is_stage`,service_type.`stages`,service_type.`ServiceTypeIconClass`,service_type.`Is_Pricing`\n"
			//+ "service_type.`Receipt_Confirm_Template`,\n"
			//+ "(JSON_OBJECT('id',master_setup.id,'name',master_setup.`name`,'code',master_setup.`code`)) AS service_type_details\n"
			+ "FROM `bpf`.`service_types_header` service_type\n"
			+ "LEFT JOIN bpf.master_general_setup master_setup ON master_setup.`id`=service_type.`ServiceType`\n"
			+ "WHERE service_type.`ID`=?1";
	
	

}

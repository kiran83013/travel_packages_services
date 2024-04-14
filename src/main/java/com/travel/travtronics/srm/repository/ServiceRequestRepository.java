package com.travel.travtronics.srm.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.srm.model.ServiceRequest;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {

	Optional<ServiceRequest> findByRequestId(Long requestId);

	@Query("select sr.productId from ServiceRequest sr where sr.requestId=?1")
	Optional<Integer> findProductByRequest(Long requestId);

	@Query(value = "SELECT customer_info.BusinessName FROM customer_info WHERE customer_info.CUSTOMERID=?1", nativeQuery = true)
	String fetchCustomerName(Long id);

	@Query(value = "SELECT CONCAT(customer_contact.`FirstName`, ' ',customer_contact.`LastName`) AS contact FROM  customer_contact WHERE customer_contact.ID=?1", nativeQuery = true)
	String fetchContactName(Long id);

	@Query(value = "SELECT DISTINCT (c.BusinessName),\r\n" + "c.CUSTOMERID,\r\n" + "c.Type,\r\n" + "c.Rating,\r\n"
			+ "c.CustomerTaxReference,\r\n" + "c.Category,\r\n" + "c.CustomerSubCategory,\r\n" + "c.Legacy_ID,\r\n"
			+ "c.RelationshipWithTA,\r\n" + "c.StartDate,\r\n" + "c.EndDate,\r\n" + "c.Status,\r\n" + "c.Freeze,\r\n"
			+ "c.FreezeDescription,\r\n" + "c.CreatedBy,\r\n" + "c.CreatedDate,\r\n" + "c.UpdatedBy,\r\n"
			+ "c.UpdatedDate,\r\n" + "c.PersonFlag,\r\n" + "c.OperatingUnit,\r\n" + "c.OTASalesPerson,\r\n"
			+ "c.Internal,\r\n" + "c.RelatedPartyRef,\r\n" + "c.AccountRelationship,\r\n" + "c.AuthorisedSignatory,\r\n"
			+ "c.CollectionAgent,\r\n" + "c.PrimarySiteUseFlag,\r\n" + "c.SiteUseCode,\r\n"
			+ "c.ServicesToBeProvided,\r\n" + "c.Markup,\r\n" + "c.purpose,\r\n" + "c.CustomerCompany,\r\n"
			+ "c.DealCategory,\r\n" + "c.IsSupplier,\r\n" + "c.Suppl_RegistrationNumber,\r\n" + "c.Industry,\r\n"
			+ "c.SubIndustry,\r\n" + "c.StatusWF,\r\n" + "c.IsCustomer,\r\n" + "c.Cust_RegistrationNumber,\r\n"
			+ "c.ShortName,\r\n" + "c.Code\r\n" + "FROM sr_request sr\r\n"
			+ "INNER JOIN customer_info c ON c.CUSTOMERID=sr.CUSTOMERID\r\n"
			+ "WHERE c.BusinessName LIKE %?%", nativeQuery = true)
	List<Map<String, String>> findByBusinessName(String searchString);

	@Query(value = "SELECT \r\n" + "c.BusinessName,\r\n" + "CONCAT(b.firstname,\" \",b.lastname)Requestor,\r\n"
			+ "a.CUSTOMERCONTACTID,\r\n" + "a.CUSTOMERID\r\n"
			+ "FROM sr_request AS a,customer_contact AS b,customer_info AS c\r\n" + "WHERE 1=1\r\n"
			+ "AND a.CUSTOMERCONTACTID= b.ID\r\n" + "AND a.CUSTOMERID = c.CUSTOMERID\r\n" + "AND a.CUSTOMERID = ?\r\n"
			+ "GROUP BY c.BusinessName,Requestor,a.CUSTOMERCONTACTID,a.CUSTOMERID", nativeQuery = true)
	List<Map<String, String>> findByCustomerCantact(Long customerId);

}

package com.travel.travtronics.bpf.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.bpf.model.ServiceRequestLogsModel;

public interface ServiceRequestLogsRepository extends JpaRepository<ServiceRequestLogsModel, Long> {

	@Query(value = "SELECT Body AS htmlContent, Subject AS mailSubject FROM e_services.bpf_mail_template WHERE Id = 16", nativeQuery = true)
	Map<String, Object> getMailTemplateInfo();
	
	@Query(value="SELECT cc.PrimaryEmail AS mailId, CONCAT_WS(' ',cc.FirstName, cc.MiddleName, cc.LastName) AS contactName \r\n"
			+ "FROM e_service_request.service_request sr\r\n"
			+ "LEFT JOIN e_services.customer_contact cc ON cc.ID = sr.Contact_Id \r\n"
			+ "WHERE 1 = 1  \r\n"
			+ "AND sr.ID = ?1", nativeQuery = true)
	Map<String, Object> getCustomerInfoBySrId(int srId);

	@Query(value = "SELECT Data AS srData FROM e_service_request.service_request_data WHERE SRID = ?1", nativeQuery = true)
	Map<String, Object> getSrDataBySrId(Integer srId);

}

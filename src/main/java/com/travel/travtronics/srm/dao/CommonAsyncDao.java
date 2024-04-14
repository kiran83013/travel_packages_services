package com.travel.travtronics.srm.dao;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.springframework.http.ResponseEntity;

import com.travel.travtronics.bpf.model.SrEntity;
import com.travel.travtronics.srm.model.CustomerContactInfoModel;

public interface CommonAsyncDao {

	public Future<ResponseEntity<String>> saveSrSummaryData(Integer srId, Integer srLineId, Integer productId,
			Integer contactId, Integer loggedUserId, String authToken);

	public CompletableFuture<Boolean> createSrDataAudit(SrEntity srData);
	
	public CustomerContactInfoModel validateContact(String mail, String phoneNo, String fristName, String lastName, Integer customerId);
	
	public Future<ResponseEntity<String>> sendHtmlEmail(String toMailId, String mailCc, String mailBcc, String subject, String mailBodyContent) throws Exception;

}

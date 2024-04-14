package com.travel.travtronics.srm.dao.impl;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.apache.commons.text.StringEscapeUtils;
import org.jsoup.parser.Parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lowagie.text.DocumentException;
import com.travel.travtronics.bpf.model.ServiceRequestLogsModel;
import com.travel.travtronics.bpf.model.SrEntity;
import com.travel.travtronics.bpf.repository.ServiceRequestLogsRepository;
import com.travel.travtronics.srm.dao.CommonAsyncDao;
import com.travel.travtronics.srm.model.CustomerContactInfoModel;
import com.travel.travtronics.srm.repository.CustomerContactInfoRepository;

import jakarta.activation.DataSource;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;

@Repository("coomonDao")
public class CommonAsyncDaoImpl implements CommonAsyncDao {
	
	@Value("${com.travel.tech.email.fromMailId}")
	protected String fromMail;

	@Value("${com.travel.tech.email.mailIdName}")
	protected String personalName;

	@Autowired
	ServiceRequestLogsRepository srLogRepository;
	
	@Autowired
	CustomerContactInfoRepository customerContactInfoRepository;
	
	@Autowired
	JavaMailSender javaMailSender;

	@Override
	@Async("customExecutor")
	public Future<ResponseEntity<String>> saveSrSummaryData(Integer srId, Integer srLineId, Integer productId,
			Integer contactId, Integer loggedUserId, String authToken) {

		CompletableFuture<ResponseEntity<String>> asyncFutureObject = new CompletableFuture<>();

		try {

			String url = "http://travel.dev.com/servicerequest/save-sr-summary-data";

			System.out.println("Sr Summary data saving:" + System.currentTimeMillis());

			RestTemplate restTemplate = new RestTemplate();

			// create headers
			HttpHeaders headers = new HttpHeaders();
			// set `content-type` header
			headers.setContentType(MediaType.APPLICATION_JSON);
			// set `accept` header
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			headers.set("Authorization", authToken);

			Map<String, Integer> dataParams = new HashMap<>();
			dataParams.put("serviceRequestId", srId);
			dataParams.put("serviceRequestLineId", srLineId);
			dataParams.put("contactId", contactId);
			dataParams.put("productId", productId);
			dataParams.put("loggedInUserId", loggedUserId);
			dataParams.put("quoteCount", 1);

			String finalInputParamsList = "";
			try {
				finalInputParamsList = new ObjectMapper().writeValueAsString(dataParams);
			} catch (JsonProcessingException e1) {
				e1.printStackTrace();
			}

			HttpEntity<String> entity = new HttpEntity<String>(finalInputParamsList, headers);

			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

			System.out.println("Sr Summary data saving-End: " + System.currentTimeMillis());

			asyncFutureObject.complete(response);
		} catch (Exception e) {
			asyncFutureObject.completeExceptionally(e);
		}

		return asyncFutureObject;

	}

	@Async("customExecutor")
	public CompletableFuture<Boolean> createSrDataAudit(SrEntity srData) {

		CompletableFuture<Boolean> asyncFutureObject = new CompletableFuture<>();

		try {
			ServiceRequestLogsModel modelData = new ServiceRequestLogsModel();
			modelData.setSrId(srData.getId());
			modelData.setCustomerId(srData.getCustomerId());
			modelData.setContactId(srData.getContactId());
			modelData.setCreatedChannel(srData.getCreatedChannel());
			modelData.setUpdatedChannel(srData.getUpdatedChannel());
			modelData.setWiw(srData.getWiw());
			modelData.setNotes(srData.getNotes());
			modelData.setTeamId(srData.getTeamId());
			modelData.setSrTypeId(srData.getSrTypeId());
			modelData.setSrStatusId(srData.getSrStatusId());
			modelData.setOpen(srData.getOpen());
			modelData.setClose(srData.getClose());
			modelData.setCancelled(srData.getCancelled());
			modelData.setSubmittedDate(srData.getSubmittedDate());
			modelData.setCloseDate(srData.getCloseDate());
			modelData.setCancelledDate(srData.getCancelledDate());
			modelData.setCreatedBy(srData.getCreatedBy());
			modelData.setUpdatedBy(srData.getUpdatedBy());
			modelData.setCreatedDate(srData.getCreatedDate());
			modelData.setUpdatedDate(srData.getUpdatedDate());
			modelData.setCreatedDevice(srData.getCreatedDevice());
			modelData.setCreatedIP(srData.getCreatedIP());
			modelData.setUpdatedDevice(srData.getUpdatedDevice());
			modelData.setUpdatedIP(srData.getUpdatedIP());
			modelData.setBrowserCreatedDate(srData.getBrowserCreatedDate());
			modelData.setBrowserUpdatedDate(srData.getBrowserUpdatedDate());
			modelData.setServiceTypeClass(srData.getServiceTypeClass());
			modelData.setPlannedEndDate(srData.getPlannedEndDate());
			modelData.setPlannedStartDate(srData.getPlannedStartDate());
			modelData.setLat(srData.getLat());
			modelData.setLng(srData.getLng());
			modelData.setLogCreatedDate(LocalDateTime.now().toString());

			srLogRepository.save(modelData);

			asyncFutureObject.complete(true);
		} catch (Exception e) {
			asyncFutureObject.completeExceptionally(e);
		}
		return asyncFutureObject;
	}
	
	public CustomerContactInfoModel validateContact(String mailId, String phoneNo, String fristName, String lastName, Integer customerId) {

		CustomerContactInfoModel contact = new CustomerContactInfoModel();

		if (mailId != null && mailId.isEmpty() == false) {
			Optional<CustomerContactInfoModel> mailContact = customerContactInfoRepository.findByPrimaryEmail(mailId);
			if (mailContact != null && mailContact.isPresent()) {
				contact = mailContact.get();
			} else {
				Optional<CustomerContactInfoModel> mailPhone = customerContactInfoRepository
						.findByPrimaryPhoneNumber(phoneNo);
				if (mailPhone != null && mailPhone.isPresent()) {
					contact = mailPhone.get();
				}
			}
			if (contact.getId() == null || contact.getId() <= 0) {
				CustomerContactInfoModel customerContactInfoModel = new CustomerContactInfoModel();
				customerContactInfoModel.setCustomerId(customerId);
				customerContactInfoModel.setPaxId(0);
				customerContactInfoModel.setFirstName(fristName);
				customerContactInfoModel.setLastName(lastName);
				customerContactInfoModel.setPrimaryEmail(mailId);
				customerContactInfoModel.setPrimaryPhoneNumber(phoneNo);
				contact = customerContactInfoRepository.save(customerContactInfoModel);
			}
		}

		return contact;
		/*
		contact = customerContactInfoRepository
				.findByPrimaryEmailOrPrimaryPhoneNumber(mailId, phoneNo).orElseGet(() -> {
					CustomerContactInfoModel customerContactInfoModel = new CustomerContactInfoModel();
					customerContactInfoModel.setCustomerId(customerId);
					customerContactInfoModel.setPaxId(0);
					customerContactInfoModel.setFirstName(fristName);
					customerContactInfoModel.setLastName(lastName);
					customerContactInfoModel.setPrimaryEmail(mail);
					customerContactInfoModel.setPrimaryPhoneNumber(phoneNo);
					return customerContactInfoRepository.save(customerContactInfoModel);
				});
		*/
	}
	
	@Async("customExecutor")
	public Future<ResponseEntity<String>> sendHtmlEmail(String toMailId, String ccMailId, String bccMailId,
	        String subject, String mailBody) {

	    CompletableFuture<ResponseEntity<String>> asyncFutureObject = new CompletableFuture<>();

	    try {
	        ResponseEntity<String> response = ResponseEntity.status(HttpStatus.OK.value()).body("Mailing function");

	        MimeMessage message = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);
	        helper.setFrom(fromMail);
	        helper.setTo(toMailId);
	        if (ccMailId != null && !ccMailId.isEmpty()) {
	            helper.setCc(ccMailId);
	        }
	        if (bccMailId != null && !bccMailId.isEmpty()) {
	            helper.setBcc(bccMailId);
	        }
	        helper.setSubject(subject);
	        helper.setText(mailBody,true);
	        
	        //System.out.println("==============Mail body content============="+mailBody);
	        
	        //byte[] pdfBytes = convertHtmlToPdf(mailBody);
			//DataSource pdfDataSource = new ByteArrayDataSource(pdfBytes, "application/pdf");
			//helper.addAttachment(subject + ".pdf", pdfDataSource);

	        javaMailSender.send(message);
	        
	        System.out.println("==============Sync mail method called =============");

	        response = ResponseEntity.status(HttpStatus.OK.value()).body("Mail sent");

	        asyncFutureObject.complete(response);
	    } catch (MailException e) {
	        // Handle mail sending exception
	        ResponseEntity<String> errorResponse = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Failed to send mail: " + e.getMessage());
	        asyncFutureObject.complete(errorResponse);
	    } catch (Exception e) {
	        asyncFutureObject.completeExceptionally(e);
	    }

	    return asyncFutureObject;
	}
	
	public byte[] convertHtmlToPdf(String content) {
	    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

	    String htmlContent = content;
	    if (!isHtmlContent(content)) {
	        htmlContent = "<html><body>" + content + "</body></html>";
	    }

	    // Escape HTML entities
	    htmlContent = StringEscapeUtils.escapeHtml4(htmlContent);

	    try {
	        Document document = Jsoup.parse(htmlContent);
	        String cleanedHtml = document.toString();

	        // Create a DOM document from the cleaned HTML string
	        org.w3c.dom.Document domDocument = (org.w3c.dom.Document) Jsoup.parse(cleanedHtml, "", Parser.xmlParser());

	        ITextRenderer renderer = new ITextRenderer();
	        renderer.setDocument(domDocument, null);
	        renderer.layout();
	        renderer.createPDF(byteArrayOutputStream);
	        renderer.finishPDF();

	        return byteArrayOutputStream.toByteArray();
	    } catch (DocumentException e) {
	        e.printStackTrace();
	    }

	    return null;
	}
	
	public byte[] convertHtmlToPdfOld(String content) {
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		try {
			ITextRenderer renderer = new ITextRenderer();
			String htmlContent = content;
			if (!isHtmlContent(content)) {
				// Wrap plain text content in a minimal HTML structure
				htmlContent = "<html><body>" + content + "</body></html>";
			}
			renderer.setDocumentFromString(htmlContent);
			renderer.layout();
			renderer.createPDF(byteArrayOutputStream);
			renderer.finishPDF();
			return byteArrayOutputStream.toByteArray();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	private boolean isHtmlContent(String content) {
		return content != null && content.trim().startsWith("<");
	}
	
}

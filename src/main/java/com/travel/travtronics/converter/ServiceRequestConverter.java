package com.travel.travtronics.converter;

import com.travel.travtronics.bpf.model.ServiceRequestDataModel;
import com.travel.travtronics.bpf.model.SrEntity;
import com.travel.travtronics.request.AddSrAdditionalData;
import com.travel.travtronics.request.ServiceRequestData;
import com.travel.travtronics.request.UpdateServiceRequestData;
import com.travel.travtronics.request.UpdateServiceRequestHeader;
import com.travel.travtronics.request.UpdateServiceRequestLineData;
import com.travel.travtronics.response.CreateServiceResponse;
import com.travel.travtronics.response.SREntityResponse;
import com.travel.travtronics.response.SrInfoResponse;

public class ServiceRequestConverter {

	public static SrEntity convertRequestDataToModelObject(ServiceRequestData data) {

		SrEntity modelData = new SrEntity();

		modelData.setCustomerId(data.getCustomerId());
		modelData.setContactId(data.getContactId());
		modelData.setCreatedChannel(data.getCreatedChannel());
		modelData.setUpdatedChannel(data.getUpdatedChannel());
		modelData.setWiw(data.getWiw());
		modelData.setNotes(data.getNotes());
		modelData.setTeamId(data.getTeamId());
		modelData.setSrTypeId(data.getSrTypeId());
		modelData.setSrStatusId(data.getSrStatusId());
		modelData.setOpen(data.getOpenFlag());
		modelData.setClose(data.getCloseFlag());
		modelData.setCancelled(data.getCancelled());
		modelData.setSubmittedDate(data.getSubmittedDate());
		modelData.setCloseDate(data.getCloseDate());
		modelData.setCancelledDate(data.getCancelledDate());
		modelData.setCreatedBy(data.getCreatedBy());
		modelData.setUpdatedBy(data.getUpdatedBy());
		modelData.setCreatedDate(data.getCreatedDate());
		modelData.setUpdatedDate(data.getUpdatedDate());
		modelData.setCreatedDevice(data.getCreatedDevice());
		modelData.setCreatedIP(data.getCreatedIP());
		modelData.setUpdatedDevice(data.getUpdatedDevice());
		modelData.setUpdatedIP(data.getUpdatedIP());
		modelData.setBrowserCreatedDate(data.getBrowserCreatedDate());
		modelData.setBrowserUpdatedDate(data.getBrowserUpdatedDate());
		modelData.setServiceTypeClass(data.getServiceTypeClass());
		modelData.setPlannedEndDate(data.getPlannedEndDate());
		modelData.setPlannedStartDate(data.getPlannedStartDate());
		modelData.setLat(data.getLat());
		modelData.setLng(data.getLng());
		modelData.setIsProcess(data.getIsProcess());
		modelData.setParentSrId(data.getParentSrId());

		if (data.getPoId() != null && data.getPoId() > 0) {
			modelData.setPoId(data.getPoId());
		}
		return modelData;
	}

	public static ServiceRequestDataModel convertRequestDataToRequestJsonDataModelObject(Long srId,
			ServiceRequestData data) {

		ServiceRequestDataModel modelData = new ServiceRequestDataModel();

		modelData.setSrId(srId);
		modelData.setData(data.getData());
		modelData.setAddlData(data.getAddlData());
		modelData.setSubmittedDate(data.getSubmittedDate());
		modelData.setCreatedBy(data.getCreatedBy());
		modelData.setUpdatedBy(data.getUpdatedBy());
		modelData.setCreatedDate(data.getCreatedDate());
		modelData.setUpdatedDate(data.getUpdatedDate());
		modelData.setCreatedDevice(data.getCreatedDevice());
		modelData.setCreatedIP(data.getCreatedIP());
		modelData.setUpdatedDevice(data.getUpdatedDevice());
		modelData.setUpdatedIP(data.getUpdatedIP());
		modelData.setBrowserCreatedDate(data.getBrowserCreatedDate());
		modelData.setBrowserUpdatedDate(data.getBrowserUpdatedDate());

		return modelData;
	}

	public static SrEntity convertUpdateRequestDataToModelObject(UpdateServiceRequestData data, SrEntity modelData) {

		if (data.getCustomerId() != null && data.getCustomerId() > 0) {
			modelData.setCustomerId(data.getCustomerId());
		}
		if (data.getContactId() != null && data.getContactId() > 0) {
			modelData.setContactId(data.getContactId());
		}
		if (data.getCreatedChannel() != null && data.getCreatedChannel() > 0) {
			modelData.setCreatedChannel(data.getCreatedChannel());
		}
		if (data.getUpdatedChannel() != null && data.getUpdatedChannel() > 0) {
			modelData.setUpdatedChannel(data.getUpdatedChannel());
		}
		if (data.getWiw() != null && data.getWiw() > 0) {
			modelData.setWiw(data.getWiw());
		}
		if (data.getNotes() != null && data.getNotes().isEmpty() == false) {
			modelData.setNotes(data.getNotes());
		}
		if (data.getTeamId() != null && data.getTeamId() > 0) {
			modelData.setTeamId(data.getTeamId());
		}
		if (data.getSrTypeId() != null && data.getSrTypeId() > 0) {
			modelData.setSrTypeId(data.getSrTypeId());
		}
		if (data.getSrStatusId() != null && data.getSrStatusId() > 0) {
			modelData.setSrStatusId(data.getSrStatusId());
		}
		if (data.getOpenFlag() != null) {
			modelData.setOpen(data.getOpenFlag());
		}
		if (data.getCloseFlag() != null) {
			modelData.setClose(data.getCloseFlag());
		}
		if (data.getCancelled() != null) {
			modelData.setCancelled(data.getCancelled());
		}
		if (data.getSubmittedDate() != null) {
			modelData.setSubmittedDate(data.getSubmittedDate());
		}
		if (data.getCloseDate() != null) {
			modelData.setCloseDate(data.getCloseDate());
		}
		if (data.getCancelledDate() != null) {
			modelData.setCancelledDate(data.getCancelledDate());
		}
		if (data.getUpdatedBy() != null && data.getUpdatedBy() > 0) {
			modelData.setUpdatedBy(data.getUpdatedBy());
		}
		if (data.getUpdatedDate() != null) {
			modelData.setUpdatedDate(data.getUpdatedDate());
		}
		if (data.getUpdatedDevice() != null && data.getUpdatedDevice().isEmpty() == false) {
			modelData.setUpdatedDevice(data.getUpdatedDevice());
		}
		if (data.getUpdatedIP() != null && data.getUpdatedIP().isEmpty() == false) {
			modelData.setUpdatedIP(data.getUpdatedIP());
		}
		if (data.getBrowserUpdatedDate() != null) {
			modelData.setBrowserUpdatedDate(data.getBrowserUpdatedDate());
		}

		if (data.getServiceTypeClass() != null && data.getServiceTypeClass() > 0) {
			modelData.setServiceTypeClass(data.getServiceTypeClass());
		}
		if (data.getPlannedEndDate() != null) {
			modelData.setPlannedEndDate(data.getPlannedEndDate());
		}
		if (data.getPlannedStartDate() != null) {
			modelData.setPlannedStartDate(data.getPlannedStartDate());
		}
		if (data.getLat() != null && data.getLat().isEmpty() == false) {
			modelData.setLat(data.getLat());
		}
		if (data.getLng() != null && data.getLng().isEmpty() == false) {
			modelData.setLng(data.getLng());
		}
		if (data.getIsProcess() != null) {
			modelData.setIsProcess(data.getIsProcess());
		}
		if (data.getParentSrId() != null) {
			modelData.setParentSrId(data.getParentSrId());
		}
		return modelData;
	}

	public static ServiceRequestDataModel convertUpdateRequestDataToRequestJsonDataModelObject(Long lineId, Long srId,
			UpdateServiceRequestData data, ServiceRequestDataModel modelData) {

		if (data.getData() != null && data.getData().isEmpty() == false) {
			modelData.setData(data.getData());
		}
		if (data.getAddlData() != null && data.getAddlData().isEmpty() == false) {
			modelData.setAddlData(data.getAddlData());
		}
		modelData.setSubmittedDate(data.getSubmittedDate());
		modelData.setCreatedBy(data.getCreatedBy());
		modelData.setUpdatedBy(data.getUpdatedBy());
		modelData.setCreatedDate(data.getCreatedDate());
		modelData.setUpdatedDate(data.getUpdatedDate());
		modelData.setCreatedDevice(data.getCreatedDevice());
		modelData.setCreatedIP(data.getCreatedIP());
		modelData.setUpdatedDevice(data.getUpdatedDevice());
		modelData.setUpdatedIP(data.getUpdatedIP());
		modelData.setBrowserCreatedDate(data.getBrowserCreatedDate());
		modelData.setBrowserUpdatedDate(data.getBrowserUpdatedDate());

		return modelData;
	}

	public static CreateServiceResponse covertSrDataModelToResponse(SrEntity srData, ServiceRequestDataModel dataJson) {

		CreateServiceResponse responseData = new CreateServiceResponse();

		responseData.setId(srData.getId());
		responseData.setCustomerId(srData.getCustomerId());
		responseData.setContactId(srData.getContactId());
		responseData.setCreatedChannel(srData.getCreatedChannel());
		responseData.setUpdatedChannel(srData.getUpdatedChannel());
		responseData.setWiw(srData.getWiw());
		responseData.setNotes(srData.getNotes());
		responseData.setTeamId(srData.getTeamId());
		responseData.setSrTypeId(srData.getSrTypeId());
		responseData.setSrStatusId(srData.getSrStatusId());
		responseData.setOpenFlag(srData.getOpen());
		responseData.setCloseFlag(srData.getClose());
		responseData.setData(dataJson.getData());
		responseData.setAddlData(dataJson.getAddlData());
		responseData.setCancelled(srData.getCancelled());
		responseData.setSubmittedDate(srData.getSubmittedDate());
		responseData.setCloseDate(srData.getCloseDate());
		responseData.setCancelledDate(srData.getCancelledDate());
		responseData.setCreatedBy(srData.getCreatedBy());
		responseData.setUpdatedBy(srData.getUpdatedBy());
		responseData.setCreatedDate(srData.getCreatedDate());
		responseData.setUpdatedDate(srData.getUpdatedDate());
		responseData.setCreatedDevice(srData.getCreatedDevice());
		responseData.setCreatedIP(srData.getCreatedIP());
		responseData.setUpdatedDevice(srData.getUpdatedDevice());
		responseData.setUpdatedIP(srData.getUpdatedIP());
		responseData.setBrowserCreatedDate(srData.getBrowserCreatedDate());
		responseData.setBrowserUpdatedDate(srData.getBrowserUpdatedDate());
		responseData.setServiceTypeClass(srData.getServiceTypeClass());

		return responseData;
	}

	public static SrEntity convertUpdateRequestDataToSrHeaderModelObject(Long srId, UpdateServiceRequestHeader data,
			SrEntity modelData) {

		if (data.getCustomerId() != null && data.getCustomerId() > 0) {
			modelData.setCustomerId(data.getCustomerId());
		}
		if (data.getContactId() != null && data.getContactId() > 0) {
			modelData.setContactId(data.getContactId());
		}
		if (data.getCreatedChannel() != null && data.getCreatedChannel() > 0) {
			modelData.setCreatedChannel(data.getCreatedChannel());
		}
		if (data.getUpdatedChannel() != null && data.getUpdatedChannel() > 0) {
			modelData.setUpdatedChannel(data.getUpdatedChannel());
		}
		if (data.getWiw() != null && data.getWiw() > 0) {
			modelData.setWiw(data.getWiw());
		}
		if (data.getNotes() != null && data.getNotes().isEmpty() == false) {
			modelData.setNotes(data.getNotes());
		}
		if (data.getTeamId() != null && data.getTeamId() > 0) {
			modelData.setTeamId(data.getTeamId());
		}
		if (data.getSrTypeId() != null && data.getSrTypeId() > 0) {
			modelData.setSrTypeId(data.getSrTypeId());
		}
		if (data.getSrStatusId() != null && data.getSrStatusId() > 0) {
			modelData.setSrStatusId(data.getSrStatusId());
		}
		if (data.getOpenFlag() != null) {
			modelData.setOpen(data.getOpenFlag());
		}
		if (data.getCloseFlag() != null) {
			modelData.setClose(data.getCloseFlag());
		}
		if (data.getCancelled() != null) {
			modelData.setCancelled(data.getCancelled());
		}
		if (data.getSubmittedDate() != null) {
			modelData.setSubmittedDate(data.getSubmittedDate());
		}
		if (data.getCloseDate() != null) {
			modelData.setCloseDate(data.getCloseDate());
		}
		if (data.getCancelledDate() != null) {
			modelData.setCancelledDate(data.getCancelledDate());
		}
		if (data.getUpdatedBy() != null && data.getUpdatedBy() > 0) {
			modelData.setUpdatedBy(data.getUpdatedBy());
		}
		if (data.getUpdatedDate() != null) {
			modelData.setUpdatedDate(data.getUpdatedDate());
		}
		if (data.getUpdatedDevice() != null && data.getUpdatedDevice().isEmpty() == false) {
			modelData.setUpdatedDevice(data.getUpdatedDevice());
		}
		if (data.getUpdatedIP() != null && data.getUpdatedIP().isEmpty() == false) {
			modelData.setUpdatedIP(data.getUpdatedIP());
		}
		if (data.getBrowserUpdatedDate() != null) {
			modelData.setBrowserUpdatedDate(data.getBrowserUpdatedDate());
		}
		if (data.getIsProcess() != null) {
			modelData.setIsProcess(data.getIsProcess());
		}
		if (data.getParentSrId() != null && data.getTeamId() > 0) {
			modelData.setParentSrId(data.getParentSrId());
		}

		return modelData;
	}

	public static ServiceRequestDataModel convertUpdateSrRequestDataToSrDataLineModelObject(Long lineId, Long srId,
			UpdateServiceRequestLineData data, ServiceRequestDataModel srData) {

		if (data.getData() != null && data.getData().isEmpty() == false) {
			srData.setData(data.getData());
		}
		if (data.getAddlData() != null && data.getAddlData().isEmpty() == false) {
			srData.setAddlData(data.getAddlData());
		} else {
			srData.setAddlData(srData.getAddlData());
		}
		srData.setSubmittedDate(data.getSubmittedDate());
		srData.setUpdatedBy(data.getUpdatedBy());
		srData.setUpdatedDate(data.getUpdatedDate());
		srData.setUpdatedDevice(data.getUpdatedDevice());
		srData.setUpdatedIP(data.getUpdatedIP());
		srData.setBrowserUpdatedDate(data.getBrowserUpdatedDate());

		return srData;
	}

	public static ServiceRequestDataModel convertUpdateSrAdditionalDataToSrAddlDataLineModelObject(Long srId,
			Long dataId, ServiceRequestDataModel srData, AddSrAdditionalData data) {

		if (dataId != null && dataId > 0) {
			if (data.getAddlData() != null && data.getAddlData().isEmpty() == false) {
				srData.setAddlData(data.getAddlData());
			}
			srData.setUpdatedBy(data.getSubmittedBy());
			srData.setUpdatedDate(data.getSubmittedDate());
			srData.setUpdatedDevice(data.getSubmittedDevice());
			srData.setUpdatedIP(data.getSubmittedIP());
			srData.setBrowserUpdatedDate(data.getBrowserSubmittedDate());
			return srData;
		} else {
			ServiceRequestDataModel modelData = new ServiceRequestDataModel();
			modelData.setAddlData(data.getAddlData());
			modelData.setSubmittedDate(data.getSubmittedDate());
			modelData.setCreatedBy(data.getSubmittedBy());
			modelData.setCreatedDate(data.getSubmittedDate());
			modelData.setCreatedDevice(data.getSubmittedDevice());
			modelData.setCreatedIP(data.getSubmittedIP());
			modelData.setBrowserCreatedDate(data.getBrowserSubmittedDate());
			return modelData;
		}

	}

	public static SrInfoResponse convertSrModelToSrInfoResponse(SrEntity srTblData) {

		SrInfoResponse responseData = new SrInfoResponse();

		responseData.setId(srTblData.getId());
		responseData.setCustomerId(srTblData.getCustomerId());
		responseData.setContactId(srTblData.getContactId());
		responseData.setCreatedChannel(srTblData.getCreatedChannel());
		responseData.setUpdatedChannel(srTblData.getUpdatedChannel());
		responseData.setWiw(srTblData.getWiw());
		responseData.setNotes(srTblData.getNotes());
		responseData.setTeamId(srTblData.getTeamId());
		responseData.setSrTypeId(srTblData.getSrTypeId());
		responseData.setSrStatusId(srTblData.getSrStatusId());
		responseData.setOpen(srTblData.getOpen());
		responseData.setClose(srTblData.getClose());
		responseData.setCancelled(srTblData.getCancelled());
		responseData.setSubmittedDate(srTblData.getSubmittedDate());
		responseData.setCloseDate(srTblData.getCloseDate());
		responseData.setCancelledDate(srTblData.getCancelledDate());
		responseData.setCreatedBy(srTblData.getCreatedBy());
		responseData.setUpdatedBy(srTblData.getUpdatedBy());
		responseData.setCreatedDate(srTblData.getCreatedDate());
		responseData.setUpdatedDate(srTblData.getUpdatedDate());
		responseData.setCreatedDevice(srTblData.getCreatedDevice());
		responseData.setCreatedIP(srTblData.getCreatedIP());
		responseData.setUpdatedDevice(srTblData.getUpdatedDevice());
		responseData.setUpdatedIP(srTblData.getUpdatedIP());
		responseData.setBrowserCreatedDate(srTblData.getBrowserCreatedDate());
		responseData.setBrowserUpdatedDate(srTblData.getBrowserUpdatedDate());
		responseData.setServiceTypeClass(srTblData.getServiceTypeClass());
		responseData.setPlannedEndDate(srTblData.getPlannedEndDate());
		responseData.setPlannedStartDate(srTblData.getPlannedStartDate());
		responseData.setLat(srTblData.getLat());
		responseData.setLng(srTblData.getLng());
		responseData.setIsProcess(srTblData.getIsProcess());
		responseData.setParentSrId(srTblData.getParentSrId());
		responseData.setSlaHeaderId(srTblData.getSlaHeaderId());
		responseData.setTaskStartDate(srTblData.getTaskStartDate());
		responseData.setTaskEndDate(srTblData.getTaskEndDate());
		responseData.setRfqId(srTblData.getRfqId());

		return responseData;

	}

	public static SREntityResponse convertResponseToEntity(SrEntity srTblData) {
		SREntityResponse responseData = new SREntityResponse();
		responseData.setId(srTblData.getId());
		responseData.setCustomerId(srTblData.getCustomerId());
		responseData.setContactId(srTblData.getContactId());
		responseData.setCreatedChannel(srTblData.getCreatedChannel());
		responseData.setUpdatedChannel(srTblData.getUpdatedChannel());
		responseData.setWiw(srTblData.getWiw());
		responseData.setNotes(srTblData.getNotes());
		responseData.setTeamId(srTblData.getTeamId());
		responseData.setSrTypeId(srTblData.getSrTypeId());
		responseData.setSrStatusId(srTblData.getSrStatusId());
		responseData.setOpen(srTblData.getOpen());
		responseData.setClose(srTblData.getClose());
		responseData.setCancelled(srTblData.getCancelled());
		responseData.setSubmittedDate(srTblData.getSubmittedDate());
		responseData.setCloseDate(srTblData.getCloseDate());
		responseData.setCancelledDate(srTblData.getCancelledDate());
		responseData.setCreatedBy(srTblData.getCreatedBy());
		responseData.setUpdatedBy(srTblData.getUpdatedBy());
		responseData.setCreatedDate(srTblData.getCreatedDate());
		responseData.setUpdatedDate(srTblData.getUpdatedDate());
		responseData.setCreatedDevice(srTblData.getCreatedDevice());
		responseData.setCreatedIP(srTblData.getCreatedIP());
		responseData.setUpdatedDevice(srTblData.getUpdatedDevice());
		responseData.setUpdatedIP(srTblData.getUpdatedIP());
		responseData.setBrowserCreatedDate(srTblData.getBrowserCreatedDate());
		responseData.setBrowserUpdatedDate(srTblData.getBrowserUpdatedDate());
		responseData.setServiceTypeClass(srTblData.getServiceTypeClass());
		responseData.setPlannedEndDate(srTblData.getPlannedEndDate());
		responseData.setPlannedStartDate(srTblData.getPlannedStartDate());
		responseData.setLat(srTblData.getLat());
		responseData.setLng(srTblData.getLng());
		responseData.setIsProcess(srTblData.getIsProcess());
		responseData.setParentSrId(srTblData.getParentSrId());
		responseData.setSlaHeaderId(srTblData.getSlaHeaderId());
		responseData.setTaskStartDate(srTblData.getTaskStartDate());
		responseData.setTaskEndDate(srTblData.getTaskEndDate());
		responseData.setRfqId(srTblData.getRfqId());

		return responseData;
	}
}

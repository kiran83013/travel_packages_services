package com.travel.travtronics.converter;

import java.util.Optional;

import com.travel.travtronics.request.PackageSchedulePriceItemLinkRequest;
import com.travel.travtronics.response.PackageSchedulePriceItemLinkResponse;
import com.travel.travtronics.srm.model.PackageSchedulePriceItemLink;

public class PackageSchedulePriceItemLinkConverter {

	/*
	 * public static PackageSchedulePriceItemLink
	 * convertSchedulePriceItemRequestTOModel(@Valid
	 * PackageSchedulePriceItemLinkRequest requestList) {
	 * 
	 * PackageSchedulePriceItemLink model = new PackageSchedulePriceItemLink();
	 * model.setAddedBy(requestList.getAddedBy());
	 * model.setAddedDate(requestList.getAddedDate());
	 * model.setAddedDevice(requestList.getAddedDevice());
	 * model.setAddedIp(requestList.getAddedIp());
	 * model.setAttribut3(requestList.getAttribut3());
	 * model.setAttribute1(requestList.getAttribute1());
	 * model.setAttribute2(requestList.getAttribute2());
	 * model.setAttribute4(requestList.getAttribute4());
	 * model.setAttribute5(requestList.getAttribute5()); //
	 * model.setId(requestList.getId());
	 * model.setLinkStatus(requestList.getLinkStatus());
	 * model.setPriceItemId(requestList.getPriceItemId());
	 * model.setProductId(requestList.getProductId());
	 * model.setScheduleId(requestList.getScheduleId());
	 * model.setServiceRequestId(requestList.getServiceRequestId());
	 * model.setServiceRequestLineId(requestList.getServiceRequestLineId());
	 * model.setServiceRequestTypeId(requestList.getServiceRequestTypeId());
	 * model.setUpdatedBy(requestList.getUpdatedBy());
	 * model.setUpdatedDate(requestList.getUpdatedDate());
	 * model.setUpdatedDevice(requestList.getUpdatedDevice());
	 * model.setUpdatedIp(requestList.getUpdatedIp()); return model; }
	 */

	/*
	 * public static PackageSchedulePriceItemLink
	 * convertPriceItemLinkUpdateRequestToModel(Long id,
	 * 
	 * @Valid PackageSchedulePriceItemLinkRequest requestList) {
	 * PackageSchedulePriceItemLink model = new PackageSchedulePriceItemLink();
	 * model.setAddedBy(requestList.getAddedBy());
	 * model.setAddedDate(requestList.getAddedDate());
	 * model.setAddedDevice(requestList.getAddedDevice());
	 * model.setAddedIp(requestList.getAddedIp());
	 * model.setAttribut3(requestList.getAttribut3());
	 * model.setAttribute1(requestList.getAttribute1());
	 * model.setAttribute2(requestList.getAttribute2());
	 * model.setAttribute4(requestList.getAttribute4());
	 * model.setAttribute5(requestList.getAttribute5()); model.setId(id);
	 * model.setLinkStatus(requestList.getLinkStatus());
	 * model.setPriceItemId(requestList.getPriceItemId());
	 * model.setProductId(requestList.getProductId());
	 * model.setScheduleId(requestList.getScheduleId());
	 * model.setServiceRequestId(requestList.getServiceRequestId());
	 * model.setServiceRequestLineId(requestList.getServiceRequestLineId());
	 * model.setServiceRequestTypeId(requestList.getServiceRequestTypeId());
	 * model.setUpdatedBy(requestList.getUpdatedBy());
	 * model.setUpdatedDate(requestList.getUpdatedDate());
	 * model.setUpdatedDevice(requestList.getUpdatedDevice());
	 * model.setUpdatedIp(requestList.getUpdatedIp()); return model; }
	 */

	public static PackageSchedulePriceItemLinkResponse convertModelToResponse(
			PackageSchedulePriceItemLink requestList) {
		PackageSchedulePriceItemLinkResponse model = new PackageSchedulePriceItemLinkResponse();
		model.setAddedBy(requestList.getAddedBy());
		model.setAddedDate(requestList.getAddedDate());
		model.setAddedDevice(requestList.getAddedDevice());
		model.setAddedIp(requestList.getAddedIp());
		model.setAttribut3(requestList.getAttribut3());
		model.setAttribute1(requestList.getAttribute1());
		model.setAttribute2(requestList.getAttribute2());
		model.setAttribute4(requestList.getAttribute4());
		model.setAttribute5(requestList.getAttribute5());
		model.setId(requestList.getId());
		model.setLinkStatus(requestList.getLinkStatus());
		model.setPriceItemId(requestList.getPriceItemId());
		model.setProductId(requestList.getProductId());
		model.setScheduleId(requestList.getScheduleId());
		model.setServiceRequestId(requestList.getServiceRequestId());
		model.setServiceRequestLineId(requestList.getServiceRequestLineId());
		model.setServiceRequestTypeId(requestList.getServiceRequestTypeId());
		model.setUpdatedBy(requestList.getUpdatedBy());
		model.setUpdatedDate(requestList.getUpdatedDate());
		model.setUpdatedDevice(requestList.getUpdatedDevice());
		model.setUpdatedIp(requestList.getUpdatedIp());
		return model;
	}

	public static PackageSchedulePriceItemLink convertSchedulePriceItemRequestTOModel(Long id,
			PackageSchedulePriceItemLinkRequest item, Optional<PackageSchedulePriceItemLink> priceItemData) {

		PackageSchedulePriceItemLink model = new PackageSchedulePriceItemLink();

		if (priceItemData != null && priceItemData.isPresent()) {
			model = priceItemData.get();
		}

		if (item.getAddedBy() != null) {
			model.setAddedBy(item.getAddedBy());
		}
		if (item.getAddedDate() != null) {
			model.setAddedDate(item.getAddedDate());
		}
		if (item.getAddedDevice() != null) {
			model.setAddedDevice(item.getAddedDevice());
		}
		if (item.getAddedIp() != null) {
			model.setAddedIp(item.getAddedIp());
		}
		if (item.getAttribut3() != null) {
			model.setAttribut3(item.getAttribut3());
		}
		if (item.getAttribute1() != null) {
			model.setAttribute1(item.getAttribute1());
		}
		if (item.getAttribute2() != null) {
			model.setAttribute2(item.getAttribute2());
		}
		if (item.getAttribute4() != null) {
			model.setAttribute4(item.getAttribute4());
		}
		if (item.getAttribute5() != null) {
			model.setAttribute5(item.getAttribute5());
		}
		if (id != null) {
			model.setId(id);
		}
		if (item.getLinkStatus() != null) {
			model.setLinkStatus(item.getLinkStatus());
		}
		if (item.getPriceItemId() != null) {
			model.setPriceItemId(item.getPriceItemId());
		}
		if (item.getProductId() != null) {
			model.setProductId(item.getProductId());
		}
		if (item.getScheduleId() != null) {
			model.setScheduleId(item.getScheduleId());
		}
		if (item.getServiceRequestId() != null) {
			model.setServiceRequestId(item.getServiceRequestId());
		}
		if (item.getServiceRequestLineId() != null) {
			model.setServiceRequestLineId(item.getServiceRequestLineId());
		}
		if (item.getServiceRequestTypeId() != null) {
			model.setServiceRequestTypeId(item.getServiceRequestTypeId());
		}
		if (item.getUpdatedBy() != null) {
			model.setUpdatedBy(item.getUpdatedBy());
		}
		if (item.getUpdatedDate() != null) {
			model.setUpdatedDate(item.getUpdatedDate());
		}
		if (item.getUpdatedDevice() != null) {
			model.setUpdatedDevice(item.getUpdatedDevice());
		}
		if (item.getUpdatedIp() != null) {
			model.setUpdatedIp(item.getUpdatedIp());
		}
		return model;
	}
}

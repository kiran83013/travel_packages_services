package com.travel.travtronics.converter;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.travel.travtronics.request.PkgHotelSupplierInfoRequest;
import com.travel.travtronics.request.PkgPriceHeaderMappingRequest;
import com.travel.travtronics.request.PkgPriceHotelRequest;
import com.travel.travtronics.request.PkgScheduleHeaderLineRequest;
import com.travel.travtronics.request.PkgSchedulePriceFlightRequest;
import com.travel.travtronics.request.PkgScheduleSegmentFlightRequest;
import com.travel.travtronics.request.PkgSubHotelRequest;
import com.travel.travtronics.request.PkgSupplierFlightRequest;
import com.travel.travtronics.request.PkgSupplierHotelRequest;
import com.travel.travtronics.srm.model.PackageScheduleHeaderLineModel;
import com.travel.travtronics.srm.model.PackageScheduleHeaderModel;
import com.travel.travtronics.srm.model.PackageSchedulePriceFlightModel;
import com.travel.travtronics.srm.model.PackageSchedulePriceHotelModel;
import com.travel.travtronics.srm.model.PackageScheduleSegmentFlightModel;
import com.travel.travtronics.srm.model.PackageScheduleSubHotelModel;
import com.travel.travtronics.srm.model.PackageScheduleSupplierFlightModel;
import com.travel.travtronics.srm.model.PackageScheduleSupplierHotelModel;

public class PkgsPriceSavingConverter {

	ZonedDateTime instance = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
	DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	String currentDateTime = formatterDateTime.format(instance); // 15-02-2022 12:43
	String currentDate = formatterDate.format(instance); // 15-02-2022 12:43

	public static PackageScheduleHeaderModel convertHotelPkgHeaderRequestToHtlHModel(
			PkgPriceHeaderMappingRequest request) {

		PackageScheduleHeaderModel headerData = new PackageScheduleHeaderModel();

		if (request.getId() != null && request.getId() > 0) {
			headerData.setId(request.getId());
		}
		headerData.setScheduleId(request.getScheduleId());
		headerData.setProductId(request.getProductId());
		headerData.setServiceRequestId(request.getServiceRequestId());
		headerData.setServiceRequestLineId(request.getServiceRequestLineId());
		headerData.setHeaderStatus(request.getHeaderStatus());
		headerData.setRemovePrice(request.getRemovePrice());
		headerData.setDynamicPrice(request.getDynamicPrice());
		headerData.setPriceOverride(request.getPriceOverride());
		headerData.setDiscountOverride(request.getDiscountOverride());
		headerData.setBaseOverride(request.getBaseOverride());
		headerData.setTaxOverride(request.getTaxOverride());
		headerData.setInputvatOverride(request.getInputvatOverride());
		headerData.setOutputvatOverride(request.getOutputvatOverride());
		headerData.setAddedBy(request.getAddedBy());
		headerData.setAddedDate(new PkgsPriceSavingConverter().currentDateTime);
		headerData.setAddedDevice(request.getAddedDeviceInfo());
		headerData.setAddedIp(request.getAddedIp());
		headerData.setPriceListHeaderId(request.getPriceListHeaderId());
		headerData.setPriceListItemId(request.getPriceListItemId());
		return headerData;
	}

	public static PackageScheduleHeaderLineModel convertHeadlineRequestToHeaderLinesModel(Long headerId,
			PkgScheduleHeaderLineRequest request, Integer addedBy, String addedDeviceInfo, String addedIp) {

		PackageScheduleHeaderLineModel lineData = new PackageScheduleHeaderLineModel();

		if (request.getId() != null && request.getId() > 0) {
			lineData.setId(request.getId());
		}
		System.out.println("step-2: "+request.getLineGroupId()+" -- "+request.getLineOptionId());
		
		lineData.setHeaderId(headerId);
		lineData.setLineGroupId(request.getLineGroupId());
		lineData.setLineOptionId(request.getLineOptionId());
		lineData.setDefaultOption(request.getDefaultOption());
		lineData.setLineStatus(request.getLineStatus());
		lineData.setAddedBy(addedBy);
		lineData.setAddedDate(new PkgsPriceSavingConverter().currentDateTime);
		lineData.setAddedDevice(addedDeviceInfo);
		lineData.setAddedIp(addedIp);

		return lineData;
	}

	public static PackageScheduleSupplierHotelModel convertHotelSplrRequestToHtlSplrModel(
			PkgSupplierHotelRequest request) {

		PackageScheduleSupplierHotelModel splrModel = new PackageScheduleSupplierHotelModel();

		if (request.getId() != null && request.getId() > 0) {
			splrModel.setId(request.getId());
		}
		splrModel.setScheduleId(request.getScheduleId());
		splrModel.setServiceRequestId(request.getServiceRequestId());
		splrModel.setServiceRequestLineId(request.getServiceRequestLineId());
		splrModel.setCheckInDay(request.getCheckInDay());
		splrModel.setCheckOutDay(request.getCheckOutDay());
		splrModel.setDaysCount(request.getDaysCount());
		splrModel.setNightCount(request.getNightCount());
		splrModel.setSupplierHotelStatus(request.getSupplierHotelStatus());
		splrModel.setSupplierId(request.getSupplierId());
		splrModel.setHotelCode(request.getHotelCode());
		splrModel.setHotelName(request.getHotelName());
		splrModel.setHotelAddress(request.getHotelAddress());
		splrModel.setAddedBy(request.getAddedBy());
		splrModel.setAddedDate(new PkgsPriceSavingConverter().currentDateTime);
		splrModel.setAddedDevice(request.getAddedDevice());
		splrModel.setAddedIp(request.getAddedIp());

		return splrModel;
	}

	public static PackageScheduleSubHotelModel convertSubHotelRequestToSubHtlModel(Long pkgSlprId,
			PkgSubHotelRequest request) {

		PackageScheduleSubHotelModel subHtlData = new PackageScheduleSubHotelModel();

		if (request.getId() != null && request.getId() > 0) {
			subHtlData.setId(request.getId());
		}
		subHtlData.setOptionId(pkgSlprId);
		subHtlData.setRoomNameId(request.getRoomNameId());
		subHtlData.setRoomName(request.getRoomName());
		subHtlData.setRoomTypeId(request.getRoomTypeId());
		subHtlData.setRoomType(request.getRoomType());
		subHtlData.setMaxAdt(request.getMaxAdt());
		subHtlData.setMaxChd(request.getMaxChd());
		subHtlData.setMaxInf(request.getMaxInf());
		subHtlData.setSubStatus(request.getSubStatus());
		subHtlData.setAddedBy(1);
		subHtlData.setAddedDate(new PkgsPriceSavingConverter().currentDateTime);
		subHtlData.setAddedDevice("pkg htl price");
		subHtlData.setAddedIp("192.178.10.6");

		return subHtlData;
	}

	public static PackageSchedulePriceHotelModel convertSubHotelRequestToSubHtlModel(Long subHtlId,
			PkgPriceHotelRequest request) {

		PackageSchedulePriceHotelModel priceModelData = new PackageSchedulePriceHotelModel();

		if (request.getId() != null && request.getId() > 0) {
			priceModelData.setId(request.getId());
		}
		priceModelData.setSubOptionId(subHtlId);
		priceModelData.setPriceStatus(request.getPriceStatus());
		priceModelData.setAdtCount(request.getAdtCount());
		priceModelData.setChdCount(request.getChdCount());
		priceModelData.setInfCount(request.getInfCount());
		priceModelData.setBase(request.getBase());
		priceModelData.setTax(request.getTax());
		priceModelData.setTaxBreakUp(request.getTaxBreakUp());
		priceModelData.setM1(request.getM1());
		priceModelData.setM2(request.getM2());
		priceModelData.setD1(request.getD1());
		priceModelData.setD2(request.getD2());
		priceModelData.setInputVat(request.getInputVat());
		priceModelData.setOutputVat(request.getOutputVat());
		priceModelData.setTotal(request.getTotal());
		priceModelData.setCurrencyCode(request.getCurrencyCode());
		priceModelData.setAddedBy(1);
		priceModelData.setAddedDate(new PkgsPriceSavingConverter().currentDateTime);
		priceModelData.setAddedDevice("pkg htl price");
		priceModelData.setAddedIp("192.178.10.6");

		return priceModelData;
	}

	public static PackageScheduleSupplierFlightModel convertFlightSplrRequestToFltSplrModel(
			PkgSupplierFlightRequest request) {

		PackageScheduleSupplierFlightModel splrModel = new PackageScheduleSupplierFlightModel();

		if (request.getId() != null && request.getId() > 0) {
			splrModel.setId(request.getId());
		}
		splrModel.setScheduleId(request.getScheduleId());
		splrModel.setServiceRequestId(request.getServiceRequestId());
		splrModel.setServiceRequestLineId(request.getServiceRequestLineId());
		splrModel.setValidatingCarrier(request.getValidatingCarrier());
		splrModel.setSupplierFlightStatus(request.getSupplierFlightStatus());
		splrModel.setSupplierId(request.getSupplierId());
		splrModel.setAddedBy(request.getAddedBy());
		splrModel.setAddedDate(new PkgsPriceSavingConverter().currentDateTime);
		splrModel.setAddedDevice(request.getAddedDevice());
		splrModel.setAddedIp(request.getAddedIp());

		return splrModel;

	}

	public static PackageScheduleSegmentFlightModel convertSegmentsRequestToSegmentsModel(Long splrId,
			PkgScheduleSegmentFlightRequest request, Integer addedBy, String addedDeviceInfo, String addedIp) {

		PackageScheduleSegmentFlightModel segModel = new PackageScheduleSegmentFlightModel();

		if (request.getId() != null && request.getId() > 0) {
			segModel.setId(request.getId());
		}
		segModel.setSupplierFlightId(splrId);
		segModel.setDeptCode(request.getDeptCode());
		segModel.setDeptDay(request.getDeptDay());
		segModel.setDeptTime(request.getDeptTime());
		segModel.setArrCode(request.getArrCode());
		segModel.setArrDay(request.getArrDay());
		segModel.setArrTime(request.getArrTime());
		segModel.setMarketingCarrier(request.getMarketingCarrier());
		segModel.setOperatingCarrier(request.getOperatingCarrier());
		segModel.setFlightNumber(request.getFlightNumber());
		segModel.setCabinClass(request.getCabinClass());
		segModel.setRbd(request.getRbd());
		segModel.setBaggage(request.getBaggage());
		segModel.setEquipment(request.getEquipment());
		segModel.setAddedBy(addedBy);
		segModel.setAddedDate(new PkgsPriceSavingConverter().currentDateTime);
		segModel.setAddedDevice(addedDeviceInfo);
		segModel.setAddedIp(addedIp);

		return segModel;
	}

	public static PackageSchedulePriceFlightModel convertFlightPriceRequestToFlightPriceModel(Long id,
			PkgSchedulePriceFlightRequest request, Integer addedBy, String addedDevice, String addedIp) {

		PackageSchedulePriceFlightModel priceModel = new PackageSchedulePriceFlightModel();

		if (request.getId() != null && request.getId() > 0) {
			priceModel.setId(request.getId());
		}
		priceModel.setSupplierFlightId(id);
		priceModel.setPriceStatus(request.getPriceStatus());
		priceModel.setPaxType(request.getPaxType());
		priceModel.setBase(request.getBase());
		priceModel.setTax(request.getTax());
		priceModel.setTaxBreakUp(request.getTaxBreakUp());
		priceModel.setM1(request.getM1());
		priceModel.setM2(request.getM2());
		priceModel.setD1(request.getD1());
		priceModel.setD2(request.getD2());
		priceModel.setTotal(request.getTotal());
		priceModel.setCurrencyCode(request.getCurrencyCode());
		priceModel.setAddedBy(addedBy);
		priceModel.setAddedDate(new PkgsPriceSavingConverter().currentDateTime);
		priceModel.setAddedDevice(addedDevice);
		priceModel.setAddedIp(addedIp);

		return priceModel;
	}

	public static PackageScheduleSupplierHotelModel convertPkgHotelSplrRequestToHtlSplrModel(
			PkgHotelSupplierInfoRequest request) {

		PackageScheduleSupplierHotelModel splrModel = new PackageScheduleSupplierHotelModel();

		if (request.getId() != null && request.getId() > 0) {
			splrModel.setId(request.getId());
		}
		splrModel.setScheduleId(request.getScheduleId());
		splrModel.setServiceRequestId(request.getServiceRequestId());
		splrModel.setServiceRequestLineId(request.getServiceRequestLineId());
		splrModel.setCheckInDay(request.getCheckInDay());
		splrModel.setCheckOutDay(request.getCheckOutDay());
		splrModel.setDaysCount(request.getDaysCount());
		splrModel.setNightCount(request.getNightCount());
		splrModel.setSupplierHotelStatus(request.getSupplierHotelStatus());
		splrModel.setSupplierId(request.getSupplierId());
		splrModel.setHotelCode(request.getHotelCode());
		splrModel.setHotelName(request.getHotelName());
		splrModel.setHotelAddress(request.getHotelAddress());
		splrModel.setAddedBy(request.getAddedBy());
		splrModel.setAddedDate(new PkgsPriceSavingConverter().currentDateTime);
		splrModel.setAddedDevice(request.getAddedDevice());
		splrModel.setAddedIp(request.getAddedIp());

		return splrModel;
	}

}

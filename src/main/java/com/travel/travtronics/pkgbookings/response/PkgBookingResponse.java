package com.travel.travtronics.pkgbookings.response;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.travel.travtronics.pkgbookings.model.BookingPackageScheduleModel;
import com.travel.travtronics.pkgbookings.model.BookingPackageSchedulePriceModel;
import com.travel.travtronics.pkgbookings.model.BookingPassengersModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PkgBookingResponse {
	
	private Long bookingId;

	private Integer productId;

    private String bookingChannel;

    private String bookingReferenceNumber;

    private Integer bookingStatusId;

    private String bookingStatusName;

    private Integer bookingServiceRequestId;

    private Integer bookingServiceRequestLineId;

    private Integer quoteId;

    private Integer supplierId;

    private String supplierReferenceId;

    private Date supplierReferenceDate;

    private Date startDate;

    private Date endDate;

    private Integer customerId;

    private Integer customerContactId;

    private String customerContactName;

    private String customerContactPhone;

    private String customerContactEmail;

    private Integer userId;

    private Integer liabilityOwnerId;

    private String baseCurrency;

    private String currency;

    private BigDecimal currencyConversionRate;

    private BigDecimal base;

    private BigDecimal tax;

    private String taxData;

    private BigDecimal commissionAmount;

    private BigDecimal commissionPercentage;

    private BigDecimal commissionPercentageToAmount;

    private BigDecimal extraCommissionAmount;

    private BigDecimal extraCommissionPercentage;

    private BigDecimal extraCommissionPercentageToAmount;

    private BigDecimal supplierTotal;

    private BigDecimal m1;

    private Integer m1TemplateId;

    private String m1TemplateData;

    private BigDecimal m2;

    private String m2ReasonData;

    private BigDecimal d1;

    private Integer d1TemplateId;

    private String d1TemplateData;

    private BigDecimal d2;

    private String d2ReasonData;

    private BigDecimal grandTotal;

    private Integer addedBy;

    private Date addedDate;

    private String addedDevice;

    private String addedIp;

    private Integer updatedBy;

    private Date updatedDate;

    private String updatedDevice;

    private String updatedIp;

    private Integer warehouse;

    private Date warehouseDate;
    
    private BookingPackageScheduleModel pkgSchedules;
    
    private List<BookingPackageSchedulePriceModel> pkgSchedulePrice;
    
    private List<BookingPassengersModel> PkgBookingPaxs;
    
    
}

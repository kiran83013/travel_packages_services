package com.travel.travtronics.pkgbookings.request;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddBookingRequest {
	
	private Long bookingId;

	@NotNull(message = "productId should not be empty")
    private Integer productId;

    private String bookingChannel;

    private String bookingReferenceNumber;

    private Integer bookingStatusId;

    private String bookingStatusName;

    @NotNull(message = "bookingServiceRequestId should not be empty")
    private Integer bookingServiceRequestId;

    @NotNull(message = "bookingServiceRequestLineId should not be empty")
    private Integer bookingServiceRequestLineId;

    private Integer quoteId;

    private Integer supplierId;

    private String supplierReferenceId;

    private String supplierReferenceDate;

    private String startDate;

    private String endDate;

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

    @NotNull(message = "addedBy should not be empty")
    private Integer addedBy;

    @NotNull(message = "addedDevice should not be empty")
    private String addedDevice;

    private String addedIp;
    
    @NotNull(message = "pkgSchedules should not be empty")
    private PkgSchedulesRequest pkgSchedules;
    
    @NotNull(message = "pkgSchedulePrice should not be empty")
    private List<PkgSchedulePriceRequest> pkgSchedulePrice;
    
    @NotNull(message = "PkgBookingPaxs should not be empty")
    private List<PkgBookingPaxRequest> PkgBookingPaxs;
    
    
}

package com.travel.travtronics.pkgbookings.request;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PkgSchedulePriceRequest {
	
	private Long bookingPackageSchedulePriceId;

    private Long bookingId;

    private Integer bookingPackageId;

    private Integer bookingScheduleId;

    private Integer scheduleServiceTypeId;

    private Integer priceItemHeaderId;

    private Integer priceItemId;

    private String priceItemName;

    private String priceItemCode;

    private String priceItemDescription;

    private Integer bookingPackageSchedulePriceStatus;

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

    private Long m1TemplateId;

    private String m1TemplateData;

    private BigDecimal m2;

    private String m2ReasonData;

    private BigDecimal d1;

    private Integer d1TemplateId;

    private String d1TemplateData;

    private BigDecimal d2;

    private String d2ReasonData;

    private BigDecimal grandTotal;

}

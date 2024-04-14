package com.travel.travtronics.pkgbookings.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "booking_package_schedule_price")
public class BookingPackageSchedulePriceModel {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_package_schedule_price_id")
    private Long bookingPackageSchedulePriceId;

    @Column(name = "booking_id")
    private Long bookingId;

    @Column(name = "booking_package_id")
    private Integer bookingPackageId;

    @Column(name = "booking_schedule_id")
    private Integer bookingScheduleId;

    @Column(name = "schedule_service_type_id")
    private Integer scheduleServiceTypeId;

    @Column(name = "price_item_header_id")
    private Integer priceItemHeaderId;

    @Column(name = "price_item_id")
    private Integer priceItemId;

    @Column(name = "price_item_name")
    private String priceItemName;

    @Column(name = "price_item_code")
    private String priceItemCode;

    @Column(name = "price_item_description")
    private String priceItemDescription;

    @Column(name = "booking_package_schedule_price_status")
    private Integer bookingPackageSchedulePriceStatus;

    @Column(name = "base_currency")
    private String baseCurrency;

    @Column(name = "currency")
    private String currency;

    @Column(name = "currency_conversion_rate")
    private BigDecimal currencyConversionRate;

    @Column(name = "base")
    private BigDecimal base;

    @Column(name = "tax")
    private BigDecimal tax;

    @Column(name = "tax_data", columnDefinition = "TEXT")
    private String taxData;

    @Column(name = "commission_amount")
    private BigDecimal commissionAmount;

    @Column(name = "commission_percentage")
    private BigDecimal commissionPercentage;

    @Column(name = "commission_percentage_to_amount")
    private BigDecimal commissionPercentageToAmount;

    @Column(name = "extra_commission_amount")
    private BigDecimal extraCommissionAmount;

    @Column(name = "extra_commission_percentage")
    private BigDecimal extraCommissionPercentage;

    @Column(name = "extra_commission_percentage_to_amount")
    private BigDecimal extraCommissionPercentageToAmount;

    @Column(name = "supplier_total")
    private BigDecimal supplierTotal;

    @Column(name = "m1")
    private BigDecimal m1;

    @Column(name = "m1_template_id")
    private Long m1TemplateId;

    @Column(name = "m1_template_data", columnDefinition = "TEXT")
    private String m1TemplateData;

    @Column(name = "m2")
    private BigDecimal m2;

    @Column(name = "m2_reason_data", columnDefinition = "TEXT")
    private String m2ReasonData;

    @Column(name = "d1")
    private BigDecimal d1;

    @Column(name = "d1_template_id")
    private Integer d1TemplateId;

    @Column(name = "d1_template_data", columnDefinition = "TEXT")
    private String d1TemplateData;

    @Column(name = "d2")
    private BigDecimal d2;

    @Column(name = "d2_reason_data", columnDefinition = "TEXT")
    private String d2ReasonData;

    @Column(name = "grand_total")
    private BigDecimal grandTotal;

    @Column(name = "added_by")
    private Integer addedBy;

    @Column(name = "added_date")
    private String addedDate;

    @Column(name = "added_device")
    private String addedDevice;

    @Column(name = "added_ip")
    private String addedIp;

    @Column(name = "updated_by")
    private Integer updatedBy;

    @Column(name = "updated_date")
    private String updatedDate;

    @Column(name = "updated_device")
    private String updatedDevice;

    @Column(name = "updated_ip")
    private String updatedIp;

}


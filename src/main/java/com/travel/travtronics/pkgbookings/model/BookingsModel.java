package com.travel.travtronics.pkgbookings.model;

import java.math.BigDecimal;
import java.util.Date;

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
@Table(name = "booking")
public class BookingsModel {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long bookingId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "booking_channel")
    private String bookingChannel;

    @Column(name = "booking_reference_number")
    private String bookingReferenceNumber;

    @Column(name = "booking_status_id")
    private Integer bookingStatusId;

    @Column(name = "booking_status_name")
    private String bookingStatusName;

    @Column(name = "booking_service_request_id")
    private Integer bookingServiceRequestId;

    @Column(name = "booking_service_request_line_id")
    private Integer bookingServiceRequestLineId;

    @Column(name = "quote_id")
    private Integer quoteId;

    @Column(name = "supplier_id")
    private Integer supplierId;

    @Column(name = "supplier_reference_id")
    private String supplierReferenceId;

    @Column(name = "supplier_reference_date")
    private String supplierReferenceDate;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "customer_contact_id")
    private Integer customerContactId;

    @Column(name = "customer_contact_name")
    private String customerContactName;

    @Column(name = "customer_contact_phone")
    private String customerContactPhone;

    @Column(name = "customer_contact_email")
    private String customerContactEmail;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "liability_owner_id")
    private Integer liabilityOwnerId;

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

    @Column(name = "tax_data")
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
    private Integer m1TemplateId;

    @Column(name = "m1_template_data")
    private String m1TemplateData;

    @Column(name = "m2")
    private BigDecimal m2;

    @Column(name = "m2_reason_data")
    private String m2ReasonData;

    @Column(name = "d1")
    private BigDecimal d1;

    @Column(name = "d1_template_id")
    private Integer d1TemplateId;

    @Column(name = "d1_template_data")
    private String d1TemplateData;

    @Column(name = "d2")
    private BigDecimal d2;

    @Column(name = "d2_reason_data")
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

    @Column(name = "warehouse")
    private Integer warehouse;

    @Column(name = "warehouse_date")
    private Date warehouseDate;

}


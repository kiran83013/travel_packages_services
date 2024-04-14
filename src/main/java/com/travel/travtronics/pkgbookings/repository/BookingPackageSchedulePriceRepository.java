package com.travel.travtronics.pkgbookings.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travtronics.pkgbookings.model.BookingPackageSchedulePriceModel;

public interface BookingPackageSchedulePriceRepository extends JpaRepository<BookingPackageSchedulePriceModel, Long> {

	List<BookingPackageSchedulePriceModel> findByBookingId(Long bookingId);

}

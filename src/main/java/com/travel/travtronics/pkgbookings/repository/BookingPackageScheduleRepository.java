package com.travel.travtronics.pkgbookings.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travtronics.pkgbookings.model.BookingPackageScheduleModel;

public interface BookingPackageScheduleRepository extends JpaRepository<BookingPackageScheduleModel, Long> {

	BookingPackageScheduleModel findByBookingId(Long bookingId);

}

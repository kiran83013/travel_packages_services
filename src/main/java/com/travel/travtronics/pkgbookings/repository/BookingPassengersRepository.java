package com.travel.travtronics.pkgbookings.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travtronics.pkgbookings.model.BookingPassengersModel;

public interface BookingPassengersRepository extends JpaRepository<BookingPassengersModel, Long> {

	List<BookingPassengersModel> findByBookingId(Long bookingId);

}

package com.travel.travtronics.pkgbookings.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travtronics.pkgbookings.model.BookingsModel;

public interface BookingsRepository extends JpaRepository<BookingsModel, Long> {

}

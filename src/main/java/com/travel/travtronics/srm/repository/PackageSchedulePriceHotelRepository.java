package com.travel.travtronics.srm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travtronics.srm.model.PackageSchedulePriceHotelModel;

public interface PackageSchedulePriceHotelRepository extends JpaRepository<PackageSchedulePriceHotelModel, Long> {

	List<PackageSchedulePriceHotelModel> findBySubOptionId(Long id);

}

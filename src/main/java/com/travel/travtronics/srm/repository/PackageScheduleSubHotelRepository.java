package com.travel.travtronics.srm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travtronics.srm.model.PackageScheduleSubHotelModel;

public interface PackageScheduleSubHotelRepository extends JpaRepository<PackageScheduleSubHotelModel, Long> {

	List<PackageScheduleSubHotelModel> findByOptionId(Long splrId);

}

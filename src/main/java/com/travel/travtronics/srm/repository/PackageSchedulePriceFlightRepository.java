package com.travel.travtronics.srm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travtronics.srm.model.PackageSchedulePriceFlightModel;

public interface PackageSchedulePriceFlightRepository extends JpaRepository<PackageSchedulePriceFlightModel, Long> {

	List<PackageSchedulePriceFlightModel> findBySupplierFlightId(Long splrHeaderId);

}

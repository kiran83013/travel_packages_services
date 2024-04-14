package com.travel.travtronics.srm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travtronics.srm.model.SrPackagePriceLinePaxModel;

public interface SrPackagePriceLinePaxRepository extends JpaRepository<SrPackagePriceLinePaxModel, Long> {

	List<SrPackagePriceLinePaxModel> findBySrPackagePriceLineId(Long pkgPriceSrId);

}

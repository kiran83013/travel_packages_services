package com.travel.travtronics.srm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travtronics.srm.model.SrPackagePriceLineBreakupModel;

public interface SrPackagePriceLineBreakupRepository extends JpaRepository<SrPackagePriceLineBreakupModel, Long> {

	List<SrPackagePriceLineBreakupModel> findBySrPackagePriceLineId(Long pkgPriceSrId);

}

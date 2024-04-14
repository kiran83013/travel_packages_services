package com.travel.travtronics.srm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.travel.travtronics.srm.model.PackageSchedulePriceItemLink;

public interface PackageSchedulePriceItemLinkRepository extends JpaRepository<PackageSchedulePriceItemLink, Long>,JpaSpecificationExecutor<PackageSchedulePriceItemLink>{

//	List<PackageSchedulePriceItemLink> findAll(Specification<PackageSchedulePriceItemLink> findByPersonSpecifications);

}

package com.travel.travtronics.srm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.travel.travtronics.srm.model.PackageConfigSearchEntity;

public interface PackageConfigSearchEntityRepository
		extends JpaRepository<PackageConfigSearchEntity, Long>, JpaSpecificationExecutor<PackageConfigSearchEntity> {



}

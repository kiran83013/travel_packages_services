package com.travel.travtronics.srm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travtronics.srm.model.SrAnxAddons;

public interface SrAnxAddonsRepository extends JpaRepository<SrAnxAddons, Integer> {

	
	List<SrAnxAddons> findByAddonSrIdAndAddonLineIdAndAddonIsDeletedIsNull(Integer sr,Integer srLine);
	
	
	
}

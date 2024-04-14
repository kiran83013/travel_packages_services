package com.travel.travtronics.srm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travtronics.srm.model.SrHotelAddons;

public interface SrHotelAddonsRepository  extends JpaRepository<SrHotelAddons, Integer>{

	//List<SrHotelAddons> findAllByAddonSrId(Integer srId);

	List<SrHotelAddons> findByAddonLineId(Integer id);

	List<SrHotelAddons> findAllByAddonSrIdAndAddonIsDeletedIsNull(Integer srId);

	List<SrHotelAddons> findByAddonLineIdAndAddonIsDeletedIsNull(Integer id);

	List<SrHotelAddons> findByAddonLineIdAndAddonSrIdAndAddonIsDeletedIsNull(Integer id, Integer sr);

	
}

package com.travel.travtronics.srm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travtronics.srm.model.SrHospitalAddons;

public interface SrHospitalAddonsRepository  extends JpaRepository<SrHospitalAddons, Integer>{

	List<SrHospitalAddons> findByAddonLineId(Integer id);

	List<SrHospitalAddons> findAllByAddonSrIdAndAddonIsDeletedIsNull(Integer srId);

	List<SrHospitalAddons> findByAddonLineIdAndAddonIsDeletedIsNull(Integer id);

	List<SrHospitalAddons> findByAddonLineIdAndAddonSrIdAndAddonIsDeletedIsNull(Integer id, Integer sr);

	
}

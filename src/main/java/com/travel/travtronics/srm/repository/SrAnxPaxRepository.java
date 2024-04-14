package com.travel.travtronics.srm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travtronics.srm.model.SrAnxPax;

public interface SrAnxPaxRepository extends JpaRepository<SrAnxPax, Long> {

	Optional<SrAnxPax> findByRequestLinePaxId(Long requestLinePaxId);
	
	List<SrAnxPax> findByRequestIdAndRequestLineIdAndPaxIsDeletedIsNull(Long sr,Long srLine);
	

}

package com.travel.travtronics.srm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travtronics.srm.model.SrAttractionsModel;

import jakarta.validation.Valid;

public interface SrAttractionsRepository extends JpaRepository<SrAttractionsModel, Long> {

	Optional<SrAttractionsModel> findByAttractionIdAndAttractionStatus(@Valid Long srAttractionId, Integer status);

}

package com.travel.travtronics.bpf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travtronics.bpf.model.TaxSlabLines;
import com.travel.travtronics.util.Status;

public interface TaxLinesRepository extends JpaRepository<TaxSlabLines, Long> {
	List<TaxSlabLines> findAllByTaxSlabHeaderIdAndStatus(Long id, Status active);
}

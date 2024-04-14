package com.travel.travtronics.bpf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travtronics.bpf.model.ServiceTypeLines;
import com.travel.travtronics.util.Status;

public interface ServiceTypesLineRepository extends JpaRepository<ServiceTypeLines, Long> {

	List<ServiceTypeLines> findAllByHeaderIdAndStatus(Long id, Status active);

	List<ServiceTypeLines> findAllByHeaderIdAndStatusAndIsPricing(Long id, Status active, Long isPricing);

}

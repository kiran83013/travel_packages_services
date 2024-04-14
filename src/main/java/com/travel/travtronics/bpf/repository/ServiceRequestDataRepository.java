package com.travel.travtronics.bpf.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.bpf.model.ServiceRequestDataModel;
import com.travel.travtronics.response.SREntityResponse;

public interface ServiceRequestDataRepository extends JpaRepository<ServiceRequestDataModel, Long> {

	@Query(value = "SELECT * FROM service_request_data WHERE SRID = ?1", nativeQuery = true)
	Optional<ServiceRequestDataModel> findBySrId(Long id);

	@Query(value = "SELECT * FROM service_request_data WHERE ID = ?1 AND SRID = ?2", nativeQuery = true)
	Optional<ServiceRequestDataModel> findBySrIdAndDataId(Long dataId, Long srId);
	
	@Query(value = "SELECT * FROM service_request_data WHERE SRID = ?1", nativeQuery = true)
	Optional<SREntityResponse> findBySRId(Long id);

}

package com.travel.travtronics.bpf.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.bpf.model.SrEntity;

public interface SrRepository extends JpaRepository<SrEntity, Long>, JpaSpecificationExecutor<SrEntity> {

	List<SrEntity> findByCloseIsNotNullAndCancelledIsNotNullAndSubmittedDateIsNull();

	List<SrEntity> findByCloseIsNullAndCancelledIsNullAndSubmittedDateIsNotNull();

	@Query(value = "SELECT *\r\n" + "FROM service_request\r\n" + "WHERE SR_Type_Id = ?", nativeQuery = true)
	Page<SrEntity> findAllSrTypeId(Long srTypeId, Pageable pageable);

	@Query(value = "SELECT sth.name AS NAME FROM bpf.service_types_header sth  WHERE sth.ID=?1", nativeQuery = true)
	Optional<String> getSrTypeName(Long srTypeId);
	
	@Query(value = "SELECT st.name AS NAME FROM bpf.status st  WHERE st.StatusId=?1", nativeQuery = true)
	Optional<String> getStatusName(Long srStatusId);

//	Page<SrEntity> searchNeedMyRequest(ServiceRequestDto searchDto, Pageable pageable);
	
//	@Query("SELECT st.name FROM ServiceTypesHeader st WHERE st.id = :srTypeId")
//    Optional<String> getSrTypeName(@Param("srTypeId") Long srTypeId);

}

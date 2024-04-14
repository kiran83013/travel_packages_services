package com.travel.travtronics.srm.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.travel.travtronics.srm.model.ServiceRequestLine;

public interface ServiceRequestLineRepository extends JpaRepository<ServiceRequestLine, Long> {

	Optional<ServiceRequestLine> findByRequestLineIdAndRequestId(Long requestLineId, Long requestId);

	Optional<ServiceRequestLine> findTopByRequestIdOrderByCreatedDateDesc(Long requestId);

	List<ServiceRequestLine> findAllByRequestId(Long requestId);

	Optional<ServiceRequestLine> findByRequestId(Long requestId);

	@Query(value = "select srl from ServiceRequestLine srl where srl.requestLineId in :requestLineIds")
	List<ServiceRequestLine> findAllByRequestLineId(@Param("requestLineIds") List<Long> requestLineIds);

	@Query(value = "{CALL srm.sequence_number_generator(:sequence_module_in,:sequence_prefix_in,:number_length)}", nativeQuery = true)
	Long generateSeqenceNumber(@Param("sequence_module_in") String sequence_module_in,
			@Param("sequence_prefix_in") String sequence_prefix_in, @Param("number_length") Integer number_length);

	Optional<ServiceRequestLine> findByRequestLineId(Long requestLineId);

	Optional<ServiceRequestLine> findByRequestLineIdAndRequestIdAndLineUuid(Long requestLineId, Long requestId,
			String lineUuid);

	@Query(value = "SELECT\n" + "`sr_flight_line`.`AddOns`\n" + "FROM \n" + "`sr_flight_line`\n" + "WHERE 1=1 AND\n"
			+ "`sr_flight_line`.`REQUESTID`=?1\n" + "AND `sr_flight_line`.`REQUESTLINEID`=?2", nativeQuery = true)
	String getAddonsBySrAndSrLine(Long requestId, Long requestLineId);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update ServiceRequestLine flight set flight.lineStatusId =:lineStatusId where flight.requestId =:requestId and flight.requestLineId=:requestLineId")
	int updateLineStatus(@Param("requestId") Long requestId, @Param("requestLineId") Long requestLineId,
			@Param("lineStatusId") Integer lineStatusId);

	@Query(value = "SELECT * FROM sr_flight_line WHERE REQUESTID = ?1 AND LineStatusId != 42", nativeQuery = true)
	List<ServiceRequestLine> getAllUnFulfilledCount(long srId);

}

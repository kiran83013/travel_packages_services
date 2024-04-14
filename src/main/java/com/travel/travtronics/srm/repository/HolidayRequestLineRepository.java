package com.travel.travtronics.srm.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.travel.travtronics.srm.model.HolidayRequestLine;

public interface HolidayRequestLineRepository extends JpaRepository<HolidayRequestLine, String> {

	Optional<HolidayRequestLine> findByRequestIdAndRequestLineId(Long requestId, Long requestLineId);

	Optional<HolidayRequestLine> findByRequestIdAndRequestLineIdAndLineUuid(Long requestId, Long requestLineId,
			String lineUuid);

	List<HolidayRequestLine> findByRequestId(Long requestId);

	@Query("select holiday.requestLineId from HolidayRequestLine holiday where holiday.requestId=?1")
	List<Long> findAllLineIdByRequestId(Long requestId);

	@Query(value = "SELECT * FROM sr_package_line WHERE request_id = ?1", nativeQuery = true)
	Optional<HolidayRequestLine> getPackageRequestInfoByRequestId(Long requestId);

	@Query(value = "SELECT b.*, qh.* FROM flight.booking b \r\n"
			+ "LEFT JOIN srm.quotes_header qh ON qh.id = b.QuoteId\r\n" + "WHERE b.ServiceRequestId = ?1\r\n"
			+ "AND\r\n" + "b.ServiceRequestLineId = ?2\r\n" + "AND\r\n" + "b.QuoteId = ?3", nativeQuery = true)
	List<Map<String, String>> findByBookingandQuotesData(Integer srId, Integer srLineId, Integer quoteId);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update HolidayRequestLine pkg set pkg.lineStatusId =:lineStatusId where pkg.requestId =:requestId and pkg.requestLineId=:requestLineId")
	int updateLineStatus(@Param("requestId") Long requestId, @Param("requestLineId") Integer requestLineId,
			@Param("lineStatusId") Integer lineStatusId);

	Long countByRequestId(Long currentSrId);

	@Query(value = "SELECT COUNT(1) FROM sr_request WHERE sr_request.`ProductId`=12 AND sr_request.`REQUESTID`=?1", nativeQuery = true)
	Long countByPackageRequestId(Long srId);

	@Query(value = "{CALL srm.sequence_number_generator(:sequence_module_in,:sequence_prefix_in,:number_length)}", nativeQuery = true)
	Long generateSeqenceNumber(@Param("sequence_module_in") String sequence_module_in,
			@Param("sequence_prefix_in") String sequence_prefix_in, @Param("number_length") Integer number_length);

}

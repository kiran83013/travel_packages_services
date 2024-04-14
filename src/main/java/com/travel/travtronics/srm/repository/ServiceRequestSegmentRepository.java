package com.travel.travtronics.srm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.srm.model.ServiceRequestSegment;

public interface ServiceRequestSegmentRepository extends JpaRepository<ServiceRequestSegment, Long> {

	Optional<ServiceRequestSegment> findByRequestSegmentId(Long requestSegmentId);

	@Query("select flight_segments from ServiceRequestSegment flight_segments where flight_segments.requestlineID=?1 and flight_segments.requestID=?2 and flight_segments.flightSegmentStatus='Active'")
	List<ServiceRequestSegment> findAllByRequestlineIDAndRequestID(Long requestlineID, Long sr);

}

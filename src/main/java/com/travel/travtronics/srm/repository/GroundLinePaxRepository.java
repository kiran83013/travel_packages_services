package com.travel.travtronics.srm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.srm.model.GTPax;

public interface GroundLinePaxRepository extends JpaRepository<GTPax, Long> {

	@Query("select pax from GTPax pax where pax.requestId=?1 and pax.requestLineId=?2 and (pax.paxIsDeleted is null or pax.paxIsDeleted=false)")
	List<GTPax> findByRequestIdAndRequestLineId(Long srId, Long srLineId);

	GTPax findByRequestLinePaxId(Long id);

}

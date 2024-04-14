package com.travel.travtronics.srm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.srm.model.PaxServiceRequestLine;

public interface PaxServiceRequestLineRepository extends JpaRepository<PaxServiceRequestLine, Long> {

	List<PaxServiceRequestLine> findAllByPaxId(Long paxId);

	List<PaxServiceRequestLine> findAllByRequestLineIdAndRequestId(Long requestLineId, Long requestId);

	Optional<PaxServiceRequestLine> findByRequestLinePaxId(Long requestLinePaxId);

	List<PaxServiceRequestLine> findAllByRequestLineIdAndPaxIdIn(Long requestLineId, List<Long> paxId);

	@Query(value = "SELECT master_country.`Name` FROM masters_srm.`master_country` WHERE master_country.`ID`=?1", nativeQuery = true)
	String fetchCountryName(Long id);

	@Query(value = "SELECT  master_nationality.`Name` FROM masters_srm.`master_nationality` WHERE master_nationality.ID=?1", nativeQuery = true)
	String fetchNationalityName(Long id);

	@Query(value = "SELECT master_pax_type.`Name` FROM masters_srm.`master_pax_type` WHERE master_pax_type.`ID`=?1", nativeQuery = true)
	String fetchPaxTypeName(Long id);

	@Query(value = "SELECT master_pax_type.`Code`  FROM masters_srm.`master_pax_type` WHERE master_pax_type.`ID`=?1", nativeQuery = true)
	String fetchPaxTypeCode(long id);

	List<PaxServiceRequestLine> findAllByRequestLineId(Long requestLineId);

	List<PaxServiceRequestLine> findAllByRequestLineIdAndRequestIdAndPaxIsDeletedIsNull(Long requestLineId, Long sr);

}

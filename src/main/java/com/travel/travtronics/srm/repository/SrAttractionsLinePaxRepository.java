package com.travel.travtronics.srm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.srm.model.SrAttractionsLinePaxModel;

public interface SrAttractionsLinePaxRepository extends JpaRepository<SrAttractionsLinePaxModel, Long> {

	@Query(value = "SELECT\n" + "pax.*\n" + "FROM\n" + "srm.`sr_attractions` attr\n"
			+ "JOIN srm.`sr_attraction_lines` line ON line.attraction_header_id = attr.attraction_id \n"
			+ " JOIN srm.`sr_attraction_line_pax` pax ON pax.attraction_line_id=line.attraction_line_id AND pax.`attraction_line_passenger_status`=1\n"
			+ "WHERE\n" + "attr.`attraction_id`=?1", nativeQuery = true)
	List<SrAttractionsLinePaxModel> findByAttractionLineId(Long attractionLineId);

	List<SrAttractionsLinePaxModel> findByAttractionLineIdAndAttractionLinePassengerStatus(Long attractionLineId,
			int i);

	@Query(value = "SELECT\n" + "`attraction_line_pax_count`\n" + "FROM\n" + "srm.`sr_attractions` attr\n"
			+ "JOIN srm.`sr_attraction_lines` line ON line.attraction_header_id = attr.attraction_id \n" + "WHERE\n"
			+ "attr.`attraction_id`=?1", nativeQuery = true)
	Integer getPaxLineCount(Long attractionLineId);

}

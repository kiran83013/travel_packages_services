package com.travel.travtronics.packages.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.travel.travtronics.packages.model.PackageItineraryHeaderModel;
import com.travel.travtronics.util.QueryConst;

public interface PackageItineraryHeaderRepository extends JpaRepository<PackageItineraryHeaderModel, Integer> {

	@Query(value = QueryConst.ITINERARY_LIST_QUERY, nativeQuery = true)
	List<Map<String, Object>> getItinaryListDataAndSearch(@Param("itineraryName") String itineraryName);

	@Query(value = QueryConst.ITINERARY_INFO_QUERY, nativeQuery = true)
	Optional<PackageItineraryHeaderModel> getItineraryInfoById(Integer id);

	@Query(value = QueryConst.ITINERARY_LINES_LIST_QUERY, nativeQuery = true)
	List<Map<String, Object>> getItinaryLinesListDataById(Integer id);

	@Query(value = QueryConst.ITEMS_LIST_QUERY, nativeQuery = true)
	List<Map<String, Object>> getItemsListDataAndSearch(@Param("itemName") String itemName);

	
}

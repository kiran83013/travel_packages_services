package com.travel.travtronics.packages.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.packages.model.PackageItineraryPriceLinesModel;

public interface PackageItineraryPriceLinesRepository extends JpaRepository<PackageItineraryPriceLinesModel, Integer> {

	@Query(value = "SELECT * FROM package_itinerary_price_lines WHERE header_id = ?1", nativeQuery = true)
	List<PackageItineraryPriceLinesModel> getPriceLinesListByHeaderId(Integer id);

}

package com.travel.travtronics.srm.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.travel.travtronics.srm.model.PackageTags;

public interface PackageTagsRepository extends JpaRepository<PackageTags, Long> {

	@Query(value = "SELECT * FROM package_tags WHERE id IN (:tagIds) AND status = 1", nativeQuery = true)
	List<PackageTags> getTagsListByIds(@Param("tagIds") List<String> tagIds);

	@Query(value = "SELECT p.ACTIVITYID AS activityID, p.ActivityName AS activityName, p.Location AS location, p.City AS city, p.Country AS country "
			+ "FROM packages.package_activity p WHERE (p.ActivityName LIKE %?1% OR p.Location LIKE %?2% OR p.City LIKE %?3% OR p.Country LIKE %?4%)", nativeQuery = true)
	List<Map<String, String>> getSearchData(String activityName, String location, String city, String country);

}

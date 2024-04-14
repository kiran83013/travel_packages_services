package com.travel.travtronics.srm.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.srm.model.SecondaryTags;

public interface SecondaryTagsRepository extends JpaRepository<SecondaryTags, Long> {

	@Query(value = "SELECT pcpt.tag_id AS tagId, pcpt.tag_name AS tagName  FROM package_config_secondary_tags AS pcpt WHERE pcpt.header_id = ?1", nativeQuery = true)
	List<Map<String, String>> findByHeaderId(Long id);

	@Query(value = "SELECT * FROM `srm`.`package_config_secondary_tags` WHERE `header_id`=?1 AND `tag_id`=?2 ORDER BY id LIMIT 1", nativeQuery = true)
	Optional<SecondaryTags> findByHeaderIdAndTagId(Long id, Long id2);

}

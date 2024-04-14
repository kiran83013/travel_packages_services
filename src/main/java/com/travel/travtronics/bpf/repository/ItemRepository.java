package com.travel.travtronics.bpf.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.bpf.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

	@Query(value = "SELECT * FROM bpf.item WHERE ID = ?1 AND Status = 'Active'", nativeQuery = true)
	Optional<Item> getItemInfoById(Long itemId);

	List<Item> findAllByOrganizationId(Long organizationId);

	Page<Item> findByOrganizationId(Long organizationId, Pageable pageable);

}

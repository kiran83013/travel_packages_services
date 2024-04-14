package com.travel.travtronics.bpf.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.travel.travtronics.bpf.model.FeildLanguageLinesModel;

public interface FeildLanguageLinesRepository extends PagingAndSortingRepository<FeildLanguageLinesModel, Integer> {

	boolean existsById(Integer id);

	Page<FeildLanguageLinesModel> findByHeaderIdAndOrgId(Integer headerId, Integer orgId, Pageable pageable);

	Optional<FeildLanguageLinesModel> findByOrgIdAndLangCodeAndFieldId(Integer orgId, String langCode, Integer fieldId);
}

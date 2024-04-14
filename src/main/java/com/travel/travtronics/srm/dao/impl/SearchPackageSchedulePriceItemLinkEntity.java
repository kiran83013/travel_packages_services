package com.travel.travtronics.srm.dao.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.jpa.domain.Specification;

import com.travel.travtronics.srm.model.PackageSchedulePriceItemLink;

import jakarta.persistence.criteria.Predicate;

public class SearchPackageSchedulePriceItemLinkEntity {

	public static Specification<PackageSchedulePriceItemLink> findByPricItemLinkSpecifications(Integer serviceRequestId,
			Integer serviceRequestLineId, Integer serviceRequestTypeId, Integer scheduleId, Integer linkStatus) {
		
		return (root, query, criteriaBuilder) -> {
			
			final Collection<Predicate> predicates = new ArrayList<>();

			if (serviceRequestId != null && serviceRequestId != 0) {

				predicates.add(criteriaBuilder.equal(root.get("serviceRequestId"), serviceRequestId));
			}
			if (serviceRequestLineId != null && serviceRequestLineId != 0) {

				predicates.add(criteriaBuilder.equal(root.get("serviceRequestLineId"), serviceRequestLineId));
			}

			if (serviceRequestTypeId != null && serviceRequestTypeId != 0) {

				predicates.add(criteriaBuilder.equal(root.get("serviceRequestTypeId"), serviceRequestTypeId));
			}

			if (scheduleId != null && scheduleId != 0) {

				predicates.add(criteriaBuilder.equal(root.get("scheduleId"), scheduleId));
			}
			if (linkStatus != null && linkStatus != 0) {

				predicates.add(criteriaBuilder.equal(root.get("linkStatus"), linkStatus));
			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

		};
	}
}

package com.travel.travtronics.bpf.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.travel.travtronics.bpf.model.Item;
import com.travel.travtronics.bpf.model.PricingHeader;
import com.travel.travtronics.bpf.model.TaxSlabHeader;
import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.request.dto.PricingCustomRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Component
public class PricingPaginationRepositoryImpl implements PricingCustomRepository {

//	@PersistenceContext
	@Qualifier("bpfEntityManager")
	@Autowired
	EntityManager em;

	@Override
	public Page<PricingHeader> fetchPricingPagination(String name, Long organization, Pageable pageable, String sortBy,
			SortType sortType) {

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<PricingHeader> createQuery = builder.createQuery(PricingHeader.class);

		Root<PricingHeader> root = createQuery.from(PricingHeader.class);

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (organization != null && organization != 0) {
			predicates.add(builder.equal(root.<Long>get("organization"), organization));
		}
		if (name != null && !name.isBlank()) {

			predicates.add(builder.like(builder.lower(root.<String>get("name")), "%" + name.toLowerCase() + "%"));
		}
		createQuery.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

		if ((sortType == SortType.asc || Objects.isNull(sortType)) && sortBy != null && !sortBy.isEmpty()) {
			createQuery.orderBy(builder.asc(root.get(sortBy)));
		}
		if (sortType == SortType.desc && sortBy != null && !sortBy.isEmpty()) {
			createQuery.orderBy(builder.desc(root.get(sortBy)));
		}

		List<PricingHeader> resultList = em.createQuery(createQuery)
				.setFirstResult(Math.toIntExact(pageable.getOffset())).setMaxResults(pageable.getPageSize())
				.getResultList();

//		CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
//
//		Root<PricingHeader> serviceRootCount = countQuery.from(PricingHeader.class);
//
//		countQuery.select(builder.count(serviceRootCount))
//				.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
//
//		Long count = em.createQuery(countQuery).getSingleResult();

		Long count = generateCountQueryForPriceHeader(name, organization);
		Page<PricingHeader> finalList = new PageImpl<>(resultList, pageable, count);

		return finalList;
	}

	private Long generateCountQueryForPriceHeader(String name, Long organization) {
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
		Root<PricingHeader> root = countQuery.from(PricingHeader.class);

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (organization != null && organization != 0) {
			predicates.add(builder.equal(root.<Long>get("organization"), organization));
		}
		if (name != null && !name.isBlank()) {

			predicates.add(builder.like(builder.lower(root.<String>get("name")), "%" + name.toLowerCase() + "%"));
		}

		countQuery.select(builder.count(root)).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

		Long count = em.createQuery(countQuery).getSingleResult();
		return count;
	}

	public Page<Item> fetchItemPagination(String name, Long organizationId, Pageable pageable, String sortBy,
			SortType sortType) {

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Item> createQuery = builder.createQuery(Item.class);

		Root<Item> root = createQuery.from(Item.class);

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (organizationId != null && organizationId != 0) {
			predicates.add(builder.equal(root.<Long>get("organizationId"), organizationId));
		}
		if (name != null && !name.isBlank()) {

			predicates.add(builder.like(builder.lower(root.<String>get("name")), "%" + name.toLowerCase() + "%"));
		}
		createQuery.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

		if ((sortType == SortType.asc || Objects.isNull(sortType)) && sortBy != null && !sortBy.isEmpty()) {
			createQuery.orderBy(builder.asc(root.get(sortBy)));
		}
		if (sortType == SortType.desc && sortBy != null && !sortBy.isEmpty()) {
			createQuery.orderBy(builder.desc(root.get(sortBy)));
		}

		List<Item> resultList = em.createQuery(createQuery).setFirstResult(Math.toIntExact(pageable.getOffset()))
				.setMaxResults(pageable.getPageSize()).getResultList();

//		CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
//
//		Root<Item> serviceRootCount = countQuery.from(Item.class);
//
//		countQuery.select(builder.count(serviceRootCount))
//				.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

//		Long count = em.createQuery(countQuery).getSingleResult();
		Long count = generateCountQueryForItem(name, organizationId);
		Page<Item> finalList = new PageImpl<>(resultList, pageable, count);

		return finalList;
	}

	private Long generateCountQueryForItem(String name, Long organizationId) {

		List<Predicate> predicates = new ArrayList<Predicate>();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
		Root<Item> root = countQuery.from(Item.class);

		if (organizationId != null && organizationId != 0) {
			predicates.add(builder.equal(root.<Long>get("organizationId"), organizationId));
		}
		if (name != null && !name.isBlank()) {

			predicates.add(builder.like(builder.lower(root.<String>get("name")), "%" + name.toLowerCase() + "%"));
		}

		countQuery.select(builder.count(root)).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

		Long count = em.createQuery(countQuery).getSingleResult();
		return count;
	}

	@Override
	public Page<TaxSlabHeader> fetchTaxPagination(Long taxCategory, Long organizationId, Pageable pageable,
			String sortBy, SortType sortType) {
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<TaxSlabHeader> createQuery = builder.createQuery(TaxSlabHeader.class);

		Root<TaxSlabHeader> root = createQuery.from(TaxSlabHeader.class);

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (organizationId != null && organizationId != 0) {
			predicates.add(builder.equal(root.<Long>get("organizationId"), organizationId));
		}

		if (taxCategory != null && taxCategory != 0) {
			predicates.add(builder.equal(root.<Long>get("taxCategory"), taxCategory));
		}
		createQuery.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

		if ((sortType == SortType.asc || Objects.isNull(sortType)) && sortBy != null && !sortBy.isEmpty()) {
			createQuery.orderBy(builder.asc(root.get(sortBy)));
		}
		if (sortType == SortType.desc && sortBy != null && !sortBy.isEmpty()) {
			createQuery.orderBy(builder.desc(root.get(sortBy)));
		}

		List<TaxSlabHeader> resultList = em.createQuery(createQuery)
				.setFirstResult(Math.toIntExact(pageable.getOffset())).setMaxResults(pageable.getPageSize())
				.getResultList();

//		CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
//
//		Root<TaxSlabHeader> serviceRootCount = countQuery.from(TaxSlabHeader.class);
//
//		countQuery.select(builder.count(serviceRootCount))
//				.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
//
//		Long count = em.createQuery(countQuery).getSingleResult();

		Long count = countQueryForTaxSlab(taxCategory, organizationId);
		Page<TaxSlabHeader> finalList = new PageImpl<>(resultList, pageable, count);

		return finalList;
	}

	private Long countQueryForTaxSlab(Long taxCategory, Long organizationId) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
		Root<TaxSlabHeader> root = countQuery.from(TaxSlabHeader.class);
		countQuery.select(builder.count(root)).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

		Long count = em.createQuery(countQuery).getSingleResult();

		return count;
	}

}

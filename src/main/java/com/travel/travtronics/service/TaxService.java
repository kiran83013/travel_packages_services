package com.travel.travtronics.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.travel.travtronics.bpf.model.MasterTaxCategory;
import com.travel.travtronics.bpf.model.TaxSlabHeader;
import com.travel.travtronics.bpf.model.TaxSlabLines;
import com.travel.travtronics.bpf.repository.ItemRepository;
import com.travel.travtronics.bpf.repository.MasterTaxCategoryRepository;
import com.travel.travtronics.bpf.repository.TaxHeaderRepository;
import com.travel.travtronics.bpf.repository.TaxLinesRepository;
import com.travel.travtronics.converter.TaxConverter;
import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.request.TaxRequestModel;
import com.travel.travtronics.request.dto.PricingCustomRepository;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.MessageStatusResponse;
import com.travel.travtronics.response.TaxHeaderResponse;
import com.travel.travtronics.util.Status;

@Service
public class TaxService {
	@Autowired
	TaxHeaderRepository headerRepository;

	@Autowired
	TaxLinesRepository linesRepository;

	@Autowired
	ItemRepository ItemRepository;

	@Autowired
	MasterTaxCategoryRepository masterTaxCategoryRepository;

	@Autowired
	PricingCustomRepository pricingCustomRepository;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public MessageStatusResponse createTaxSlab(TaxRequestModel model) {

		TaxSlabHeader save = headerRepository.save(TaxConverter.convertTaxJsonToModel(model.getTaxSlabHeader()));
		logger.info("--header saved--");

		model.getTaxSlabLines().stream().forEach(line -> {
			line.setTaxSlabHeaderId(save.getId());
			logger.info("---headerid saved in lines---");
		});

		linesRepository.saveAll(TaxConverter.convertTaxJsonLinesToModels(model.getTaxSlabLines()));

		logger.info("---lines saved to db--");
		return new MessageStatusResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name());
	}

	public APIResponse getTaxHeader(Long id) {
		Optional<TaxSlabHeader> findById = headerRepository.findById(id);
		if (findById.isEmpty()) {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), Collections.emptyList());
		} else {
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),
					Collections.singletonList(TaxConverter.convertTaxModelToResponse(findById.get())));
		}

	}

	public APIResponse getTaxHeaderListByOrganization(Long organizationId) {
		List<TaxHeaderResponse> map = TaxConverter
				.convertTaxJsonHeaderToModels(headerRepository.findAllByOrganizationId(organizationId)).stream()
				.map(tax -> {
					Optional<MasterTaxCategory> findById = masterTaxCategoryRepository.findById(tax.getTaxCategory());
					tax.setTaxCategoryName(findById.get().getName() != null ? findById.get().getName() : null);
					return tax;
				}).collect(Collectors.toList());
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), map);
	}

	public MessageStatusResponse modifyTaxSlab(TaxRequestModel model, Long id) {
		Optional<TaxSlabHeader> findById = headerRepository.findById(id);
		if (findById.isEmpty()) {
			return new MessageStatusResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name());
		} else {

			TaxSlabHeader save = headerRepository.save(TaxConverter.updateTaxJsonToModel(model.getTaxSlabHeader(), id));

			logger.info("--header saved--");

			model.getTaxSlabLines().stream().forEach(line -> {
				line.setTaxSlabHeaderId(save.getId());
				logger.info("---headerid saved in lines---");
			});

			linesRepository.saveAll(TaxConverter.convertTaxJsonLinesToModels(model.getTaxSlabLines()));

			logger.info("---lines saved to db--");
			return new MessageStatusResponse(HttpStatus.OK.value(), HttpStatus.OK.name());

		}
	}

	public APIResponse getTaxLinesByHeader(Long id) {
		List<TaxSlabLines> models = linesRepository.findAllByTaxSlabHeaderIdAndStatus(id, Status.Active);
		if (models.isEmpty()) {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), Collections.emptyList());
		} else {
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),
					TaxConverter.convertTaxLineModelsToResponse(models));
		}
	}

	public APIResponse getTaxDataByComponent(Long id) {
		List<TaxSlabHeader> headerModels = headerRepository.findByTaxCategory(id);

		List<TaxSlabLines> collect = headerModels.stream().map(model -> {

			List<TaxSlabLines> activeLineModels = linesRepository.findAllByTaxSlabHeaderIdAndStatus(model.getId(),
					Status.Active);

			return activeLineModels;
		}).flatMap(Collection::stream).collect(Collectors.toList());
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),
				TaxConverter.convertTaxLineModelsToResponse(collect));
	}

	public APIResponsePaging getPagenationByOrganization(Long organizationId, Long taxCategory, int pageNo,
			int pageSize, String sortBy, SortType sortType) {
//		Pageable pageable = PageRequest.of(pageNo, pageSize,
//				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
//		if (organizationId != null) {
//
//			Page<TaxSlabHeader> findByOrganizationId = headerRepository.findByOrganizationId(organizationId, pageable);
//
//			Page<TaxHeaderResponse> item = findByOrganizationId.map(model -> {
//				TaxHeaderResponse response = TaxConverter.convertTaxModelToResponse(model);
//				Optional<MasterTaxCategory> findById = masterTaxCategoryRepository.findById(response.getTaxCategory());
//				response.setTaxCategoryName(findById.isPresent() ? findById.get().getName() : null);
//				return response;
//			});
//
//			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(),
//					new ArrayList<>(), item.getNumber(), item.getTotalElements(), item.getTotalPages());
//		} else {
//			Page<TaxSlabHeader> findByOrganizationId = headerRepository.findAll(pageable);
//			Page<TaxHeaderResponse> itemPage = findByOrganizationId.map(model -> {
//				TaxHeaderResponse response = TaxConverter.convertTaxModelToResponse(model);
//				Optional<MasterTaxCategory> findById = masterTaxCategoryRepository.findById(response.getTaxCategory());
//				response.setTaxCategoryName(findById.isPresent() ? findById.get().getName() : null);
//				return response;
//			});
//
//			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), itemPage.getContent(),
//					new ArrayList<>(), itemPage.getNumber(), itemPage.getTotalElements(), itemPage.getTotalPages());
//		}

		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<TaxSlabHeader> findByOrganizationId = pricingCustomRepository.fetchTaxPagination(taxCategory, organizationId,
				pageable, sortBy, sortType);
		Page<TaxHeaderResponse> item = findByOrganizationId.map(model -> {
			TaxHeaderResponse response = TaxConverter.convertTaxModelToResponse(model);
			Optional<MasterTaxCategory> findById = masterTaxCategoryRepository.findById(response.getTaxCategory());
			response.setTaxCategoryName(findById.isPresent() ? findById.get().getName() : null);
			return response;
		});

		return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(), new ArrayList<>(),
				item.getNumber(), item.getTotalElements(), item.getTotalPages());
	}

}

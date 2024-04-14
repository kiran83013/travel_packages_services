package com.travel.travtronics.srm.dao.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travel.travtronics.converter.MedInfoConverter;
import com.travel.travtronics.converter.PackageTagsConverter;
import com.travel.travtronics.master.model.CitiesMaster;
import com.travel.travtronics.master.repository.CityMasterRepository;
import com.travel.travtronics.request.MedInfoRequest;
import com.travel.travtronics.request.PackageTagsRequest;
import com.travel.travtronics.request.dto.MedInfoSearchParametersDTO;
import com.travel.travtronics.request.dto.PackageImageConfigDto;
import com.travel.travtronics.response.MedInfoResponse;
import com.travel.travtronics.srm.dao.PackageConfigDao;
import com.travel.travtronics.srm.model.FromCities;
import com.travel.travtronics.srm.model.FromCityImmutableEntity;
import com.travel.travtronics.srm.model.MedInfo;
import com.travel.travtronics.srm.model.PackageConfigSearchEntity;
import com.travel.travtronics.srm.model.PackageConfigurationImages;
import com.travel.travtronics.srm.model.PackageTags;
import com.travel.travtronics.srm.model.PrimaryTags;
import com.travel.travtronics.srm.model.SecondaryTags;
import com.travel.travtronics.srm.model.ToCities;
import com.travel.travtronics.srm.model.ToCityImmutableEntity;
import com.travel.travtronics.srm.repository.FromCitiesRepository;
import com.travel.travtronics.srm.repository.MedInfoRepository;
import com.travel.travtronics.srm.repository.PackageConfigSearchEntityRepository;
import com.travel.travtronics.srm.repository.PackageConfigurationImagesRepository;
import com.travel.travtronics.srm.repository.PackageTagsRepository;
import com.travel.travtronics.srm.repository.PrimaryTagsRepository;
import com.travel.travtronics.srm.repository.SecondaryTagsRepository;
import com.travel.travtronics.srm.repository.ToCitiesRepository;
import com.travel.travtronics.util.Status;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;

@Repository("PachageConfigDao")
public class PackageConfigDaoImpl implements PackageConfigDao {
	@Autowired
	MedInfoRepository medInfoRepository;

	@Autowired
	PrimaryTagsRepository primaryTagsRepository;

	@Autowired
	PackageTagsRepository packageTagsRepository;

	@Autowired
	SecondaryTagsRepository secondaryTagsRepository;

	@Autowired
	FromCitiesRepository fromCitiesRepository;

	@Autowired
	ToCitiesRepository toCitiesRepository;

	@Autowired
	CityMasterRepository cityMasterRepository;

	@Autowired
	PackageConfigurationImagesRepository packageConfigurationImagesRepository;

	@Autowired
	PackageConfigSearchEntityRepository packageConfigSearchEntityRepository;

	private boolean isNullOrEmpty(String value) {
		return value == null || value.trim().isEmpty();
	}

	@Override
	public MedInfo packageConfig(MedInfoRequest request) throws Exception {
		List<String> errorMessages = new ArrayList<>();

		if (isNullOrEmpty(request.getMedName())) {
			// errorMessages.add("name is mandatory");
		}

//		if (isNullOrEmpty(request.getMedCode())) {
//			errorMessages.add("code is mandatory");
//		}
//
//		if (isNullOrEmpty(request.getMedDescription())) {
//			errorMessages.add("description is mandatory");
//		}

		if (request.getMedDays() == null || request.getMedDays() <= 0) {
			// errorMessages.add("days is mandatory and should be greater than 0");
		}

//		if (request.getValidFrom() == null) {
//			errorMessages.add("validFrom is mandatory");
//		}

		if (request.getPrimaryTags() == null) {
			// errorMessages.add("primaryTags is mandatory");
		}

//		if (request.getFromCities() == null) {
//			errorMessages.add("fromCities is mandatory");
//		}
//
//		if (request.getToCities() == null) {
//			errorMessages.add("toCities is mandatory");
//		}

		if (!errorMessages.isEmpty()) {
			throw new Exception("Validation failed :" + String.join(",", errorMessages));

		}
		MedInfo medInfo = MedInfoConverter.convertRequestToEntity(request);
		medInfo = medInfoRepository.save(medInfo);

		if (medInfo != null && medInfo.getId() > 0) {

			/**************** START primary tag insertion *****************/
			if (request.getPrimaryTags() != null && request.getPrimaryTags().isEmpty() == false) {
				List<PrimaryTags> primaryTagsList = new ArrayList<PrimaryTags>();
				List<String> primaryTags = Arrays.asList(request.getPrimaryTags().split(","));
				List<PackageTags> ptagsList = packageTagsRepository.getTagsListByIds(primaryTags);
				if (ptagsList != null && ptagsList.size() > 0) {
					for (PackageTags ptag : ptagsList) {
						PrimaryTags tagInfo = new PrimaryTags();
						tagInfo.setHeaderId(medInfo.getId());
						tagInfo.setTagId(ptag.getId());
						tagInfo.setTagName(ptag.getName());
						tagInfo.setRecordStatus(Status.Active);
						tagInfo.setCreatedBy(request.getCreatedBy());
						primaryTagsList.add(tagInfo);
					}
					primaryTagsRepository.saveAll(primaryTagsList);
				}
			}
			/**************** END primary tag insertion *****************/
			/**************** START secondary tag insertion *****************/
			if (request.getSecondaryTags() != null && request.getSecondaryTags().isEmpty() == false) {
				List<SecondaryTags> secondaryTagsList = new ArrayList<SecondaryTags>();
				List<String> secondaryTags = Arrays.asList(request.getSecondaryTags().split(","));
				List<PackageTags> stagsList = packageTagsRepository.getTagsListByIds(secondaryTags);
				if (stagsList != null && stagsList.size() > 0) {
					for (PackageTags stag : stagsList) {
						SecondaryTags tagInfo = new SecondaryTags();
						tagInfo.setHeaderId(medInfo.getId());
						tagInfo.setTagId(stag.getId());
						tagInfo.setTagName(stag.getName());
						tagInfo.setRecordStatus(Status.Active);
						tagInfo.setCreatedBy(request.getCreatedBy());
						secondaryTagsList.add(tagInfo);
					}
					secondaryTagsRepository.saveAll(secondaryTagsList);
				}
			}
			/**************** END secondary tag insertion *****************/
			/**************** START from cites insertion *****************/
			if (request.getFromCities() != null && request.getFromCities().isEmpty() == false) {
				List<FromCities> fromCitiesList = new ArrayList<FromCities>();
				List<String> fromCities = Arrays.asList(request.getFromCities().split(","));
//				List<CitiesMaster> fCitiesList = cityMasterRepository.getCtyListByCodes(fromCities);

				if (fromCities != null && fromCities.size() > 0) {
					for (String fcity : fromCities) {
						FromCities cityInfo = new FromCities();
						cityInfo.setHeaderId(medInfo.getId());
						cityInfo.setCityCode(fcity);
						cityInfo.setRecordStatus(Status.Active);
						cityInfo.setCreatedBy(request.getCreatedBy());
						fromCitiesList.add(cityInfo);
					}
					fromCitiesRepository.saveAll(fromCitiesList);
				}
			}
			/**************** END from cites insertion *****************/
			/**************** START to cites insertion *****************/
			if (request.getToCities() != null && request.getToCities().isEmpty() == false) {
				List<ToCities> toCitiesList = new ArrayList<ToCities>();
				List<String> toCities = Arrays.asList(request.getToCities().split(","));
//				List<CitiesMaster> tCitiesList = cityMasterRepository.getCtyListByCodes(toCities);
				if (toCities != null && toCities.size() > 0) {
					for (String tcity : toCities) {
						ToCities cityInfo = new ToCities();
						cityInfo.setHeaderId(medInfo.getId());
						cityInfo.setCityCode(tcity);
						cityInfo.setRecordStatus(Status.Active);
						cityInfo.setCreatedBy(request.getCreatedBy());
						toCitiesList.add(cityInfo);
					}
					toCitiesRepository.saveAll(toCitiesList);
				}
			}
			/**************** END to cites insertion *****************/
		}
		return medInfo;
	}

	@Override
	public MedInfo medInfo(Long id, MedInfoRequest request) throws Exception {
		List<String> errorMessages = new ArrayList<>();

		if (isNullOrEmpty(request.getMedName())) {
			// errorMessages.add("name is mandatory");
		}

//		if (isNullOrEmpty(request.getMedCode())) {
//			errorMessages.add("code is mandatory");
//		}
//
//		if (isNullOrEmpty(request.getMedDescription())) {
//			errorMessages.add("description is mandatory");
//		}

		if (request.getMedDays() == null || request.getMedDays() <= 0) {
			// errorMessages.add("days is mandatory and should be greater than 0");
		}

//		if (request.getValidFrom() == null) {
//			errorMessages.add("validFrom is mandatory");
//		}

		if (request.getPrimaryTags() == null) {
			// errorMessages.add("primaryTags is mandatory");
		}

//		if (request.getFromCities() == null) {
//			errorMessages.add("fromCities is mandatory");
//		}
//
//		if (request.getToCities() == null) {
//			errorMessages.add("toCities is mandatory");
//		}

		if (!errorMessages.isEmpty()) {
			throw new Exception("Validation failed :" + String.join(",", errorMessages));
		}
		MedInfo existingMedInfo = medInfoRepository.findById(id).orElse(null);

		if (existingMedInfo == null) {
			throw new Exception("MedInfo not found");

		}
		if (request.getMedName() != null && request.getMedName().isEmpty() == false) {
			existingMedInfo.setMedName(request.getMedName());
		}
		if (request.getMedCode() != null && request.getMedCode().isEmpty() == false) {
			existingMedInfo.setMedCode(request.getMedCode());
		}
		if (request.getMedDescription() != null && request.getMedDescription().isEmpty() == false) {
			existingMedInfo.setMedDescription(request.getMedDescription());
		}
		if (request.getMedDays() != null) {
			existingMedInfo.setMedDays(request.getMedDays());
		}
		if (request.getMedCategory() != null) {
			existingMedInfo.setMedCategory(request.getMedCategory());
		}
		if (request.getMedType() != null) {
			existingMedInfo.setMedType(request.getMedType());
		}
		if (request.getRecordStatus() != null) {
			existingMedInfo.setRecordStatus(request.getRecordStatus());
		}
		if (request.getValidFrom() != null) {
			existingMedInfo.setValidFrom(request.getValidFrom());
		}
		if (request.getValidTo() != null) {
			existingMedInfo.setValidTo(request.getValidTo());
		}
		if (request.getUpdatedBy() != null) {
			existingMedInfo.setUpdatedBy(request.getUpdatedBy());
		}
		if (request.getSrId() != null && request.getSrId() > 0) {
			existingMedInfo.setSrId(request.getSrId());
		}

		existingMedInfo = medInfoRepository.save(existingMedInfo);

		if (existingMedInfo != null && existingMedInfo.getId() > 0) {

			/**************** START primary tag insertion *****************/
			if (request.getPrimaryTags() != null && request.getPrimaryTags().isEmpty() == false) {
//				List<PrimaryTags> primaryTagsList = new ArrayList<PrimaryTags>();
				List<String> primaryTags = Arrays.asList(request.getPrimaryTags().split(","));
				List<PackageTags> ptagsList = packageTagsRepository.getTagsListByIds(primaryTags);

				List<PrimaryTags> primaryTagsToUpdate = new ArrayList<>();
				List<PrimaryTags> primaryTagsToCreate = new ArrayList<>();

				if (ptagsList != null && ptagsList.size() > 0) {
					for (PackageTags ptag : ptagsList) {
						Optional<PrimaryTags> existingTag = primaryTagsRepository
								.findByHeaderIdAndTagId(existingMedInfo.getId(), ptag.getId());
						if (existingTag.isPresent()) {
							PrimaryTags tagInfo = existingTag.get();
							tagInfo.setTagName(ptag.getName());
							tagInfo.setRecordStatus(Status.Active);
							// ... Update other fields if needed
							primaryTagsToUpdate.add(tagInfo);
						} else {
							PrimaryTags newTagInfo = new PrimaryTags();
							newTagInfo.setHeaderId(existingMedInfo.getId());
							newTagInfo.setTagId(ptag.getId());
							newTagInfo.setTagName(ptag.getName());
							newTagInfo.setRecordStatus(Status.Active);
							newTagInfo.setCreatedBy(request.getCreatedBy());
							primaryTagsToCreate.add(newTagInfo);
						}
					}

					primaryTagsRepository.saveAll(primaryTagsToUpdate);
					primaryTagsRepository.saveAll(primaryTagsToCreate);
				}
			}
			/**************** END primary tag insertion *****************/
			/**************** START secondary tag insertion *****************/
			if (request.getSecondaryTags() != null && request.getSecondaryTags().isEmpty() == false) {
//				List<SecondaryTags> secondaryTagsList = new ArrayList<SecondaryTags>();
				List<String> secondaryTags = Arrays.asList(request.getSecondaryTags().split(","));
				List<PackageTags> stagsList = packageTagsRepository.getTagsListByIds(secondaryTags);

				List<SecondaryTags> secondaryTagsToUpdate = new ArrayList<>();
				List<SecondaryTags> secondaryTagsToCreate = new ArrayList<>();

				if (stagsList != null && stagsList.size() > 0) {
					for (PackageTags ptag : stagsList) {
						Optional<SecondaryTags> existingTag = secondaryTagsRepository
								.findByHeaderIdAndTagId(existingMedInfo.getId(), ptag.getId());
						if (existingTag.isPresent()) {
							SecondaryTags tagInfo = existingTag.get();
							tagInfo.setTagName(ptag.getName());
							tagInfo.setRecordStatus(Status.Active);
							// ... Update other fields if needed
							secondaryTagsToUpdate.add(tagInfo);
						} else {
							SecondaryTags newTagInfo = new SecondaryTags();
							newTagInfo.setHeaderId(existingMedInfo.getId());
							newTagInfo.setTagId(ptag.getId());
							newTagInfo.setTagName(ptag.getName());
							newTagInfo.setRecordStatus(Status.Active);
							newTagInfo.setCreatedBy(request.getCreatedBy());
							secondaryTagsToCreate.add(newTagInfo);
						}
					}

					secondaryTagsRepository.saveAll(secondaryTagsToUpdate);
					secondaryTagsRepository.saveAll(secondaryTagsToCreate);
				}
			}
			/**************** END secondary tag insertion *****************/
			/**************** START from cites insertion *****************/
			if (request.getFromCities() != null && request.getFromCities().isEmpty() == false) {
//				List<FromCities> fromCitiesList = new ArrayList<FromCities>();
				List<String> fromCities = Arrays.asList(request.getFromCities().split(","));
				List<CitiesMaster> fCitiesList = cityMasterRepository.getCtyListByCodes(fromCities);

				List<FromCities> fromCitiesToUpdate = new ArrayList<>();
				List<FromCities> fromCitiesToCreate = new ArrayList<>();

				if (fCitiesList != null && fCitiesList.size() > 0) {
					for (CitiesMaster fcity : fCitiesList) {
						Optional<FromCities> existingTag = fromCitiesRepository
								.findByHeaderIdAndCityCode(existingMedInfo.getId(), fcity.getCode());
						if (existingTag.isPresent()) {
							FromCities cityInfo = existingTag.get();
							cityInfo.setCityName(fcity.getName());
							cityInfo.setCityCode(fcity.getCode());
							cityInfo.setRecordStatus(Status.Active);
							fromCitiesToUpdate.add(cityInfo);
						} else {
							FromCities newCityInfo = new FromCities();
							newCityInfo.setHeaderId(existingMedInfo.getId());
							newCityInfo.setCityId(fcity.getId());
							newCityInfo.setCityName(fcity.getName());
							newCityInfo.setCityCode(fcity.getCode());
							newCityInfo.setRecordStatus(Status.Active);
							newCityInfo.setCreatedBy(request.getCreatedBy());
							fromCitiesToCreate.add(newCityInfo);
						}
					}

					fromCitiesRepository.saveAll(fromCitiesToUpdate);
					fromCitiesRepository.saveAll(fromCitiesToCreate);
				}
			}
			/**************** END from cites insertion *****************/
			/**************** START to cites insertion *****************/
			if (request.getToCities() != null && request.getToCities().isEmpty() == false) {
//				List<ToCities> toCitiesList = new ArrayList<ToCities>();
				List<String> toCities = Arrays.asList(request.getToCities().split(","));
				List<CitiesMaster> tCitiesList = cityMasterRepository.getCtyListByCodes(toCities);

				List<ToCities> toCitiesToUpdate = new ArrayList<>();
				List<ToCities> toCitiesToCreate = new ArrayList<>();

				if (tCitiesList != null && tCitiesList.size() > 0) {
					for (CitiesMaster tcity : tCitiesList) {
						Optional<ToCities> existingTag = toCitiesRepository
								.findByHeaderIdAndCityCode(existingMedInfo.getId(), tcity.getCode());
						if (existingTag.isPresent()) {
							ToCities cityInfo = existingTag.get();
							cityInfo.setCityName(tcity.getName());
							cityInfo.setCityCode(tcity.getCode());
							cityInfo.setRecordStatus(Status.Active);
							toCitiesToUpdate.add(cityInfo);
						} else {
							ToCities newCityInfo = new ToCities();
							newCityInfo.setHeaderId(existingMedInfo.getId());
							newCityInfo.setCityId(tcity.getId());
							newCityInfo.setCityName(tcity.getName());
							newCityInfo.setCityCode(tcity.getCode());
							newCityInfo.setRecordStatus(Status.Active);
							newCityInfo.setCreatedBy(request.getCreatedBy());
							toCitiesToCreate.add(newCityInfo);
						}
					}

					toCitiesRepository.saveAll(toCitiesToUpdate);
					toCitiesRepository.saveAll(toCitiesToCreate);
				}
			}
			/**************** END to cites insertion *****************/
		}
		return existingMedInfo;

	}

	@Override
	public MedInfoResponse medInfo(Long id) throws Exception {
		MedInfo medInfo = medInfoRepository.findByIdList(id).orElseThrow(() -> new Exception("MedInfo not found"));
		
		Integer productId = 0;
		String productName = "";
		if(medInfo != null && medInfo.getSrId() != null && medInfo.getSrId() > 0) {
			Map<String, Object> srInfo = medInfoRepository.getSrInfoById(medInfo.getSrId());
			if(srInfo != null && srInfo.isEmpty() == false) {
				productId = Integer.parseInt(srInfo.get("productId").toString());
				productName = srInfo.get("productName").toString();
			}
		}
		MedInfoResponse result = new MedInfoResponse();
		List<Map<String, String>> primaryTagsList = primaryTagsRepository.findByHeaderId(medInfo.getId());
		result.setPrimaryTags(primaryTagsList);
		List<Map<String, String>> secondaryTagsList = secondaryTagsRepository.findByHeaderId(medInfo.getId());
		result.setSecondaryTags(secondaryTagsList);
		List<Map<String, String>> fromCitiesList = fromCitiesRepository.findByHeaderId(medInfo.getId());
		result.setFromCities(fromCitiesList);
		List<Map<String, String>> toCitiesList = toCitiesRepository.findByHeaderId(medInfo.getId());
		result.setToCities(toCitiesList);
		result.setMedName(medInfo.getMedName());
		result.setMedCode(medInfo.getMedCode());
		result.setMedDescription(medInfo.getMedDescription());
		result.setMedDays(medInfo.getMedDays());
		result.setMedCategory(medInfo.getMedCategory());
		result.setMedType(medInfo.getMedType());
		result.setRecordStatus(medInfo.getRecordStatus());
		result.setValidFrom(medInfo.getValidFrom());
		result.setValidTo(medInfo.getValidTo());
		result.setCreatedBy(medInfo.getCreatedBy());
		result.setUpdatedBy(medInfo.getUpdatedBy());
		result.setCreatedDate(medInfo.getCreatedDate());
		result.setUpdatedDate(medInfo.getUpdatedDate());
		result.setId(medInfo.getId());
		result.setSrId(medInfo.getSrId());
		result.setProductId(productId);
		result.setProductName(productName);
		
		return result;
	}

	@Override
	public List<PackageConfigurationImages> packageImages(List<PackageImageConfigDto> request) {
		return request.stream().map(req -> {

			Optional<PackageConfigurationImages> isExists = packageConfigurationImagesRepository.findById(req.getId());
			if (isExists.isPresent()) {

				BeanUtils.copyProperties(req, isExists.get(), "createdDate", "createdBy", "updatedDate");
				isExists.get()
						.setUpdatedDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				return packageConfigurationImagesRepository.save(isExists.get());

			} else {
				PackageConfigurationImages entity = new PackageConfigurationImages();
				BeanUtils.copyProperties(req, entity, "createdDate", "updatedBy", "updatedDate");

				entity.setCreatedDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				return packageConfigurationImagesRepository.save(entity);
			}

		}).collect(Collectors.toList());

	}

	@Override
	public List<PackageImageConfigDto> packageImages(Long id) {
		return packageConfigurationImagesRepository.findByPackageId(id).stream().map(entity -> {
			PackageImageConfigDto dto = new PackageImageConfigDto();
			BeanUtils.copyProperties(entity, dto);
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	//@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<MedInfoResponse> packageData(MedInfoSearchParametersDTO searchParameters) {
		List<PackageConfigSearchEntity> data = packageConfigSearchEntityRepository
				.findAll((root, query, criteriaBuilder) -> {
					final Collection<Predicate> predicates = new ArrayList<>();

					if (searchParameters.getPrimaryTags() != null && !searchParameters.getPrimaryTags().isBlank()) {
						Join<PrimaryTags, PackageConfigSearchEntity> primaryTagsJoin = root.join("primaryTags",
								JoinType.LEFT);
						Set<Long> primaryTags = Arrays.asList(searchParameters.getPrimaryTags().split(",")).stream()
								.map(Long::valueOf).collect(Collectors.toSet());
						Predicate primaryTagsPredicate = primaryTagsJoin.get("tagId").in(primaryTags);
						predicates.add(primaryTagsPredicate);
					}

					if (searchParameters.getSecondaryTags() != null && !searchParameters.getSecondaryTags().isBlank()) {
						Join<SecondaryTags, PackageConfigSearchEntity> secoondaryTagsJoin = root.join("secondaryTags",
								JoinType.LEFT);
						Set<Long> secondaryTags = Arrays.asList(searchParameters.getSecondaryTags().split(",")).stream()
								.map(Long::valueOf).collect(Collectors.toSet());
						Predicate secondaryTagsPredicate = secoondaryTagsJoin.get("tagId").in(secondaryTags);
						predicates.add(secondaryTagsPredicate);
					}
					if (searchParameters.getFromCityCodes() != null && !searchParameters.getFromCityCodes().isBlank()) {
						Join<FromCityImmutableEntity, PackageConfigSearchEntity> fromCitiesJoin = root
								.join("fromCities", JoinType.LEFT);
						Set<String> fromCityCodes = Arrays.asList(searchParameters.getFromCityCodes().split(","))
								.stream().collect(Collectors.toSet());
						Predicate fromCityPredicate = fromCitiesJoin.get("cityCode").in(fromCityCodes);

						predicates.add(fromCityPredicate);
					}

					if (searchParameters.getToCityCodes() != null && !searchParameters.getToCityCodes().isBlank()) {
						Join<ToCityImmutableEntity, PackageConfigSearchEntity> toCitiesJoin = root.join("toCities",
								JoinType.LEFT);

						Set<String> toCityCodes = Arrays.asList(searchParameters.getToCityCodes().split(",")).stream()
								.collect(Collectors.toSet());
						Predicate toCityPredicate = toCitiesJoin.get("cityCode").in(toCityCodes);
						predicates.add(toCityPredicate);

					}

					if (searchParameters.getMedName() != null && !searchParameters.getMedName().isBlank()) {
						predicates.add(
								criteriaBuilder.like(root.get("medName"), "%" + searchParameters.getMedName() + "%"));
					}
					if (searchParameters.getMedCategory() != null && searchParameters.getMedCategory() != 0) {
						predicates
								.add(criteriaBuilder.equal(root.get("medCategory"), searchParameters.getMedCategory()));
					}
					if (searchParameters.getMedType() != null && searchParameters.getMedType() != 0) {
						predicates.add(criteriaBuilder.equal(root.get("medType"), searchParameters.getMedType()));
					}

					if (searchParameters.getCreatedBy() != null && searchParameters.getCreatedBy() != 0) {
						predicates.add(criteriaBuilder.equal(root.get("createdBy"), searchParameters.getCreatedBy()));
					}
					if (searchParameters.getUpdatedBy() != null && searchParameters.getUpdatedBy() != 0) {
						predicates.add(criteriaBuilder.equal(root.get("updatedBy"), searchParameters.getUpdatedBy()));
					}
					if (searchParameters.getRecordStatus() != null && !searchParameters.getRecordStatus().isBlank()) {
						predicates.add(criteriaBuilder.equal(root.get("recordStatus"), searchParameters.getMedType()));
					}

					if (searchParameters.getValidFrom() != null) {
						Expression<Date> sqlDateFunction = criteriaBuilder.function("date", Date.class,
								root.get("validFrom"));

						Predicate dateEqualPredicate = criteriaBuilder.greaterThanOrEqualTo(sqlDateFunction,
								searchParameters.getValidFrom());
						predicates.add(dateEqualPredicate);
					}

					if (searchParameters.getValidTo() != null) {
						Expression<Date> sqlDateFunction = criteriaBuilder.function("date", Date.class,
								root.get("validTo"));

						Predicate dateEqualPredicate = criteriaBuilder.lessThanOrEqualTo(sqlDateFunction,
								searchParameters.getValidTo());
						predicates.add(dateEqualPredicate);
					}

					query.distinct(true); // Select distinct records
					return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
				});

		return data.stream().map(eachData -> {
			MedInfoResponse response = new MedInfoResponse();

			BeanUtils.copyProperties(eachData, response, "primaryTags", "secondaryTags", "fromCities", "toCities");

			List<Map<String, String>> primaryTags = eachData.getPrimaryTags().stream().map(eachTag -> {

				Map<String, String> tagMap = Map.of("tagId", eachTag.getTagId().toString(), "tagName",
						eachTag.getTagName());

				return tagMap;

			}).collect(Collectors.toList());

			response.setPrimaryTags(primaryTags);

			List<Map<String, String>> secondaryTags = eachData.getSecondaryTags().stream().map(eachTag -> {

				Map<String, String> tagMap = Map.of("tagId", eachTag.getTagId().toString(), "tagName",
						eachTag.getTagName());

				return tagMap;

			}).collect(Collectors.toList());

			response.setSecondaryTags(secondaryTags);

			List<Map<String, String>> fromCity = eachData.getFromCities().stream().map(eachFromCity -> {

				Map<String, String> cityMap = new HashedMap<>();

				cityMap.put("cityId", String.valueOf(eachFromCity.getCityId()));
				cityMap.put("cityCode", eachFromCity.getCityCode());
				cityMap.put("cityName", eachFromCity.getCityName());
				return cityMap;

			}).collect(Collectors.toList());

			response.setFromCities(fromCity);

			List<Map<String, String>> toCity = eachData.getToCities().stream().map(eachToCity -> {

				Map<String, String> cityMap = new HashedMap<>();

				cityMap.put("cityId", String.valueOf(eachToCity.getCityId()));
				cityMap.put("cityCode", eachToCity.getCityCode());
				cityMap.put("cityName", eachToCity.getCityName());
				return cityMap;

			}).collect(Collectors.toList());

			response.setToCities(toCity);

			Map<String, Object> srInfo = packageConfigurationImagesRepository.getSrInfoById(eachData.getSrId());
			if (srInfo != null && srInfo.isEmpty() == false) {
				if (srInfo.get("productId") != null) {
					response.setProductId(Integer.parseInt(srInfo.get("productId").toString()));
				}
				if (srInfo.get("productName") != null) {
					response.setProductName(srInfo.get("productName").toString());
				}
			}

			return response;
		}).collect(Collectors.toList());

	}

	@Override
	public PackageTags packageTags(PackageTagsRequest request) {
		return packageTagsRepository.save(PackageTagsConverter.convertRequestToEntity(request));

	}

	@Override
	public PackageTags packageTags(Long id, PackageTagsRequest request) {
		PackageTags existingPackageTags = packageTagsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("PackageTags not found for id: " + id));

		PackageTagsConverter.updateEntityFromRequest(existingPackageTags, request);
		return packageTagsRepository.save(existingPackageTags);
	}

	@Override
	public PackageTags packageTags(Long id) {
		PackageTags packageTags = packageTagsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("PackageTags not found for id: " + id));
		return packageTags;
	}

	@Override
	public List<PackageTags> packageTags() {

		return packageTagsRepository.findAll();
	}

	@Override
	public List<PackageTags> packageTags(List<PackageTagsRequest> request) {
		return packageTagsRepository.saveAll(PackageTagsConverter.convertRequestToMultipuleEntity(request));
	}

	@Override
	public List<Map<String, String>> activitySearchData(String activityName, String location, String city,
			String country) {

		return packageTagsRepository.getSearchData(activityName, location, city, country);
	}

}

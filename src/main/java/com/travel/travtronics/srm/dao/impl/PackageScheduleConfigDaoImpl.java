package com.travel.travtronics.srm.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travel.travtronics.converter.PackageScheduleConverter;
import com.travel.travtronics.converter.PackageScheduleDatesConverter;
import com.travel.travtronics.request.PackageScheduleRequest;
import com.travel.travtronics.response.dto.PackageCityDto;
import com.travel.travtronics.response.dto.PackageScheduleDatesDto;
import com.travel.travtronics.response.dto.PackageScheduleDto;
import com.travel.travtronics.srm.dao.PackageScheduleConfigDao;
import com.travel.travtronics.srm.model.TravelPackageSchedule;
import com.travel.travtronics.srm.model.TravelPackageScheduleDates;
import com.travel.travtronics.srm.repository.PackageScheduleDatesRepository;
import com.travel.travtronics.srm.repository.TravelPackageScheduleRepository;
import com.travel.travtronics.util.Status;

@Repository("packageScheduleConfigDao")
public class PackageScheduleConfigDaoImpl implements PackageScheduleConfigDao {

	@Autowired
	TravelPackageScheduleRepository travelPackageScheduleRepository;

	@Autowired
	PackageScheduleDatesRepository travelPackageScheduleDatesRepository;

	@Override
	public PackageScheduleDto createPackageSchedule(PackageScheduleRequest request) {
		TravelPackageSchedule packageScheduleConverter = PackageScheduleConverter
				.convertPackageScheduleRequestToPackageScheduleModel(request);

		TravelPackageSchedule packageSchedule = travelPackageScheduleRepository.save(packageScheduleConverter);
		if (packageSchedule.getId() != null) {
			travelPackageScheduleDatesRepository.saveAll(PackageScheduleDatesConverter
					.convertPackageScheduleDatesRequest(request.getScheduleDates(), packageSchedule.getId()));
		}
		PackageScheduleDto packageScheduleDto = convertPackageSchedule(packageSchedule);

		return packageScheduleDto;
	}

	private PackageScheduleDto convertPackageSchedule(TravelPackageSchedule request) {
		PackageScheduleDto packageScheduleDto = new PackageScheduleDto();
		Optional<PackageCityDto> packageStartCity = travelPackageScheduleRepository
				.findCityByCode(request.getStartCity()).stream().findFirst().map(this::mapToPackageCityDto);
		Optional<PackageCityDto> packageEndCity = travelPackageScheduleRepository.findCityByCode(request.getEndCity())
				.stream().findFirst().map(this::mapToPackageCityDto);
		packageStartCity.ifPresent(city -> {
			
			packageScheduleDto.setStartCity(packageStartCity.get());
		});
		packageEndCity.ifPresent(city -> {
			
			packageScheduleDto.setEndCity(packageEndCity.get());
		});
		List<TravelPackageScheduleDates> travelPackageScheduleDates = travelPackageScheduleDatesRepository
				.findByScheduleId(request.getId());
		if (!travelPackageScheduleDates.isEmpty()) {
			packageScheduleDto.setScheduleDates(
					travelPackageScheduleDates.stream().filter(model -> model.getRecordStatus() == Status.Active)
							.map(this::convertPackageScheduleDates).collect(Collectors.toList()));
		}

		packageScheduleDto.setId(request.getId());
		packageScheduleDto.setPackageConfigurationId(request.getPackageConfigurationId());
		packageScheduleDto.setScheduleName(request.getScheduleName());
		packageScheduleDto.setStartDate(request.getStartDate());
		packageScheduleDto.setCountMin(request.getCountMin());
		packageScheduleDto.setCountMax(request.getCountMax());
		packageScheduleDto.setDescription(request.getDescription());
		packageScheduleDto.setStatus(request.getStatus());
		packageScheduleDto.setTeam(request.getTeam());
		packageScheduleDto.setOwner(request.getOwner());
		packageScheduleDto.setTimeZone(request.getTimeZone());
		packageScheduleDto.setMarketingStartDate(request.getMarketingStartDate());
		packageScheduleDto.setBookingStartDate(request.getBookingStartDate());
		packageScheduleDto.setBookingEndDate(request.getBookingEndDate());
		packageScheduleDto.setMarketingStartDate(request.getMarketingStartDate());
		packageScheduleDto.setMarketingEndDate(request.getMarketingEndDate());
		packageScheduleDto.setMarketingBudgetMin(request.getMarketingBudgetMin());
		packageScheduleDto.setMarketingBudgetMax(request.getMarketingBudgetMax());
		packageScheduleDto.setSalesStartDate(request.getSalesStartDate());
		packageScheduleDto.setCampaignCode(request.getCampaignCode());
		packageScheduleDto.setCouponTemplate(request.getCouponTemplate());
		packageScheduleDto.setMetaSlug(request.getMetaSlug());
		packageScheduleDto.setMetaTitle(request.getMetaTitle());
		packageScheduleDto.setMetaKeywords(request.getMetaKeywords());
		packageScheduleDto.setMetaDescription(request.getMetaDescription());
		packageScheduleDto.setRecordStatus(request.getRecordStatus());
		packageScheduleDto.setCreatedBy(request.getCreatedBy());
		packageScheduleDto.setCreatedDate(request.getCreatedDate());
		packageScheduleDto.setUpdatedBy(request.getUpdatedBy());
		packageScheduleDto.setUpdatedDate(request.getUpdatedDate());
		packageScheduleDto.setPackageScheduleTextEditor(request.getPackageScheduleTextEditor());
		packageScheduleDto.setSrTypeLink(request.getSrTypeLink());
		packageScheduleDto.setAdvanceAmountMax(request.getAdvanceAmountMax());
		packageScheduleDto.setAdvanceAmountMin(request.getAdvanceAmountMin());
		packageScheduleDto.setCommissionAmount(request.getCommissionAmount());
		packageScheduleDto.setCommissionPercentage(request.getCommissionPercentage());
		packageScheduleDto.setPriceWithFlights(request.getPriceWithFlights());
		packageScheduleDto.setPriceWithOutFlights(request.getPriceWithOutFlights());
		return packageScheduleDto;
	}

	private PackageCityDto mapToPackageCityDto(Object[] result) {
		// BigInteger idBigInteger = (BigInteger) result[0];
		Long idLong = (Long) result[0];

		PackageCityDto cityDto = new PackageCityDto();
		cityDto.setId(idLong);
		cityDto.setCode((String) result[1]);
		cityDto.setName((String) result[2]);
		return cityDto;
	}

	private PackageScheduleDatesDto convertPackageScheduleDates(TravelPackageScheduleDates request) {
		PackageScheduleDatesDto packageScheduleDates = new PackageScheduleDatesDto();
		packageScheduleDates.setId(request.getId());
		packageScheduleDates.setStartDate(request.getStartDate());
		packageScheduleDates.setEndDate(request.getEndDate());
		packageScheduleDates.setCreatedBy(request.getCreatedBy());
		packageScheduleDates.setUpdatedBy(request.getUpdatedBy());
		packageScheduleDates.setRecordStatus(request.getRecordStatus());
		// packageScheduleDates.setHtmlEditor(request.getHtmlEditor());
		return packageScheduleDates;
	}

	@Override
	public PackageScheduleDto updatePackageSchedule(PackageScheduleRequest request) throws Exception {
		TravelPackageSchedule findPackageSchedule = travelPackageScheduleRepository.findById(request.getId())
				.orElseThrow(() -> new Exception("Given package schedule not found"));

		TravelPackageSchedule packageScheduleConverter = PackageScheduleConverter
				.convertPackageScheduleRequestToPackageScheduleModel(request);
		TravelPackageSchedule packageSchedule = travelPackageScheduleRepository.save(packageScheduleConverter);
		if (packageSchedule.getId() != null) {
			travelPackageScheduleDatesRepository.saveAll(PackageScheduleDatesConverter
					.convertPackageScheduleDatesRequest(request.getScheduleDates(), packageSchedule.getId()));
		}
		PackageScheduleDto packageScheduleDto = convertPackageSchedule(packageSchedule);

		return packageScheduleDto;
	}

	@Override
	public PackageScheduleDto packageSchedule(Long id) throws Exception {
		TravelPackageSchedule findPackageSchedule = travelPackageScheduleRepository.findById(id)
				.orElseThrow(() -> new Exception("Given package schedule not found"));
		PackageScheduleDto packageScheduleDto = convertPackageSchedule(findPackageSchedule);
		return packageScheduleDto;
	}

	@Override
	public List<PackageScheduleDto> allPackageSchedule(Long id) throws Exception {
		List<TravelPackageSchedule> findPackageSchedules = travelPackageScheduleRepository
				.findByPackageConfigurationId(id);

		if (findPackageSchedules.isEmpty()) {
			throw new Exception("No package schedules found");
		}
		return findPackageSchedules.stream().map(this::convertPackageSchedule).collect(Collectors.toList());

	}

}

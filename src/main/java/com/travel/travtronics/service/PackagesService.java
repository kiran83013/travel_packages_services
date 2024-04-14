package com.travel.travtronics.service;

import java.io.Serializable;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.travel.travtronics.converter.PackagesConverter;
import com.travel.travtronics.packages.model.PackageItineraryHeaderModel;
import com.travel.travtronics.packages.model.PackageItineraryModel;
import com.travel.travtronics.packages.model.PackageItineraryPriceHeaderModel;
import com.travel.travtronics.packages.model.PackageItineraryPriceLinesModel;
import com.travel.travtronics.packages.repository.PackageItineraryHeaderRepository;
import com.travel.travtronics.packages.repository.PackageItineraryPriceHeaderRepository;
import com.travel.travtronics.packages.repository.PackageItineraryPriceLinesRepository;
import com.travel.travtronics.packages.repository.PackageItineraryRepository;
import com.travel.travtronics.request.ItineraryPriceLinesRequest;
import com.travel.travtronics.request.PackagePriceRequest;
import com.travel.travtronics.request.UpdateItineraryPriceLinesRequest;
import com.travel.travtronics.request.UpdatePackagePriceRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.PackagesPriceInfoResponse;
import com.travel.travtronics.srm.model.PkgHotelPriceConfigModel;
import com.travel.travtronics.srm.repository.PkgHotelPriceConfigRepository;

import jakarta.validation.Valid;

@Service
public class PackagesService {

	@Autowired
	PackageItineraryHeaderRepository packageItineraryHeaderRepository;

	@Autowired
	PackageItineraryRepository packageItineraryRepository;

	@Autowired
	PackageItineraryPriceHeaderRepository packageItineraryPriceHeaderRepository;

	@Autowired
	PackageItineraryPriceLinesRepository packageItineraryPriceLinesRepository;

	public APIResponse getItineraryList(String itinaryName) {

		if (itinaryName.isBlank() || itinaryName.isEmpty()) {
			itinaryName = null;
		}

		List<Map<String, Object>> itinaryList = packageItineraryHeaderRepository
				.getItinaryListDataAndSearch(itinaryName);

		if (itinaryList != null && itinaryList.size() > 0) {
			return new APIResponse(HttpStatus.OK.value(), "Package itinerary list", itinaryList);
		} else {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), "No data found in the system",
					Collections.emptyList());
		}
	}

	public APIResponse getItineraryDetailsById(Integer id) {

		if (id != null && id > 0) {

			Optional<PackageItineraryHeaderModel> itineraryInfo = packageItineraryHeaderRepository
					.getItineraryInfoById(id);
			if (itineraryInfo.isPresent()) {

				List<Map<String, Object>> itinaryLinesList = packageItineraryHeaderRepository
						.getItinaryLinesListDataById(id);

				if (itinaryLinesList != null && itinaryLinesList.size() > 0) {
					return new APIResponse(HttpStatus.OK.value(), "Package itinerary lines info", itinaryLinesList);
				} else {
					return new APIResponse(HttpStatus.NOT_FOUND.value(), "No data found in the system",
							Collections.emptyList());
				}

			} else {
				return new APIResponse(HttpStatus.NOT_FOUND.value(), "No data found in the system",
						Collections.emptyList());
			}

		} else {
			return new APIResponse(HttpStatus.BAD_REQUEST.value(), "Invalid data, id should not be empty or zero",
					Collections.emptyList());
		}

	}

	public APIResponse getPackagesItemsList(String itemName) {

		if (itemName.isBlank() || itemName.isEmpty()) {
			itemName = null;
		}

		List<Map<String, Object>> ItemsList = packageItineraryHeaderRepository.getItemsListDataAndSearch(itemName);

		if (ItemsList != null && ItemsList.size() > 0) {
			return new APIResponse(HttpStatus.OK.value(), "Package Items list", ItemsList);
		} else {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), "No data found in the system",
					Collections.emptyList());
		}
	}

	public APIResponse savePackagePricingData(PackagePriceRequest requestData) {

		if (requestData.getItineraryId() == null || requestData.getItineraryId() < 0) {
			return new APIResponse(HttpStatus.BAD_REQUEST.value(), "Invalid ItineraryId", Collections.emptyList());
		}

		if (isLegalDate(requestData.getValidFromDate()) == false) {
			return new APIResponse(HttpStatus.BAD_REQUEST.value(), "Invalid validFromDate", Collections.emptyList());
		}

		if (isLegalDate(requestData.getValidToDate()) == false) {
			return new APIResponse(HttpStatus.BAD_REQUEST.value(), "Invalid validToDate", Collections.emptyList());
		}

		if (requestData.getCreatedBy() == null || requestData.getCreatedBy() < 0) {
			return new APIResponse(HttpStatus.BAD_REQUEST.value(), "Invalid createdBy", Collections.emptyList());
		}

		if (requestData.getItineraryPriceLines() == null || requestData.getItineraryPriceLines().size() == 0) {
			return new APIResponse(HttpStatus.BAD_REQUEST.value(), "Invalid itineraryPriceLines data",
					Collections.emptyList());
		}

		PackageItineraryPriceHeaderModel headerData = packageItineraryPriceHeaderRepository.save(PackagesConverter
				.convertPriceHeaderRequestDataToModel(requestData.getItineraryId(), requestData.getValidFromDate(),
						requestData.getValidToDate(), requestData.getCreatedBy(), requestData.getIpAddress(),
						requestData.getName(), requestData.getDescription(), requestData.getBuId(),
						requestData.getCcId(), requestData.getLocationId(), requestData.getCustomerType()));

		PackagesPriceInfoResponse responseInfo = new PackagesPriceInfoResponse();

		if (headerData != null && headerData.getId() != null) {

			List<PackageItineraryPriceLinesModel> itineraryPriceLines = new ArrayList<PackageItineraryPriceLinesModel>();

			if (requestData.getItineraryPriceLines().size() > 0) {
				Integer headerId = headerData.getId();
				for (ItineraryPriceLinesRequest lineData : requestData.getItineraryPriceLines()) {

					Optional<PackageItineraryModel> itineraryLineInfo = packageItineraryRepository
							.findById(lineData.getItineraryLineId());

					PackageItineraryPriceLinesModel lineInfo = packageItineraryPriceLinesRepository
							.save(PackagesConverter.convertPriceLinesRequestDataToModel(lineData, headerId,
									itineraryLineInfo.get()));
					if (lineInfo != null && lineInfo.getLineId() > 0) {
						itineraryPriceLines.add(lineInfo);
					}
				}

				Optional<PackageItineraryHeaderModel> itineraryInfo = packageItineraryHeaderRepository
						.findById(headerData.getItineraryId());

				responseInfo = PackagesConverter.convertPriceLinesModelDataToResponse(headerData, itineraryPriceLines,
						itineraryInfo.get());
			}

			return new APIResponse(HttpStatus.OK.value(), "Successfully data saved in the system",
					Collections.singletonList(responseInfo));

		} else {
			return new APIResponse(HttpStatus.FAILED_DEPENDENCY.value(), "Failed, Data not saved in the system.",
					Collections.emptyList());
		}

	}

	public APIResponse updatePackagePricingData(@Valid UpdatePackagePriceRequest requestData, Integer headerId) {

		if (headerId == null || headerId < 0) {
			return new APIResponse(HttpStatus.BAD_REQUEST.value(), "Invalid headerId", Collections.emptyList());
		}

		if (requestData.getItineraryId() == null || requestData.getItineraryId() < 0) {
			return new APIResponse(HttpStatus.BAD_REQUEST.value(), "Invalid ItineraryId", Collections.emptyList());
		}

		if (isLegalDate(requestData.getValidFromDate()) == false) {
			return new APIResponse(HttpStatus.BAD_REQUEST.value(), "Invalid validFromDate", Collections.emptyList());
		}

		if (isLegalDate(requestData.getValidToDate()) == false) {
			return new APIResponse(HttpStatus.BAD_REQUEST.value(), "Invalid validToDate", Collections.emptyList());
		}

		if (requestData.getUpdatedBy() == null || requestData.getUpdatedBy() < 0) {
			return new APIResponse(HttpStatus.BAD_REQUEST.value(), "Invalid updatedBy", Collections.emptyList());
		}

		if (requestData.getItineraryPriceLines() == null || requestData.getItineraryPriceLines().size() == 0) {
			return new APIResponse(HttpStatus.BAD_REQUEST.value(), "Invalid itineraryPriceLines data",
					Collections.emptyList());
		}

		Optional<PackageItineraryPriceHeaderModel> headerInfo = packageItineraryPriceHeaderRepository
				.findById(headerId);
		if (headerInfo.isPresent() == false) {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), "Not found any data with provide id.",
					Collections.emptyList());
		}

		PackageItineraryPriceHeaderModel headerData = packageItineraryPriceHeaderRepository
				.save(PackagesConverter.convertUpdatePriceHeaderRequestDataToModel(headerInfo.get(),
						requestData.getItineraryId(), requestData.getValidFromDate(), requestData.getValidToDate(),
						requestData.getUpdatedBy(), requestData.getIpAddress(), requestData.getName(),
						requestData.getDescription(), requestData.getBuId(), requestData.getCcId(),
						requestData.getLocationId(), requestData.getCustomerType()));

		PackagesPriceInfoResponse responseInfo = new PackagesPriceInfoResponse();

		if (headerData != null && headerData.getId() != null) {

			List<PackageItineraryPriceLinesModel> itineraryPriceLines = new ArrayList<PackageItineraryPriceLinesModel>();

			if (requestData.getItineraryPriceLines().size() > 0) {

				for (UpdateItineraryPriceLinesRequest lineData : requestData.getItineraryPriceLines()) {

					PackageItineraryPriceLinesModel updateData = new PackageItineraryPriceLinesModel();
					if (lineData.getLineId() != null && lineData.getLineId() > 0) {
						Optional<PackageItineraryPriceLinesModel> thisLine = packageItineraryPriceLinesRepository
								.findById(lineData.getLineId());
						if (thisLine.isPresent()) {
							updateData = thisLine.get();
						}
					}

					Optional<PackageItineraryModel> itineraryLineInfo = packageItineraryRepository
							.findById(lineData.getItineraryLineId());

					PackageItineraryPriceLinesModel lineInfo = packageItineraryPriceLinesRepository
							.save(PackagesConverter.convertUpdatePriceLinesRequestDataToModel(updateData, lineData,
									headerId, itineraryLineInfo.get()));
					if (lineInfo != null && lineInfo.getLineId() > 0) {
						itineraryPriceLines.add(lineInfo);
					}
				}

				Optional<PackageItineraryHeaderModel> itineraryInfo = packageItineraryHeaderRepository
						.findById(headerData.getItineraryId());

				responseInfo = PackagesConverter.convertPriceLinesModelDataToResponse(headerData, itineraryPriceLines,
						itineraryInfo.get());
			}

			return new APIResponse(HttpStatus.OK.value(), "Successfully data updated in the system",
					Collections.singletonList(responseInfo));

		} else {
			return new APIResponse(HttpStatus.FAILED_DEPENDENCY.value(), "Failed, Data not updated in the system.",
					Collections.emptyList());
		}
	}

	public APIResponse getPackagePricingList() {

		List<PackagesPriceInfoResponse> pricingListData = new ArrayList<PackagesPriceInfoResponse>();

		List<PackageItineraryPriceHeaderModel> headerDataList = packageItineraryPriceHeaderRepository.findAll();

		if (headerDataList.size() > 0) {
			for (PackageItineraryPriceHeaderModel headerData : headerDataList) {

				Optional<PackageItineraryHeaderModel> itineraryInfo = packageItineraryHeaderRepository
						.findById(headerData.getItineraryId());

				List<PackageItineraryPriceLinesModel> lineList = packageItineraryPriceLinesRepository
						.getPriceLinesListByHeaderId(headerData.getId());
				if (lineList.size() > 0) {
					PackagesPriceInfoResponse responseInfo = PackagesConverter
							.convertPriceLinesModelDataToResponse(headerData, lineList, itineraryInfo.get());
					pricingListData.add(responseInfo);
				}
			}
			return new APIResponse(HttpStatus.OK.value(), "Pricing data", pricingListData);
		} else {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), "No data found in the system",
					Collections.emptyList());
		}
	}

	public APIResponse getPackagePriceInfo(Integer headerId) {

		Optional<PackageItineraryPriceHeaderModel> headerData = packageItineraryPriceHeaderRepository
				.findById(headerId);

		if (headerData != null && headerData.isPresent()) {

			PackagesPriceInfoResponse responseInfo = new PackagesPriceInfoResponse();

			List<PackageItineraryPriceLinesModel> lineList = packageItineraryPriceLinesRepository
					.getPriceLinesListByHeaderId(headerData.get().getId());
			if (lineList.size() > 0) {
				Optional<PackageItineraryHeaderModel> itineraryInfo = packageItineraryHeaderRepository
						.findById(headerData.get().getItineraryId());

				responseInfo = PackagesConverter.convertPriceLinesModelDataToResponse(headerData.get(), lineList,
						itineraryInfo.get());
			}

			return new APIResponse(HttpStatus.OK.value(), "Pricing data", Collections.singletonList(responseInfo));
		} else {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), "No data found in the system",
					Collections.emptyList());
		}

	}

	/**
	 * Date validation check
	 * 
	 * @param dateString
	 * @return
	 */
	public boolean isLegalDate(String dateString) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // simple date format

		sdf.setLenient(false);

		return sdf.parse(dateString, new ParsePosition(0)) != null;
	}

	/*************************** For testing perpuse only ***************************/
	
	@Autowired
	private PkgHotelPriceConfigRepository PkgHotelPriceConfigRepository;
	
	public APIResponse getHotelPriceCombinations(int adtCount, int chdCount) {
		
	    List<PkgHotelPriceConfigModel> roomTypes = PkgHotelPriceConfigRepository.findAll();

	    // Map to store combinations grouped by total price
	    Map<Integer, List<String>> combinationsMap = new HashMap<>();

	    // Calculate total cost for each room type
	    for (PkgHotelPriceConfigModel roomType : roomTypes) {
	        int numAdultRooms = 1;
	        if (roomType.getMaximumAdultsAllowed() > 0) {
	            numAdultRooms = adtCount / roomType.getMaximumAdultsAllowed();
	        }
	        int numChildRooms = 1;
	        if (roomType.getMaximumChildrenAllowed() > 0) {
	            numChildRooms = chdCount / roomType.getMaximumChildrenAllowed();
	        }
	        int minRooms = Math.min(numAdultRooms, numChildRooms);

	        for (int i = 1; i <= minRooms; i++) {
	            int totalAdults = i * roomType.getMaximumAdultsAllowed();
	            int totalChildren = i * roomType.getMaximumChildrenAllowed();
	            if (totalAdults <= adtCount && totalChildren <= chdCount) {
	                int totalCost = roomType.getPricePerNight();

	                // Construct room combination string
	                String combination = roomType.getSupplier() + "-" + roomType.getHotel() + "-" +
	                        roomType.getRoomName() + "-" + roomType.getRoomType() 
	                        +" Adt: "+roomType.getMaximumAdultsAllowed()
	                        +" Chd: "+roomType.getMaximumChildrenAllowed()
	                        + " -> " + totalCost;

	                // Add combination to map
	                combinationsMap.computeIfAbsent(totalCost, k -> new ArrayList<>()).add(combination);
	            }
	        }
	    }

	    // Convert combinations map to list of maps for response
	    List<Map<String, Object>> response = new ArrayList<>();
	    for (Map.Entry<Integer, List<String>> entry : combinationsMap.entrySet()) {
	        Map<String, Object> map = new HashMap<>();
	        map.put("combination", entry.getValue());
	        map.put("totalPrice", entry.getKey() * entry.getValue().size());
	        response.add(map);
	    }

	    return new APIResponse(HttpStatus.OK.value(), "Pricing data", response);
	}
	
	public APIResponse getHotelPriceCombinationsB2(int adtCount, int chdCount) {

		List<PkgHotelPriceConfigModel> roomTypes = PkgHotelPriceConfigRepository.findAll();

		// Map to store combinations grouped by total price
		Map<Integer, List<String>> combinationsMap = new HashMap<>();

		// Calculate total cost for each room type
		for (PkgHotelPriceConfigModel roomType : roomTypes) {
			int numAdultRooms = adtCount / roomType.getMaximumAdultsAllowed();
			int numChildRooms = roomType.getMaximumChildrenAllowed() > 0
					? chdCount / roomType.getMaximumChildrenAllowed()
					: 0;
			int minRooms = Math.min(numAdultRooms, numChildRooms);

			for (int i = 1; i <= minRooms; i++) {
				int totalAdults = i * roomType.getMaximumAdultsAllowed();
				int totalChildren = i * roomType.getMaximumChildrenAllowed();
				if (totalAdults <= adtCount && totalChildren <= chdCount) {
					int totalCost = roomType.getPricePerNight();

					// Construct room combination string
					String combination = roomType.getSupplier() + "-" + roomType.getHotel() + "-"
							+ roomType.getRoomName() + "-" + roomType.getRoomType() + " Adt: " + totalAdults + " Chd: "
							+ totalChildren + " -> " + totalCost;

					// Add combination to map
					combinationsMap.computeIfAbsent(totalCost, k -> new ArrayList<>()).add(combination);
				}
			}
		}

		// Convert combinations map to list of maps for response
		List<Map<String, Object>> response = new ArrayList<>();
		for (Map.Entry<Integer, List<String>> entry : combinationsMap.entrySet()) {
			Map<String, Object> map = new HashMap<>();
			map.put("combination", entry.getValue());
			map.put("totalPrice", entry.getKey() * entry.getValue().size());
			response.add(map);
		}

		return new APIResponse(HttpStatus.OK.value(), "Pricing data", response);
	}
	
}

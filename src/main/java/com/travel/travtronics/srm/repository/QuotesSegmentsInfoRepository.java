package com.travel.travtronics.srm.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.srm.model.QuotesSegmentsInfoModel;
import com.travel.travtronics.util.QueryConst;

public interface QuotesSegmentsInfoRepository extends JpaRepository<QuotesSegmentsInfoModel, Integer> {

	List<QuotesSegmentsInfoModel> findByHeaderId(Integer id);
	
	@Query(value = QueryConst.GET_SCHEDULE_INFO_BY_SCHD_CONFIG_ID_QRY, nativeQuery = true)
	Map<String, Object> getSchduleInfo(Long scheduleId);

	@Query(value = QueryConst.GET_SCHEDULE_DATES_INFO_BY_SCHD_DATE_ID_QRY, nativeQuery = true)
	Map<String, Object> getSchduleDatesInfo(Integer scheduledDatesId);

}

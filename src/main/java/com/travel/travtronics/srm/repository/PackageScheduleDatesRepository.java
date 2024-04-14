package com.travel.travtronics.srm.repository;

import com.travel.travtronics.srm.model.TravelPackageScheduleDates;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackageScheduleDatesRepository extends JpaRepository<TravelPackageScheduleDates, Long> {
    List<TravelPackageScheduleDates> findByScheduleId(Long scheduleId);
}

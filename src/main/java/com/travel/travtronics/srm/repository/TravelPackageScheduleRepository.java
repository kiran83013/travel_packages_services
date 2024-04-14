package com.travel.travtronics.srm.repository;

import com.travel.travtronics.srm.model.TravelPackageSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TravelPackageScheduleRepository extends JpaRepository<TravelPackageSchedule, Long> {


    @Query(value = "SELECT city.id AS id, city.citycode AS `code`,city.name AS `name` FROM\tmasters_srm.master_airports AS city WHERE city.type = 'C' AND city.citycode = ?1", nativeQuery = true)
    List<Object[]> findCityByCode(String code);

    List<TravelPackageSchedule> findByPackageConfigurationId(Long id);
}

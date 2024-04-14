package com.travel.travtronics.srm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.srm.model.HolidayPackagePassengersModel;

public interface HolidayPackagePassengersRepository extends JpaRepository<HolidayPackagePassengersModel, Integer> {

	@Query(value = "SELECT * FROM sr_package_room_passengers WHERE passenger_room_id = ?1 AND (passenger_delete_flag IS NULL OR passenger_delete_flag = FALSE)", nativeQuery = true)
	List<HolidayPackagePassengersModel> getRoomPaxsByRoomId(Integer id);

}
package com.travel.travtronics.srm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.srm.model.HolidayPackageRoomsModel;

public interface HolidayPackageRoomsRepository extends JpaRepository<HolidayPackageRoomsModel, Integer> {

	@Query(value = "SELECT * FROM sr_package_rooms " + "WHERE room_sr_id = ?1 " + "AND room_line_id = ?2 "
			+ "AND (room_delete_flag IS NULL OR room_delete_flag = FALSE)", nativeQuery = true)
	List<HolidayPackageRoomsModel> findByRoomSrIdAndRoomLineId(Long requestId, Long requestLineId);

	Optional<HolidayPackageRoomsModel> findByIdAndRoomSrIdAndRoomLineId(Integer id, Integer roomSrId,
			Integer roomLineId);

	List<HolidayPackageRoomsModel> findByIdIn(int[] roomLineIds);

}

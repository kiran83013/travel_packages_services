package com.travel.travtronics.srm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travtronics.srm.model.SrHospitalRooms;

public interface SrHospitalRoomsRepository extends JpaRepository<SrHospitalRooms, Integer> {


	List<SrHospitalRooms> findByRoomLineId(Integer id);

	List<SrHospitalRooms> findAllByRoomSrIdAndRoomIsDeletedIsNull(Integer srId);

	List<SrHospitalRooms> findByRoomLineIdAndRoomIsDeletedIsNull(Integer id);

	List<SrHospitalRooms> findByRoomLineIdAndRoomSrIdAndRoomIsDeletedIsNull(Integer id, Integer sr);

	

}

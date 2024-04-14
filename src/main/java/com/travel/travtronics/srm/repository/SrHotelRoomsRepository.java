package com.travel.travtronics.srm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travtronics.srm.model.SrHotelRooms;

public interface SrHotelRoomsRepository extends JpaRepository<SrHotelRooms, Integer> {


	List<SrHotelRooms> findByRoomLineId(Integer id);

	List<SrHotelRooms> findAllByRoomSrIdAndRoomIsDeletedIsNull(Integer srId);

	List<SrHotelRooms> findByRoomLineIdAndRoomIsDeletedIsNull(Integer id);

	List<SrHotelRooms> findByRoomLineIdAndRoomSrIdAndRoomIsDeletedIsNull(Integer id, Integer sr);

	

}

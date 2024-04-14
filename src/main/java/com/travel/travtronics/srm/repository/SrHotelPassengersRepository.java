package com.travel.travtronics.srm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travtronics.srm.model.SrHotelPassengers;

public interface SrHotelPassengersRepository extends JpaRepository<SrHotelPassengers, Integer> {

	List<SrHotelPassengers> findAllByPassengerRoomId(Integer id);

	List<SrHotelPassengers> findAllByPassengerSrIdAndPassengerIsDeletedIsNull(Integer srId);

	List<SrHotelPassengers> findByPassengerLineIdAndPassengerIsDeletedIsNull(Integer id);

	List<SrHotelPassengers> findByPassengerLineIdAndPassengerSrIdAndPassengerIsDeletedIsNull(Integer id, Integer sr);



}

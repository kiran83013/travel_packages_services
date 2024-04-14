package com.travel.travtronics.srm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travtronics.srm.model.SrHospitalPassengers;

public interface SrHospitalPassengersRepository extends JpaRepository<SrHospitalPassengers, Integer> {

	List<SrHospitalPassengers> findAllByPassengerRoomId(Integer id);

	List<SrHospitalPassengers> findAllByPassengerSrIdAndPassengerIsDeletedIsNull(Integer srId);

	List<SrHospitalPassengers> findByPassengerLineIdAndPassengerIsDeletedIsNull(Integer id);

	List<SrHospitalPassengers> findByPassengerLineIdAndPassengerSrIdAndPassengerIsDeletedIsNull(Integer id, Integer sr);



}

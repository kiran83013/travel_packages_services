package com.travel.travtronics.packages.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travtronics.packages.model.PackageItineraryModel;

public interface PackageItineraryRepository extends JpaRepository<PackageItineraryModel, Integer> {

}

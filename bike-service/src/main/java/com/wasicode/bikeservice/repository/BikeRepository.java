package com.wasicode.bikeservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wasicode.bikeservice.entity.Bike;

public interface BikeRepository extends JpaRepository<Bike,Integer>{

    public List<Bike> findByUserId(int userId);

}

package com.wasicode.carservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wasicode.carservice.entity.Car;
import com.wasicode.carservice.repository.CarRepository;

@Service
public class CarService {
    
    @Autowired
    private CarRepository carRepository;

    public List<Car> getAll(){
        return  carRepository.findAll();
    }
    
    public Car getCarById(int id){
        return carRepository.findById(id).orElse(null);

    }

    public Car save(Car car){
        return carRepository.save(car);
    }

    public List<Car> getByUserId(int userId){
        return carRepository.findByUserId(userId);
    }

}

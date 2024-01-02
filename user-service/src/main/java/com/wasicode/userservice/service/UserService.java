package com.wasicode.userservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wasicode.userservice.entity.User;
import com.wasicode.userservice.feignclients.BikeFeingClient;
import com.wasicode.userservice.feignclients.CarFeingClient;
import com.wasicode.userservice.models.Bike;
import com.wasicode.userservice.models.Car;
import com.wasicode.userservice.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CarFeingClient carFeingClient;

    @Autowired
    BikeFeingClient bikeFeingClient;

    @Autowired
    RestTemplate restTemplate;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }
    
    public User save(User user){
        User userNew=userRepository.save(user);
        return userNew;
    }

    public List<Car> getCars(int userId){
        List<Car> cars= restTemplate.getForObject("http://localhost:8002/car/byuser/"+userId, List.class);
        return cars;
    }

    public List<Bike> getBikes(int userId){
        List<Bike> bikes= restTemplate.getForObject("http://localhost:8003/bike/byuser/"+userId, List.class);
        return bikes;
    }

    public Car saveCar(int userId,Car car){
        car.setUserId(userId);
        Car newCar= carFeingClient.save(car);
        return newCar;
    }

    public Bike savBike(int userId, Bike bike){
        bike.setUserId(userId);
        Bike newBike=bikeFeingClient.savBike(bike);
        return newBike;
    }

    public Map<String,Object> getUserVehicle(int userId){
        User user=userRepository.findById(userId).orElse(null);
        Map<String,Object> result=new HashMap<>();
        if(user!=null){
            result.put("user", user);
            
            List<Bike> listBike=bikeFeingClient.getBikes(userId);
            result.put("bikes", listBike);

            List<Car> listCar=carFeingClient.getCarByUserId(userId);
            result.put("cars", listCar);

        }

        return result;

    }
}

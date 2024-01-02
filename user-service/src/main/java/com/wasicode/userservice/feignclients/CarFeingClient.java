package com.wasicode.userservice.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wasicode.userservice.models.Car;

@FeignClient(name = "car-service",url = "http://localhost:8002/car")
public interface CarFeingClient {
    @PostMapping
    Car save(@RequestBody Car car);

    @GetMapping("/byuser/{userId}")
    List<Car> getCarByUserId(@PathVariable("userId")int  userId);
}

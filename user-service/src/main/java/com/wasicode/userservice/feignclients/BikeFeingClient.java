package com.wasicode.userservice.feignclients;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.wasicode.userservice.models.Bike;

@FeignClient(name="bike-service", url = "http://localhost:8003/bike")
public interface BikeFeingClient {
    @PostMapping
    Bike savBike(Bike bike);

    @GetMapping("/byuser/{userId}")
    List<Bike> getBikes(@PathVariable("userId") int userId);

}

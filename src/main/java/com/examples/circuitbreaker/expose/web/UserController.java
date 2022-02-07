package com.examples.circuitbreaker.expose.web;

import com.examples.circuitbreaker.model.Car;
import com.examples.circuitbreaker.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @CircuitBreaker(name = "carsCB", fallbackMethod = "fallBackGetCars")
    @GetMapping(value="cars")
    public List<Car> findAll(){
        return userService.getCars(1);
    }

    public List<Car> fallBackGetCars(RuntimeException e){
        System.out.println("action fallBackGetCars");
        return new ArrayList<>();
    }

}

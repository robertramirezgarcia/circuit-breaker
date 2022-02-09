package com.examples.circuitbreaker.expose.web;

import com.examples.circuitbreaker.model.Car;
import com.examples.circuitbreaker.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@Api(value = "users")
public class UserController {

    @Autowired
    private UserService userService;


    /*@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the cars",
            content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))
            })
    }) */
    @ApiOperation(value = "list of cars", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @CircuitBreaker(name = "carsCB", fallbackMethod = "fallBackGetCars")
    @GetMapping(value="cars")
    public List<Car> findAll(){
        return userService.getCars(1);
    }

    public List<Car> fallBackGetCars(RuntimeException e){
        System.out.println("action fallBackGetCars");
        return new ArrayList<>();
    }

    @ApiOperation(value = "unit car", response = Car.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @CircuitBreaker(name = "carCB", fallbackMethod = "fallBackGetCar")
    @GetMapping(value = "car/{id}")
    public Car findCarById(@PathVariable String id){
        return this.buildCar(id);
    }

    private Car buildCar(String id){
        Car car = new Car();
        car.setBrand("toyota");
        car.setModel("yaris");
        car.setUserId(2);
        return car;
    }

    public Car fallBackGetCar(RuntimeException e){
        return new Car();
    }

}

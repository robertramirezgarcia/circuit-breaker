package com.examples.circuitbreaker.service;

import com.examples.circuitbreaker.model.Car;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class UserService {

    public List<Car> getCars(int userId) throws NumberFormatException{
//        return getAllCars().collect(Collectors.toList());
        throw new NumberFormatException();
    }

    private Stream<Car> getAllCars(){
        List<Car> carList = new ArrayList<Car>();
        try {
            carList.add(new Car("abcd","s1",1));
            Thread.sleep(1000);
            carList.add(new Car("efgh","s2",2));

        } catch(InterruptedException e){
            System.out.println(e);
        }
        return carList.stream();
    }

}


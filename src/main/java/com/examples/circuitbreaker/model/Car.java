package com.examples.circuitbreaker.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Car {

    private String brand;
    private String model;
    private int userId;
}

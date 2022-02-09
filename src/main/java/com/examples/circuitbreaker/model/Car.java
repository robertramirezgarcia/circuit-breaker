package com.examples.circuitbreaker.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Car {

    @ApiModelProperty(notes = "Brand of the car")
    private String brand;
    @ApiModelProperty(notes = "Model of the car")
    private String model;
    @ApiModelProperty(notes = "UserId of the car")
    private int userId;
}

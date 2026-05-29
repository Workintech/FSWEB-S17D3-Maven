package com.workintech.zoo.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Koala {
    private int id;
    @NotBlank
    private String name;
    private double sleepHour;
    @Positive
    private  double weight;
    @NotBlank
    private String gender;
}

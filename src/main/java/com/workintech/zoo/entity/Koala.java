package com.workintech.zoo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class Koala {
    private long id;
    private String name;
    private double weight;
    private double sleepHour;
    private String gender;
}

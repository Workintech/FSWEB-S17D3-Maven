package com.workintech.zoo.entity;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Kangaroo {
    private int id;
    @NotBlank
    private String name;

    @Positive
    private double height;

    @Positive
    private double weight;

    @NotBlank
    private String gender;
    private boolean isAggressive;


    public boolean getIsAggressive() {
        return isAggressive;
    }

    public void setIsAggressive(boolean aggressive) {
        isAggressive = aggressive;
    }

}

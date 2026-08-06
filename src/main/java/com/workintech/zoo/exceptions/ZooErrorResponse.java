package com.workintech.zoo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ZooErrorResponse {
    private String message;
    private int status;
    private long timestamp;
}

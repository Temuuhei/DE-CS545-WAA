package com.example.bmicalculator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
public class Input {
    @Max(value=250, message = "weight must be between 50 and 250")
    @Min(value=50,  message = "weight must be between 50 and 250")
    private double weight;

    @Max(value=10, message = "height must be between 3 and 10")
    @Min(value=3, message = "height must be between 3 and 10")
    private double height;
}

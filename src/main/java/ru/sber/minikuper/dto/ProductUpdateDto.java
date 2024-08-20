package ru.sber.minikuper.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductUpdateDto {

    @Size(min = 1, max = 150)
    private String title;

    @Size(min = 1, max = 500)
    private String description;

    @DecimalMin(value = "0.01", inclusive = true)
    private BigDecimal price;

    @Min(value = 1, message = "Minimum is 1")
    @Max(value = 3, message = "Maximum is 3")
    private Integer status;
}

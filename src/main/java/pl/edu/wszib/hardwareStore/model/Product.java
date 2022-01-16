package pl.edu.wszib.hardwareStore.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Product {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private Category category;
    private int quantity;
}

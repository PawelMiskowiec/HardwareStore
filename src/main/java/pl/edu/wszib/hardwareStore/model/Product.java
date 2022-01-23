package pl.edu.wszib.hardwareStore.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@Entity(name = "tproduct")
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String brand;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private Category category;

    private int quantity;

    public Product(String name, String brand, BigDecimal price, Category category, int quantity) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }
}

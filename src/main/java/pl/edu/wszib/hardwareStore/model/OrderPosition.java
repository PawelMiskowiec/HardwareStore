package pl.edu.wszib.hardwareStore.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "torderposition")
@NoArgsConstructor
public class OrderPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    private int quantity;

    public OrderPosition(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}

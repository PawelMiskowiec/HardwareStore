package pl.edu.wszib.hardwareStore.model;

import lombok.Data;

@Data
public class OrderPosition {
    private Product product;
    private int quantity;
    public OrderPosition(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}

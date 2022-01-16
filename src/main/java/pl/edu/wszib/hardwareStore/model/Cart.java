package pl.edu.wszib.hardwareStore.model;

import lombok.Value;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Value
public class Cart {
    List<OrderPosition> orderPositions = new ArrayList<>();
    
    public double getSum(){
        BigDecimal cartSum = new BigDecimal("0");
        for (OrderPosition orderPosition: this.orderPositions) {
            cartSum=cartSum.add(orderPosition.getProduct().getPrice().multiply(BigDecimal.valueOf(orderPosition.getQuantity())));
        }
        return cartSum.doubleValue();
    }

    public void addOrderPosition(Product product, int quantity) {
        orderPositions.add(new OrderPosition(product, quantity));
    }

    public OrderPosition getOrderPositionByProductId(Long productId){
        return orderPositions
                .stream()
                .filter(orderPosition -> orderPosition.getProduct().getId().equals(productId))
                .findFirst()
                .get();
    }
}

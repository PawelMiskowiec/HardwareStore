package pl.edu.wszib.hardwareStore.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Order {
    private Long OrderId;
    private List<OrderPosition> orderPositions = new ArrayList<>();
    private String login;

    public void addOrderPosition(OrderPosition orderPosition){
        orderPositions.add(orderPosition);
    }
}

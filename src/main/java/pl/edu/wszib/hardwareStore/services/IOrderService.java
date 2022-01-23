package pl.edu.wszib.hardwareStore.services;

import pl.edu.wszib.hardwareStore.model.Order;

import java.util.List;
import java.util.Set;

public interface IOrderService {
    List<Order> getOrders();
    void addOrder();
}

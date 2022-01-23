package pl.edu.wszib.hardwareStore.database;

import pl.edu.wszib.hardwareStore.model.OrderPosition;

import java.util.List;

public interface IOrderPositionsDAO {
    void addOrderPosition(OrderPosition orderPosition);
    List<OrderPosition> getOrderPositionsByOrderId(int orderId);
}

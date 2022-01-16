package pl.edu.wszib.hardwareStore.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wszib.hardwareStore.database.DB;
import pl.edu.wszib.hardwareStore.model.Cart;
import pl.edu.wszib.hardwareStore.model.Order;
import pl.edu.wszib.hardwareStore.model.OrderPosition;
import pl.edu.wszib.hardwareStore.session.SessionObject;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    DB database;
    CartService cartService;

    @Resource
    SessionObject sessionObject;

    public List<Order> getOrders() {
        if(sessionObject.isLogged()){
            return database.getOrders(sessionObject.getUser().getLogin());
        }
        return Collections.emptyList();
    }

    public void addOrder() {
        if(sessionObject.isLogged()){
            database.addOrder(sessionObject.getCart(), sessionObject.getUser().getLogin());
            sessionObject.getCart().getOrderPositions().forEach(orderPosition -> database.decreaseQuantity(orderPosition.getProduct().getId(), orderPosition.getQuantity()));
            cartService.emptyCart();
        }
    }


}

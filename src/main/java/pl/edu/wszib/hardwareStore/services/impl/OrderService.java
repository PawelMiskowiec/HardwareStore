package pl.edu.wszib.hardwareStore.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wszib.hardwareStore.database.IOrderDAO;
import pl.edu.wszib.hardwareStore.database.IOrderPositionsDAO;
import pl.edu.wszib.hardwareStore.database.IProductDAO;
import pl.edu.wszib.hardwareStore.model.Order;
import pl.edu.wszib.hardwareStore.model.OrderPosition;
import pl.edu.wszib.hardwareStore.model.Product;
import pl.edu.wszib.hardwareStore.services.IOrderService;
import pl.edu.wszib.hardwareStore.session.SessionObject;

import javax.annotation.Resource;
import java.util.*;

@Service
@AllArgsConstructor
public class OrderService implements IOrderService{
    private final IOrderDAO orderDAO;
    private final IProductDAO productDAO;
    private final IOrderPositionsDAO orderPositionsDAO;
    private final CartService cartService;

    @Resource
    SessionObject sessionObject;

    @Override
    public List<Order> getOrders() {
        if(sessionObject.isLogged()){
            return orderDAO.getOrdersByUserId(sessionObject.getUser().getId());
        }
        return Collections.emptyList();
    }

    @Override
    public void addOrder() {
        if(sessionObject.isLogged()){
            HashSet<OrderPosition> orderPositions = addOrderPositions();
            if(!orderPositions.isEmpty()){
                Order order = new Order(orderPositions, sessionObject.getUser());
                orderDAO.addOrder(order);
                DecreaseProductsQuantity();
                cartService.emptyCart();
            }
        }
    }

    private void DecreaseProductsQuantity() {
        sessionObject.getCart().getOrderPositions().forEach(orderPosition ->{
            Optional<Product> productBox = productDAO.getById(orderPosition.getProduct().getId());
            if(productBox.isPresent()){
                productBox.get().setQuantity(productBox.get().getQuantity() - orderPosition.getQuantity());
                productDAO.updateProduct(productBox.get());
            }
        });
    }

    private HashSet<OrderPosition> addOrderPositions(){
        HashSet<OrderPosition> orderPositions = new HashSet<>();
        sessionObject.getCart().getOrderPositions().forEach(orderPosition -> {
            orderPositionsDAO.addOrderPosition(orderPosition);
            orderPositions.add(orderPosition);
        });
        return orderPositions;
    }

}

package pl.edu.wszib.hardwareStore.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.edu.wszib.hardwareStore.database.DB;
import pl.edu.wszib.hardwareStore.model.Cart;
import pl.edu.wszib.hardwareStore.model.Product;
import pl.edu.wszib.hardwareStore.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CartService {
    DB database;

    @Resource
    SessionObject sessionObject;

    public void addProduct(Long productId){
        Optional<Product> product = database.getProductById(productId);
        if(checkIfPresentInCart(productId)){
            addQuantityIfPresentInCart(productId);
        } else{
            addProductToCart(product, 1);
        }
    }

    private boolean checkIfPresentInCart(Long productId){
        return sessionObject.getCart().getOrderPositions()
                .stream()
                .anyMatch(orderPosition -> orderPosition.getProduct().getId().equals(productId));
    }

    private void addQuantityIfPresentInCart(Long productId){
        sessionObject.getCart().getOrderPositions()
                .stream()
                .filter(orderPosition -> orderPosition.getProduct().getId().equals(productId))
                .findFirst()
                .ifPresent(orderPosition -> orderPosition.setQuantity(orderPosition.getQuantity()+1));
    }

    private void addProductToCart(Optional<Product> product, int quantity){
        if(product.isPresent()
                && database.getProductById(product.get().getId()).isPresent()
                && database.getProductById(product.get().getId()).get().getQuantity()>=quantity){
            product.ifPresent(value -> sessionObject.getCart().addOrderPosition(value, quantity));
        }

    }

    public void emptyCart(){
        if(sessionObject.isLogged()){
            sessionObject.setCart(new Cart());
        }
    }

}

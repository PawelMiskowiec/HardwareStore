package pl.edu.wszib.hardwareStore.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.edu.wszib.hardwareStore.database.DB;
import pl.edu.wszib.hardwareStore.database.IProductDAO;
import pl.edu.wszib.hardwareStore.model.Cart;
import pl.edu.wszib.hardwareStore.model.Product;
import pl.edu.wszib.hardwareStore.services.ICartService;
import pl.edu.wszib.hardwareStore.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartService implements ICartService{
    IProductDAO productDAO;

    @Resource
    SessionObject sessionObject;

    public void addProduct(Long productId){
        Optional<Product> product = productDAO.getProductById(productId);

        if(checkIfPresentInCart(productId)){
            if(sessionObject.getCart().getOrderPositionByProductId(productId).getQuantity() + 1
                    <= productDAO.getProductById(productId).get().getQuantity()){
                addProductQuantity(productId);
            }
        } else{
            addProductToCart(product, 1);
        }
    }

    private boolean checkIfPresentInCart(Long productId){
        return sessionObject.getCart().getOrderPositions()
                .stream()
                .anyMatch(orderPosition -> orderPosition.getProduct().getId().equals(productId));
    }

    private void addProductQuantity(Long productId){
        sessionObject.getCart().getOrderPositions()
                .stream()
                .filter(orderPosition -> orderPosition.getProduct().getId().equals(productId))
                .findFirst()
                .ifPresent(orderPosition -> orderPosition.setQuantity(orderPosition.getQuantity()+1));
    }

    private void addProductToCart(Optional<Product> product, int quantity){
        if(product.isPresent()
                && productDAO.getProductById(product.get().getId()).isPresent()
                && productDAO.getProductById(product.get().getId()).get().getQuantity()>=quantity){
            product.ifPresent(value -> sessionObject.getCart().addOrderPosition(value, quantity));
        }

    }

    public void emptyCart(){
        if(sessionObject.isLogged()){
            sessionObject.setCart(new Cart());
        }
    }

}

package pl.edu.wszib.hardwareStore.services;

import pl.edu.wszib.hardwareStore.model.Product;

import java.util.Optional;

public interface ICartService {
    void addProduct(Long productId);
    void emptyCart();
}

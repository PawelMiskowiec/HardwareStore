package pl.edu.wszib.hardwareStore.services;

import pl.edu.wszib.hardwareStore.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> getAllProducts();
}

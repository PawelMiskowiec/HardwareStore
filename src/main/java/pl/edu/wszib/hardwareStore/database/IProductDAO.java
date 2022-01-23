package pl.edu.wszib.hardwareStore.database;

import pl.edu.wszib.hardwareStore.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductDAO {
    List<Product> getProducts();
    Optional<Product> getProductById(Long id);
    void updateProduct(Product product);
}

package pl.edu.wszib.hardwareStore.database;

import pl.edu.wszib.hardwareStore.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductDAO {
    List<Product> getProducts();
    Optional<Product> getById(Long id);
    List<Product> getByCategory(String category);
    void updateProduct(Product product);
    void addProduct(Product product);
    void deleteById(Product product);
}

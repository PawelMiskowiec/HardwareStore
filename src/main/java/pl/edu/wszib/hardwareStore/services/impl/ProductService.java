package pl.edu.wszib.hardwareStore.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wszib.hardwareStore.database.IProductDAO;
import pl.edu.wszib.hardwareStore.model.Product;
import pl.edu.wszib.hardwareStore.services.IProductService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {
    IProductDAO productDAO;

    @Override
    public List<Product> getAllProducts() {
        return productDAO.getProducts();
    }

}

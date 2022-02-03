package pl.edu.wszib.hardwareStore.controllers.restapi;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.edu.wszib.hardwareStore.exceptions.IncorrectCategoryException;
import pl.edu.wszib.hardwareStore.model.Category;
import pl.edu.wszib.hardwareStore.model.Product;
import pl.edu.wszib.hardwareStore.services.IProductService;
import pl.edu.wszib.hardwareStore.session.SessionObject;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
@AllArgsConstructor
public class ProductRestController {
    private final IProductService productService;

    @Resource
    SessionObject sessionObject;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Product>> getProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id){
        return ResponseEntity.of(productService.getById(id));
    }

    @GetMapping(value = "/category/{category}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable String category){
        return ResponseEntity.ok(productService.getByCategory(category));
    }

    @PostMapping
    public ResponseEntity<Void> addProduct(String name, String brand, long price, String category, int quantity){
        Product product = new Product(name, brand, new BigDecimal(price),
                Category.parseString(category).orElseThrow(IncorrectCategoryException::new), quantity);
        productService.addProduct(product);
        URI uri = createdURI("/" + product.getId().toString());
        return ResponseEntity.created(uri).build();
    }

    private URI createdURI(String path){
        return ServletUriComponentsBuilder.fromCurrentRequestUri().path(path).build().toUri();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteById(@PathVariable Long id){
        if(getById(id).hasBody()){
            productService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>("Product with id " + id + " not found", HttpStatus.NOT_FOUND);
    }

}

package pl.edu.wszib.hardwareStore.database;

import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Component;
import pl.edu.wszib.hardwareStore.model.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Component
@Data
public class DB {
    private List<User> users = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private AtomicLong PRODUCT_ID_NEXT_VALUE = new AtomicLong(0l);
    private AtomicLong ORDER_ID_NEXT_VALUE = new AtomicLong(0l);

    public DB() {

        this.products.add(Product
                .builder()
                .id(nextProductID())
                .name("Hammer")
                .brand("Stanley")
                .category(Category.TOOLS)
                .price(new BigDecimal("20"))
                .quantity(5)
                .build());

        this.products.add(Product
                .builder()
                .id(nextProductID())
                .name("Polyurethane sheet 40x40cm 40ShA")
                .brand("ePlastics")
                .category(Category.MATERIALS)
                .price(new BigDecimal("130"))
                .quantity(10)
                .build());

        this.products.add(Product.builder()
                .id(nextProductID())
                .name("M3 Nut")
                .brand("B&N")
                .category(Category.PARTS)
                .price(new BigDecimal("1.20"))
                .quantity(100)
                .build());
        this.users.add(new User("admin", DigestUtils.md5Hex("admin")));
        this.users.add(new User("user", DigestUtils.md5Hex("user")));
    }

    private Long nextProductID(){
        return PRODUCT_ID_NEXT_VALUE.getAndIncrement();
    }

    private Long nextOrderID(){
        return ORDER_ID_NEXT_VALUE.getAndIncrement();
    }

    public Optional<User> getUserByLogin(String login){
        return users
                .stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst();
    }

    public Optional<Product> getProductById(Long id){
        return products
                .stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    public int getProductQuantityById(Long id){
        return products
                .stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .get()
                .getQuantity();
    }

    public Optional<User> addUser(String login, String password) {
        if (users.stream().noneMatch(user -> user.getLogin().equals(login))){
            User newUser = new User(login, password);
            this.users.add(newUser);
            return Optional.of(newUser);
        }
        return Optional.empty();
    }

    public void addOrder(Cart cart, String login) {
        Order order = new Order();

        order.setOrderId(nextOrderID());
        order.setLogin(login);
        cart.getOrderPositions().forEach(order::addOrderPosition);

        orders.add(order);
    }

    public List<Order> getOrders(String login) {
        return orders
                .stream()
                .filter(order -> order.getLogin().equals(login))
                .toList();
    }


    public void decreaseQuantity(Long productId, int quantity) {
        if (getProductById(productId).isPresent()){
            getProductById(productId).get()
                    .setQuantity(getProductById(productId).get().getQuantity() - quantity);
        }
    }
}

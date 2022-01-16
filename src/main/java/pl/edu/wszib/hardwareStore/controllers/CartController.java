package pl.edu.wszib.hardwareStore.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.wszib.hardwareStore.services.CartService;
import pl.edu.wszib.hardwareStore.services.OrderService;
import pl.edu.wszib.hardwareStore.session.SessionObject;

import javax.annotation.Resource;

@RequestMapping(value = "/cart")
@Controller
@AllArgsConstructor
public class CartController {
    CartService cartService;
    OrderService orderService;

    @Resource
    SessionObject sessionObject;

    @GetMapping()
    public String cart(Model model){
        model.addAttribute("cart", this.sessionObject.getCart());
        model.addAttribute("logged", this.sessionObject.isLogged());
        model.addAttribute("sum", this.sessionObject.getCart().getSum());
        return "cart";
    }

    @GetMapping(value = "/add/{productId}")
    public String addProductToCard(@PathVariable Long productId){
        cartService.addProduct(productId);
        return "redirect:/main";
    }

    @GetMapping(value = "/confirmOrder")
    public String confirmOrder(){
        orderService.addOrder();
        return "redirect:/orders";
    }

}

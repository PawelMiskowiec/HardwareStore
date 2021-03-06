package pl.edu.wszib.hardwareStore.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.wszib.hardwareStore.services.impl.OrderService;
import pl.edu.wszib.hardwareStore.session.SessionObject;

import javax.annotation.Resource;

@RequestMapping("orders")
@Controller
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @Resource
    SessionObject sessionObject;

    @GetMapping
    public String getOrder( Model model){
        model.addAttribute("orders", this.orderService.getOrders());
        model.addAttribute("logged", this.sessionObject.isLogged());
        return "orders";
    }

    @GetMapping(value = "/confirmOrder")
    public String confirmOrder(){
        if(!sessionObject.getCart().getOrderPositions().isEmpty()){
            orderService.addOrder();
        }
        return "redirect:/orders";
    }

}

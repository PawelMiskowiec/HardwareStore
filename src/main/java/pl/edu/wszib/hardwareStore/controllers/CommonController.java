package pl.edu.wszib.hardwareStore.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.wszib.hardwareStore.database.DB;
import pl.edu.wszib.hardwareStore.services.IProductService;
import pl.edu.wszib.hardwareStore.session.SessionObject;

import javax.annotation.Resource;

@RequestMapping(value = "/")
@Controller
@AllArgsConstructor
public class CommonController {
    private final IProductService productService;

    @Resource
    SessionObject sessionObject;

    @GetMapping
    public String getMain(){
        return "redirect:/main";
    }

    @GetMapping(value = "/main")
    public String main(Model model){
        model.addAttribute("products", this.productService.getAllProducts());
        model.addAttribute("logged", this.sessionObject.isLogged());
        return "main";
    }

    @GetMapping(value = "/contact")
    public String getContact(Model model){
        model.addAttribute("logged", this.sessionObject.isLogged());
        return "contact";
    }

}

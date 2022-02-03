package pl.edu.wszib.hardwareStore.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.hardwareStore.exceptions.AuthValidationException;
import pl.edu.wszib.hardwareStore.services.impl.AuthenticationService;
import pl.edu.wszib.hardwareStore.session.SessionObject;
import pl.edu.wszib.hardwareStore.validators.LoginValidator;

import javax.annotation.Resource;


@RequestMapping(value = "/")
@Controller
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Resource
    SessionObject sessionObject;

    @GetMapping(value = "/login")
    public String getLoginForm() { return "login"; }

    @PostMapping(value = "/login")
    public String signIn(@RequestParam String login, @RequestParam String password){
        try{
            LoginValidator.validateLogin(login);
            LoginValidator.validatePass(password);
        } catch (AuthValidationException e){
            return "redirect:/login";
        }

        authenticationService.authenticate(login, password);

        if(this.sessionObject.isLogged()){
            return "redirect:/main";
        } else{
            return "redirect:/login";
        }
    }

    @GetMapping(value = "/signup")
    public String getSignUpForm() { return "signup"; }

    @PostMapping(value = "/signup")
    public String register(@RequestParam String login, @RequestParam String password){
        authenticationService.register(login, password);
        try{
            LoginValidator.validateLogin(login);
            LoginValidator.validatePass(password);
        } catch (AuthValidationException e){
            return "redirect:/login";
        }

        if(this.sessionObject.isLogged()){
            return "redirect:/main";
        } else{
            return "redirect:/signup";
        }
    }

    @GetMapping(value = "/logout")
    public String logout() {
        this.sessionObject.setUser(null);
        return "redirect:/main";
    }


}

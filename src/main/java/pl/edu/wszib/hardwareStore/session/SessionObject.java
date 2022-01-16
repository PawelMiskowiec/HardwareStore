package pl.edu.wszib.hardwareStore.session;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.edu.wszib.hardwareStore.model.Cart;
import pl.edu.wszib.hardwareStore.model.User;

@Component
@SessionScope
@Data
public class SessionObject {
    private User user = null;
    private Cart cart = new Cart();

    public boolean isLogged() { return this.user != null;}
}

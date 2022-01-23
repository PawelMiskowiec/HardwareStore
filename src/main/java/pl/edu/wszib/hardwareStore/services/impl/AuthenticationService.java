package pl.edu.wszib.hardwareStore.services.impl;

import lombok.AllArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import pl.edu.wszib.hardwareStore.database.DB;
import pl.edu.wszib.hardwareStore.database.IUserDAO;
import pl.edu.wszib.hardwareStore.model.User;
import pl.edu.wszib.hardwareStore.services.IAuthentiactionService;
import pl.edu.wszib.hardwareStore.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationService implements IAuthentiactionService {
    private final IUserDAO userDAO;

    @Resource
    SessionObject sessionObject;

    @Override
    public void authenticate(String login, String password){
        Optional<User> user = userDAO.getUserByLogin(login);

        if(user.isPresent() && user.get().getPass().equals(DigestUtils.md5Hex(password)))
            this.sessionObject.setUser(user.get());
    }

    @Override
    public void register(String login, String password) {
        Optional<User> user = userDAO.addUser(new User(login, DigestUtils.md5Hex(password)));

        user.ifPresent(value -> this.sessionObject.setUser(value));
    }
}

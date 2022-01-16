package pl.edu.wszib.hardwareStore.services;

import lombok.AllArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import pl.edu.wszib.hardwareStore.database.DB;
import pl.edu.wszib.hardwareStore.model.User;
import pl.edu.wszib.hardwareStore.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private DB database;

    @Resource
    SessionObject sessionObject;

    public void authenticate(String login, String password){
        Optional<User> user = database.getUserByLogin(login);

        if(user.isPresent() && user.get().getPass().equals(DigestUtils.md5Hex(password)))
            this.sessionObject.setUser(user.get());
    }

    public void signUp(String login, String password) {
        Optional<User> user = database.addUser(login, DigestUtils.md5Hex(password));

        if(user.isPresent()){
            this.sessionObject.setUser(user.get());
        }
    }
}

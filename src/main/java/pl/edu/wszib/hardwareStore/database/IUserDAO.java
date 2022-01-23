package pl.edu.wszib.hardwareStore.database;

import pl.edu.wszib.hardwareStore.model.User;

import java.util.Optional;

public interface IUserDAO {
    Optional<User> getUserByLogin(String login);
    Optional<User> getUserById(Long id);
    Optional<User> addUser(User user);
}

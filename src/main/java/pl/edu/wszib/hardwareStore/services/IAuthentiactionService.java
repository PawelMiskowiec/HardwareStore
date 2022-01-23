package pl.edu.wszib.hardwareStore.services;

public interface IAuthentiactionService {
    void authenticate(String login, String password);
    void register(String login, String password);
}

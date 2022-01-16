package pl.edu.wszib.hardwareStore.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String login;
    private String pass;
}

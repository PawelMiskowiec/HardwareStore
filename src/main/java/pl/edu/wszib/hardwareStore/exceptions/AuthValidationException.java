package pl.edu.wszib.hardwareStore.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthValidationException extends RuntimeException{
    private String info;
}

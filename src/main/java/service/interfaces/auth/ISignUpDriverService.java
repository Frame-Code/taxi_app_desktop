package service.interfaces.auth;

import domain.entities.Driver;

public interface ISignUpDriverService {
    boolean signUp(Driver driver);
}

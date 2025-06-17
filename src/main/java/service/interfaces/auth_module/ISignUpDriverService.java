package service.interfaces.auth_module;

import domain.entities.Driver;

public interface ISignUpDriverService {
    boolean signUp(Driver driver);
}

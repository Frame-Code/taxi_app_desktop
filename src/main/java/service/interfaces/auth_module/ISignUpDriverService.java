package service.interfaces.auth_module;

import domain.entities.Driver;

/**
 *
 * @author Daniel Mora Cantillo
 */
public interface ISignUpDriverService {
    boolean signUp(Driver driver);
}

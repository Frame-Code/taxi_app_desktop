package service.interfaces.auth_module;

import domain.entities.Client;

/**
 *
 * @author Daniel Mora Cantillo
 */
public interface ISignUpClientService {
    boolean signUp(Client client);
}

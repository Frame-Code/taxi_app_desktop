package service.interfaces.auth_module;

import domain.entities.Client;

public interface ISignUpClientService {
    boolean signUp(Client client);
}

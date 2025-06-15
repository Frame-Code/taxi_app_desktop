package service.interfaces.auth;

import domain.entities.Client;

public interface ISignUpClientService {
    boolean signUp(Client client);
}

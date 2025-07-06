package service.interfaces.auth_module;

import domain.entities.Client;
import domain.repository.interfaces.ClientRepository;

/**
 *
 * @author Daniel Mora Cantillo
 */
public interface ISignUpClientService {
    boolean signUp(Client client, ClientRepository repository);
}

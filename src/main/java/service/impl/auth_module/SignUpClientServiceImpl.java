
package service.impl.auth_module;

import domain.entities.Client;
import domain.repository.interfaces.ClientRepository;
import domain.repository.interfaces.UserRepository;
import service.interfaces.auth_module.ISignUpClientService;
import service.interfaces.auth_module.SignUpService;
import lombok.extern.apachecommons.CommonsLog;

/**
 *
 * @author Daniel Mora Cantillo
 */
@CommonsLog
public class SignUpClientServiceImpl extends SignUpService implements ISignUpClientService {
    public SignUpClientServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Override
    public boolean signUp(Client client, ClientRepository clientRepository) {
        if (isUsedEmail(client.getUserEntity().getEmail()) || isUsedPhone(client.getUserEntity().getPhone())) {
            log.warn("Can't sign up user, the email or phone is already in use");
            return false;
        }
        clientRepository.save(client);
        log.info("User signed up successfully");
        return true;
    }
}
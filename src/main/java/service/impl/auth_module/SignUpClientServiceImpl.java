package service.impl.auth_module;

import domain.entities.Client;
import domain.repository.INTERFACES.ClientRepository;
import service.interfaces.auth_module.ISignUpClientService;
import service.interfaces.auth_module.SignUpService;
import lombok.extern.apachecommons.CommonsLog;


@CommonsLog
public class SignUpClientServiceImpl extends SignUpService implements ISignUpClientService {
    public SignUpClientServiceImpl(ClientRepository repository) {
        super(repository);
    }

    @Override
    public boolean signUp(Client client) {
        if (isUsedEmail(client.getUser().getEmail()) || isUsedPhone(client.getUser().getPhone())) {
            log.warn("Can't sign up user, the email or phone is already in use");
            return false;
        }
        super.getRepository().save(client);
        log.info("User signed up successfully");
        return true;
    }
}

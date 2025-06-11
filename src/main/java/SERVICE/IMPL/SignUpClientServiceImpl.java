package SERVICE.IMPL;

import DOMAIN.ENTITIES.Client;
import DOMAIN.ENTITIES.User;
import DOMAIN.REPOSITORY.INTERFACES.ClientRepository;
import SERVICE.INTERFACES.ISignUpClientService;
import SERVICE.INTERFACES.SignUpService;
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

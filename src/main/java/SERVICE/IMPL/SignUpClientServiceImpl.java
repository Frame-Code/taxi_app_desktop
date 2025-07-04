package service.impl;


import domain.entities.Client;
import domain.repository.interfaces.ClientRepository;
import domain.repository.interfaces.UserRepository;
import lombok.extern.apachecommons.CommonsLog;
import service.interfaces.ISignUpClientService;
import service.interfaces.SignUpService;


@CommonsLog
public class SignUpClientServiceImpl extends SignUpService implements ISignUpClientService {
    private final ClientRepository repository;

    public SignUpClientServiceImpl(ClientRepository repository, UserRepository userRepository) {
        super(userRepository);
        this.repository = repository;
    }

    @Override
    public boolean signUp(Client client) {
        if (isUsedEmail(client.getUserEntity().getEmail()) || isUsedPhone(client.getUserEntity().getPhone())) {
            log.warn("Can't sign up userEntity, the email or phone is already in use");
            return false;
        }
        repository.save(client);
        log.info("Cliente signed up successfully");
        return true;
    }
}

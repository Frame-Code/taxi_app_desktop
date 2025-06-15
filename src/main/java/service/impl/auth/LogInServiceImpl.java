package service.impl.auth;

import domain.repository.INTERFACES.ClientRepository;
import service.interfaces.auth.ILogInService;
import shared.utils.PasswordUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

@RequiredArgsConstructor
@CommonsLog
public class LogInServiceImpl implements ILogInService {
    private final ClientRepository repository;

    @Override
    public boolean login(String email, String plainPassword) {
        return repository.findByEmail(email)
                .map(client -> {
                    return PasswordUtils.verifyPassword(plainPassword, client.getUser().getPasswordHash());
                }).orElse(false);
    }
}

package service.impl;

import domain.repository.interfaces.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import service.interfaces.ILogInService;
import shared.utils.PasswordUtils;

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

package service.impl.auth_module;

import domain.repository.interfaces.ClientRepository;
import service.interfaces.auth_module.ILogInService;
import shared.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

/**
 *
 * @author Daniel Mora Cantillo
 */
@RequiredArgsConstructor
@CommonsLog
public class LogInServiceImpl implements ILogInService {
    private final ClientRepository repository;

    @Override
    public boolean login(String email, String plainPassword) {
        return repository.findByEmail(email)
                .map(client ->  PasswordUtil.verifyPassword(plainPassword, client.getUserEntity().getPasswordHash()))
                .orElse(false);
    }
}

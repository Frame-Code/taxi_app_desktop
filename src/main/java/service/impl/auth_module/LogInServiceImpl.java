package service.impl.auth_module;

import domain.entities.UserEntity;
import domain.repository.interfaces.ClientRepository;
import domain.repository.interfaces.UserRepository;
import service.interfaces.auth_module.ILogInService;
import shared.enums.ROLE_NAME;
import shared.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

import java.util.Optional;

/**
 *
 * @author Daniel Mora Cantillo
 */
@RequiredArgsConstructor
@CommonsLog
public class LogInServiceImpl implements ILogInService {
    private final UserRepository repository;

    @Override
    public boolean login(String email, String plainPassword) {
        return repository.findByEmail(email)
                .map(user ->  PasswordUtil.verifyPassword(plainPassword, user.getPasswordHash()))
                .orElse(false);
    }

    @Override
    public boolean isClient(String email) {
        return repository.findByEmail(email)
                .map(user -> user.getRole().getRoleName().equals(ROLE_NAME.CLIENT))
                .orElse(false);
    }

    @Override
    public boolean isDriver(String email) {
        return repository.findByEmail(email)
                .map(user -> user.getRole().getRoleName().equals(ROLE_NAME.CAB))
                .orElse(false);
    }

    @Override
    public boolean isAdministrator(String email) {
        return repository.findByEmail(email)
                .map(user -> user.getRole().getRoleName().equals(ROLE_NAME.ADMINISTRATOR))
                .orElse(false);
    }


}

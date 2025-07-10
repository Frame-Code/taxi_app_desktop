package service.impl.role_module;

import domain.entities.Role;
import domain.repository.interfaces.RoleRepository;
import lombok.RequiredArgsConstructor;
import service.interfaces.role_module.IRoleService;
import shared.enums.ROLE_NAME;

import java.util.Optional;

@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {
    private final RoleRepository repository;

    @Override
    public Optional<Role> findByName(ROLE_NAME roleName) {
        return repository.findByName(roleName);
    }
}

package service.interfaces.role_module;

import domain.entities.Role;
import shared.enums.ROLE_NAME;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(ROLE_NAME roleName);
}

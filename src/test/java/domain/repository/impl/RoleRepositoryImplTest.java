package domain.repository.impl;

import domain.entities.Permission;
import domain.entities.Role;
import domain.repository.interfaces.RoleRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import shared.enums.PERMISSION_NAME;
import shared.enums.ROLE_NAME;
import shared.utils.HibernateUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoleRepositoryImplTest {
    private static RoleRepository repository;

    @BeforeAll
    static void setUp() {
        repository = new RoleRepositoryImpl(HibernateUtil.getSessionFactory("hibernate-test.cfg.xml"));
    }

    @Test
    void save() {
        Role role = Role.builder()
                .roleName(ROLE_NAME.CAB)
                .permissions(
                        List.of(
                                Permission.builder()
                                        .permissionName(PERMISSION_NAME.CREATE)
                                        .build(),
                                Permission.builder()
                                        .permissionName(PERMISSION_NAME.UPDATE)
                                        .build(),
                                Permission.builder()
                                        .permissionName(PERMISSION_NAME.READ)
                                        .build()
                        )
                ).build();
        assertEquals(role, repository.save(role));
    }
}
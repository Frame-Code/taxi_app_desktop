package domain.repository.impl;

import domain.entities.Client;
import domain.entities.User;
import domain.repository.interfaces.ClientRepository;
import service.impl.auth_module.LogInServiceImpl;
import service.interfaces.auth_module.ILogInService;
import shared.utils.HibernateUtil;
import shared.utils.PasswordUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogInServiceImplTest {
    private static ClientRepository repository;
    private static ILogInService service;

    @BeforeAll
    public static void setUp() {
        repository = new ClientRepositoryImpl(HibernateUtil.getSessionFactory("hibernate-test.cfg.xml"));
        service = new LogInServiceImpl(repository);
    }

    @Test
    void login() {
        repository.save(Client.builder()
                .user(User.builder()
                        .createdBy("Admin")
                        .names("Daniel")
                        .lastNames("Mora")
                        .email("mail@email.com")
                        .passwordHash(PasswordUtil.hashPassword("pass"))
                        .phone("0941")
                        .build())
                .build());

        assertTrue(service.login("mail@email.com", "pass"));

    }
}
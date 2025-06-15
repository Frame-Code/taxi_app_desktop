package domain.repository.IMPL;

import domain.entities.Client;
import domain.entities.User;
import domain.repository.INTERFACES.ClientRepository;
import service.impl.auth.LogInServiceImpl;
import service.interfaces.auth.ILogInService;
import shared.utils.HibernateUtil;
import shared.utils.PasswordUtils;
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
                        .passwordHash(PasswordUtils.hashPassword("pass"))
                        .phone("0941")
                        .build())
                .build());

        assertTrue(service.login("mail@email.com", "pass"));

    }
}
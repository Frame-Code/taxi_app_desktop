package service.impl;

import domain.entities.Client;
import domain.entities.User;
import domain.repository.IMPL.ClientRepositoryImpl;
import domain.repository.INTERFACES.ClientRepository;
import service.impl.auth.SignUpClientServiceImpl;
import service.interfaces.auth.ISignUpClientService;
import shared.utils.HibernateUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SignUpClientServiceImplTest {
    private static ClientRepository repository;
    private static ISignUpClientService service;

    @BeforeAll
    public static void setUp() {
        repository = new ClientRepositoryImpl(HibernateUtil.getSessionFactory("hibernate-test.cfg.xml"));
        service = new SignUpClientServiceImpl(repository);
    }

    @Test
    void signUp() {
        assertTrue(service.signUp(Client.builder()
                .user(User.builder()
                        .names("Daniel")
                        .lastNames("Mora")
                        .bornDate(LocalDate.now())
                        .email("mail@email.com")
                        .phone("0941")
                        .passwordHash("pass")
                        .build())
                .build()));

    }
}
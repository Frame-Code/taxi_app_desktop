package SERVICE.IMPL;

import DOMAIN.ENTITIES.Client;
import DOMAIN.ENTITIES.User;
import DOMAIN.REPOSITORY.IMPL.ClientRepositoryImpl;
import DOMAIN.REPOSITORY.INTERFACES.ClientRepository;
import SERVICE.INTERFACES.ISignUpClientService;
import SHARED.UTILS.HibernateUtil;
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
                        .email("mail@mail.com")
                        .phone("0941")
                        .passwordHash("pass")
                        .build())
                .build()));

    }
}
package DOMAIN.REPOSITORY.IMPL;

import DOMAIN.ENTITIES.Client;
import DOMAIN.ENTITIES.User;
import DOMAIN.REPOSITORY.INTERFACES.ClientRepository;
import SHARED.UTILS.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ClientRepositoryImplTest {
    private static ClientRepository repository;

    @BeforeAll
    static void setUp() {
        repository = new ClientRepositoryImpl(HibernateUtil.getSessionFactory("hibernate-test.cfg.xml"));
    }

    @Test
    void findByEmail() {
        Client client = Client.builder().user(User.builder()
                .email("mail@email.com")
                .bornDate(LocalDate.now())
                .phone("091452")
                .names("Daniel Mora")
                .lastNames("Cantillo")
                .createdBy("Admin")
                .passwordHash("jkl1")
                .build()).build();
        repository.save(client);
        Optional<Client> clientOpt = repository.findByEmail("mail@email.com");
        assertEquals(clientOpt.get().getUser().getEmail(), "mail@email.com");
    }

    @Test
    void save() {
        Client client = Client.builder().user(User.builder()
                .email("mail1@email.com")
                .bornDate(LocalDate.now())
                .phone("0914522")
                .names("Daniel Mora")
                .lastNames("Cantillo")
                .createdBy("Admin")
                .passwordHash("jkl1")
                .build()).build();
        Client saved = repository.save(client);
        assertEquals(client, saved);
    }

    @Test
    void findById() {

    }

    @Test
    void findAll() {
        Client client = Client.builder().user(User.builder()
                .email("mail@email.com")
                .bornDate(LocalDate.now())
                .phone("091452")
                .names("Daniel Mora")
                .lastNames("Cantillo")
                .createdBy("Admin")
                .passwordHash("jkl1")
                .build()).build();
        repository.save(client);
        var clients = repository.findAll();
        assertNotNull(clients);
    }

    @Test
    void deleteById() {
    }

    @Test
    void update() {
    }
}
package DOMAIN.REPOSITORY.IMPL;

import DOMAIN.ENTITIES.Client;
import DOMAIN.ENTITIES.User;
import DOMAIN.REPOSITORY.INTERFACES.ClientRepository;
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

import static org.junit.jupiter.api.Assertions.*;

class ClientRepositoryImplTest {
    private static ClientRepository repository;

    @BeforeAll
    static void setUp() {
        repository = new ClientRepositoryImpl();
    }

    @Test
    void findByEmail() {
    }

    @Test
    void save() {
        Client client = Client.builder().user(User.builder()
                        .email("mail@email.com")
                        .bornDate(LocalDate.now())
                        .phone("091452")
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
    }

    @Test
    void deleteById() {
    }

    @Test
    void update() {
    }
}
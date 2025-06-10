package DOMAIN.REPOSITORY.IMPL;

import DOMAIN.ENTITIES.Client;
import DOMAIN.ENTITIES.User;
import DOMAIN.REPOSITORY.INTERFACES.ClientRepository;
import SHARED.UTILS.HibernateUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Daniel Mora Cantillo
 * */
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
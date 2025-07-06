package domain.repository.impl;

import domain.entities.Client;
import domain.entities.UserEntity;
import domain.repository.interfaces.ClientRepository;
import shared.utils.HibernateUtil;
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
        Client client = Client.builder().userEntity(UserEntity.builder()
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
        assertEquals("mail@email.com", clientOpt.get().getUserEntity().getEmail());
    }

    @Test
    void save() {
        Client client = Client.builder().userEntity(UserEntity.builder()
                .email("mail@email.com")
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
    void findAll() {
        Client client = Client.builder().userEntity(UserEntity.builder()
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

}
package domain.repository.impl;

import domain.entities.Fare;
import domain.repository.interfaces.FareRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import shared.utils.HibernateUtil;

import static org.junit.jupiter.api.Assertions.*;

class FareRepositoryImplTest {
    private static FareRepository repository;
    @BeforeAll
    static void setUp() {
        repository = new FareRepositoryImpl(HibernateUtil.getSessionFactory("hibernate-local.cfg.xml"));
    }
    @Test
    void save() {
        Fare fare = Fare.builder()
                .baseFare(2.0)
                .pricePerKm(0.5)
                .pricePerMinute(0.25)
                .build();
        repository.save(fare);
    }
}
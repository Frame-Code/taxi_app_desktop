package domain.repository.impl;

import domain.entities.Car;
import domain.repository.interfaces.CarRepository;
import shared.utils.HibernateUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Daniel Mora Cantillo
 * */
class CarRepositoryImplTest {
    private static CarRepository repository;

    @BeforeAll
    static void setUp() {
        repository = new CarRepositoryImpl(HibernateUtil.getSessionFactory("hibernate-test.cfg.xml"));
    }

    @Test
    void save() {
        Car car = Car.builder()
                .chassisNumber("fdfdf")
                .year("2021")
                .licensePlate("fdsdsd")
                .brand("fsdsd")
                .model("kia")
                .build();
        Car saved = repository.save(car);
        assertEquals(car, saved);
    }

}
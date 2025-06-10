package DOMAIN.REPOSITORY.IMPL;

import DOMAIN.ENTITIES.Province;
import DOMAIN.REPOSITORY.INTERFACES.ProvinceRepository;
import SHARED.UTILS.HibernateUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProvinceRepositoryImplTest {
    private static ProvinceRepository repository;

    @BeforeAll
    static void setUp() {
        repository = new ProvinceRepositoryImpl(HibernateUtil.getSessionFactory("hibernate-test.cfg.xml"));
    }

    @Test
    void findByName() {
        Province province = Province.builder()
                .name("Guayas")
                .build();

        repository.save(province);

        var provinceOpt = repository.findByName("Guayas");
        assertEquals(provinceOpt.get().getName(), province.getName());

    }

    @Test
    void save() {
        Province province = Province.builder()
                .name("Guayas")
                .build();

        var provinceSaved = repository.save(province);
        assertEquals(province, provinceSaved);
    }

}
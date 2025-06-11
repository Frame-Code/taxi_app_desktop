package DOMAIN.REPOSITORY.IMPL;

import DOMAIN.ENTITIES.City;
import DOMAIN.ENTITIES.Province;
import DOMAIN.REPOSITORY.INTERFACES.CityRepository;
import DOMAIN.REPOSITORY.INTERFACES.ProvinceRepository;
import SHARED.UTILS.HibernateUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Daniel Mora Cantillo
 * */
class CityRepositoryImplTest {
    private static CityRepository cityRepository;
    private static ProvinceRepository provinceRepository;

    @BeforeAll
    static void setUp() {
        provinceRepository = new ProvinceRepositoryImpl(HibernateUtil.getSessionFactory("hibernate-test.cfg.xml"));
        cityRepository = new CityRepositoryImpl(HibernateUtil.getSessionFactory("hibernate-test.cfg.xml"), provinceRepository);

    }

    @Test
    void findCitiesByProvinceName() {
        City city1 = City.builder()
                .name("Guayaquil")
                .build();
        City city2 = City.builder()
                .name("Duran")
                .build();

        Province province = Province.builder()
                .name("Guayas")
                .build();
        province.setCities(Set.of(city1, city2));
        provinceRepository.save(province);
        List<City> cities = cityRepository.findCitiesByProvinceName("guayas");
        assertNotNull(cities);
        assert !cities.isEmpty();
    }

    @Test
    void save() {
        City city = City.builder()
                .name("Guayaquil")
                .build();
        City citySaved = cityRepository.save(city);
        assertEquals(citySaved, city);
    }
}
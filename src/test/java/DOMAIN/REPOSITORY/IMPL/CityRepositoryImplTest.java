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
                .cities(Set.of(city1, city2))
                .build();
        Province province1 = provinceRepository.save(province);
        System.out.println(province1.toString());
        List<City> cities = cityRepository.findCitiesByProvinceName("guayas");
        assertNotNull(cities);
        System.out.println(cities.isEmpty());
        assert cities.isEmpty();
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
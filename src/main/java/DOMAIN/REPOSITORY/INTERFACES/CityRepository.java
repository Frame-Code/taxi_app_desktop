package DOMAIN.REPOSITORY.INTERFACES;

import DOMAIN.ENTITIES.City;
import DOMAIN.REPOSITORY.DAO.INTERFACES.IBaseDao;

import java.util.List;

/**
 * @author Daniel Mora Cantillo
 * */
public interface CityRepository extends IBaseDao<City> {
    List<City> findCitiesByProvinceName(String provinceName);
}

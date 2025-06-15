package domain.repository.INTERFACES;

import domain.entities.City;
import domain.repository.DAO.INTERFACES.IBaseDao;

import java.util.List;

/**
 * @author Daniel Mora Cantillo
 * */
public interface CityRepository extends IBaseDao<City> {
    List<City> findCitiesByProvinceName(String provinceName);
}

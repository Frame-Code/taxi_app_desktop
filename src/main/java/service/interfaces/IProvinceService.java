package service.interfaces;

import domain.entities.Province;

import java.util.List;
import java.util.Optional;

/**
 * @author Daniel Mora Cantillo
 * */
public interface IProvinceService {
    List<Province> getProvinces();
    Province save(Province province);
    Province save(String provinceName);
    Optional<Province> findByName(String name);
    Optional<Province> addCity(String provinceName, String cityName);
}

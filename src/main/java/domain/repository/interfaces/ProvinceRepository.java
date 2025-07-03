package domain.repository.interfaces;

import domain.entities.Province;
import domain.repository.dao.interfaces.IBaseDao;

import java.util.Optional;

/**
 * @author Daniel Mora Cantillo
 * */
public interface ProvinceRepository extends IBaseDao<Province> {
    Optional<Province> findByName(String name);
}

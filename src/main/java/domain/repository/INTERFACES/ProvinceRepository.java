package domain.repository.INTERFACES;

import domain.entities.Province;
import domain.repository.DAO.INTERFACES.IBaseDao;

import java.util.Optional;

/**
 * @author Daniel Mora Cantillo
 * */
public interface ProvinceRepository extends IBaseDao<Province> {
    Optional<Province> findByName(String name);
}

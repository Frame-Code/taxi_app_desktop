package DOMAIN.REPOSITORY.INTERFACES;

import DOMAIN.ENTITIES.Province;
import DOMAIN.REPOSITORY.DAO.INTERFACES.IBaseDao;

import java.util.Optional;

public interface ProvinceRepository extends IBaseDao<Province> {
    Optional<Province> findByName(String name);
}

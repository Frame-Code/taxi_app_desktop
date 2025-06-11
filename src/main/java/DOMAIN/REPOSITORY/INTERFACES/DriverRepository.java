package DOMAIN.REPOSITORY.INTERFACES;

import DOMAIN.ENTITIES.Driver;
import DOMAIN.REPOSITORY.DAO.INTERFACES.IBaseDao;

import java.util.Optional;

public interface DriverRepository extends IBaseDao<Driver> {
    Optional<Driver> findByLicensePlate(String licensePlate);
}

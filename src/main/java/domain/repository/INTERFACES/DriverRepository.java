package domain.repository.INTERFACES;

import domain.entities.Driver;
import domain.repository.DAO.INTERFACES.IBaseDao;

import java.util.Optional;

public interface DriverRepository extends IBaseDao<Driver> {
    Optional<Driver> findByLicensePlate(String licensePlate);
}

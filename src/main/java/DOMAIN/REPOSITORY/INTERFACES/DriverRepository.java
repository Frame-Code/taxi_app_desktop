package domain.repository.interfaces;

import domain.entities.Driver;
import domain.repository.dao.interfaces.IBaseDao;

import java.util.Optional;

public interface DriverRepository extends IBaseDao<Driver> {
    Optional<Driver> findByLicensePlate(String licensePlate);
}

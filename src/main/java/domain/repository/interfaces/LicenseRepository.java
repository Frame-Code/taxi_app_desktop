package domain.repository.interfaces;

import domain.entities.License;
import domain.repository.dao.interfaces.IBaseDao;
import java.util.Optional;

/**
 *
 * @author Alex
 */
public interface LicenseRepository  extends IBaseDao<License>{
     Optional<License> findByDriverLicense(String driverLicense);
}

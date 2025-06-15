package service.impl.auth;

import domain.entities.Driver;
import domain.repository.INTERFACES.ClientRepository;
import domain.repository.INTERFACES.DriverRepository;
import service.interfaces.auth.ISignUpDriverService;
import service.interfaces.auth.SignUpService;
import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
public class SignUpDriverServiceImpl extends SignUpService implements ISignUpDriverService {
    private final DriverRepository driverRepository;

    public SignUpDriverServiceImpl(ClientRepository repository, DriverRepository driverRepository) {
        super(repository);
        this.driverRepository = driverRepository;
    }

    @Override
    public boolean signUp(Driver driver) {
        if (isUsedEmail(driver.getUser().getEmail()) || isUsedPhone(driver.getUser().getPhone())) {
            log.warn("Can't sign up user, the email or phone is already in use");
            return false;
        }

        if (isUsedDriverLicense(driver.getLicense().getDriverLicense())) {
            log.warn("Can't sign up user, the driver license is already in use");
            return false;
        }

        driverRepository.save(driver);
        log.info("User signed up successfully");
        return true;
    }

    public boolean isUsedDriverLicense(String driverLicense) {
        return driverRepository.findByLicensePlate(driverLicense).isPresent();
    }
}

package service.interfaces.auth_module;

import domain.entities.Car;
import domain.entities.Driver;
import domain.repository.interfaces.DriverRepository;

public interface ISignUpDriverService {
    boolean signUp(Driver driver, Car car, DriverRepository driverRepository);
}

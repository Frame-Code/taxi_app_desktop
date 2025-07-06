package service.interfaces;

import domain.entities.Car;
import domain.entities.Driver;

public interface ISignUpDriverService {
    boolean signUp(Driver driver, Car car);
}

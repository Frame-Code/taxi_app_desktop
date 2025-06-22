package service.interfaces.ride_module;

import domain.entities.Fare;

import java.util.Optional;

public interface IFareService {
    Fare save(Fare fare);
    Optional<Fare> findFare();
    Fare update(Fare fare);
}

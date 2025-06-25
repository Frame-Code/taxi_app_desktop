package service.impl.ride_service;

import domain.entities.Fare;
import domain.repository.interfaces.FareRepository;
import lombok.RequiredArgsConstructor;
import service.interfaces.ride_module.IFareService;

import java.util.Optional;

/**
 *
 * @author Daniel Mora Cantillo
 */
@RequiredArgsConstructor
public class FareServiceImpl implements IFareService {
    private final FareRepository repository;

    @Override
    public Fare save(Fare fare) {
        return repository.save(fare);
    }

    @Override
    public Optional<Fare> findFare() {
        return repository.findById(1L);
    }

    @Override
    public Fare update(Fare fare) {
        return repository.save(fare);
    }
}

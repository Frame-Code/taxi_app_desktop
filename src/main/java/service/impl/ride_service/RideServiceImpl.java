package service.impl.ride_service;

import domain.entities.Ride;
import domain.repository.interfaces.RideRepository;
import lombok.RequiredArgsConstructor;
import service.interfaces.ride_module.IRideService;

@RequiredArgsConstructor
public class RideServiceImpl implements IRideService {
    private final RideRepository repository;

    @Override
    public Ride save(Ride ride) {
        return repository.save(ride);
    }
}

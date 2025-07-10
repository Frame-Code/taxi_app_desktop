package service.impl.ride_service;

import domain.entities.Ride;
import domain.repository.interfaces.CabRepository;
import domain.repository.interfaces.RideRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import service.interfaces.ride_module.IRideCabService;
import shared.enums.STATUS_RIDE;
import shared.enums.STATUS_TAXI;

import java.util.Optional;

@CommonsLog
@RequiredArgsConstructor
public class RideCabServiceImpl implements IRideCabService {
    private final RideRepository repository;
    private final CabRepository cabRepository;

    @Override
    public Ride findByCab(Long id) throws InterruptedException {
        log.info("Cab: verifying if a new ride was assigned to me...");
        while (true) {
            var rideOpt = repository.findByCab(id);
            if (rideOpt.isPresent() && rideOpt.get().isInitialized()) {
                return rideOpt.get();
            }
            Thread.sleep(3000);
        }
    }


    @Override
    public boolean setReadyToStart(Long id) {
        return repository.findById(id)
                .map(ride -> {
                    ride.setStatus(STATUS_RIDE.READY_TO_START);
                    repository.update(ride);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public boolean setReadyToFinally(Long id) {
        return repository.findById(id)
                .map(ride -> {
                    ride.setStatus(STATUS_RIDE.READY_TO_FINALLY);
                    repository.update(ride);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public boolean setCabEnable(Long id) {
        return cabRepository.findById(id)
                .map(cab -> {
                    cab.setStatus(STATUS_TAXI.ENABLE);
                    cabRepository.update(cab);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public boolean isInProcess(Long id) throws InterruptedException {
        log.info("Cab: Starting verifier task for ride...");
        boolean isOriginConfirmed = false;
        while (true) {
            var rideOpt = repository.findById(id);
            if (rideOpt.isEmpty()) {
                break;
            }

            if (rideOpt.get().isInProcess()) {
                isOriginConfirmed = true;
                break;
            }

            Thread.sleep(2000);
        }
        return isOriginConfirmed;
    }

    @Override
    public boolean isOriginConfirm(Long id) throws InterruptedException {
        log.info("Cab: Starting verifier task for ride...");
        boolean isOriginConfirmed = false;
        while (true) {
            var rideOpt = repository.findById(id);
            if (rideOpt.isEmpty()) {
                break;
            }

            if (rideOpt.get().isOriginConfirmed()) {
                isOriginConfirmed = true;
                break;
            }

            Thread.sleep(2000);
        }
        return isOriginConfirmed;
    }

    @Override
    public boolean setCabDisable(Long id) {
        return cabRepository.findById(id)
                .map(cab -> {
                    cab.setStatus(STATUS_TAXI.DISABLE);
                    cabRepository.update(cab);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public boolean isEnded(Long id) throws InterruptedException {
        log.info("Cab: Starting verifier task for ride...");
        boolean isOriginConfirmed = false;
        while (true) {
            var rideOpt = repository.findById(id);
            if (rideOpt.isEmpty()) {
                break;
            }

            if (rideOpt.get().isEnded()) {
                isOriginConfirmed = true;
                break;
            }

            Thread.sleep(2000);
        }
        return isOriginConfirmed;
    }

}

package service.impl.ride_service;

import domain.repository.interfaces.FareRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import service.interfaces.ride_module.IRideCalculationsService;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author Daniel Mora Cantillo
 */
@CommonsLog
@RequiredArgsConstructor
public class IRideCalculationsServiceImpl implements IRideCalculationsService {
    private final FareRepository repository;

    @Override
    public double getPrice(double distance, double duration) {
        log.info("Calculating price for ride...");
        return Math.round(repository.findById(1L)
                .map(fare ->
                        (fare.getPricePerKm() * distance) / 1000
                        + (fare.getPricePerMinute() * getMinutes(duration))
                        + (fare.getBaseFare()))
                .orElse(0.0));
    }

    private double getMinutes(double seconds) {
        return (TimeUnit.SECONDS.toMinutes((long) seconds) -
                (TimeUnit.SECONDS.toHours((long) seconds)* 60));
    }
}

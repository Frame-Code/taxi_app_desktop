package service.impl.matching_module;

import domain.entities.Cab;
import domain.repository.INTERFACES.TaxiLiveAddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import service.interfaces.matching_module.IFindCabsService;
import shared.utils.GeolocationUtil;

import java.util.List;

@CommonsLog
@RequiredArgsConstructor
public class FindCabsServiceImpl implements IFindCabsService {
    private final double MAX_DISTANCE = 6500;
    private final double MIN_DISTANCE = 4000;
    private final TaxiLiveAddressRepository repository;

    @Override
    public List<Cab> findNearbyCabs(double latitude, double longitude) {
        double minDistance = MIN_DISTANCE;
        List<Cab> nearbyCabs = List.of();
        while (minDistance <= MAX_DISTANCE) {
            nearbyCabs = repository.findNearbyCabs(GeolocationUtil.coordinatesToWKT(longitude, latitude), minDistance);
            if(!nearbyCabs.isEmpty()) {
                log.info("Nearby cabs founded for the client with the following coordinates " + "lat: " + latitude + "long: " + longitude);
                log.info("Total cabs founded: " + nearbyCabs.size());
                break;
            }
            minDistance += 500;
        }

        if(nearbyCabs.isEmpty()) {
            log.warn("No nearby cabs founded for for the client with the following coordinates " + "lat: " + latitude + "long: " + longitude);
        }
        return nearbyCabs;
    }
}

package service.impl.matching_module;

import domain.entities.Address;
import domain.entities.Cab;
import domain.entities.Client;
import domain.entities.Payment;
import domain.entities.Ride;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import service.interfaces.ride_module.IRideService;
import service.interfaces.matching_module.IFindCabsService;
import service.interfaces.matching_module.IMatchService;
import shared.dto.CoordinatesRideDTO;
import shared.enums.STATUS_RIDE;
import shared.enums.STATUS_TAXI;
import shared.utils.GeolocationUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Daniel Mora Cantillo
 */
@CommonsLog
@RequiredArgsConstructor
public class MatchServiceImpl implements IMatchService {
    private final IFindCabsService findCabsService;
    private final IRideService rideService;

    @Override
    public List<Cab> findNearbyCabs(double latitude, double longitude) {
        return findCabsService.findNearbyCabs(latitude, longitude).stream()
                .filter(cab -> cab.getStatus().equals(STATUS_TAXI.ENABLE))
                .toList();
    }

    @Override
    public Optional<Cab> requestCab(CoordinatesRideDTO coordinatesRideDTO, List<Cab> cabs, Client client, Payment payment) {
        return cabs.stream().findFirst().map(cab -> {
            var ride = Ride.builder()
                    .client(client)
                    .cab(cab)
                    .startDate(LocalDateTime.now())
                    .startAddressReference(coordinatesRideDTO.originReference())
                    .endAddressReference(coordinatesRideDTO.destinyReference())
                    .startAddress(Address.builder()
                            .location(GeolocationUtil.createPoint(
                                    coordinatesRideDTO.originLatitude(),
                                    coordinatesRideDTO.originLongitude()
                            ))
                            .build())
                    .endAddress(Address.builder()
                            .location(GeolocationUtil.createPoint(
                                    coordinatesRideDTO.destinyLatitude(),
                                    coordinatesRideDTO.destinyLongitude()
                            ))
                            .build())
                    .status(STATUS_RIDE.INITIALIZED)
                    .payment(payment)
                    .build();
            rideService.save(ride);
            return cab;
        }).or(Optional::empty);
    }
}

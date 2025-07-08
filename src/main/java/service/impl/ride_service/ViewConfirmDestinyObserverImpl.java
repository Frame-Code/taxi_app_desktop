package service.impl.ride_service;

import lombok.RequiredArgsConstructor;
import service.interfaces.ride_module.IRideService;
import service.interfaces.ride_module.OpenViewObserver;
import shared.dto.CoordinatesRideDTO;
import ui.UIConfirmarLlegadaDestinoClient;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
public class ViewConfirmDestinyObserverImpl implements OpenViewObserver {
    private final IRideService rideService;

    @Override
    public void open(Long registerID) throws NoSuchElementException {
        rideService.findById(registerID).map(ride -> {
            new UIConfirmarLlegadaDestinoClient(
                    new CoordinatesRideDTO(
                            ride.getStartAddressReference(),
                            ride.getOriginLatitude(),
                            ride.getOriginLongitude(),
                            ride.getEndAddressReference(),
                            ride.getDestinyLatitude(),
                            ride.getDestinyLongitude()))
                    .setVisible(true);
            return null;
        }).orElseThrow();
    }
}

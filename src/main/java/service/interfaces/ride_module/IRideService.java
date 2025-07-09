package service.interfaces.ride_module;

import domain.entities.Ride;
import shared.dto.CoordinatesRideDTO;
import shared.dto.CoordinatesToMatchDTO;
import shared.dto.InfoRideDTO;

import java.io.IOException;
import java.util.Optional;

/**
 *
 * @author Daniel Mora Cantillo
 */
public interface IRideService {
    Ride save(Ride ride);
    Optional<Ride> findById(Long id);
    boolean setOriginConfirm(Long id);
    boolean isReadyToStart(Long id) throws InterruptedException;
    boolean isOriginConfirm(Long id) throws InterruptedException;
    boolean isEnded(Long id);
    Optional<Ride> findByCab(Long id_cab);
    Optional<InfoRideDTO> getInfoRide(CoordinatesRideDTO coordinatesRideDTO) throws IOException;

}

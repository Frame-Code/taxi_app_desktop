package service.interfaces.ride_module;

import domain.entities.Ride;
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
    Optional<InfoRideDTO> getInfoRide(CoordinatesToMatchDTO coordinatesToMatchDTO) throws IOException;

}

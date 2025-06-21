package service.interfaces.ride_module;

import shared.dto.CoordinatesToMatchDTO;
import shared.dto.InfoRideDTO;

import java.io.IOException;
import java.util.Optional;

public interface IRideCalculationsService {
    Optional<InfoRideDTO> getInfoRide(CoordinatesToMatchDTO coordinatesToMatchDTO) throws IOException;
    
}

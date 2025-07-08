package service.interfaces.matching_module;

import domain.entities.Cab;
import domain.entities.Client;
import domain.entities.Payment;
import shared.dto.CoordinatesRideDTO;
import shared.dto.CoordinatesToMatchDTO;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Daniel Mora Cantillo
 */
public interface IMatchService {
    List<Cab> findNearbyCabs(double latitude, double longitude);
    Optional<Cab> requestCab(CoordinatesRideDTO coordinatesRideDTO, List<Cab> cabs, Client client, Payment payment);
}

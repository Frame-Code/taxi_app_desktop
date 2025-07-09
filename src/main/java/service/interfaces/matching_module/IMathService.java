package service.interfaces.matching_module;

import domain.entities.Cab;
import domain.entities.Client;
import domain.entities.Payment;
import shared.dto.CoordinatesToMatchDTO;

import java.util.List;
import java.util.Optional;

public interface IMathService {
    List<Cab> findNearbyCabs(double latitude, double longitude);
    Optional<Cab> requestCab(CoordinatesToMatchDTO coordinatesToMatchDTO, List<Cab> cabs, Client client, Payment payment);
}

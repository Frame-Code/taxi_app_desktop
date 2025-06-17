package service.interfaces.matching;

import domain.entities.Cab;
import shared.dto.CoordinatesToMatchDTO;

import java.util.List;
import java.util.Optional;

public interface IMathService {
    List<Cab> findNearbyCabs(double latitude, double longitude);
    Optional<Cab> requestCab(CoordinatesToMatchDTO coordinatesToMatchDTO, List<Cab> cabs);
}

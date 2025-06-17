package service.impl.matching;

import domain.entities.Cab;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import service.interfaces.matching.IFindCabsService;
import service.interfaces.matching.IMathService;
import shared.dto.CoordinatesToMatchDTO;
import shared.enums.STATUS_TAXI;

import java.util.List;
import java.util.Optional;

@CommonsLog
@RequiredArgsConstructor
public class MatchServiceImpl implements IMathService {
    private final IFindCabsService findCabsService;

    @Override
    public List<Cab> findNearbyCabs(double latitude, double longitude) {
        return findCabsService.findNearbyCabs(latitude, longitude).stream()
                .filter(cab -> cab.getStatus().equals(STATUS_TAXI.ENABLE))
                .toList();
    }

    @Override
    public Optional<Cab> requestCab(CoordinatesToMatchDTO coordinatesToMatchDTO, List<Cab> cabs) {


        cabs.stream().findFirst;


        return Optional.empty();
    }
}

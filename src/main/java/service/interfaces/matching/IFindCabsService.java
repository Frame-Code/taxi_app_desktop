package service.interfaces.matching;

import domain.entities.Cab;

import java.util.List;

public interface IFindCabsService {
    List<Cab> findNearbyCabs(double latitude, double longitude);
}

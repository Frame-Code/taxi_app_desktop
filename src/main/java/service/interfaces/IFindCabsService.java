package service.interfaces;

import domain.entities.Cab;

import java.util.List;

public interface IFindCabsService {
    List<Cab> findNearbyCabs(double latitude, double longitude);
}

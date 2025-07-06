package service.interfaces.matching_module;

import domain.entities.Cab;

import java.util.List;

/**
 *
 * @author Daniel Mora Cantillo
 */
public interface IFindCabsService {
    List<Cab> findNearbyCabs(double latitude, double longitude);
}

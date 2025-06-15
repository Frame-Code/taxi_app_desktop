package service.impl.auth;

import domain.entities.Cab;
import service.interfaces.IFindCabsService;

import java.util.List;

public class FindCabsServiceImpl implements IFindCabsService {
    private final double MAX_DISTANCE = 6500;
    private final double MIN_DISTANCE = 4000;

    @Override
    public List<Cab> findNearbyCabs(double latitude, double longitude) {
        /*double minDistance = MIN_DISTANCE;
        List<Cab> nearbyCabs = List.of();
        while (minDistance <= MAX_DISTANCE) {
            nearbyCabs = findCabsService.findNearbyCabs(coordinatesDTO, minDistance);
            if(!nearbyCabs.isEmpty()) {
                log.info("Nearby cabs founded for the client " + coordinatesDTO.toString() + " with distance min: " + minDistance);
                log.info("Total cabs founded: " + nearbyCabs.size());
                break;
            }
            minDistance += 500;
        }

        if(nearbyCabs.isEmpty()) {
            log.warn("No nearby cabs founded for the client " + coordinatesDTO.toString());
        }


        return List.of();*/
        return List.of();
    }
}

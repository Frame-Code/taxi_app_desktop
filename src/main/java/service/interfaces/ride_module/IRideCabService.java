package service.interfaces.ride_module;

import domain.entities.Ride;


public interface IRideCabService {
    Ride findByCab(Long id) throws InterruptedException;
    boolean setReadyToStart(Long id);
    boolean setReadyToFinally(Long id);
    boolean setCabEnable(Long id);
    boolean isInProcess(Long id) throws InterruptedException;
    boolean isOriginConfirm(Long id) throws InterruptedException;
    boolean isEnded(Long id) throws InterruptedException;

}

package service.interfaces.ride_module;

import java.util.NoSuchElementException;

public interface OpenViewObserver {
    void open(Long registerID) throws NoSuchElementException;
}

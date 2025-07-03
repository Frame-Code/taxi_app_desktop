package domain.repository.interfaces;

import domain.entities.Cab;
import domain.entities.TaxiLiveAddress;
import domain.repository.dao.interfaces.IBaseDao;

import java.util.List;

public interface TaxiLiveAddressRepository extends IBaseDao<TaxiLiveAddress> {
    List<Cab> findNearbyCabs(String pointWKT, double meters_distance);
}

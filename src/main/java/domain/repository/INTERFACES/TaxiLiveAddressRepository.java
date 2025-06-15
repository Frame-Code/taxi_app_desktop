package domain.repository.INTERFACES;

import domain.entities.Cab;
import domain.entities.TaxiLiveAddress;
import domain.repository.DAO.INTERFACES.IBaseDao;

import java.util.List;

public interface TaxiLiveAddressRepository extends IBaseDao<TaxiLiveAddress> {
    List<Cab> findNearbyCabs(String pointWKT, int meters_distance);
}

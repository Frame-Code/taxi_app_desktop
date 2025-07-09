package domain.repository.interfaces;

import domain.entities.Ride;
import domain.repository.dao.interfaces.IBaseDao;

import java.util.Optional;

public interface RideRepository extends IBaseDao<Ride> {
    Optional<Ride> findByCab(Long id_cab);
}

package domain.repository.interfaces;

import domain.entities.Cab;
import domain.repository.dao.interfaces.IBaseDao;

import java.util.Optional;

public interface CabRepository extends IBaseDao<Cab> {
    Optional<Cab> findCabByDriverEmail(String email);
}

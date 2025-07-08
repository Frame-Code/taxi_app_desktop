package domain.repository.interfaces;

import domain.entities.Client;
import domain.repository.dao.interfaces.IBaseDao;

import java.util.Optional;

/**
 * @author Daniel Mora Cantillo
 * */
public interface ClientRepository extends IBaseDao<Client> {
    Optional<Client> findByEmail(String email);
    Optional<Client> findByPhone(String phone);
}

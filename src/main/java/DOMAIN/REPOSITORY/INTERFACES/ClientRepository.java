package DOMAIN.REPOSITORY.INTERFACES;

import DOMAIN.ENTITIES.Client;
import DOMAIN.REPOSITORY.DAO.INTERFACES.IBaseDao;

import java.util.Optional;

/**
 * @author Daniel Mora Cantillo
 * */
public interface ClientRepository extends IBaseDao<Client> {
    Optional<Client> findByEmail(String email);
    Optional<Client> findByPhone(String phone);
}

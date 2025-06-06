package DOMAIN.REPOSITORY.INTERFACES;

import DOMAIN.ENTITIES.Client;
import DOMAIN.REPOSITORY.DAO.INTERFACES.IBaseDao;

import java.util.Optional;

public interface ClientRepository extends IBaseDao<Client> {
    Optional<Client> findByEmail(String email);
}

package service.interfaces.auth_module;

import domain.entities.Cab;
import domain.entities.Client;

import java.util.Optional;

public interface IUserGeneralService {
    Optional<Client> findClientByEmail(String email);
    Optional<Cab> findCabByDriverEmail(String email);
}

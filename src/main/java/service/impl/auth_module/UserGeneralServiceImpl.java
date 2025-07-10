package service.impl.auth_module;

import domain.entities.Cab;
import domain.entities.Client;
import domain.repository.interfaces.CabRepository;
import domain.repository.interfaces.ClientRepository;
import lombok.RequiredArgsConstructor;
import service.interfaces.auth_module.IUserGeneralService;

import java.util.Optional;

@RequiredArgsConstructor
public class UserGeneralServiceImpl implements IUserGeneralService {
    private final ClientRepository clientRepository;
    private final CabRepository cabRepository;

    @Override
    public Optional<Client> findClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    @Override
    public Optional<Cab> findCabByDriverEmail(String email) {
        return cabRepository.findCabByDriverEmail(email);
    }
}

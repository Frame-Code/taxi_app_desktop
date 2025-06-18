package service.interfaces.auth_module;

import domain.repository.interfaces.ClientRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class SignUpService {
    private final ClientRepository repository;

    public boolean isUsedEmail(String email) {
        return repository.findByEmail(email).isPresent();
    }

    public boolean isUsedPhone(String phone) {
        return repository.findByPhone(phone).isPresent();
    }
}

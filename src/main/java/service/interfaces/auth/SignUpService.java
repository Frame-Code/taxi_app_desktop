package service.interfaces.auth;

import domain.repository.INTERFACES.ClientRepository;
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

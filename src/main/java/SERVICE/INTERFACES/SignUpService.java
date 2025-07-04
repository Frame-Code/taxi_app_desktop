package service.interfaces;

import domain.repository.interfaces.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class SignUpService {
    protected final UserRepository repository;

    public boolean isUsedEmail(String email) {
        return repository.findByEmail(email).isPresent();
    }

    public boolean isUsedPhone(String phone) {
        return repository.findByPhone(phone).isPresent();
    }
}

package service.interfaces;

import DOMAIN.REPOSITORY.INTERFACES.UserRepository;
import lombok.Getter;

@Getter
public abstract class SignUpService {
    
    protected final UserRepository userRepository; // Ahora se usa UserRepository para validaciones de usuario

    // Constructor para inyección de UserRepository
    public SignUpService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isUsedEmail(String email) {
        return userRepository.findByEmail(email).isPresent(); // Usa UserRepository
    }

    public boolean isUsedPhone(String phone) {
        return userRepository.findByPhone(phone).isPresent(); // Usa UserRepository
    }
}

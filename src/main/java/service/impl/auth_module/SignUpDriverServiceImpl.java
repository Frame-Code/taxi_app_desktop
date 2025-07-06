package service.impl.auth_module;

import domain.entities.Cab;
import domain.entities.Car;
import domain.entities.Driver;
import domain.entities.Role;
import domain.entities.UserEntity;
import domain.repository.interfaces.CabRepository;
import domain.repository.interfaces.CarRepository;
import domain.repository.interfaces.DriverRepository;
import domain.repository.interfaces.RoleRepository;
import domain.repository.interfaces.UserRepository;
import jakarta.transaction.Transactional;

import java.util.Optional;

import lombok.extern.apachecommons.CommonsLog;
import service.interfaces.auth_module.ISignUpDriverService;

import service.interfaces.auth_module.SignUpService;
import shared.enums.ROLE_NAME;
import shared.enums.STATUS_TAXI;
import shared.utils.PasswordUtil;

@CommonsLog
public class SignUpDriverServiceImpl extends SignUpService implements ISignUpDriverService {
    private final RoleRepository roleRepository;
    private final CabRepository cabRepository;

    public SignUpDriverServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            CabRepository cabRepository) {
        super(userRepository);
        this.roleRepository = roleRepository;
        this.cabRepository = cabRepository;
    }

    @Override
    @Transactional
    public boolean signUp(Driver driver, Car car, DriverRepository driverRepository) {
        if(isUsedEmail(driver.getUserEntity().getEmail()) || isUsedPhone(driver.getUserEntity().getPhone())) {
            log.info("Email or phone is already in use");
            return false;
        }

        if (driver == null || driver.getUserEntity() == null || driver.getLicense() == null || car == null) {
            log.warn("Cannot sign up userEntity: Driver, UserEntity, License, or Vehicle entities are null.");
            throw new IllegalArgumentException("Datos de conductor, usuario, licencia o vehículo incompletos.");
        }

        UserEntity userEntity = driver.getUserEntity();

        if (userEntity.getPasswordHash() == null || userEntity.getPasswordHash().isEmpty()) {
            log.warn("Cannot sign up userEntity: Password is empty. Hashing skipped.");
            throw new IllegalArgumentException("La contraseña no puede estar vacía.");
        }
        String plainPassword = userEntity.getPasswordHash();
        userEntity.setPasswordHash(PasswordUtil.hashPassword(plainPassword));


        Optional<Role> driverRole = roleRepository.findByName(ROLE_NAME.CAB);
        if (driverRole.isEmpty()) {
            log.error("Role 'DRIVER' not found in the system. Please ensure it's configured.");
            throw new IllegalStateException("El rol '" + ROLE_NAME.CAB.name() + "' no está configurado en el sistema.");
        }
        userEntity.setRole(driverRole.get());

        cabRepository.save(Cab.builder()
                .vehicle(car)
                .driver(driver)
                .status(STATUS_TAXI.DISABLE)
                .build());

        log.info("Driver signed up successfully for userEntity: " + userEntity.getEmail());
        return true;
    }
}
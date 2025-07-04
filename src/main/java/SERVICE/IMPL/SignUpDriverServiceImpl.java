package service.impl;

import domain.entities.Driver;
import domain.entities.License;
import domain.entities.Role;
import domain.entities.User;
import domain.entities.Vehicle;

import domain.repository.interfaces.DriverRepository;
import DOMAIN.REPOSITORY.INTERFACES.LicenseRepository;
import DOMAIN.REPOSITORY.INTERFACES.UserRepository;
import DOMAIN.REPOSITORY.INTERFACES.VehicleRepository;
import DOMAIN.REPOSITORY.INTERFACES.RolRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;

import lombok.extern.apachecommons.CommonsLog;
import service.interfaces.ISignUpDriverService;
import service.interfaces.SignUpService; 

import shared.enums.ROLE_NAME;
import shared.utils.PasswordUtils;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@CommonsLog
public class SignUpDriverServiceImpl extends SignUpService implements ISignUpDriverService {

    private final DriverRepository driverRepository;
    private final LicenseRepository licenseRepository;
    private final VehicleRepository vehicleRepository;
    private final RolRepository roleRepository;
    private final UserRepository userRepository;

    // Constructor que inyecta todas las dependencias necesarias
    public SignUpDriverServiceImpl(
            UserRepository userRepository,
            DriverRepository driverRepository,
            LicenseRepository licenseRepository,
            VehicleRepository vehicleRepository,
            RolRepository roleRepository) {
        super(userRepository);
        this.userRepository = userRepository;
        this.driverRepository = driverRepository;
        this.licenseRepository = licenseRepository;
        this.vehicleRepository = vehicleRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional // Asegura la atomicidad de la operación
    public boolean signUp(Driver driver) {
        // Validación de nulidad básica (es recomendable mantenerla)
        if (driver == null || driver.getUser() == null || driver.getLicense() == null || driver.getVehicle() == null) {
            log.warn("Cannot sign up user: Driver, User, License, or Vehicle entities are null.");
            throw new IllegalArgumentException("Datos de conductor, usuario, licencia o vehículo incompletos.");
        }

        User user = (User) driver.getUser();
        License license = (License) driver.getLicense();
        Vehicle vehicle = (Vehicle) driver.getVehicle();
        
        if (user.getPasswordHash() == null || user.getPasswordHash().isEmpty()) {
            log.warn("Cannot sign up user: Password is empty. Hashing skipped.");
            throw new IllegalArgumentException("La contraseña no puede estar vacía.");
        }
        String plainPassword = (String) user.getPasswordHash();
        user.setPasswordHash(PasswordUtils.hashPassword(plainPassword));


        // **Asignación del Rol "DRIVER" (Se mantiene como lógica de negocio esencial)**
        Optional<Role> driverRole = roleRepository.findByName(ROLE_NAME.DRIVER.name());
        if (driverRole.isEmpty()) {
            log.error("Role 'DRIVER' not found in the system. Please ensure it's configured.");
            throw new IllegalStateException("El rol '" + ROLE_NAME.DRIVER.name() + "' no está configurado en el sistema.");
        }
        user.setRole(driverRole.get());

         // Persistencia de las Entidades
        User savedUser = userRepository.save(user); // Captura el User guardado
        License savedLicense = licenseRepository.save(license); // Captura la License guardada
        Vehicle savedVehicle = vehicleRepository.save(vehicle); // Captura el Vehicle guardado

        driverRepository.save(driver); 

        log.info("Driver signed up successfully for user: " + user.getEmail());
        return true;
    }
}
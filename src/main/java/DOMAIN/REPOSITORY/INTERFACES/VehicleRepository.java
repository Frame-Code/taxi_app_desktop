/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DOMAIN.REPOSITORY.INTERFACES;

import domain.entities.Vehicle; //
import domain.repository.dao.interfaces.IBaseDao; //
import java.util.Optional;

/**
 *
 * @author Alex
 */
public interface VehicleRepository {
    Optional<Vehicle> findByPlate(String plate); 

    
}

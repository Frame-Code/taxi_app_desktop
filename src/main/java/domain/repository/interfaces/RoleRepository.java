/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domain.repository.interfaces;

import domain.entities.Role;
import domain.repository.dao.interfaces.IBaseDao;
import shared.enums.ROLE_NAME;

import java.util.Optional;
/**
 *
 * @author Alex
 */
public interface RoleRepository extends IBaseDao<Role> {
    Optional<Role> findByName(ROLE_NAME name);
}

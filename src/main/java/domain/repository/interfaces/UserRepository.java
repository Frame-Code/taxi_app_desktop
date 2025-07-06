package domain.repository.interfaces;

import domain.entities.UserEntity;
import domain.repository.dao.interfaces.IBaseDao;
import java.util.Optional;

/**
 *
 * @author Alex
 */
public interface UserRepository extends IBaseDao<UserEntity>{
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByPhone(String phone);
}

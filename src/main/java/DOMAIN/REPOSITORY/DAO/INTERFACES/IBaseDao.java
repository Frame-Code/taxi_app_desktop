package DOMAIN.REPOSITORY.DAO.INTERFACES;

import java.util.List;
import java.util.Optional;

public interface IBaseDao <T> {
    T save(T client);
    Optional<T> findById(Long id);
    List<T> findAll();
    void deleteById(Long id);
    T update(T client);
}

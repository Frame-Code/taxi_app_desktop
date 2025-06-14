package DOMAIN.REPOSITORY.DAO.INTERFACES;

import java.util.List;
import java.util.Optional;

/**
 * @author Daniel Mora Cantillo
 * */
public interface IBaseDao <T> {
    T save(T object);
    Optional<T> findById(Long id);
    List<T> findAll();
    void deleteById(Long id);
    T update(T object);
}

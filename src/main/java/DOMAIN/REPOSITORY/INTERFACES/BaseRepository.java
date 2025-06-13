package DOMAIN.REPOSITORY.INTERFACES;

import SHARED.UTILS.HibernateUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;

/**
 * @author Daniel Mora Cantillo
 * */
@Getter
@RequiredArgsConstructor
public abstract class BaseRepository {
    private final SessionFactory sessionFactory;

    public BaseRepository() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }
}

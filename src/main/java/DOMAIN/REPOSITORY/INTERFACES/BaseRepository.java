package domain.repository.interfaces;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import shared.utils.HibernateUtil;

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

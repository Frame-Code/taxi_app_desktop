package domain.repository.interfaces;

import shared.utils.HibernateUtil;
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

    protected String formatName(String name) {
        return name.toLowerCase().trim();
    }
}

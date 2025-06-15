package shared.utils;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Daniel Mora Cantillo
 * */
public class HibernateUtil {
    private static SessionFactory buildSessionFactory(String configFile) {
        try {
            return new Configuration().configure(configFile).buildSessionFactory();
        } catch (HibernateException ex) {
            throw new RuntimeException("Error creating session factory " + ex);
        }
    }

    public static void shutdown() {
        getSessionFactory().close();
    }

    public static SessionFactory getSessionFactory(String configFile) {
        return buildSessionFactory(configFile);
    }

    public static SessionFactory getSessionFactory() {
        return buildSessionFactory("hibernate.cfg.xml");
    }
}

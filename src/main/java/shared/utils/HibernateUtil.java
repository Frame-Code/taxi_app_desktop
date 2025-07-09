package shared.utils;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.BootstrapServiceRegistry;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.event.spi.PostUpdateEventListener;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author Daniel Mora Cantillo
 * */
public class HibernateUtil {
    private static Configuration configuration;
    private static synchronized SessionFactory buildSessionFactory(String configFile) {
        try {
            if(configuration == null) {
                configuration = new Configuration()
                        .configure(configFile);
            }
            return configuration.buildSessionFactory();
        } catch (HibernateException | NullPointerException ex) {
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

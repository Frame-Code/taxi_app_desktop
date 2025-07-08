package shared.utils;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.registry.BootstrapServiceRegistry;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.event.spi.PostUpdateEventListener;

import java.util.List;

/**
 * @author Daniel Mora Cantillo
 * */
public class HibernateUtil {
    private static Metadata metadata;
    private static synchronized SessionFactory buildSessionFactory(String configFile) {
        try {
            if(metadata == null) {
                BootstrapServiceRegistry bootstrapServiceRegistry = new BootstrapServiceRegistryBuilder()
                        .applyIntegrator(new IntegratorHibernate())


            }
            return metadata.buildSessionFactory();
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

package domain.repository.IMPL;

import domain.entities.Cab;
import domain.entities.Province;
import domain.entities.TaxiLiveAddress;
import domain.repository.INTERFACES.BaseRepository;
import domain.repository.INTERFACES.TaxiLiveAddressRepository;
import lombok.extern.apachecommons.CommonsLog;
import org.hibernate.HibernateException;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@CommonsLog
public class TaxiLiveAddressRepositoryImpl extends BaseRepository implements TaxiLiveAddressRepository {
    public TaxiLiveAddressRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Cab> findNearbyCabs(String pointWTK, int meters_distance) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = super.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            String sqlQuery = """
                    SELECT * FROM address\s
                    INNER JOIN taxi_live_address ON address.id = taxi_live_address.id\s
                    WHERE ST_Distance_Sphere(address.location, ST_GeomFromText(:point, 4326)) <= :meters
                   \s""";
            NativeQuery<TaxiLiveAddress> query = session.createNativeQuery(sqlQuery, TaxiLiveAddress.class);
            query.setParameter("meters", meters_distance);
            query.setParameter("point", pointWTK);
            List<TaxiLiveAddress> nearbyCabsAddress = query.getResultList();
            transaction.commit();
            log.info("Nearby cabs list" + nearbyCabsAddress.isEmpty());
            log.info("Cabs founded successfully");
            return nearbyCabsAddress.stream().map(TaxiLiveAddress::getCab).toList();
        } catch (NullPointerException | NoSuchElementException e) {
            if (transaction != null) {
                log.error("Error finding cabs: " + e.getMessage());
                transaction.rollback();
            }
        } finally {
            if (transaction != null) {
                session.close();
                log.info("Hibernate session closed");
            }
        }
        return List.of();
    }

    @Override
    public TaxiLiveAddress save(TaxiLiveAddress taxiLiveAddress) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = super.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(taxiLiveAddress);
            transaction.commit();
            log.info("province saved successfully");
        } catch (HibernateException | NullPointerException e) {
            if (transaction != null) {
                log.error("Error saving province: " + e.getMessage());
                transaction.rollback();
            }
        } finally {
            if (transaction != null) {
                session.close();
                log.info("Hibernate session closed");
            }
        }
        return taxiLiveAddress;
    }

    @Override
    public Optional<TaxiLiveAddress> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<TaxiLiveAddress> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public TaxiLiveAddress update(TaxiLiveAddress object) {
        return null;
    }
}

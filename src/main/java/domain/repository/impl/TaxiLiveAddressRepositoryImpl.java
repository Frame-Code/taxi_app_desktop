package domain.repository.impl;

import domain.entities.Cab;
import domain.entities.TaxiLiveAddress;
import domain.repository.interfaces.BaseRepository;
import domain.repository.interfaces.TaxiLiveAddressRepository;
import lombok.extern.apachecommons.CommonsLog;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@CommonsLog
public class TaxiLiveAddressRepositoryImpl extends BaseRepository implements TaxiLiveAddressRepository {
    public TaxiLiveAddressRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Cab> findNearbyCabs(String pointWTK, double meters_distance) {
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
            log.info("Taxi_live_address saved successfully");
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
        throw new UnsupportedOperationException("This method has not implemented");
    }

    @Override
    public List<TaxiLiveAddress> findAll() {
        throw new UnsupportedOperationException("This method has not implemented");
    }

    @Override
    public void deleteById(Long id) {
        throw new UnsupportedOperationException("This method has not implemented");
    }

    @Override
    public TaxiLiveAddress update(TaxiLiveAddress object) {
        throw new UnsupportedOperationException("This method has not implemented");
    }
}

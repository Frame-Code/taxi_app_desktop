package DOMAIN.REPOSITORY.IMPL;

import DOMAIN.ENTITIES.Driver;
import DOMAIN.REPOSITORY.INTERFACES.BaseRepository;
import DOMAIN.REPOSITORY.INTERFACES.DriverRepository;
import jakarta.persistence.NoResultException;
import lombok.extern.apachecommons.CommonsLog;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

@CommonsLog
public class DriverRepositoryImpl extends BaseRepository implements DriverRepository {
    @Override
    public Driver save(Driver driver) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = super.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(driver);
            transaction.commit();
            log.info("Driver saved successfully");
        } catch (HibernateException | NullPointerException e) {
            if(transaction != null) {
                log.error("Error saving driver: " + e.getMessage());
                transaction.rollback();
            }
        } finally {
            if(transaction != null) {
                session.close();
                log.info("Hibernate session closed");
            }
        }
        return driver;
    }

    @Override
    public Optional<Driver> findById(Long id) {
        var driver = super.getSessionFactory().openSession().find(Driver.class, id);
        super.getSessionFactory().getCurrentSession().close();
        return Optional.ofNullable(driver);
    }

    @Override
    public List<Driver> findAll() {
        var drivers = super.getSessionFactory().openSession().createQuery("FROM Driver", Driver.class).list();
        super.getSessionFactory().getCurrentSession().close();
        return drivers;
    }

    @Override
    public void deleteById(Long id) {
        var driverOpt = findById(id);
        if(driverOpt.isEmpty()) {
            log.error("Driver not found");
            return;
        }

        Transaction transaction = null;
        Session session = null;
        try {
            session = super.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.remove(driverOpt.get().getId());
            transaction.commit();
            log.info("Driver deleted successfully");
        } catch (HibernateException | NullPointerException ex) {
            if(transaction != null) {
                log.error("Error deleting driver: " + ex.getMessage());
                transaction.rollback();
            }
        } finally {
            if(transaction != null) {
                session.close();
                log.info("Hibernate session closed");
            }
        }
    }

    @Override
    public Driver update(Driver driver) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = super.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.merge(driver);
            transaction.commit();
            log.info("Driver saved successfully");
        } catch (HibernateException | NullPointerException ex) {
            if(transaction != null) {
                log.error("Error saving driver: " + ex.getMessage());
                transaction.rollback();
            }
        } finally {
            if(transaction != null) {
                session.close();
                log.info("Hibernate session closed");
            }
        }
        return driver;
    }

    @Override
    public Optional<Driver> findByLicensePlate(String licensePlate) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = super.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query<Driver> query = session.createQuery("SELECT d FROM Driver d WHERE d.user.license.driverLicense = :driverLicense", Driver.class);
            query.setParameter("driverLicense", licensePlate);
            Driver client = query.getSingleResult();
            transaction.commit();
            return Optional.of(client);
        } catch (HibernateException | NullPointerException | NoResultException e) {
            if(transaction != null) {
                log.error("Error finding driver: " + e.getMessage());
                transaction.rollback();
            }
        } finally {
            if(transaction != null) {
                session.close();
                log.info("Hibernate session closed");
            }
        }
        return Optional.empty();
    }
}

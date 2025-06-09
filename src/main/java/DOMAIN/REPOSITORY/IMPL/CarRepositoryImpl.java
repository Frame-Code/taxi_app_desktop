package DOMAIN.REPOSITORY.IMPL;

import DOMAIN.ENTITIES.Car;
import DOMAIN.ENTITIES.Client;
import DOMAIN.REPOSITORY.INTERFACES.CarRepository;
import lombok.AllArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

@CommonsLog
@AllArgsConstructor
public class CarRepositoryImpl extends BaseRepository implements CarRepository {

    public CarRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Car save(Car car) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = super.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(car);
            transaction.commit();
            log.info("Car saved successfully");
        } catch (HibernateException | NullPointerException e) {
            if(transaction != null) {
                log.error("Error saving car: " + e.getMessage());
                transaction.rollback();
            }
        } finally {
            if(transaction != null) {
                session.close();
                log.info("Hibernate session closed");
            }
        }
        return car;

    }

    @Override
    public Optional<Car> findById(Long id) {
        var car = super.getSessionFactory().openSession().find(Car.class, id);
        super.getSessionFactory().getCurrentSession().close();
        return Optional.of(car);
    }

    @Override
    public List<Car> findAll() {
        var cars = super.getSessionFactory().openSession().createQuery("FROM Car", Car.class).list();
        super.getSessionFactory().getCurrentSession().close();
        return cars;
    }

    @Override
    public void deleteById(Long id) {
        var carOpt = findById(id);
        if(carOpt.isEmpty()) {
            log.error("Car not found");
            return;
        }

        Transaction transaction = null;
        Session session = null;
        try {
            session = super.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.remove(carOpt.get().getId());
            transaction.commit();
            log.info("Car deleted successfully");
        } catch (HibernateException | NullPointerException ex) {
            if(transaction != null) {
                log.error("Error deleting car: " + ex.getMessage());
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
    public Car update(Car client) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = super.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.merge(client);
            transaction.commit();
            log.info("Client saved successfully");
        } catch (HibernateException | NullPointerException ex) {
            if(transaction != null) {
                log.error("Error saving client: " + ex.getMessage());
                transaction.rollback();
            }
        } finally {
            if(transaction != null) {
                session.close();
                log.info("Hibernate session closed");
            }
        }
        return client;
    }
}

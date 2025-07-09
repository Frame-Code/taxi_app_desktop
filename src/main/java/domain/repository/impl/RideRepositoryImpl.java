package domain.repository.impl;

import domain.entities.Ride;
import domain.repository.interfaces.BaseRepository;
import domain.repository.interfaces.CabRepository;
import domain.repository.interfaces.RideRepository;
import jakarta.persistence.NoResultException;
import lombok.extern.apachecommons.CommonsLog;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

@CommonsLog
public class RideRepositoryImpl extends BaseRepository implements RideRepository {
    private final CabRepository cabRepository;

    public RideRepositoryImpl(SessionFactory sessionFactory, CabRepository cabRepository) {
        super(sessionFactory);
        this.cabRepository = cabRepository;
    }

    @Override
    public Ride save(Ride ride) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = super.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(ride);
            transaction.commit();
            log.info("Ride saved successfully");
        } catch (HibernateException | NullPointerException e) {
            if (transaction != null) {
                log.error("Error saving ride: " + e.getMessage());
                transaction.rollback();
            }
        } finally {
            if (transaction != null) {
                session.close();
                log.info("Hibernate session closed");
            }
        }
        return ride;
    }

    @Override
    public Optional<Ride> findById(Long id) {
        var ride = super.getSessionFactory().openSession().find(Ride.class, id);
        super.getSessionFactory().getCurrentSession().close();
        return Optional.ofNullable(ride);
    }

    @Override
    public List<Ride> findAll() {
        var ride = super.getSessionFactory().openSession().createQuery("FROM Ride", Ride.class).list();
        super.getSessionFactory().getCurrentSession().close();
        return ride;
    }

    @Override
    public void deleteById(Long id) {
        var rideOpt = findById(id);
        if (rideOpt.isEmpty()) {
            log.error("Ride not found");
            return;
        }

        Transaction transaction = null;
        Session session = null;
        try {
            session = super.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.remove(rideOpt.get().getId());
            transaction.commit();
            log.info("City deleted successfully");
        } catch (HibernateException | NullPointerException ex) {
            if (transaction != null) {
                log.error("Error deleting ride: " + ex.getMessage());
                transaction.rollback();
            }
        } finally {
            if (transaction != null) {
                session.close();
                log.info("Hibernate session closed");
            }
        }
    }

    @Override
    public Ride update(Ride ride) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = super.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.merge(ride);
            transaction.commit();
            log.info("Ride saved successfully");
        } catch (HibernateException | NullPointerException ex) {
            if(transaction != null) {
                log.error("Error saving ride: " + ex.getMessage());
                transaction.rollback();
            }
        } finally {
            if(transaction != null) {
                session.close();
                log.info("Hibernate session closed");
            }
        }
        return ride;
    }

    @Override
    public Optional<Ride> findByCab(Long id_cab) {
        return cabRepository.findById(id_cab)
                .map(cab -> {
                    log.info("Finding ride by cab...");
                    Transaction transaction = null;
                    Session session = null;
                    Ride ride = null;
                    try {
                        session = super.getSessionFactory().openSession();
                        transaction = session.beginTransaction();
                        Query<Ride> query = session.createQuery("SELECT r FROM Ride r WHERE r.cab.id = :id", Ride.class);
                        query.setParameter("id", id_cab);
                        ride = query.getSingleResult();
                        transaction.commit();
                        return ride;
                    } catch (HibernateException | NullPointerException | NoResultException e) {
                        if(transaction != null) {
                            log.error("Error finding ride: " + e.getMessage());
                            transaction.rollback();
                        }
                    } finally {
                        if(transaction != null) {
                            session.close();
                            log.info("Hibernate session closed");
                        }
                    }
                    log.info("Ride not founded");
                    return ride;
                })
                .or(Optional::empty);

    }
}

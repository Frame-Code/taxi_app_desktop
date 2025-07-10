package domain.repository.impl;

import domain.entities.Cab;
import domain.entities.Client;
import domain.repository.interfaces.BaseRepository;
import domain.repository.interfaces.CabRepository;
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
public class CabRepositoryImpl extends BaseRepository implements CabRepository {
    public CabRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Cab save(Cab cab) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = super.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(cab);
            transaction.commit();
            log.info("Cab saved successfully");
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
        return cab;
    }

    @Override
    public Optional<Cab> findById(Long id) {
        var cab = super.getSessionFactory().openSession().find(Cab.class, id);
        super.getSessionFactory().getCurrentSession().close();
        return Optional.ofNullable(cab);
    }

    @Override
    public List<Cab> findAll() {
        var cabs = super.getSessionFactory().openSession().createQuery("FROM Cab", Cab.class).list();
        super.getSessionFactory().getCurrentSession().close();
        return cabs;
    }

    @Override
    public void deleteById(Long id) {
        var cabOpt = findById(id);
        if(cabOpt.isEmpty()) {
            log.error("Cab not found");
            return;
        }

        Transaction transaction = null;
        Session session = null;
        try {
            session = super.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.remove(cabOpt.get().getId());
            transaction.commit();
            log.info("Cab deleted successfully");
        } catch (HibernateException | NullPointerException ex) {
            if(transaction != null) {
                log.error("Error deleting cab: " + ex.getMessage());
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
    public Cab update(Cab cab) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = super.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.merge(cab);
            transaction.commit();
            log.info("Cab updated successfully");
        } catch (HibernateException | NullPointerException ex) {
            if(transaction != null) {
                log.error("Error updating cab: " + ex.getMessage());
                transaction.rollback();
            }
        } finally {
            if(transaction != null) {
                session.close();
                log.info("Hibernate session closed");
            }
        }
        return cab;
    }

    @Override
    public Optional<Cab> findCabByDriverEmail(String email) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = super.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query<Cab> query = session.createQuery("SELECT c FROM Cab c WHERE c.driver.userEntity.email = :email", Cab.class);
            query.setParameter("email", email);
            Cab cab = query.getSingleResult();
            transaction.commit();
            return Optional.of(cab);
        } catch (HibernateException | NullPointerException | NoResultException e) {
            if(transaction != null) {
                log.warn("Error finding cab: " + e.getMessage());
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

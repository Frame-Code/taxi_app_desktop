package domain.repository.impl;

import domain.entities.Car;
import domain.entities.Fare;
import domain.repository.interfaces.BaseRepository;
import domain.repository.interfaces.FareRepository;
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
public class FareRepositoryImpl extends BaseRepository implements FareRepository {

    public FareRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Fare save(Fare fare) {
        var fareOpt = findById(1L);
        if(fareOpt.isPresent()) {
            return update(fare);
        }

        Transaction transaction = null;
        Session session = null;
        try {
            session = super.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(fare);
            transaction.commit();
            log.info("Fare saved successfully");
        } catch (HibernateException | NullPointerException e) {
            if(transaction != null) {
                log.error("Error saving fare: " + e.getMessage());
                transaction.rollback();
            }
        } finally {
            if(transaction != null) {
                session.close();
                log.info("Hibernate session closed");
            }
        }
        return fare;

    }

    @Override
    public Optional<Fare> findById(Long id) {
        var fare = super.getSessionFactory().openSession().find(Fare.class, id);
        super.getSessionFactory().getCurrentSession().close();
        return Optional.ofNullable(fare);
    }

    @Override
    public List<Fare> findAll() {
        return findById(1L).stream().toList();
    }

    @Override
    public void deleteById(Long id) {
        throw new UnsupportedOperationException("Can't delete a fare");
    }

    @Override
    public Fare update(Fare fare) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = super.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.merge(fare);
            transaction.commit();
            log.info("Fare saved successfully");
        } catch (HibernateException | NullPointerException ex) {
            if(transaction != null) {
                log.error("Error saving fare: " + ex.getMessage());
                transaction.rollback();
            }
        } finally {
            if(transaction != null) {
                session.close();
                log.info("Hibernate session closed");
            }
        }
        return fare;
    }
}

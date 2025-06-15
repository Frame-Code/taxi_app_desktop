package domain.repository.IMPL;

import domain.entities.Cab;
import domain.repository.INTERFACES.BaseRepository;
import domain.repository.INTERFACES.CabRepository;
import lombok.extern.apachecommons.CommonsLog;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
        return cab;
    }

    @Override
    public Optional<Cab> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Cab> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Cab update(Cab object) {
        return null;
    }
}

package domain.repository.impl;

import domain.entities.UserEntity;
import domain.repository.interfaces.BaseRepository;
import domain.repository.interfaces.UserRepository;
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
public class UserRepositoryImpl extends BaseRepository implements UserRepository {

    public UserRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    @Override
    public Optional<UserEntity> findByEmail(String email) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = super.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query<UserEntity> query = session.createQuery("SELECT u FROM UserEntity u WHERE u.email = :email", UserEntity.class);
            query.setParameter("email", email);
            UserEntity userEntity = query.getSingleResult();
            transaction.commit();
            return Optional.of(userEntity);
        } catch (HibernateException | NullPointerException | NoResultException e) {
            if(transaction != null) {
                log.error("Error finding userEntity: " + e.getMessage());
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

    @Override
    public Optional<UserEntity> findByPhone(String phone) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = super.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query<UserEntity> query = session.createQuery("SELECT u FROM UserEntity u WHERE u.phone = :phone", UserEntity.class);
            query.setParameter("phone", phone);
            UserEntity userEntity = query.getSingleResult();
            transaction.commit();
            return Optional.of(userEntity);
        } catch (HibernateException | NullPointerException | NoResultException e) {
            if(transaction != null) {
                log.error("Error finding userEntity: " + e.getMessage());
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

    @Override
    public UserEntity save(UserEntity userEntity) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = super.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(userEntity);
            transaction.commit();
            log.info("UserEntity saved successfully");
        } catch (HibernateException | NullPointerException e) {
            if(transaction != null) {
                log.error("Error saving userEntity: " + e.getMessage());
                transaction.rollback();
            }
        } finally {
            if(transaction != null) {
                session.close();
                log.info("Hibernate session closed");
            }
        }
        return userEntity;
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        var user = super.getSessionFactory().openSession().createQuery("FROM UserEntity", UserEntity.class).getSingleResult();
        super.getSessionFactory().getCurrentSession().close();
        return Optional.ofNullable(user);
    }

    @Override
    public List<UserEntity> findAll() {
        var users = super.getSessionFactory().openSession().createQuery("FROM UserEntity", UserEntity.class).list();
        super.getSessionFactory().getCurrentSession().close();
        return users;
    }

    @Override
    public void deleteById(Long id) {
        var userOpt = findById(id);
        if(userOpt.isEmpty()) {
            log.error("UserEntity not found");
            return;
        }

        Transaction transaction = null;
        Session session = null;
        try {
            session = super.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.remove(userOpt.get().getId());
            transaction.commit();
            log.info("UserEntity deleted successfully");
        } catch (HibernateException | NullPointerException ex) {
            if(transaction != null) {
                log.error("Error deleting userEntity: " + ex.getMessage());
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
    public UserEntity update(UserEntity userEntity) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = super.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.merge(userEntity);
            transaction.commit();
            log.info("UserEntity saved successfully");
        } catch (HibernateException | NullPointerException ex) {
            if(transaction != null) {
                log.error("Error saving userEntity: " + ex.getMessage());
                transaction.rollback();
            }
        } finally {
            if(transaction != null) {
                session.close();
                log.info("Hibernate session closed");
            }
        }
        return userEntity;
    }
}

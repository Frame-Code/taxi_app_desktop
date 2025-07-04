package domain.repository.impl;

import domain.entities.Role;
import domain.repository.interfaces.BaseRepository;
import domain.repository.interfaces.RoleRepository;
import lombok.extern.apachecommons.CommonsLog;
import org.hibernate.HibernateException;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@CommonsLog
public class RoleRepositoryImpl extends BaseRepository implements RoleRepository {

    public RoleRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Optional<Role> findByName(String name) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = super.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query<Role> query = session.createQuery("SELECT r FROM Role r WHERE r.name LIKE :name", Role.class);
            query.setParameter("name", formatName(name));
            Role role = query.getSingleResult();
            transaction.commit();
            return Optional.of(role);
        } catch (NullPointerException | NoSuchElementException e) {
            if (transaction != null) {
                log.error("Error finding role: " + e.getMessage());
                transaction.rollback();
            }
        } catch (NonUniqueResultException e) {
            throw new RuntimeException("Error, duplicated values for role names, exception {}", e);
        } finally {
            if (transaction != null) {
                session.close();
                log.info("Hibernate session closed");
            }
        }
        return Optional.empty();
    }

    @Override
    public Role save(Role role) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = super.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(role);
            transaction.commit();
            log.info("Role saved successfully");
        } catch (HibernateException | NullPointerException e) {
            if(transaction != null) {
                log.error("Error saving role: " + e.getMessage());
                transaction.rollback();
            }
        } finally {
            if(transaction != null) {
                session.close();
                log.info("Hibernate session closed");
            }
        }
        return role;

    }

    @Override
    public Optional<Role> findById(Long id) {
        var role = super.getSessionFactory().openSession().find(Role.class, id);
        super.getSessionFactory().getCurrentSession().close();
        return Optional.ofNullable(role);

    }

    @Override
    public List<Role> findAll() {
        var roles = super.getSessionFactory().openSession().createQuery("FROM Role", Role.class).list();
        super.getSessionFactory().getCurrentSession().close();
        return roles;
    }

    @Override
    public void deleteById(Long id) {
        var roleOpt = findById(id);
        if(roleOpt.isEmpty()) {
            log.error("Role not found");
            return;
        }

        Transaction transaction = null;
        Session session = null;
        try {
            session = super.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.remove(roleOpt.get().getId());
            transaction.commit();
            log.info("Car deleted successfully");
        } catch (HibernateException | NullPointerException ex) {
            if(transaction != null) {
                log.error("Error deleting role: " + ex.getMessage());
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
    public Role update(Role role) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = super.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.merge(role);
            transaction.commit();
            log.info("Role saved successfully");
        } catch (HibernateException | NullPointerException ex) {
            if(transaction != null) {
                log.error("Error saving role: " + ex.getMessage());
                transaction.rollback();
            }
        } finally {
            if(transaction != null) {
                session.close();
                log.info("Hibernate session closed");
            }
        }
        return role;
    }
}

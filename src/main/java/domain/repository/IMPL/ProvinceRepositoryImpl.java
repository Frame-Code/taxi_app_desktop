package domain.repository.IMPL;

import domain.entities.Province;
import domain.repository.INTERFACES.BaseRepository;
import domain.repository.INTERFACES.ProvinceRepository;
import lombok.AllArgsConstructor;
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

/**
 * @author Daniel Mora Cantillo
 * */
@AllArgsConstructor
@CommonsLog
public class ProvinceRepositoryImpl extends BaseRepository implements ProvinceRepository {

    public ProvinceRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    @Override
    public Optional<Province> findByName(String name) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = super.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query<Province> query = session.createQuery("SELECT p FROM Province p WHERE p.name = :name", Province.class);
            query.setParameter("name", formatName(name));
            Province province = query.getSingleResult();
            transaction.commit();
            return Optional.of(province);
        } catch (NullPointerException | NoSuchElementException e) {
            if (transaction != null) {
                log.error("Error finding province: " + e.getMessage());
                transaction.rollback();
            }
        } catch (NonUniqueResultException e) {
            throw new RuntimeException("Error, duplicated values for province names, exception {}", e);
        } finally {
            if (transaction != null) {
                session.close();
                log.info("Hibernate session closed");
            }
        }
        return Optional.empty();
    }

    @Override
    public Province save(Province province) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = super.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            province.setName(formatName(province.getName()));
            session.persist(province);
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
        return province;
    }

    @Override
    public Optional<Province> findById(Long id) {
        var province = super.getSessionFactory().openSession().find(Province.class, id);
        super.getSessionFactory().getCurrentSession().close();
        return Optional.ofNullable(province);
    }

    @Override
    public List<Province> findAll() {
        var provinceList = super.getSessionFactory().openSession().createQuery("FROM Province", Province.class).list();
        super.getSessionFactory().getCurrentSession().close();
        return provinceList;
    }

    @Override
    public void deleteById(Long id) {
        var provinceOpt = findById(id);
        if (provinceOpt.isEmpty()) {
            log.error("Province not found");
            return;
        }

        Transaction transaction = null;
        Session session = null;
        try {
            session = super.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.remove(provinceOpt.get().getId());
            transaction.commit();
            log.info("Province deleted successfully");
        } catch (HibernateException | NullPointerException ex) {
            if (transaction != null) {
                log.error("Error deleting province: " + ex.getMessage());
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
    public Province update(Province province) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = super.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.merge(province);
            transaction.commit();
            log.info("Province saved successfully");
        } catch (HibernateException | NullPointerException ex) {
            if(transaction != null) {
                log.error("Error saving province: " + ex.getMessage());
                transaction.rollback();
            }
        } finally {
            if(transaction != null) {
                session.close();
                log.info("Hibernate session closed");
            }
        }
        return province;
    }

    protected String formatName(String name) {
        name = name.toLowerCase();
        name = name.trim();
        return name;
    }
}

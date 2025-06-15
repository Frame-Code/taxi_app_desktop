package domain.repository.IMPL;

import domain.entities.City;
import domain.repository.INTERFACES.BaseRepository;
import domain.repository.INTERFACES.CityRepository;
import domain.repository.INTERFACES.ProvinceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

/**
 * @author Daniel Mora Cantillo
 */
@AllArgsConstructor
@CommonsLog
public class CityRepositoryImpl extends BaseRepository implements CityRepository {
    private final ProvinceRepository provinceRepository;

    public CityRepositoryImpl(SessionFactory sessionFactory, ProvinceRepository provinceRepository) {
        super(sessionFactory);
        this.provinceRepository = provinceRepository;
    }

    @Override
    public List<City> findCitiesByProvinceName(String provinceName) {
        var provinceOpt = provinceRepository.findByName(provinceName);
        if(provinceOpt.isEmpty()) {
            log.info("No province founded");
            return List.of();
        }
        log.info("Province founded");
        return provinceOpt.get().getCities().stream().toList();
    }

    @Override
    public City save(City city) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = super.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            city.setName(formatName(city.getName()));
            session.persist(city);
            transaction.commit();
            log.info("city saved successfully");
        } catch (HibernateException | NullPointerException e) {
            if (transaction != null) {
                log.error("Error saving city: " + e.getMessage());
                transaction.rollback();
            }
        } finally {
            if (transaction != null) {
                session.close();
                log.info("Hibernate session closed");
            }
        }
        return city;
    }

    @Override
    public Optional<City> findById(Long id) {
        var city = super.getSessionFactory().openSession().find(City.class, id);
        super.getSessionFactory().getCurrentSession().close();
        return Optional.ofNullable(city);
    }

    @Override
    public List<City> findAll() {
        var cities = super.getSessionFactory().openSession().createQuery("FROM City", City.class).list();
        super.getSessionFactory().getCurrentSession().close();
        return cities;
    }

    @Override
    public void deleteById(Long id) {
        var cityOpt = findById(id);
        if (cityOpt.isEmpty()) {
            log.error("City not found");
            return;
        }

        Transaction transaction = null;
        Session session = null;
        try {
            session = super.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.remove(cityOpt.get().getId());
            transaction.commit();
            log.info("City deleted successfully");
        } catch (HibernateException | NullPointerException ex) {
            if (transaction != null) {
                log.error("Error deleting city: " + ex.getMessage());
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
    public City update(City city) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = super.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.merge(city);
            transaction.commit();
            log.info("City saved successfully");
        } catch (HibernateException | NullPointerException ex) {
            if(transaction != null) {
                log.error("Error saving city: " + ex.getMessage());
                transaction.rollback();
            }
        } finally {
            if(transaction != null) {
                session.close();
                log.info("Hibernate session closed");
            }
        }
        return city;
    }

    protected String formatName(String name) {
        name = name.toLowerCase();
        name = name.trim();
        return name;
    }
}

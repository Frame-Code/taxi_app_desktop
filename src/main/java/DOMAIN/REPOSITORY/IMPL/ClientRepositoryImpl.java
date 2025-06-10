package DOMAIN.REPOSITORY.IMPL;

import DOMAIN.ENTITIES.Client;
import DOMAIN.REPOSITORY.INTERFACES.ClientRepository;
import SHARED.UTILS.HibernateUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author Daniel Mora Cantillo
 * */
@CommonsLog
@AllArgsConstructor
public class ClientRepositoryImpl extends BaseRepository implements ClientRepository {

    public ClientRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Optional<Client> findByEmail(String email) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = super.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query<Client> query = session.createQuery("SELECT c FROM Client c WHERE c.user.email = :email", Client.class);
            query.setParameter("email", email);
            Client client = query.getSingleResult();
            transaction.commit();
            return Optional.of(client);
        } catch (HibernateException | NullPointerException e) {
            if(transaction != null) {
                log.error("Error finding client: " + e.getMessage());
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
    public Optional<Client> findById(Long id) {
        var client = super.getSessionFactory().openSession().find(Client.class, id);
        super.getSessionFactory().getCurrentSession().close();
        return Optional.ofNullable(client);
    }

    @Override
    public Client save(Client client) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = super.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(client);
            transaction.commit();
            log.info("Client saved successfully");
        } catch (HibernateException | NullPointerException e) {
            if(transaction != null) {
                log.error("Error saving client: " + e.getMessage());
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

    @Override
    public List<Client> findAll() {
        var clients = super.getSessionFactory().openSession().createQuery("FROM Client", Client.class).list();
        super.getSessionFactory().getCurrentSession().close();
        return clients;
    }

    @Override
    public void deleteById(Long id) {
        var clientOpt = findById(id);
        if(clientOpt.isEmpty()) {
            log.error("Client not found");
            return;
        }

        Transaction transaction = null;
        Session session = null;
        try {
            session = super.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.remove(clientOpt.get().getId());
            transaction.commit();
            log.info("Client deleted successfully");
        } catch (HibernateException | NullPointerException ex) {
            if(transaction != null) {
                log.error("Error deleting client: " + ex.getMessage());
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
    public Client update(Client client) {
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

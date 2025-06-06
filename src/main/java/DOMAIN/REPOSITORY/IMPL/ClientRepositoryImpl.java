package DOMAIN.REPOSITORY.IMPL;

import DOMAIN.ENTITIES.Client;
import DOMAIN.REPOSITORY.INTERFACES.ClientRepository;
import SHARED.UTILS.HibernateUtil;
import lombok.extern.apachecommons.CommonsLog;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

@CommonsLog
public class ClientRepositoryImpl implements ClientRepository {
    @Override
    public Optional<Client> findByEmail(String email) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query<Client> query = session.createQuery("SELECT c FROM Client c WHERE id = :id", Client.class);
            query.setParameter("id", email);
            Client client = query.getSingleResult();
            transaction.commit();
            return Optional.of(client);
        } catch (HibernateException ex) {
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
        return Optional.empty();
    }

    @Override
    public Client save(Client client) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(client);
            transaction.commit();
            log.info("Client saved successfully");
        } catch (HibernateException ex) {
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

    @Override
    public Optional<Client> findById(Long id) {
        var client = HibernateUtil.getSessionFactory().openSession().find(Client.class, id);
        HibernateUtil.shutdown();
        return Optional.of(client);
    }

    @Override
    public List<Client> findAll() {
        var clients = HibernateUtil.getSessionFactory().openSession().createQuery("FROM Client", Client.class).list();
        HibernateUtil.shutdown();
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
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.remove(clientOpt.get().getId());
            transaction.commit();
            log.info("Client deleted successfully");
        } catch (HibernateException ex) {
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
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.merge(client);
            transaction.commit();
            log.info("Client saved successfully");
        } catch (HibernateException ex) {
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

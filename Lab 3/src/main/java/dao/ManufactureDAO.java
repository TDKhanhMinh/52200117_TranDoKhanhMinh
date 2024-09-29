package dao;

import entity.HibernateUtils;
import entity.Manufacture;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ManufactureDAO {


    public void add(Manufacture manufacture) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(manufacture);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Read
    public void getById(int id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.get(Manufacture.class, id);
        }
    }

    public List<Manufacture> getAll() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery("from manufacture ", Manufacture.class).list();
        }
    }

    // Update
    public void update(Manufacture manufacture) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(manufacture);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Delete
    public void remove(Manufacture manufacture) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(manufacture);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Delete by ID
    public void removeById(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Manufacture manufacture = session.get(Manufacture.class, id);
            if (manufacture != null) {
                session.delete(manufacture);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public boolean moreThan100Employees() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Long count = session.createQuery("select count(name) from manufacture where employee > 100", Long.class)
                    .uniqueResult();
            return count == 0;
        }
    }

    public long getAllEmployees() {
        ;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Long count = session.createQuery("select sum(employee) from manufacture ", Long.class)
                    .uniqueResult();
            return count;
        }

    }

    public Manufacture getLastUSManufacture() throws Exception {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Manufacture manufacture = session.createQuery("from manufacture where location = 'US' order by id desc", Manufacture.class)
                    .setMaxResults(1)
                    .uniqueResult();
            if (manufacture == null) {
                throw new InvalidOperationException("There is no producer that meets the above criteria");
            }
            return manufacture;
        }
    }
}




package dao;

import entity.HibernateUtils;
import entity.Manufacture;
import entity.Phone;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;


public class PhoneDAO {


    public void add(Phone phone) {
        Transaction transaction = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
//            if (phone.getManufacture() == null) {
//               Manufacture manufacture= findManufacture(phone.getId());
//                if (manufacture != null) {
//                    phone.setManufacture(manufacture);
//                }
//            }
            session.save(phone);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Phone getById(int id) {
        Session HibernateUtil;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.get(Phone.class, id);
        }
    }

    public List<Phone> getAll() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery("from mobilephone", Phone.class).list();
        }
    }

    public void update(Phone phone) {
        Transaction transaction = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(phone);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void remove(String id) {

        Transaction transaction = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.remove(id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();

        }

    }

    public void remove(Phone phone) {
        Transaction transaction = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.delete(phone);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Phone getPhoneWithHighestPrice() {
        Transaction transaction = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        Phone phone = null;
        try {
            Query query = session.createQuery("FROM mobilephone ORDER BY price DESC");
            query.setMaxResults(1);
            phone = (Phone) query.uniqueResult();
        } finally {
            session.close();
        }
        return phone;
    }


    public List<Phone> getPhonesSortedByCountry() {
        Transaction transaction = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<Phone> phones = null;
        try {
            Query query = session.createQuery("FROM mobilephone ORDER BY country ASC");
            phones = query.list();
        } finally {
            session.close();
        }
        return phones;
    }


    public boolean isPhoneAbove50M() {
        Transaction transaction = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        boolean exists = false;
        try {
            Query query = session.createQuery("SELECT 1 FROM mobilephone WHERE price > 50000000");
            exists = query.uniqueResult() != null;
        } finally {
            session.close();
        }
        return exists;
    }


    public Phone getFirstPinkPhoneAbove15M() {
        Transaction transaction = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        Phone phone = null;
        try {
            Query query = session.createQuery("FROM mobilephone WHERE color = :color AND price > :price");
            query.setParameter("color", "Pink");
            query.setParameter("price", 15000000);
            query.setMaxResults(1);
            phone = (Phone) query.uniqueResult();
        } finally {
            session.close();
        }
        return phone;
    }

    private final ManufactureDAO manufactureDAO;
    private Manufacture manufacture;

    public PhoneDAO() {
        this.manufactureDAO = new ManufactureDAO();
    }


//    private Manufacture findManufacture(String phoneID) {
//        List<Manufacture> manufactures = manufactureDAO.getAll();
//        String PhoneID = phoneID.toLowerCase();
//        for (Manufacture manufacture : manufactures) {
//            if (PhoneID.contains(manufacture.getId().toLowerCase())) {
//                return manufacture;
//            }
//        }
//        return null;
//    }
}



package ua.com.nure.dlas.repository.impl;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger LOG = Logger.getLogger(CoursesDAOHibernateImpl.class);

    protected BaseDAO() {
    }

    public Session getSession() {
        try {
            return sessionFactory.getCurrentSession();
        } catch(HibernateException e) {
            return sessionFactory.openSession();
        }
    }

    protected void begin() {
        getSession().getTransaction().begin();
    }

    protected void commit() {
        Transaction tx = getSession().getTransaction();
        if(tx.isActive()) {
            tx.commit();
        }
    }

    protected void rollback() {
        try {
            getSession().getTransaction().rollback();
            getSession().close();
        } catch (HibernateException e) {
            LOG.error("Repository fail", e);
        }
    }

    public void close() {
        getSession().close();
    }


}

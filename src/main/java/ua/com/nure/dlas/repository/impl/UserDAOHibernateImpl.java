package ua.com.nure.dlas.repository.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import ua.com.nure.dlas.model.User;
import ua.com.nure.dlas.repository.UserDAO;


@Repository
public class UserDAOHibernateImpl extends BaseDAO implements UserDAO {

    @Override
    public User getUser(String email) throws Exception {
        try {
            begin();
            Query q = getSession().createQuery("from User where email = :email");
            q.setString("email", email);
            User user = (User) q.uniqueResult();
            commit();
            return user;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not get user " + email, e);
        }
    }
}

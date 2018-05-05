package ua.com.nure.dlas.repository.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import ua.com.nure.dlas.model.User;
import ua.com.nure.dlas.repository.UserDAO;


@Repository
public class UserDAOHibernateImpl extends BaseDAO implements UserDAO {

    private static final Logger LOG = Logger.getLogger(UserDAOHibernateImpl.class);

    @Override
    public User getUser(String email) {
        try {
            begin();
            Query q = getSession().createQuery("from User where email = :email");
            q.setString("email", email);
            User user = (User) q.uniqueResult();
            commit();
            return user;
        } catch (HibernateException e) {
            rollback();
            LOG.error("Could not get user " + email, e);
        }
        return null;
    }

    @Override
    public String getUserGroupName(String email) {
        try {
            begin();
            Query q = getSession().createSQLQuery("select group_name from groups where student_email = ?")
                    .addScalar("group_name", new StringType());
            q.setString(0, email);
            String groupName = q.uniqueResult().toString();
            commit();
            return groupName;
        } catch (HibernateException e) {
            rollback();
            LOG.error("Could not get group name for user " + email, e);
        }
        return StringUtils.EMPTY;
    }
}

package ua.com.nure.dlas.repository.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import ua.com.nure.dlas.model.SubmittedCourse;
import ua.com.nure.dlas.model.User;
import ua.com.nure.dlas.repository.UserDAO;

import java.util.Collections;
import java.util.List;


@Repository
public class UserDAOHibernateImpl extends BaseDAO implements UserDAO {

    private static final Logger LOG = Logger.getLogger(UserDAOHibernateImpl.class);

    @Override
    public User getUser(String email) {
        try {
            Session session = openCurrentSession();
            Query q = session.createQuery("from User where email = :email");
            q.setString("email", email);
            return (User) q.uniqueResult();
        } catch (HibernateException e) {
            LOG.error("Could not get user " + email, e);
        } finally {
            closeCurrentSession();
        }
        return null;
    }

    @Override
    public String getUserGroupName(String email) {
        try {
            Session session = openCurrentSession();
            Query q = session.createSQLQuery("select group_name from groups where student_email = ?")
                    .addScalar("group_name", new StringType());
            q.setString(0, email);
            return q.uniqueResult().toString();
        } catch (HibernateException e) {
            LOG.error("Could not get group name for user " + email, e);
        } finally {
            closeCurrentSession();
        }
        return StringUtils.EMPTY;
    }

    @Override
    public List<SubmittedCourse> getSubmittedCoursesForStudent(String studentEmail) {
        try {
            Session session = openCurrentSession();
            Query q = session.createQuery("from SubmittedCourse where studentEmail = :studentEmail");
            q.setParameter("studentEmail", studentEmail);
            return q.list();
        } catch (HibernateException e) {
            LOG.error("Could not get courses", e);
            return Collections.emptyList();
        } finally {
            closeCurrentSession();
        }
    }
}

package ua.com.nure.dlas.repository.impl;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import ua.com.nure.dlas.model.Course;
import ua.com.nure.dlas.repository.CoursesDAO;

import java.util.Collections;
import java.util.List;

public class CoursesDAOHibernateImpl extends BaseDAO implements CoursesDAO {

    private int batchSize;

    public CoursesDAOHibernateImpl(int batchSize) {
        this.batchSize = batchSize;
    }

    private static final Logger LOG = Logger.getLogger(CoursesDAOHibernateImpl.class);

    @Override
    public boolean uploadCourses(List<Course> courses) {
        try {
            begin();

            for (int i = 0; i < courses.size(); i++) {
                getSession().save(courses.get(i));
                if (i % batchSize == 0) {
                    getSession().flush();
                    getSession().clear();
                }
            }
            commit();
            return true;
        } catch (HibernateException e) {
            rollback();
            LOG.error("Could not upload courses", e);
            return false;
        }
    }

    @Override
    public List<Course> getCoursesForGroup(String groupName) {
        try {
            begin();
            Query q = getSession().createQuery("from Course where groupName = :groupName");
            q.setParameter("groupName", groupName);
            List<Course> notes = q.list();
            commit();
            return notes;
        } catch (HibernateException e) {
            rollback();
            LOG.error("Could not get courses", e);
            return Collections.emptyList();
        }
    }
}

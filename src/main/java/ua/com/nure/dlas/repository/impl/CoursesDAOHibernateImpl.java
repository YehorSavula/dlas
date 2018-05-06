package ua.com.nure.dlas.repository.impl;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import ua.com.nure.dlas.model.Course;
import ua.com.nure.dlas.model.SubmittedCourse;
import ua.com.nure.dlas.model.SubmittedCourseStatus;
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
            Session session = openCurrentSessionwithTransaction();

            for (int i = 0; i < courses.size(); i++) {
                session.save(courses.get(i));
                if (i % batchSize == 0) {
                    session.flush();
                    session.clear();
                }
            }
            return true;
        } catch (HibernateException e) {
            getCurrentTransaction().rollback();
            LOG.error("Could not upload courses", e);
            return false;
        } finally {
            closeCurrentSessionwithTransaction();
        }
    }

    @Override
    public List<Course> getCoursesForGroup(String groupName) {
        try {
            Session session = openCurrentSession();
            Query q = session.createQuery("from Course where groupName = :groupName");
            q.setParameter("groupName", groupName);
            return q.list();
        } catch (HibernateException e) {
            LOG.error("Could not get courses", e);
            return Collections.emptyList();
        } finally {
            closeCurrentSession();
        }
    }

    @Override
    public Integer uploadSubmittedCourse(SubmittedCourse submittedCourse) {
        try {
            return (Integer) openCurrentSession().save(submittedCourse);
        } catch (HibernateException e) {
            LOG.error("Could not get courses", e);
            return null;
        } finally {
            closeCurrentSession();
        }
    }

    @Override
    public Course getCourseById(Integer courseId) {
        try {
            Session session = openCurrentSession();
            return (Course) session.get(Course.class, courseId);
        } catch (HibernateException e) {
            LOG.error("Could not get course by id " + courseId, e);
            return null;
        } finally {
            closeCurrentSession();
        }
    }

    @Override
    public SubmittedCourse getSubmittedCourseById(Integer courseId) {
        try {
            Session session = openCurrentSession();
            return (SubmittedCourse) session.get(SubmittedCourse.class, courseId);
        } catch (HibernateException e) {
            LOG.error("Could not get course by id " + courseId, e);
            return null;
        } finally {
            closeCurrentSession();
        }
    }

    @Override
    public List<SubmittedCourse> getAcceptedCourses() {
        try {
            Session session = openCurrentSession();
            Query q = session.createQuery("from SubmittedCourse where courseStatus = :courseStatus");
            q.setParameter("courseStatus", SubmittedCourseStatus.ACCEPTED);
            return q.list();
        } catch (HibernateException e) {
            LOG.error("Could not get accepted courses", e);
            return null;
        } finally {
            closeCurrentSession();
        }
    }
}

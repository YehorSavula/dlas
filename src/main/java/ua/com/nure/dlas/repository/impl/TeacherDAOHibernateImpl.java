package ua.com.nure.dlas.repository.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.StringType;
import ua.com.nure.dlas.model.SubmittedCourse;
import ua.com.nure.dlas.model.SubmittedCourseStatus;
import ua.com.nure.dlas.repository.TeacherDAO;

import java.util.Collections;
import java.util.List;

public class TeacherDAOHibernateImpl extends BaseDAO implements TeacherDAO {

    private static final Logger LOG = Logger.getLogger(TeacherDAOHibernateImpl.class);

    @Override
    public String getTeacherEmailForCourse(Integer courseId) {
        try {
            Session session = openCurrentSession();
            Query q = session.createSQLQuery("select teacher_email from teacher_course where course_id = ?")
                    .addScalar("teacher_email", new StringType());
            q.setInteger(0, courseId);
            return q.uniqueResult().toString();
        } catch (HibernateException e) {
            LOG.error("Could not get teacher email for course " + courseId, e);
        } finally {
            closeCurrentSession();
        }
        return StringUtils.EMPTY;
    }

    @Override
    public List<SubmittedCourse> getNotAcceptedCoursesForTeacher(String teacherEmail) {
        try {
            Session session = openCurrentSession();
            Query q = session.createQuery("from SubmittedCourse where teacherEmail = :teacherEmail and courseStatus = :courseStatus");
            q.setParameter("teacherEmail", teacherEmail);
            q.setParameter("courseStatus", SubmittedCourseStatus.SUBMITTED);
            return q.list();
        } catch (HibernateException e) {
            LOG.error("Could not get courses", e);
            return Collections.emptyList();
        } finally {
            closeCurrentSession();
        }
    }
}

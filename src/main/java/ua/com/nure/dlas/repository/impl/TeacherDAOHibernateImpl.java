package ua.com.nure.dlas.repository.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.StringType;
import ua.com.nure.dlas.model.Course;
import ua.com.nure.dlas.model.SubmittedCourse;
import ua.com.nure.dlas.model.SubmittedCourseStatus;
import ua.com.nure.dlas.repository.TeacherDAO;

import java.util.Collections;
import java.util.List;

public class TeacherDAOHibernateImpl extends BaseDAO implements TeacherDAO {

    private int batchSize;

    public TeacherDAOHibernateImpl(int batchSize) {
        this.batchSize = batchSize;
    }

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

    @Override
    public void setCourseStatus(Integer submittedCourseId, SubmittedCourseStatus courseStatus) {
        try {
            Session session = openCurrentSession();
            SubmittedCourse course = (SubmittedCourse) session.get(SubmittedCourse.class, submittedCourseId);
            course.setCourseStatus(courseStatus);
            session.update(course);
            session.flush();
        } catch (HibernateException e) {
            LOG.error("Could not get courses", e);
        } finally {
            closeCurrentSession();
        }
    }

    @Override
    public List<Course> getCoursesWithoutCriteria(String teacherEmail) {
        try {
            Session session = openCurrentSession();
            return session.createSQLQuery("select * from course INNER JOIN " +
                    "teacher_course ON course.id=teacher_course.course_id where " +
                    "teacher_course.teacher_email = :teacherEmail AND course.criteria_added = 0")
                    .addEntity(Course.class)
                    .setParameter("teacherEmail", teacherEmail).list();
        } catch (HibernateException e) {
            LOG.error("Could not get courses", e);
            return Collections.emptyList();
        } finally {
            closeCurrentSession();
        }
    }

    @Override
    public void uploadCriteries(Integer courseId, List<String> criteries) {
        try {
            Session session = openCurrentSessionwithTransaction();

            for (int i = 0; i < criteries.size(); i++) {
                session.createSQLQuery("INSERT INTO courses_criteria (course_id, criteria) VALUES (:courseId, :criteria)")
                .setParameter("courseId", courseId)
                .setParameter("criteria", criteries.get(i))
                .executeUpdate();

                if (i % batchSize == 0) {
                    session.flush();
                    session.clear();
                }
            }

            session.createSQLQuery("update course set course.criteria_added = 1 where course.id = :courseId")
                    .setParameter("courseId", courseId)
                    .executeUpdate();

        } catch (HibernateException e) {
            getCurrentTransaction().rollback();
            LOG.error("Could not upload courses", e);
        } finally {
            closeCurrentSessionwithTransaction();
        }
    }

    @Override
    public List<String> getCourseCriteria(Integer courseId) {
        try {
            Session session = openCurrentSession();
            return session.createSQLQuery("select criteria from courses_criteria where course_id = :courseId")
                    .setParameter("courseId", courseId).list();
        } catch (HibernateException e) {
            LOG.error("Could not get courses", e);
            return Collections.emptyList();
        } finally {
            closeCurrentSession();
        }
    }
}

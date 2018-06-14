package ua.com.nure.dlas.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.nure.dlas.model.Course;
import ua.com.nure.dlas.model.SubmittedCourse;
import ua.com.nure.dlas.model.SubmittedCourseStatus;
import ua.com.nure.dlas.repository.TeacherDAO;
import ua.com.nure.dlas.services.TeacherService;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDAO teacherDAO;

    @Override
    public String getTeacherEmailForCourse(Integer courseId) {
        return teacherDAO.getTeacherEmailForCourse(courseId);
    }

    @Override
    public List<SubmittedCourse> getNotAcceptedCourses(String teacherEmail) {
        return teacherDAO.getNotAcceptedCoursesForTeacher(teacherEmail);
    }

    @Override
    public void acceptCourse(Integer submittedCourseId) {
        teacherDAO.setCourseStatus(submittedCourseId, SubmittedCourseStatus.ACCEPTED);
    }

    @Override
    public void rejectCourse(Integer submittedCourseId) {
        teacherDAO.setCourseStatus(submittedCourseId, SubmittedCourseStatus.REJECTED);
    }

    @Override
    public List<Course> getCoursesWithoutCriteria(String teacherEmail) {
        return teacherDAO.getCoursesWithoutCriteria(teacherEmail);
    }

    @Override
    public void uploadCriteries(Integer courseId, List<String> criteries) {
        teacherDAO.uploadCriteries(courseId, criteries);
    }

    @Override
    public List<String> getCourseCriteria(Integer courseId) {
        return teacherDAO.getCourseCriteria(courseId);
    }
}

package ua.com.nure.dlas.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.nure.dlas.model.SubmittedCourse;
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
}
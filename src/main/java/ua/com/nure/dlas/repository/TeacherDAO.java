package ua.com.nure.dlas.repository;

import ua.com.nure.dlas.model.SubmittedCourse;

import java.util.List;

public interface TeacherDAO {

    String getTeacherEmailForCourse(Integer courseId);

    List<SubmittedCourse> getNotAcceptedCoursesForTeacher(String teacherEmail);

}

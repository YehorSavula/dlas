package ua.com.nure.dlas.repository;

import ua.com.nure.dlas.model.SubmittedCourse;
import ua.com.nure.dlas.model.SubmittedCourseStatus;

import java.util.List;

public interface TeacherDAO {

    String getTeacherEmailForCourse(Integer courseId);

    List<SubmittedCourse> getNotAcceptedCoursesForTeacher(String teacherEmail);

    void setCourseStatus(Integer submittedCourseId, SubmittedCourseStatus courseStatus);
}

package ua.com.nure.dlas.services;

import ua.com.nure.dlas.model.Course;
import ua.com.nure.dlas.model.SubmittedCourse;

import java.util.List;

public interface TeacherService {

    String getTeacherEmailForCourse(Integer courseId);

    List<SubmittedCourse> getNotAcceptedCourses(String teacherEmail);

    void acceptCourse(Integer submittedCourseId);

    void rejectCourse(Integer submittedCourseId);

    List<Course> getCoursesWithoutCriteria(String teacherEmail);

    void uploadCriteries(Integer courseId, List<String> criteries);

    List<String> getCourseCriteria(Integer courseId);
}

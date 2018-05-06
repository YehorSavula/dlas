package ua.com.nure.dlas.repository;

import ua.com.nure.dlas.model.Course;
import ua.com.nure.dlas.model.SubmittedCourse;

import java.util.List;

public interface CoursesDAO {

    boolean uploadCourses(List<Course> courses);

    List<Course> getCoursesForGroup(String groupName);

    Integer uploadSubmittedCourse(SubmittedCourse submittedCourse);

    Course getCourseById(Integer courseId);

    SubmittedCourse getSubmittedCourseById(Integer courseId);

    List<SubmittedCourse> getAcceptedCourses();
}

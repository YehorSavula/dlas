package ua.com.nure.dlas.repository;

import ua.com.nure.dlas.model.Course;

import java.util.List;

public interface CoursesDAO {

    boolean uploadCourses(List<Course> courses);

    List<Course> getCoursesForGroup(String groupName);
}

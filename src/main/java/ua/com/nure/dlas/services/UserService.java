package ua.com.nure.dlas.services;

import ua.com.nure.dlas.dto.AddCourseData;
import ua.com.nure.dlas.model.Course;
import ua.com.nure.dlas.model.SubmittedCourse;
import ua.com.nure.dlas.model.User;

import java.util.List;

public interface UserService {

    User getUser(String username) throws Exception;

    String getGroupName(String email);

    List<SubmittedCourse> getSubmittedCourses(String studentEmail);

    SubmittedCourse getSubmittedCourseById(Integer courseId);

    Integer submitCourse(AddCourseData particularCourseData);

    Course getCourseById(Integer courseId);

    String getUserNameByEmail(String email);
}

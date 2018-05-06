package ua.com.nure.dlas.repository;

import ua.com.nure.dlas.model.SubmittedCourse;
import ua.com.nure.dlas.model.User;

import java.util.List;

public interface UserDAO {

    User getUser(String email);

    String getUserGroupName(String email);

    List<SubmittedCourse> getSubmittedCoursesForStudent(String studentEmail);
}

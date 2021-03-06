package ua.com.nure.dlas.services;

import org.springframework.web.multipart.MultipartFile;
import ua.com.nure.dlas.model.Course;
import ua.com.nure.dlas.model.SubmittedCourse;

import java.util.List;

public interface ManagerService {

    boolean uploadProgram(MultipartFile file);

    List<Course> getCoursesForGroup(String groupName);

    List<SubmittedCourse> getAcceptedCourses();
}

package ua.com.nure.dlas.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import ua.com.nure.dlas.model.Course;
import ua.com.nure.dlas.model.SubmittedCourse;
import ua.com.nure.dlas.repository.CoursesDAO;
import ua.com.nure.dlas.services.ManagerService;
import ua.com.nure.dlas.services.utils.ProgramParser;

import java.util.List;

public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private CoursesDAO coursesDAO;

    @Autowired
    private ProgramParser programParser;

    @Override
    public boolean uploadProgram(MultipartFile file) {
        List<Course> parsedCourses = programParser.parseProgram(file);

        if (parsedCourses.isEmpty()) {
            return false;
        }

        return coursesDAO.uploadCourses(parsedCourses);
    }

    @Override
    public List<Course> getCoursesForGroup(String groupName) {
        return coursesDAO.getCoursesForGroup(groupName);
    }

    @Override
    public List<SubmittedCourse> getAcceptedCourses() {
        return coursesDAO.getAcceptedCourses();
    }
}

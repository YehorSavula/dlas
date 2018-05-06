package ua.com.nure.dlas.services.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nure.dlas.model.Course;
import ua.com.nure.dlas.model.SubmittedCourse;
import ua.com.nure.dlas.model.SubmittedCourseStatus;
import ua.com.nure.dlas.model.User;
import ua.com.nure.dlas.repository.CoursesDAO;
import ua.com.nure.dlas.repository.UserDAO;
import ua.com.nure.dlas.services.TeacherService;
import ua.com.nure.dlas.services.UserService;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private static final double AUTO_ACCEPT_CRITERIA = 0.8;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CoursesDAO coursesDAO;

    @Override
    public User getUser(String username) {
        return userDAO.getUser(username);
    }

    @Override
    public String getGroupName(String email) {
        return userDAO.getUserGroupName(email);
    }

    @Override
    public List<SubmittedCourse> getSubmittedCourses(String studentEmail) {
        return userDAO.getSubmittedCoursesForStudent(studentEmail);
    }

    @Override
    public SubmittedCourse getSubmittedCourseById(Integer courseId) {
        return coursesDAO.getSubmittedCourseById(courseId);
    }

    @Override
    public Integer submitCourse(Integer courseId, String studentEmail, Integer lecturesHours, Integer practicalHours,
                                String courseUrl, String certificateUrl, Integer graduate) {
        String teacherEmail = teacherService.getTeacherEmailForCourse(courseId);
        Course course = coursesDAO.getCourseById(courseId);
        if (StringUtils.isEmpty(teacherEmail) || Objects.isNull(course)) {
            return null;
        }

        SubmittedCourse submittedCourse = new SubmittedCourse();
        submittedCourse.setCourseId(courseId);
        submittedCourse.setStudentEmail(studentEmail);
        submittedCourse.setLecturesHours(lecturesHours);
        submittedCourse.setPracticalHours(practicalHours);
        submittedCourse.setCourseUrl(courseUrl);
        submittedCourse.setCertificateUrl(certificateUrl);
        submittedCourse.setGraduate(graduate);
        submittedCourse.setTeacherEmail(teacherEmail);
        boolean isAutoAccepted = course.getHours() * AUTO_ACCEPT_CRITERIA <= (lecturesHours + practicalHours);
        submittedCourse.setCourseStatus(isAutoAccepted ? SubmittedCourseStatus.ACCEPTED : SubmittedCourseStatus.SUBMITTED);

        return coursesDAO.uploadSubmittedCourse(submittedCourse);
    }

    @Override
    public Course getCourseById(Integer courseId) {
        return coursesDAO.getCourseById(courseId);
    }

    @Override
    public String getUserNameByEmail(String email) {
        User user = userDAO.getUser(email);
        return user.getFirstName() + StringUtils.SPACE + user.getLastName();
    }
}

package ua.com.nure.dlas.services.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nure.dlas.dto.AddCourseData;
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
import java.util.stream.Collectors;

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
    public Integer submitCourse(AddCourseData particularCourseData) {
        String teacherEmail = teacherService.getTeacherEmailForCourse(particularCourseData.getCourseId());
        Course course = coursesDAO.getCourseById(particularCourseData.getCourseId());
        if (StringUtils.isEmpty(teacherEmail) || Objects.isNull(course)) {
            return null;
        }

        SubmittedCourse submittedCourse = new SubmittedCourse();
        submittedCourse.setCourseId(particularCourseData.getCourseId());
        submittedCourse.setStudentEmail(particularCourseData.getStudentEmail());
        submittedCourse.setLecturesHours(particularCourseData.getLecturesHours());
        submittedCourse.setPracticalHours(particularCourseData.getPracticalHours());
        submittedCourse.setCourseUrl(particularCourseData.getCourseUrl());
        submittedCourse.setCertificateUrl(particularCourseData.getCertificateUrl());
        submittedCourse.setGraduate(particularCourseData.getGraduate());
        submittedCourse.setTeacherEmail(teacherEmail);

        int submittedCurseHours = particularCourseData.getLecturesHours() + particularCourseData.getPracticalHours();
        boolean isHourAccepted = course.getHours() * AUTO_ACCEPT_CRITERIA <= submittedCurseHours;

        List<String> courseCriteria = teacherService.getCourseCriteria(particularCourseData.getCourseId());
        List<String> notAcceptedCriteria = courseCriteria.stream()
                .filter(i -> !particularCourseData.getSubmiitedCriteria().contains(i))
                .collect(Collectors.toList());

        boolean isCriteriaAccepted = courseCriteria.size() * AUTO_ACCEPT_CRITERIA <= notAcceptedCriteria.size();
        submittedCourse.setCourseStatus(isHourAccepted && isCriteriaAccepted ? SubmittedCourseStatus.ACCEPTED
                : SubmittedCourseStatus.SUBMITTED);
        submittedCourse.setAcceptedCriteries(courseCriteria.size() - notAcceptedCriteria.size());

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

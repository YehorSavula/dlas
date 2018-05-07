package ua.com.nure.dlas.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import ua.com.nure.dlas.dto.NotAcceptedCourse;
import ua.com.nure.dlas.model.Course;
import ua.com.nure.dlas.model.SubmittedCourse;
import ua.com.nure.dlas.services.TeacherService;
import ua.com.nure.dlas.services.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Comparator;
import java.util.List;

@Controller
public class TeacherController {

    @Autowired
    private UserService userService;

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/not-accepted-courses", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView getNotAcceptedCoursesPage(HttpServletResponse response, Principal principal,
                                           @RequestParam(value = "submittedCourseId", required = false) Integer submittedCourseId) {

        ModelAndView modelAndView = new ModelAndView();

        List<SubmittedCourse> submittedCourses = teacherService.getNotAcceptedCourses(principal.getName());
        submittedCourses.sort(Comparator.comparingInt(SubmittedCourse::getCourseId));
        SubmittedCourse activeSubmittedCourse;
        if (submittedCourseId != null) {
            activeSubmittedCourse = submittedCourses.stream()
                    .filter(submittedCourse1 -> submittedCourse1.getId().equals(submittedCourseId))
                    .findFirst().orElse(null);
        } else {
            activeSubmittedCourse = submittedCourses.stream()
                    .min(Comparator.comparingInt(SubmittedCourse::getCourseId))
                    .orElse(null);
        }

        if (activeSubmittedCourse == null) {
            modelAndView.setViewName("not-accepted-courses");
            return modelAndView;
        }

        modelAndView.addObject("submittedCourses", submittedCourses);

        Course course = userService.getCourseById(activeSubmittedCourse.getCourseId());
        NotAcceptedCourse activeCourse = new NotAcceptedCourse();
        activeCourse.setCourseName(course.getName());
        activeCourse.setCourseUrl(activeSubmittedCourse.getCourseUrl());
        activeCourse.setCertificateUrl(activeSubmittedCourse.getCertificateUrl());
        activeCourse.setLecturesHours(activeSubmittedCourse.getLecturesHours());
        activeCourse.setPracticalHours(activeSubmittedCourse.getPracticalHours());
        activeCourse.setGraduate(activeSubmittedCourse.getGraduate());
        modelAndView.addObject("activeCourse", activeCourse);
        modelAndView.addObject("activeId", activeSubmittedCourse.getId());

        modelAndView.setViewName("not-accepted-courses");
        return modelAndView;
    }

    @RequestMapping(value = "/accept-course", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void performCourseOperation(HttpServletResponse response, Principal principal,
                                           @RequestParam(value = "submittedCourseId", required = false) Integer submittedCourseId,
                                           @RequestParam(value = "operation", required = false) String operation) throws IOException {
        if (StringUtils.isNotEmpty(operation)) {
            if (operation.equals("accept")) {
                teacherService.acceptCourse(submittedCourseId);
            } else {
                teacherService.rejectCourse(submittedCourseId);
            }
        }

        response.sendRedirect("/dlas/not-accepted-courses");
    }
}

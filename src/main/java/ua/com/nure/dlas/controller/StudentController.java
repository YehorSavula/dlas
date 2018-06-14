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
import ua.com.nure.dlas.dto.ActiveCourse;
import ua.com.nure.dlas.dto.AddCourseData;
import ua.com.nure.dlas.model.Course;
import ua.com.nure.dlas.model.SubmittedCourse;
import ua.com.nure.dlas.services.ManagerService;
import ua.com.nure.dlas.services.TeacherService;
import ua.com.nure.dlas.services.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.Comparator;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private UserService userService;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView getStudentHomePage(HttpServletResponse response, Principal principal,
                                           @RequestParam(value = "submittedCourseId", required = false) Integer submittedCourseId) {
        ModelAndView modelAndView = new ModelAndView();

        List<SubmittedCourse> submittedCourses = userService.getSubmittedCourses(principal.getName());
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
            modelAndView.setViewName("student");
            return modelAndView;
        }

        modelAndView.addObject("submittedCourses", submittedCourses);

        Course course = userService.getCourseById(activeSubmittedCourse.getCourseId());
        ActiveCourse activeCourse = new ActiveCourse();
        activeCourse.setCourseName(course.getName());
        activeCourse.setCourseUrl(activeSubmittedCourse.getCourseUrl());
        activeCourse.setCertificateUrl(activeSubmittedCourse.getCertificateUrl());
        activeCourse.setStatus(activeSubmittedCourse.getCourseStatus().name());
        activeCourse.setGraduate(activeSubmittedCourse.getGraduate());
        modelAndView.addObject("activeCourse", activeCourse);
        modelAndView.addObject("activeId", activeSubmittedCourse.getId());
        modelAndView.setViewName("student");

        return modelAndView;
    }

    @RequestMapping(value = "/add-course", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView getAddCoursePage(HttpServletResponse response, Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        String groupName = userService.getGroupName(principal.getName());
        if (StringUtils.isNotEmpty(groupName)) {
            modelAndView.addObject("courses", managerService.getCoursesForGroup(groupName));
        }
        modelAndView.setViewName("add-course");
        return modelAndView;
    }

    @RequestMapping(value = "/fill-criteria", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView fillParticularData(Principal principal, HttpSession session,
                          @RequestParam("course") Integer courseId,
                          @RequestParam("lectures-hours") Integer lecturesHours,
                          @RequestParam("practical-hours") Integer practicalHours,
                          @RequestParam("course-url") String courseUrl,
                          @RequestParam("certificate-url") String certificateUrl,
                          @RequestParam("graduate") Integer graduate) {

        AddCourseData particularFilledCourse = new AddCourseData();
        particularFilledCourse.setCourseId(courseId);
        particularFilledCourse.setLecturesHours(lecturesHours);
        particularFilledCourse.setPracticalHours(practicalHours);
        particularFilledCourse.setCourseUrl(courseUrl);
        particularFilledCourse.setCertificateUrl(certificateUrl);
        particularFilledCourse.setGraduate(graduate);
        particularFilledCourse.setStudentEmail(principal.getName());

        session.setAttribute("addedCourseData", particularFilledCourse);

        List<String> courseCriteries = teacherService.getCourseCriteria(courseId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("courseCriteries", courseCriteries);
        modelAndView.setViewName("fill-criteries");
        return modelAndView;
    }

    @RequestMapping(value = "/add-course", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void submitCourse(HttpServletResponse response, HttpSession session,
                             @RequestParam("criteries") List<String> submittedCriteries) throws IOException {
        AddCourseData particularFilledCourse = (AddCourseData) session.getAttribute("addedCourseData");
        particularFilledCourse.setSubmiitedCriteria(submittedCriteries);

        Integer submittedCourseId = userService.submitCourse(particularFilledCourse);

        session.setAttribute("addedCourseData", null);
        response.sendRedirect(submittedCourseId == null ? "/dlas/student" : "/dlas/student?submittedCourseId=" + submittedCourseId);
    }
}

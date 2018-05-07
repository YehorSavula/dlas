package ua.com.nure.dlas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.com.nure.dlas.dto.AcceptedCourse;
import ua.com.nure.dlas.model.Course;
import ua.com.nure.dlas.model.SubmittedCourse;
import ua.com.nure.dlas.services.ManagerService;
import ua.com.nure.dlas.services.UserService;

import javax.servlet.http.HttpServletResponse;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/upload-program", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView getManagerHomePage(HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("upload-program");
        return modelAndView;
    }

    @RequestMapping(value = "/accepted-courses", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView getAcceptedCourses(HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        List<SubmittedCourse> acceptedCourses = managerService.getAcceptedCourses();
        acceptedCourses.sort(Comparator.comparingInt(SubmittedCourse::getId));

        List<AcceptedCourse> acceptedCoursesDto = acceptedCourses.stream()
                .map(submittedCourse -> {
                    AcceptedCourse acceptedCourse = new AcceptedCourse();
                    Course course = userService.getCourseById(submittedCourse.getCourseId());
                    acceptedCourse.setCourseName(course.getName());
                    acceptedCourse.setGroupName(course.getGroupName());
                    acceptedCourse.setGraduate(submittedCourse.getGraduate());
                    acceptedCourse.setStudentName(userService.getUserNameByEmail(submittedCourse.getStudentEmail()));
                    acceptedCourse.setTeacherName(userService.getUserNameByEmail(submittedCourse.getTeacherEmail()));
                    acceptedCourse.setCourseUrl(submittedCourse.getCourseUrl());
                    acceptedCourse.setCertificateUrl(submittedCourse.getCertificateUrl());
                    return acceptedCourse;
                }).collect(Collectors.toList());
        modelAndView.addObject("acceptedCourses", acceptedCoursesDto);
        modelAndView.setViewName("accepted-courses");
        return modelAndView;
    }

    @RequestMapping(value = "/upload-program", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView uploadProgram(@RequestParam("upload-file") MultipartFile file) {
        ModelAndView view = new ModelAndView();
        view.setViewName("upload-status");
        view.addObject("isUploaded", managerService.uploadProgram(file));
        return view;
    }

}

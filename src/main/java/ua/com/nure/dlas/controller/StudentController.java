package ua.com.nure.dlas.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import ua.com.nure.dlas.services.ManagerService;
import ua.com.nure.dlas.services.UserService;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class StudentController {

    @Autowired
    private UserService userService;

    @Autowired
    private ManagerService managerService;

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView getStudentHomePage(HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
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
}

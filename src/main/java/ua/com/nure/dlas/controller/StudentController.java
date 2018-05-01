package ua.com.nure.dlas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@Controller
public class StudentController {

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView getStudentHomePage(HttpServletResponse response, Principal principal) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student");
        return modelAndView;
    }
}

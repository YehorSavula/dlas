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
import ua.com.nure.dlas.services.ManagerService;

import javax.servlet.http.HttpServletResponse;

@Controller
public class ManagerController {

    @Autowired
    private ManagerService managerService;

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

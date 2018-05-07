package ua.com.nure.dlas.services;

import ua.com.nure.dlas.model.SubmittedCourse;

import java.util.List;

public interface TeacherService {

    String getTeacherEmailForCourse(Integer courseId);

    List<SubmittedCourse> getNotAcceptedCourses(String teacherEmail);

    void acceptCourse(Integer submittedCourseId);

    void rejectCourse(Integer submittedCourseId);
}

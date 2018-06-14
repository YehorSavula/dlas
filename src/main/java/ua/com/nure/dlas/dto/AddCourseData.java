package ua.com.nure.dlas.dto;

import java.util.List;

public class AddCourseData {

    private Integer courseId;
    private Integer lecturesHours;
    private Integer practicalHours;
    private String courseUrl;
    private String certificateUrl;
    private Integer graduate;
    private String studentEmail;
    private List<String> submiitedCriteria;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getLecturesHours() {
        return lecturesHours;
    }

    public void setLecturesHours(Integer lecturesHours) {
        this.lecturesHours = lecturesHours;
    }

    public Integer getPracticalHours() {
        return practicalHours;
    }

    public void setPracticalHours(Integer practicalHours) {
        this.practicalHours = practicalHours;
    }

    public String getCourseUrl() {
        return courseUrl;
    }

    public void setCourseUrl(String courseUrl) {
        this.courseUrl = courseUrl;
    }

    public String getCertificateUrl() {
        return certificateUrl;
    }

    public void setCertificateUrl(String certificateUrl) {
        this.certificateUrl = certificateUrl;
    }

    public Integer getGraduate() {
        return graduate;
    }

    public void setGraduate(Integer graduate) {
        this.graduate = graduate;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public List<String> getSubmiitedCriteria() {
        return submiitedCriteria;
    }

    public void setSubmiitedCriteria(List<String> submiitedCriteria) {
        this.submiitedCriteria = submiitedCriteria;
    }
}

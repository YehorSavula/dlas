package ua.com.nure.dlas.dto;

public class NotAcceptedCourse {

    private String courseName;
    private String courseUrl;
    private String certificateUrl;
    private Integer lecturesHours;
    private Integer practicalHours;
    private Integer graduate;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

    public Integer getGraduate() {
        return graduate;
    }

    public void setGraduate(Integer graduate) {
        this.graduate = graduate;
    }
}

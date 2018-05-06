package ua.com.nure.dlas.dto;

public class ActiveCourse {

    private Integer submittedCourseId;
    private String courseName;
    private String courseUrl;
    private String certificateUrl;
    private Integer graduate;
    private boolean accepted;

    public Integer getSubmittedCourseId() {
        return submittedCourseId;
    }

    public void setSubmittedCourseId(Integer submittedCourseId) {
        this.submittedCourseId = submittedCourseId;
    }

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

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public Integer getGraduate() {
        return graduate;
    }

    public void setGraduate(Integer graduate) {
        this.graduate = graduate;
    }
}

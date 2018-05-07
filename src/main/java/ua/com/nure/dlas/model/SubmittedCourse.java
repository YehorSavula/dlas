package ua.com.nure.dlas.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "submitted_courses")
public class SubmittedCourse {

    private Integer id;
    private Integer courseId;
    private String studentEmail;
    private Integer lecturesHours;
    private Integer practicalHours;
    private String courseUrl;
    private String certificateUrl;
    private Integer graduate;
    private SubmittedCourseStatus courseStatus;
    private String teacherEmail;

    public SubmittedCourse(Integer courseId, String studentEmail, Integer lecturesHours, Integer practicalHours,
                           String courseUrl, String certificateUrl, Integer graduate,
                           SubmittedCourseStatus courseStatus, String teacherEmail) {
        this.courseId = courseId;
        this.studentEmail = studentEmail;
        this.lecturesHours = lecturesHours;
        this.practicalHours = practicalHours;
        this.courseUrl = courseUrl;
        this.certificateUrl = certificateUrl;
        this.graduate = graduate;
        this.courseStatus = courseStatus;
        this.teacherEmail = teacherEmail;
    }

    public SubmittedCourse() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    @PrimaryKeyJoinColumn
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotNull
    @Column(name = "course_id")
    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @NotNull
    @Column(name = "student_email")
    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    @NotNull
    @Column(name = "lectures_hours")
    public Integer getLecturesHours() {
        return lecturesHours;
    }

    public void setLecturesHours(Integer lecturesHours) {
        this.lecturesHours = lecturesHours;
    }

    @NotNull
    @Column(name = "practical_hours")
    public Integer getPracticalHours() {
        return practicalHours;
    }

    public void setPracticalHours(Integer practicalHours) {
        this.practicalHours = practicalHours;
    }

    @NotNull
    @Column(name = "course_url")
    public String getCourseUrl() {
        return courseUrl;
    }

    public void setCourseUrl(String courseUrl) {
        this.courseUrl = courseUrl;
    }

    @NotNull
    @Column(name = "certificate_url")
    public String getCertificateUrl() {
        return certificateUrl;
    }

    public void setCertificateUrl(String certificateUrl) {
        this.certificateUrl = certificateUrl;
    }

    @NotNull
    @Column(name = "graduate")
    public Integer getGraduate() {
        return graduate;
    }

    public void setGraduate(Integer graduate) {
        this.graduate = graduate;
    }

    @NotNull
    @Column(name = "course_status", columnDefinition = "enum('SUBMITTED', 'ACCEPTED', 'REJECTED')")
    @Enumerated(EnumType.STRING)
    public SubmittedCourseStatus getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(SubmittedCourseStatus courseStatus) {
        this.courseStatus = courseStatus;
    }

    @NotNull
    @Column(name = "teacher_email")
    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }
}

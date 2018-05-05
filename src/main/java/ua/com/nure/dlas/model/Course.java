package ua.com.nure.dlas.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="course")
public class Course {

    private Integer id;
    private String name;
    private Integer hours;
    private String groupName;

    public Course() {
    }

    public Course(Integer id, String name, Integer hours, String groupName) {
        this.id = id;
        this.name = name;
        this.hours = hours;
        this.groupName = groupName;
    }

    public Course(String name, Integer hours, String groupName) {
        this.name = name;
        this.hours = hours;
        this.groupName = groupName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    @PrimaryKeyJoinColumn
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    @Size(min = 5, max = 100)
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Column(name = "hours")
    public Integer getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    @NotNull
    @Size(min = 5, max = 100)
    @Column(name = "group_name")
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}

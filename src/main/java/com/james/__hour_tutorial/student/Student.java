package com.james.__hour_tutorial.student;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.james.__hour_tutorial.studentprofile.StudentProfile;
import com.james.__hour_tutorial.school.School;
import jakarta.persistence.*;

@Entity
@Table(name = "yomama")
public class Student {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String firstname;

    @Column(name = "lastname!", length = 20)
    private String lastname;

    @Column(unique = true)
    private String email;

    @Column(updatable = false)
    private Integer age;

    @OneToOne(
            mappedBy = "student",
            cascade = CascadeType.ALL
    )
    private StudentProfile studentProfile;

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public StudentProfile getStudentProfile() {
        return studentProfile;
    }

    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }

    @ManyToOne
    @JoinColumn(
            name = "school-id"
    )
    @JsonBackReference
    private School school;

    public Student() {
    }

    public Student(String firstname, String lastname, String email, Integer age) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}

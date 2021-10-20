package com.example.bai19_autocompletetextview;

import java.util.Date;

public class Student {
    private String id;
    private String name;
    private boolean gender;
    private Date birthday;
    private String placeOfBirth;

    public Student() {
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isGender() {
        return gender;
    }
    public void setGender(boolean gender) {
        this.gender = gender;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getPlaceOfBirth() {
        return placeOfBirth;
    }
    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }
    public Student(String id, String name, boolean gender, Date birthday,
                   String placeOfBirth) {
        super();
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.placeOfBirth = placeOfBirth;
    }
}

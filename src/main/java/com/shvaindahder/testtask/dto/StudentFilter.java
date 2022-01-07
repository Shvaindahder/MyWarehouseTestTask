package com.shvaindahder.testtask.dto;


public class StudentFilter {
    private String surname;

    public StudentFilter() {
    }

    public StudentFilter(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}

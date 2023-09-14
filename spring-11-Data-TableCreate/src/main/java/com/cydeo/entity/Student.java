package com.cydeo.entity;


import jakarta.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "studentFirstName")
    private String firstName;
    @Column(name = "studentLastName")
    private String lastName;
    private String email;
}

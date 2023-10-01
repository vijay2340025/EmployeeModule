package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "employee")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String dob;
}

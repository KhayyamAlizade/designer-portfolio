package com.design.portfolio.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String email;
    private String password;
    private String role;
    @ElementCollection
    private List<String> roles;


}

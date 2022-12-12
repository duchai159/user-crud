package com.example.usercrud.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "UserLogin")
@Data
public class UserLogin {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    private String password;
}

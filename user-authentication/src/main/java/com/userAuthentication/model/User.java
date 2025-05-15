package com.userAuthentication.model;


import com.userAuthentication.enums.SalaryType;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "user_email")//if nullable not mentioned then its considered as null by default by jpa
    private String userEmail;

    @Enumerated(EnumType.STRING)
    @Column(name = "salary_type", nullable = false)
    private SalaryType salaryType;

    @Column(name = "salary", nullable = false)
    private Double salary;

    @Column(name = "user_address", nullable = false)
    private String address;

    @Column(name = "user_active_or_inactive")
    private boolean status;

    @Lob
    @Column(name = "user_photo", columnDefinition = "BYTEA")//this is null by default
    private byte[] photo;

    @Lob
    @Column(name = "user_document", columnDefinition = "BYTEA")
    private byte[] document;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "notes") //nullable by default
    private String notes;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
//    @JoinTable(
//            name = "users_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id")
//    )
    private Set<Role> roles = new HashSet<>();





}

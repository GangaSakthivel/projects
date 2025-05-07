package com.example.UserAuthenticationSpring.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {



        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private Long phoneNumber;
        private String password;
        private Double salary;

        @Enumerated(EnumType.STRING)
        private Status status;

        //Employee ID (Auto generated),
        //Employee Name, Phone Number, Salary and Account Status.


}


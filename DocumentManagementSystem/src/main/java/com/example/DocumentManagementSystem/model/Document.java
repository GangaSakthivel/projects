package com.example.DocumentManagementSystem.model;


import jakarta.persistence.*;

import java.sql.Time;

@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String fileType;
    private Time uploadTime;
    @Lob
    private byte[] fileData;


}

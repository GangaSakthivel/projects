package com.example.userManagement.model;


import jakarta.persistence.*;
import lombok.*;
@AllArgsConstructor //Useful for manual object creation
@NoArgsConstructor //Required by JPA/Hibernate
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    //if no column name provided then field name would be taken as a column name
    private String password;

    @ManyToOne (fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "location_id") //location_id refers to the id column in the locations table.
    private Location location;

    //fetch = FetchType.EAGER: When you fetch a user from the database,
    // JPA will also automatically fetch the location for that user.

    //optional = false: A user must have a location (cannot be null).
    //You can't save a user without assigning a location.

    //


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}

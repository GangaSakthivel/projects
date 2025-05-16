package com.userAuthentication.model;

import com.userAuthentication.enums.RoleName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role  {


    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id ;

    @Column(name = "role_name", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    RoleName roleName ;




}

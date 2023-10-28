package com.scaler.adminmanagementservice.models;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Admin {
    @Id
    @GeneratedValue(generator = "idgenerator")
    @GenericGenerator(name = "idgenerator", strategy = "increment")
    private Long id;

    private String name;

    @Column(unique=true)
    private String email;
    private String password;
}

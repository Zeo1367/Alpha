package com.eduprimehub.alpha.models.entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseActorEntity extends BaseEntity {

    @Column(name = "user_type",columnDefinition = "varchar(20)",nullable = false)
    private String firstName;

    @Column(name = "user_type",columnDefinition = "varchar(20)",nullable = false)
    private String middleName;

    @Column(name = "user_type",columnDefinition = "varchar(20)",nullable = false)
    private String lastName;

    @Column(name = "user_type",columnDefinition = "varchar(20)",nullable = false)
    private String mobileNumber;

    @Column(name = "user_type",columnDefinition = "varchar(20)",nullable = false)
    private String email;

    @Column(name = "user_type",columnDefinition = "varchar(20)",nullable = false)
    private String gender;
}

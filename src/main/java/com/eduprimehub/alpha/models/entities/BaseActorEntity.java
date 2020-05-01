package com.eduprimehub.alpha.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created by Aditya
 */
@ToString
@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public class BaseActorEntity extends BaseEntity {

    @Column(name = "first_name", columnDefinition = "varchar(20)", nullable = false)
    private String firstName;

    @Column(name = "middle_name", columnDefinition = "varchar(20)", nullable = false)
    private String middleName;

    @Column(name = "last_name", columnDefinition = "varchar(20)", nullable = false)
    private String lastName;

    @Column(name = "mobile_number", columnDefinition = "varchar(12)", nullable = false)
    private String mobileNumber;

    @Column(name = "email", columnDefinition = "varchar(30)", nullable = false)
    private String email;

    @Column(name = "gender", columnDefinition = "varchar(6)", nullable = false)
    private String gender;

    @Column(name = "date_of_birth", columnDefinition = "Integer(20)", nullable = false)
    private Long dateOfBirth;
}

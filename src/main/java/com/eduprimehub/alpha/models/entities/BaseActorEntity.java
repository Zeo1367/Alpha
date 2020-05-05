package com.eduprimehub.alpha.models.entities;

import com.eduprimehub.alpha.models.enums.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Created by Aditya
 */
@ToString
@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public class BaseActorEntity extends BaseEntity {

    @Column(columnDefinition = "varchar(8)", nullable = false)
    private String uuid;

    @Column(name = "first_name", columnDefinition = "varchar(20)", nullable = false)
    private String firstName;

    @Column(name = "middle_name", columnDefinition = "varchar(15)", nullable = false)
    private String middleName;

    @Column(name = "last_name", columnDefinition = "varchar(20)", nullable = false)
    private String lastName;

    @Column(name = "mobile_number", columnDefinition = "varchar(20)", nullable = false)
    private String mobileNumber;

    @Column(columnDefinition = "varchar(45)", nullable = false)
    private String email;

    @Column(nullable = false)
    private Gender gender;

    @Column(name = "date_of_birth", columnDefinition = "Date", nullable = false)
    private Date dateOfBirth;
}

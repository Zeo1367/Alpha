package com.eduprimehub.alpha.models.objects;

import com.eduprimehub.alpha.models.enums.Gender;
import com.eduprimehub.alpha.models.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;
import java.util.Set;

/**
 * Created by Aditya
 */
@ToString
@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfo extends BaseObject {

    private Integer id;
    private String uuid;
    private String firstName;
    private String lastName;
    private String userName;
    private String mobileNumber;
    private String password;
    private Integer otp;
    private String email;
    private Gender gender;
    private String status;
    private UserType userType;
    private Date dateOfBirth;
    private Boolean isUserVerified;

    private Set<SportObject> sportObjects;
    private Set<ClubObject> clubObjects;
    private Set<CountryObject> countryObjects;

}

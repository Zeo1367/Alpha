package com.eduprimehub.alpha.models.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by Aditya
 */
@ToString
@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfo extends BaseObject {

    private String id;
    private String firstName;
    private String lastName;
    private String userName;
    private String mobileNumber;
    private String email;
    private String status;
    private String userType;
    private Boolean isUserVerified;

}

package com.eduprimehub.alpha.models.objects;

import com.eduprimehub.alpha.models.enums.UserAccountStatus;
import com.eduprimehub.alpha.models.enums.UserActivityStatus;
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
public class UserAccessDetailInfo extends BaseObject{

//    private String password;
    private Boolean isPremiumUser;
    private UserAccountStatus userAccountStatus;
    private UserActivityStatus userActivityStatus;
    private Integer otp;
    private String token;
    private Long tokenExpiryTime;
    private String tokenExpiryTimeUnit;
    private Boolean firstTimeLogin;

}

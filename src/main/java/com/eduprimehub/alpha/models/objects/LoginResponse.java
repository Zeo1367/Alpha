package com.eduprimehub.alpha.models.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class LoginResponse extends BaseObject {

    private UserInfo userInfo;
    private UserAccessDetailInfo userAccessDetailInfo;
    private String token;
    private String tokenExpiryTime;
    private String tokenExpiryTimeUnit;

}
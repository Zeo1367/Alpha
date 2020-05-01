package com.eduprimehub.alpha.models.objects;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginRequest extends BaseObject {

    private String userName;

    private String password;

    private String mobileNumber;

    private String email;
}

package com.eduprimehub.alpha.validators;

import com.eduprimehub.alpha.models.objects.BaseRequest;
import com.eduprimehub.alpha.models.objects.LoginRequest;
import com.eduprimehub.alpha.models.objects.UserInfo;
import com.eduprimehub.alpha.utils.ValidationUtils;
import org.springframework.stereotype.Component;

@Component
public class LoginValidator {

    public LoginRequest validateLoginRequestObject(BaseRequest<LoginRequest> loginRequestObject){

        ValidationUtils.assertNotNull(loginRequestObject,"Request must not be empty!");
        LoginRequest loginRequest = loginRequestObject.getRequest();

        ValidationUtils.assertNotNull(loginRequest,"Request Object must not be empty");
        ValidationUtils.assertNotNull(loginRequest.getUserName(), "Username must not be empty");
        ValidationUtils.assertNotNull(loginRequest.getPassword(), "Password must not be empty");
        return loginRequest;
    }

    public UserInfo validateSignUpRequestObject(BaseRequest<UserInfo> signUpRequestObject) {

        ValidationUtils.assertNotNull(signUpRequestObject,"Request must not be empty!");
        UserInfo userInfo = signUpRequestObject.getRequest();

        ValidationUtils.assertNotNull(userInfo,"Request Object must not be empty");
        ValidationUtils.assertNotNull(userInfo.getFirstName(),"First name must not be empty!");
        ValidationUtils.assertNotNull(userInfo.getLastName(),"Last name must not be empty!");
        ValidationUtils.assertNotNull(userInfo.getUserName(),"Username must not be empty!");
        ValidationUtils.assertNotNull(userInfo.getMobileNumber(),"Mobile number must not be empty!");
        ValidationUtils.assertNotNull(userInfo.getPassword(),"Password must not be empty!");

        return userInfo;
    }
}

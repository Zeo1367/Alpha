package com.eduprimehub.alpha.validators;

import com.eduprimehub.alpha.models.objects.BaseRequest;
import com.eduprimehub.alpha.models.objects.LoginRequest;
import com.eduprimehub.alpha.utils.ValidationUtils;
import org.springframework.stereotype.Component;

@Component
public class LoginValidator {

    public LoginRequest validateLoginRequestObject(BaseRequest<LoginRequest> loginRequestObject){
        ValidationUtils.assertNotNull(loginRequestObject);
        LoginRequest loginRequest = loginRequestObject.getRequest();
        ValidationUtils.assertNotNull(loginRequest,loginRequest.getUserName(),loginRequest.getPassword());
        return loginRequest;
    }

    public String validateForgorRequest(String userName){
        ValidationUtils.assertNotNull(userName);
        return userName;
    }
}

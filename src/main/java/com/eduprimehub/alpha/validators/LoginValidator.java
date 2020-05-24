package com.eduprimehub.alpha.validators;

import com.eduprimehub.alpha.models.objects.BaseRequest;
import com.eduprimehub.alpha.models.objects.OtpObject;
import com.eduprimehub.alpha.models.objects.UserInfo;
import com.eduprimehub.alpha.utils.ValidationUtils;
import org.springframework.stereotype.Component;

@Component
public class LoginValidator {

    public OtpObject validateLoginRequestObject(BaseRequest<OtpObject> loginRequestObject){

        ValidationUtils.assertNotNull(loginRequestObject,"Request must not be empty!");
        OtpObject otpObject = loginRequestObject.getRequest();

        ValidationUtils.assertNotNull(otpObject,"Request Object must not be empty");
//        ValidationUtils.assertNotNull(loginRequest.getUserName(), "Username must not be empty");
        ValidationUtils.assertNotNull(otpObject.getMobileNumber(), "MobileNumber must not be empty");
        ValidationUtils.assertNotNull(otpObject.getOtp(), "Otp must not be empty");
//        ValidationUtils.assertNotNull(loginRequest.getPassword(), "Password must not be empty");
        return otpObject;
    }

    public UserInfo validateSignUpRequestObject(BaseRequest<UserInfo> signUpRequestObject) {

        ValidationUtils.assertNotNull(signUpRequestObject,"Request must not be empty!");
        UserInfo userInfo = signUpRequestObject.getRequest();

        ValidationUtils.assertNotNull(userInfo,"Request Object must not be empty");
//        ValidationUtils.assertNotNull(userInfo.getFirstName(),"First name must not be empty!");
//        ValidationUtils.assertNotNull(userInfo.getLastName(),"Last name must not be empty!");
        ValidationUtils.assertNotNull(userInfo.getUserName(),"Username must not be empty!");
        ValidationUtils.assertNotNull(userInfo.getMobileNumber(),"Mobile number must not be empty!");
//        ValidationUtils.assertNotNull(userInfo.getPassword(),"Password must not be empty!");

        return userInfo;
    }

    public OtpObject validateOtpRequestObject(BaseRequest<OtpObject> otpObjectBaseRequest) {

        ValidationUtils.assertNotNull(otpObjectBaseRequest,"Request must not be empty!");
        OtpObject otpObject = otpObjectBaseRequest.getRequest();

        ValidationUtils.assertNotNull(otpObject,"Request Object must not be empty");
        ValidationUtils.assertNotNull(otpObject.getMobileNumber(), "MobileNumber must not be empty");
        ValidationUtils.assertNotNull(otpObject.getOtp(), "Otp must not be empty");
        return otpObject;
    }


    public OtpObject validateRequestForOtpObject(BaseRequest<OtpObject> otpObjectBaseRequest) {

        ValidationUtils.assertNotNull(otpObjectBaseRequest,"Request must not be empty!");
        OtpObject otpObject = otpObjectBaseRequest.getRequest();

        ValidationUtils.assertNotNull(otpObject,"Request Object must not be empty");
        ValidationUtils.assertNotNull(otpObject.getMobileNumber(), "MobileNumber must not be empty");
        return otpObject;
    }
}

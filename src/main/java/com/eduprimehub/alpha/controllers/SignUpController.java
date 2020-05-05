package com.eduprimehub.alpha.controllers;

import com.eduprimehub.alpha.models.objects.*;
import com.eduprimehub.alpha.services.SignUpService;
import com.eduprimehub.alpha.utils.ApplicationConstant;
import com.eduprimehub.alpha.validators.LoginValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping(ApplicationConstant.SIGNUP_BASE_URL)
public class SignUpController {

    @Autowired
    private SignUpService signupService;
    @Autowired
    private LoginValidator loginValidator;


    @PostMapping(value = ApplicationConstant.SIGNUP_USER_URL, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<LoginResponse> signUpExternalUser(@RequestBody BaseRequest<UserInfo> signUpRequestObject,
                                                          HttpServletRequest httpServletRequest) {
        GenericResponse<LoginResponse> response = new GenericResponse<>();
        try {
            UserInfo signUpUserInfo = loginValidator.validateSignUpRequestObject(signUpRequestObject);
            LoginResponse loginResponseObject = signupService.signUpExternalUser(signUpUserInfo);
            return response.createSuccessResponse(loginResponseObject, 200);

        } catch (BusinessException businessException) {
            log.info("Business Exp {}", String.valueOf(businessException.getCause()));
            return response.createErrorResponse(businessException.getErrorCode(), businessException.getMessage());

        } catch (Exception exception) {
            log.info("Business Exp {}", String.valueOf(exception.getCause()));
            return response.createErrorResponse(408, exception.getMessage());
        }
    }
}

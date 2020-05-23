package com.eduprimehub.alpha.controllers;

import com.eduprimehub.alpha.models.objects.*;
import com.eduprimehub.alpha.services.SignUpService;
import com.eduprimehub.alpha.utils.ApplicationConstant;
import com.eduprimehub.alpha.validators.LoginValidator;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping(ApplicationConstant.SIGNUP_BASE_URL)
public class SignUpController {

    @Autowired
    private SignUpService signupService;
    @Autowired
    private LoginValidator loginValidator;

    @CrossOrigin(origins = "*")
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

    @SneakyThrows
    @CrossOrigin(origins = "*")
    @PostMapping(value = ApplicationConstant.VALIDATE_SIGNUP_URL, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<?> validateSignup(@RequestBody BaseRequest<OtpObject> otpRequestObject,
                                       HttpServletRequest httpServletRequest) throws Exception {
        GenericResponse<LoginResponse> response = new GenericResponse<>();

        try {
            //Todo: validator to be more effective
            OtpObject otpObject = loginValidator.validateOtpRequestObject(otpRequestObject);
            LoginResponse loginResponseObject = signupService.validateSignup(otpObject);
            //Todo: look for the response logic more generic
//            return ResponseEntity.status(HttpStatus.CREATED).body(loginResponseObject);
            return response.createSuccessResponse(loginResponseObject, 200);

            //Todo: look for more generic exception logic

        } catch (BusinessException businessException) {
            log.info("Business Exp {}", String.valueOf(businessException.getCause()));
            return response.createErrorResponse(businessException.getErrorCode(), businessException.getMessage());

        } catch (Exception exception) {
            log.info("login Exp {}", String.valueOf(exception.getCause()));
            return response.createErrorResponse(408, exception.getMessage());
        }
    }
}

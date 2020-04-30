package com.eduprimehub.alpha.controllers;

import com.eduprimehub.alpha.models.objects.*;
import com.eduprimehub.alpha.services.SignupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller("/signup")
public class SignUpController {
    @Autowired
    private SignupService signupService;

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    public BaseResponse<LoginResponse> signUpExternalUser(@RequestBody BaseRequest<SignUpRequest> signUpRequestObject,
                                                          HttpServletRequest httpServletRequest) {
        GenericResponse<LoginResponse> response = new GenericResponse<>();
        try {
            SignUpRequest signUpRequest = signUpRequestObject.getRequest();
            LoginResponse loginResponseObject = signupService.signUpExternalUser(signUpRequest);
            return response.createSuccessResponse(loginResponseObject, null);
        } catch (BusinessException be) {
            log.info("Business Exp {}", be);
            return response.createErrorResponse(be.getErrorCode(), be.getMessage());
        } catch (Exception th) {
            log.info("login Exp {}", (Object) th.getStackTrace());
            return response.createErrorResponse(401, "ResponseCodeHandler.getMessage(401)");
        }
    }
}

package com.eduprimehub.alpha.controllers;

import com.eduprimehub.alpha.models.objects.*;
import com.eduprimehub.alpha.services.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(path = "/external", method = RequestMethod.POST)
    public BaseResponse<LoginResponse> loginExternalUser(@RequestBody BaseRequest<LoginRequest> loginRequestObject,
                                                      HttpServletRequest httpServletRequest) {
        GenericResponse<LoginResponse> response = new GenericResponse<LoginResponse>();
        try {
            LoginRequest loginRequest = loginRequestObject.getRequest();
            LoginResponse loginResponseObject = loginService.loginExternalUser(loginRequest);
            return response.createSuccessResponse(loginResponseObject, null);
        } catch (BusinessException be) {
            log.info("Business Exp {}", be);
            return response.createErrorResponse(be.getErrorCode(), be.getMessage());
        } catch (Exception th) {
            log.info("login Exp {}", (Object) th.getStackTrace());
            return response.createErrorResponse(401, "ResponseCodeHandler.getMessage(401)");
        }
    }

    @RequestMapping(path = "/internal", method = RequestMethod.POST)
    public BaseResponse<LoginResponse> loginInternalUser(@RequestBody BaseRequest<LoginRequest> loginRequestObject,
                                                        HttpServletRequest httpServletRequest) {
        GenericResponse<LoginResponse> response = new GenericResponse<LoginResponse>();
        try {
            LoginRequest loginRequest = loginRequestObject.getRequest();
            LoginResponse loginResponseObject = loginService.loginExternalUser(loginRequest);
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

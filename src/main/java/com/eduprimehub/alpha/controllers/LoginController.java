package com.eduprimehub.alpha.controllers;

import com.eduprimehub.alpha.models.objects.*;
import com.eduprimehub.alpha.services.LoginService;
import com.eduprimehub.alpha.utils.ApplicationConstant;
import com.eduprimehub.alpha.validators.LoginValidator;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequestMapping(ApplicationConstant.LOGIN_BASE_URL)
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private LoginValidator loginValidator;


    @SneakyThrows
    @PostMapping(value = ApplicationConstant.LOGIN_EXTERNAL_USER_URL, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<?> loginExternalUser(@RequestBody BaseRequest<LoginRequest> loginRequestObject,
                                             HttpServletRequest httpServletRequest) {
        GenericResponse<LoginResponse> response = new GenericResponse<>();

        try {
            //Todo: validator to be more effective
            LoginRequest loginRequest = loginValidator.validateLoginRequestObject(loginRequestObject);
            LoginResponse loginResponseObject = loginService.loginExternalUser(loginRequest);
            //Todo: look for the response logic more generic
//            return ResponseEntity.status(HttpStatus.CREATED).body(loginResponseObject);
            return response.createSuccessResponse(loginResponseObject, 200);
            //Todo: look for more generic exception logic
        } catch (BusinessException be) {
            log.info("Business Exp {}", (Object) be.getStackTrace());
            throw new BusinessException(be.getMessage());
        } catch (Exception th) {
            log.info("login Exp {}", (Object) th.getStackTrace());
            throw new BusinessException(th.getMessage());
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

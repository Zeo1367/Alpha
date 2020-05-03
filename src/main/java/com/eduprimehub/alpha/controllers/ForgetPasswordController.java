package com.eduprimehub.alpha.controllers;


import com.eduprimehub.alpha.models.objects.*;
import com.eduprimehub.alpha.services.ForgetPasswordService;
import com.eduprimehub.alpha.validators.LoginValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RestController("/password")
public class ForgetPasswordController {
    @Autowired
    private ForgetPasswordService forgetPasswordService;
    @Autowired
    private LoginValidator loginValidator;


    @RequestMapping(path ="/forgot", method = RequestMethod.GET)
        public BaseResponse<ForgotResponse> forgetPassword(@RequestParam String userName, HttpServletRequest httpServletRequest)
    {
        GenericResponse<ForgotResponse> response = new GenericResponse<>();

        try {
            String userNameRequest = loginValidator.validateForgorRequest(userName);

            ForgotResponse forgotResponse = forgetPasswordService.forgotPasswordService(userNameRequest);

            return response.createSuccessResponse(forgotResponse, null);
        } catch (Exception th) {
            log.info("forgot Exp {}", (Object) th.getStackTrace());
            return response.createErrorResponse(401, "ResponseCodeHandler.getMessage(401)");
        }






    }




}

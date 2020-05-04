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
//Aditya: above mapping of password is perfect but always name classes with service not parts of feature
//eg. password is a service but forgot password is a specific feature of password service.
// other features can be update password so if you name this as forgot password then you won't be able to create
// update password api here and then you have to make a new class for it which will be troublesome because
// again all re work of creating class.
public class ForgetPasswordController {

    //Aditya: this is showing warning that it will not create bean of it please check why?
    @Autowired
    private ForgetPasswordService forgetPasswordService;

    //Aditya: here it can work but always create a different validator class of a service never mix two service together
    @Autowired
    private LoginValidator loginValidator;


    @RequestMapping(path ="/forgot", method = RequestMethod.GET)
        public BaseResponse<ForgotResponse> forgetPassword(@RequestParam String userName, HttpServletRequest httpServletRequest)
    {
        GenericResponse<ForgotResponse> response = new GenericResponse<>();

        try {
            //Aditya: naming of the method
            String userNameRequest = loginValidator.validateForgorRequest(userName);

            ForgotResponse forgotResponse = forgetPasswordService.forgotPasswordService(userNameRequest);

            return response.createSuccessResponse(forgotResponse, null);
        } catch (Exception th) {
            log.info("forgot Exp {}", (Object) th.getStackTrace());
            return response.createErrorResponse(401, "ResponseCodeHandler.getMessage(401)");
        }






    }




}

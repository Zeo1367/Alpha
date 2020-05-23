package com.eduprimehub.alpha.controllers;

import com.eduprimehub.alpha.models.objects.*;
import com.eduprimehub.alpha.services.OtpService;
import com.eduprimehub.alpha.utils.ApplicationConstant;
import com.eduprimehub.alpha.validators.LoginValidator;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequestMapping(ApplicationConstant.OTP_BASE_URL)
@RestController
public class OtpController {

    @Autowired
    private LoginValidator loginValidator;

    @Autowired
    private OtpService otpService;

    @SneakyThrows
    @CrossOrigin(origins = "*")
    @PostMapping(value = ApplicationConstant.LOGIN_EXTERNAL_USER_URL, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<?> validateOtp(@RequestBody BaseRequest<OtpObject> otpRequestObject,
                                             HttpServletRequest httpServletRequest) throws Exception {
        GenericResponse<LoginResponse> response = new GenericResponse<>();

        try {
            //Todo: validator to be more effective
            OtpObject otpObject = loginValidator.validateOtpRequestObject(otpRequestObject);
            LoginResponse loginResponseObject = otpService.validateOtp(otpObject);
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

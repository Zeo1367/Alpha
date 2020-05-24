package com.eduprimehub.alpha.controllers;

import com.eduprimehub.alpha.models.objects.*;
import com.eduprimehub.alpha.services.ProfileService;
import com.eduprimehub.alpha.utils.ApplicationConstant;
import com.eduprimehub.alpha.utils.ValidationUtils;
import com.eduprimehub.alpha.validators.ProfileValidator;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
public class ProfileController {

    @Autowired
    private ProfileValidator profileValidator;
    @Autowired
    private ProfileService profileService;


    public ResponseEntity<?> fetchUserProfile(@RequestBody BaseRequest<UserInfo> profileRequest) {

        return null;
    }

    @SneakyThrows
    @CrossOrigin(origins = "*")
    @GetMapping(value = ApplicationConstant.LOAD_DATA_URL, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<?> loadData(@RequestParam(required = false) Integer sport) throws Exception {
        GenericResponse<UserInfo> response = new GenericResponse<>();

        try {
            //Todo: validator to be more effective
//            profileValidator.validateLoadDataObject(sport);
            UserInfo userInfo = profileService.loadData(sport);
            //Todo: look for the response logic more generic
//            return ResponseEntity.status(HttpStatus.CREATED).body(loginResponseObject);
            return response.createSuccessResponse(userInfo, 200);

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

package com.eduprimehub.alpha.services;

import com.eduprimehub.alpha.helpers.UserHelper;
import com.eduprimehub.alpha.models.entities.User;
import com.eduprimehub.alpha.models.entities.UserAccessDetails;
import com.eduprimehub.alpha.models.enums.TokenTag;
import com.eduprimehub.alpha.models.objects.*;
import com.eduprimehub.alpha.repositories.UserAccessDetailsRepository;
import com.eduprimehub.alpha.repositories.UserRepository;
import com.eduprimehub.alpha.utils.TokenHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class SignUpService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHelper userHelper;

    @Autowired
    private TokenHandler tokenHandler;

    @Qualifier("userAccessDetailsRepository")
    @Autowired
    private UserAccessDetailsRepository userAccessDetailsRepository;

    @Autowired
    private OtpService otpService;
    @Autowired
    private LoginService loginService;


    public LoginResponse signUpExternalUser(UserInfo signUpUserInfo) throws BusinessException {
        User user = userRepository.findUserByUserNameOrMobileNumber(signUpUserInfo.getUserName(), signUpUserInfo.getMobileNumber());

        if (user == null) {
            user = userHelper.getUserFromUserInfo(signUpUserInfo);
            userRepository.save(user);

            UserAccessDetails userAccessDetails = createUserAccessDetailsAfterSignup(user, signUpUserInfo.getPassword());
            otpService.createAndSaveOtp(user.getMobileNumber(), userAccessDetails);
            userAccessDetailsRepository.save(userAccessDetails);

            return userHelper.getLoginResponse(user, userAccessDetails, userAccessDetails.getToken());
        } else {
            log.error("user already exists!");
            throw new BusinessException(409, "user already exists");
        }
    }

    //Change the logic of token
    private UserAccessDetails createUserAccessDetailsAfterSignup(User user, String password) {

        Map<TokenTag, String> tokenMap = tokenHandler.createToken(user.getUserName(), user.getUuid());
        return userHelper.getUserAccessDetails(tokenMap, password, user);
    }



    public LoginResponse validateSignup(OtpObject otpObject) throws BusinessException {
        LoginResponse loginResponse = otpService.validateOtp(otpObject);
        if (loginResponse != null) {

            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setMobileNumber(otpObject.getMobileNumber());
            loginResponse = loginService.loginExternalUser(loginRequest);
            return loginResponse;
        }
        throw new BusinessException("Otp didn't match! Please enter the right value");
    }
}

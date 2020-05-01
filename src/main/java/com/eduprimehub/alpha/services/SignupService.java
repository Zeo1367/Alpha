package com.eduprimehub.alpha.services;

import com.eduprimehub.alpha.helpers.UserHelper;
import com.eduprimehub.alpha.models.entities.User;
import com.eduprimehub.alpha.models.objects.BusinessException;
import com.eduprimehub.alpha.models.objects.LoginResponse;
import com.eduprimehub.alpha.models.objects.SignUpRequest;
import com.eduprimehub.alpha.models.objects.UserInfo;
import com.eduprimehub.alpha.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserHelper userHelper;

    //TOdo: complete login
    public LoginResponse signUpExternalUser(SignUpRequest signUpRequest) throws BusinessException {
        LoginResponse loginResponse = new LoginResponse();
        UserInfo userInfo = signUpRequest.getUserInfo();
        User user = userRepository.findUserByIdAndIsActive(userInfo.getUserName(), true);
        if (user == null) {
            user = userHelper.getUserFromUserInfo(userInfo);
            //Todo: complete loginResponse
            user = userRepository.save(user);
            BeanUtils.copyProperties(user, userInfo);
            loginResponse.setUserInfo(userInfo);
            return loginResponse;
        }
        return null;
    }
}

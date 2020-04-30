package com.eduprimehub.alpha.services;

import com.eduprimehub.alpha.models.entities.User;
import com.eduprimehub.alpha.models.objects.*;
import com.eduprimehub.alpha.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupService {
    @Autowired
    private UserRepository userRepository;

    //TOdo: complete login
    public LoginResponse signUpExternalUser(SignUpRequest signUpRequest) throws BusinessException {
        LoginResponse loginResponse = new LoginResponse();
        UserInfo userInfo = signUpRequest.getUserInfo();
        User user = userRepository.findUserByIdAndIsActive(userInfo.getUserName(),true);
        if(user==null){
            user = new User();
            //Todo: switch this to helper
            BeanUtils.copyProperties(userInfo,user);
            //Todo: complete loginResponse
            user = userRepository.save(user);
            BeanUtils.copyProperties(user,userInfo);
            loginResponse.setUserInfo(userInfo);
            return loginResponse;
        }
        return null;
    }
}

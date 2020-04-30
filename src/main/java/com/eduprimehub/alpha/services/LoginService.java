package com.eduprimehub.alpha.services;

import com.eduprimehub.alpha.models.entities.User;
import com.eduprimehub.alpha.models.objects.BusinessException;
import com.eduprimehub.alpha.models.objects.LoginRequest;
import com.eduprimehub.alpha.models.objects.LoginResponse;
import com.eduprimehub.alpha.models.objects.UserInfo;
import com.eduprimehub.alpha.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    //TOdo: complete login
    public LoginResponse loginExternalUser(LoginRequest loginRequest) throws BusinessException {
        LoginResponse loginResponse = new LoginResponse();
        User user = userRepository.findUserByIdAndIsActive(loginRequest.getUserName(),true);
        if(user!=null){
            UserInfo userInfo = new UserInfo();
            //Todo: switch this to helper
            BeanUtils.copyProperties(user,userInfo);
            //Todo: complete loginResponse
            loginResponse.setUserInfo(userInfo);
            return loginResponse;
        }
        return null;
    }
}

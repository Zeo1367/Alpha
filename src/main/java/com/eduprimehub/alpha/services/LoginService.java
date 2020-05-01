package com.eduprimehub.alpha.services;

import com.eduprimehub.alpha.helpers.UserHelper;
import com.eduprimehub.alpha.models.entities.User;
import com.eduprimehub.alpha.models.objects.BusinessException;
import com.eduprimehub.alpha.models.objects.LoginRequest;
import com.eduprimehub.alpha.models.objects.LoginResponse;
import com.eduprimehub.alpha.models.objects.UserInfo;
import com.eduprimehub.alpha.repositories.UserRepository;
import com.eduprimehub.alpha.utils.TokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserHelper userHelper;
    @Autowired
    private TokenHandler tokenHandler;

    //TOdo: complete login
    public LoginResponse loginExternalUser(LoginRequest loginRequest) throws BusinessException {
        LoginResponse loginResponse = new LoginResponse();
        User user = userRepository.findUserByIdAndIsActive(loginRequest.getUserName(), true);
        if (user != null) {
            UserInfo userInfo = userHelper.getUserInfoFromUser(user);
            //TOdo: apply redis fetcher for the token
            //Todo: fetch token for the user
            Map<String,String> tokenMap = tokenHandler.fetchToken( userInfo.getId());
            if(tokenMap==null){
                tokenMap = tokenHandler.createToken(userInfo.getUserName(), userInfo.getId());
            }
            //Todo: complete loginResponse
            loginResponse.setUserInfo(userInfo);
            loginResponse.setToken(tokenMap.get("TOKEN"));

            //Todo: correct token Expiry logic
            loginResponse.setTokenExpiry(Long.valueOf(tokenMap.get("TIMEOUT")));

            return loginResponse;
        } else {
            throw  new BusinessException("user doesn't exists!");
        }
    }
}

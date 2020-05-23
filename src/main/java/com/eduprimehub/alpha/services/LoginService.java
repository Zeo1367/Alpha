package com.eduprimehub.alpha.services;

import com.eduprimehub.alpha.helpers.UserHelper;
import com.eduprimehub.alpha.models.entities.User;
import com.eduprimehub.alpha.models.entities.UserAccessDetails;
import com.eduprimehub.alpha.models.enums.TokenTag;
import com.eduprimehub.alpha.models.enums.UserAccountStatus;
import com.eduprimehub.alpha.models.objects.BusinessException;
import com.eduprimehub.alpha.models.objects.LoginRequest;
import com.eduprimehub.alpha.models.objects.LoginResponse;
import com.eduprimehub.alpha.repositories.UserAccessDetailsRepository;
import com.eduprimehub.alpha.repositories.UserRepository;
import com.eduprimehub.alpha.utils.TokenHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHelper userHelper;

    @Autowired
    private TokenHandler tokenHandler;

    @Qualifier("userAccessDetailsRepository")
    @Autowired
    private UserAccessDetailsRepository userAccessDetailsRepository;


    public LoginResponse loginExternalUser(LoginRequest loginRequest) throws BusinessException {
        User user = userRepository.findUserByMobileNumber(loginRequest.getMobileNumber());
        UserAccessDetails userAccessDetails;

        if (user != null) {
            String token = tokenHandler.fetchToken(user.getUserName());
            Map<TokenTag, String> tokenMap;

            if (token != null) {
                return userHelper.getLoginResponse(user, null, token);

            } else {
                log.info("Token is null! fetching userAccessDetails");
                userAccessDetails = userAccessDetailsRepository.findUserAccessDetailsByUuidAndAndUserAccountStatus
                        (user, UserAccountStatus.ACTIVE.getUserAccountStatus());

                if (userAccessDetails != null) {
                    tokenMap = tokenHandler.createToken(user.getUserName(), user.getUuid());
                    updateNewTokenToUserAccessDetails(userAccessDetails, tokenMap);

                    return userHelper.getLoginResponse(user, userAccessDetails, userAccessDetails.getToken());
                } else {
                    throw new BusinessException(409, "User is not Active! Please contact Admin!");
                }
            }
        } else {
            throw new BusinessException(409, "user doesn't exists!");
        }
    }

    private void updateNewTokenToUserAccessDetails(UserAccessDetails userAccessDetails, Map<TokenTag, String> tokenMap) {
        userAccessDetails.setToken(tokenMap.get(TokenTag.TOKEN));
        userAccessDetails.setTokenExpiryTime(Long.valueOf(tokenMap.get(TokenTag.TIMEOUT)));
        userAccessDetailsRepository.save(userAccessDetails);
    }
}

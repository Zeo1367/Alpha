package com.eduprimehub.alpha.helpers;

import com.eduprimehub.alpha.models.entities.User;
import com.eduprimehub.alpha.models.entities.UserAccessDetails;
import com.eduprimehub.alpha.models.enums.TokenTag;
import com.eduprimehub.alpha.models.enums.UserAccountStatus;
import com.eduprimehub.alpha.models.enums.UserActivityStatus;
import com.eduprimehub.alpha.models.enums.UserType;
import com.eduprimehub.alpha.models.objects.LoginResponse;
import com.eduprimehub.alpha.models.objects.UserAccessDetailInfo;
import com.eduprimehub.alpha.models.objects.UserInfo;
import com.eduprimehub.alpha.properties.AlphaProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class UserHelper {


    @Autowired
    private AlphaProperties alphaProperties;

    public User getUserFromUserInfo(UserInfo userInfo) {
        User user = new User();
        BeanUtils.copyProperties(userInfo, user);

        user.setUuid(RandomStringUtils.randomAlphanumeric(8));
        if (user.getUserType() == null) user.setUserType(UserType.PLAYER.getUserType());
        return user;
    }

    public UserInfo getUserInfoFromUser(User user) {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(user, userInfo);
        return userInfo;
    }

    public UserAccessDetails getUserAccessDetails(Map<TokenTag, String> tokenMap, String password, User user) {
        UserAccessDetails userAccessDetails = new UserAccessDetails();

        userAccessDetails.setUuid(user);
        userAccessDetails.setIsPremiumUser(Boolean.FALSE);
        userAccessDetails.setPassword(password);
        userAccessDetails.setToken(tokenMap.get(TokenTag.TOKEN));
        userAccessDetails.setTokenExpiryTime(Long.valueOf(tokenMap.get(TokenTag.TIMEOUT)));
        userAccessDetails.setUserAccountStatus(UserAccountStatus.ACTIVE.getUserAccountStatus());
        userAccessDetails.setUserActivityStatus(UserActivityStatus.OFFLINE.getUserActivityStatus());
        return userAccessDetails;
    }

    public LoginResponse getLoginResponse(User user, UserAccessDetails userAccessDetails, String token) {
        LoginResponse loginResponse = new LoginResponse();

        if (user != null)
            loginResponse.setUserInfo(getUserInfoFromUser(user));
        if (userAccessDetails != null)
            loginResponse.setUserAccessDetailInfo(getUserAccessDetailsInfo(userAccessDetails));

        loginResponse.setToken(token);
        loginResponse.setTokenExpiryTime(alphaProperties.getTokenExpiryTime());
        loginResponse.setTokenExpiryTimeUnit(alphaProperties.getTokenExpiryTimeUnit());
        return loginResponse;
    }

    private UserAccessDetailInfo getUserAccessDetailsInfo(UserAccessDetails userAccessDetails) {
        UserAccessDetailInfo userAccessDetailInfo = new UserAccessDetailInfo();

        BeanUtils.copyProperties(userAccessDetails, userAccessDetailInfo);
        return userAccessDetailInfo;
    }
}

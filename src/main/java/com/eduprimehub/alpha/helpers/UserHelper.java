package com.eduprimehub.alpha.helpers;

import com.eduprimehub.alpha.models.entities.User;
import com.eduprimehub.alpha.models.objects.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserHelper {


    public User getUserFromUserInfo(UserInfo userInfo) {
        User user = new User();
        BeanUtils.copyProperties(userInfo, user);
        return user;
    }

    public UserInfo getUserInfoFromUser(User user) {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(user, userInfo);
        return userInfo;
    }
}

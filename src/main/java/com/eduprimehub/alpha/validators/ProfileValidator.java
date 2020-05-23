package com.eduprimehub.alpha.validators;

import com.eduprimehub.alpha.models.enums.SportsEnum;
import com.eduprimehub.alpha.models.objects.BaseRequest;
import com.eduprimehub.alpha.models.objects.UserInfo;
import com.eduprimehub.alpha.utils.ValidationUtils;
import org.springframework.stereotype.Component;

@Component
public class ProfileValidator {

    public UserInfo validateLoadDataObject(BaseRequest<UserInfo> userInfoBaseRequest){

        ValidationUtils.assertNotNull(userInfoBaseRequest,"Request must not be empty!");
        UserInfo userInfo = userInfoBaseRequest.getRequest();

        ValidationUtils.assertNotNull(userInfo,"Request Object must not be empty");
//        ValidationUtils.assertNotNull(loginRequest.getUserName(), "Username must not be empty");
        ValidationUtils.assertNotNull(userInfo.getSportObjects(), "MobileNumber must not be empty");
//        ValidationUtils.assertNotNull(loginRequest.getPassword(), "Password must not be empty");
        return userInfo;
    }

    public void validateLoadDataObject(Integer sport){

        ValidationUtils.assertNotNull(sport,"sport name is incorrect must not be empty!");

    }
}

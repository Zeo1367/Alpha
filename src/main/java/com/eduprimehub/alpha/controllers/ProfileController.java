package com.eduprimehub.alpha.controllers;

import com.eduprimehub.alpha.models.objects.BaseRequest;
import com.eduprimehub.alpha.models.objects.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ProfileController {

    public ResponseEntity<?> fetchUserProfile(@RequestBody BaseRequest<UserInfo> profileRequest){

        return null;
    }
}

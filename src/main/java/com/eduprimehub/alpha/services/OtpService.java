package com.eduprimehub.alpha.services;

import com.eduprimehub.alpha.models.entities.User;
import com.eduprimehub.alpha.models.entities.UserAccessDetails;
import com.eduprimehub.alpha.models.enums.UserAccountStatus;
import com.eduprimehub.alpha.models.objects.*;
import com.eduprimehub.alpha.properties.AlphaProperties;
import com.eduprimehub.alpha.repositories.UserAccessDetailsRepository;
import com.eduprimehub.alpha.repositories.UserRepository;
import com.eduprimehub.alpha.utils.OtpUtils;
import com.eduprimehub.alpha.utils.ValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class OtpService {

    @Autowired
    private OtpUtils otpUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private AlphaProperties alphaProperties;

    @Autowired
    private UserAccessDetailsRepository userAccessDetailsRepository;

    public Integer fetchOtp(String mobileNumber) {
        User user = userRepository.findUserByMobileNumber(mobileNumber);

        if (user != null) {
            Integer otp = new Random().nextInt(999999);
            otpUtils.sendOtp(otp, user.getMobileNumber());
            return otp;
        }
        return null;
    }

    public LoginResponse validateOtp(OtpObject otpObject) throws BusinessException {
        Integer otp;
        String mobileNumber = otpObject.getMobileNumber();

        if (Boolean.parseBoolean(alphaProperties.getRedisActive())) {
            ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
            Object value = valueOperations.get(mobileNumber);

            if (value != null) {
                otp = (Integer) value;
            } else {
                otp = fetchOtpFromDB(mobileNumber);
            }
        } else {
                otp = fetchOtpFromDB(mobileNumber);
        }

        if (otpObject.getOtp().equals(otp)) {
            return new LoginResponse();
        }
        throw new BusinessException("Otp didn't match! Please enter the right value");
    }

    private Integer fetchOtpFromDB(String mobileNumber) throws BusinessException {
        User user = userRepository.findUserByMobileNumber(mobileNumber);
        UserAccessDetails userAccessDetails;

        if (user != null) {
            log.info("Otp is null! fetching userAccessDetails");
            userAccessDetails = userAccessDetailsRepository.findUserAccessDetailsByUuidAndAndUserAccountStatus
                    (user, UserAccountStatus.ACTIVE.getUserAccountStatus());

            if (userAccessDetails != null) {
                return userAccessDetails.getOtp();
            }
        }
        log.info("User doesn't exists for mobile {}", mobileNumber);
        throw new BusinessException("User doesn't exists! Please contact admin!!");
    }

    public void saveOtpToRedis(String mobileNumber, Integer otp) {
        int timeout = Integer.parseInt(alphaProperties.getTokenExpiryTime());
        String timeoutUnit = alphaProperties.getTokenExpiryTimeUnit();

        if (Boolean.parseBoolean(alphaProperties.getRedisActive())) {
            ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
            valueOperations.set(mobileNumber, otp, timeout, TimeUnit.valueOf(timeoutUnit));
        }
    }


}

package com.eduprimehub.alpha.services;

import com.eduprimehub.alpha.models.entities.User;
import com.eduprimehub.alpha.models.objects.ForgotResponse;
import com.eduprimehub.alpha.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

public class ForgetPasswordService {


    @Autowired
    private UserRepository userRepository;

    public static int generateRandomDigits(int n) {
        int m = (int) Math.pow(10, n - 1);
        return m + new Random().nextInt(9 * m);
    }

    public ForgotResponse forgotPasswordService(String userName){

        ForgotResponse forgotResponse = new ForgotResponse();
        User user = userRepository.findUserByIdAndIsActive(userName, true);
        String mobileNumber = user.getMobileNumber();
        forgotResponse.setMobileNumber(mobileNumber);
        int otp = ForgetPasswordService.generateRandomDigits(6);
        forgotResponse.setOtp(otp);
        return forgotResponse;

    }


}

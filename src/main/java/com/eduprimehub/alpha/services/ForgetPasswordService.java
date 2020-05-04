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
        //the method name is find user by id but you are passing userName instead of id
        User user = userRepository.findUserByIdAndIsActive(userName, true);

        //always have a null check on object you get because here if user is null (i.e. no such active user exists in our db)
        //then the next line will cause NullPointerException
        String mobileNumber = user.getMobileNumber();
        forgotResponse.setMobileNumber(mobileNumber);
        //This is good but we developers should always try for built in method having similar functionality ( saves time)
        //eg. look for apacheUtils library that may have random generator.
        int otp = ForgetPasswordService.generateRandomDigits(6);
        forgotResponse.setOtp(otp);

        //mention a Todo: send the otp to the user's mobile
        //this will help you in remembering what is remaining, you have saved for later.
        return forgotResponse;

    }


}

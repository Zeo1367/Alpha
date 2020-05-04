package com.eduprimehub.alpha.models.objects;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ForgotResponse extends BaseObject {

    String mobileNumber;
    //Aditya: always use Wrapper class for everything except loop logic
    //if any how otp sets as null here it will stop the functioning of the application
    //on the other hand if you use Integer instead of int then it will handle null and later we can correct it
    int otp;
}

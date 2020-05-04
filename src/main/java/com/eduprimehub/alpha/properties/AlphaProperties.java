package com.eduprimehub.alpha.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@Data
@Component
@Validated
@ConfigurationProperties("alpha")
public class AlphaProperties {

    @NotEmpty(message = "database name cannot be null!")
    private String primaryDatabaseName;

    @NotEmpty(message = "redis host cannot be null!")
    private String redisHost;

    @NotEmpty(message = "redis port cannot be null!")
    private String redisPort;

    @NotEmpty(message = "redis should either be active or inactive!")
    private String redisActive;

    @NotEmpty(message = "token expiry time cannot be null!")
    private String tokenExpiryTime;

    @NotEmpty(message = "token expiry time unit cannot be null!")
    private String tokenExpiryTimeUnit;
}

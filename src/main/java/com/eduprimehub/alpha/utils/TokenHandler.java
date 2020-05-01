package com.eduprimehub.alpha.utils;

import com.eduprimehub.alpha.properties.AlphaProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class TokenHandler {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private AlphaProperties alphaProperties;

    public Map<String,String> fetchToken(String id) {
        //Todo: Change valueOperations to bean
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        return (Map<String, String>) valueOperations.get(id);
    }

    public Map<String, String> createToken(String... keys) {
        Integer timeout = alphaProperties.getTokenExpiryTime();
        Map<String, String> tokenMap = new HashMap<>();
        Set<String> keysSet = getTokenKeysSet(keys);
        final byte[][] encodedByteArray = {new byte[0]};
        keysSet.forEach(
                (tokenString) ->
                        encodedByteArray[0] = Base64.getEncoder().encode((tokenString).getBytes()));
        tokenMap.put("TOKEN", Arrays.deepToString(encodedByteArray));
        tokenMap.put("TIMEOUT", String.valueOf(timeout));

        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(keys[0], tokenMap.toString(), timeout, TimeUnit.HOURS);
        return tokenMap;
    }

    private Set<String> getTokenKeysSet(String[] keys) {
        return new HashSet<>(Arrays.asList(keys));
    }

}

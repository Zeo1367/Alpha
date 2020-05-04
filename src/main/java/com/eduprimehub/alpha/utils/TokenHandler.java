package com.eduprimehub.alpha.utils;

import com.eduprimehub.alpha.models.enums.TokenTag;
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

    public String fetchToken(String id) {
        //Todo: Change valueOperations to bean
        if (Boolean.parseBoolean(alphaProperties.getRedisActive())) {
            ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
            Object value = valueOperations.get(id);

            if (value == null) return null;
            else {
                return String.valueOf(value);
            }
        }
        return null;
    }

    //optimize this method
    public Map<TokenTag, String> createToken(String... keys) {
        Map<TokenTag, String> tokenMap = new HashMap<>();

        int timeout = Integer.parseInt(alphaProperties.getTokenExpiryTime());
        String timeoutUnit = alphaProperties.getTokenExpiryTimeUnit();
        String token = String.valueOf(getTokenKeysSet(keys).hashCode());

        tokenMap.put(TokenTag.TOKEN, token);
        tokenMap.put(TokenTag.TIMEOUT, String.valueOf(timeout));
        tokenMap.put(TokenTag.TIMEOUT_UNIT, String.valueOf(timeoutUnit));

        if (Boolean.parseBoolean(alphaProperties.getRedisActive())) {
            ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
            valueOperations.set(keys[1], tokenMap.toString(), timeout, TimeUnit.valueOf(timeoutUnit));
        }
        return tokenMap;
    }

    private Set<String> getTokenKeysSet(String[] keys) {
        return new HashSet<>(Arrays.asList(keys));
    }

}

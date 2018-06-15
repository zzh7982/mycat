package com.sbjava.cat.service.impl;

import com.sbjava.cat.model.CatUser;
import com.sbjava.cat.utils.CatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * description: UserSession
 *
 * @author ralf
 * @version [1.0, 2018/6/14]
 */
@Service
public class UserSessionService {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private UserService userService;

    public void saveToken(String key, String value) {
        redisTemplate.opsForValue().set(key, value, 7, TimeUnit.DAYS);
    }

    public String getOpenId(String token) {
        if (StringUtils.isEmpty(token)) {
            throw new CatException("请重新登录", -100);
        }
        return redisTemplate.opsForValue().get(token).split("#")[0];
    }

    public String getSessionKey(String token) {
        if (StringUtils.isEmpty(token)) {
            throw new CatException("请重新登录", -100);
        }
        return redisTemplate.opsForValue().get(token).split("#")[1];
    }

    public CatUser getUserByToken(String token) {
        try {
            Map<String, Object> map = new HashMap<>(1);
            map.put("openId", getOpenId(token));
            return userService.findByPro(map).get(0);
        } catch (Exception e) {
            throw new CatException("请重新登录", -100);
        }
    }
}

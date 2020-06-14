package com.yiding.saas.ydsaas.web.jwt;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.yiding.saas.ydsaas.model.YdUser;
import com.yiding.saas.ydsaas.service.YdUserService;
import javafx.util.Pair;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Calendar;
import java.util.Date;


/**
 * jwt 核心service
 * 生成token 校验token
 */
@Service
public class JwtTokenService {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private YdUserService ydUserService;
    /**
     * 过期时间
     */
    @Value("${login.jwt.time}")
    private String time;

    /**
     * 复杂度
     */
    private int deep = 3;


    /**
     * 产生token
     *
     * @return
     */
    public String getToken(YdUser ydUser) {
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.SECOND, Integer.valueOf(time));
        Date expireDate = nowTime.getTime();
        long now = System.nanoTime();
        JwtContext.setUserInfo(ydUser.getUserLoginAccount(), ydUser);
        String token = JWT.create().withAudience(String.valueOf(ydUser.getUserLoginAccount()), String.valueOf(now))
                .withExpiresAt(expireDate)
                .sign(Algorithm.HMAC256(getSecret(now)));
        return token;
    }


    /**
     * 生成签名字符串
     *
     * @param nowTime
     * @return
     */
    private String getSecret(long nowTime) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("#").append(nowTime).append("$").append(nowTime << deep).append("&").append(nowTime >> deep);
        stringBuilder.append(stringBuilder.toString().hashCode());
        return stringBuilder.toString();
    }


    /**
     * 校验token合法性
     *
     * @param token
     * @return
     */
    public Pair<Boolean, String> varifyToken(String token) {
        if (StringUtils.isBlank(token)) {
            return new Pair<>(false, "toke is null");
        }
        try {
            String tokenSec = token.split("\\.")[1];
            String stringSec = new String(Base64.getDecoder().decode(tokenSec));
            JSONObject jsonObject = JSONObject.parseObject(stringSec);
            String[] arr = jsonObject.getObject("aud",String[].class);
            String userAccount = arr[0];
            Long uid = Long.valueOf(arr[1]);
            YdUser user = JwtContext.getUserInfo(userAccount);
            if (user == null) {
                user = ydUserService.findByAccount(userAccount);
                JwtContext.setUserInfo(userAccount, user);
            }
            JwtUserContext.setUser(user);
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(getSecret(uid))).build();
            jwtVerifier.verify(token);
            return new Pair<>(true, "ok");
        } catch (Exception e) {
            log.error("varifyToken::error:", "token 不合法");
            return new Pair<>(false, "未登录");
        }
    }
}

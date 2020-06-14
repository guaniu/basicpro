package com.yiding.saas.ydsaas.web.jwt;

import com.yiding.saas.ydsaas.model.YdUser;

import java.util.concurrent.ConcurrentHashMap;

public class JwtContext {

    private static ConcurrentHashMap<String, YdUser> concurrentHashMap;

    private static ConcurrentHashMap<String, YdUser> getConcurrentHashMap() {
        if (concurrentHashMap == null) {
            concurrentHashMap = new ConcurrentHashMap<>();
        }
        return concurrentHashMap;
    }

    public static void setUserInfo(String account, YdUser ydUser) {
        getConcurrentHashMap().put(account, ydUser);
    }

    public static YdUser getUserInfo(String account) {
        return getConcurrentHashMap().get(account);
    }

}

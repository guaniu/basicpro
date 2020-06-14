package com.yiding.saas.ydsaas.web.jwt;

import com.yiding.saas.ydsaas.model.YdUser;


/**
 * 用户上下文信息
 */
public class JwtUserContext {
    private static ThreadLocal<YdUser> userInfo = new ThreadLocal<YdUser>();

    public JwtUserContext() {
    }

    public static YdUser getUser() {
        return (YdUser) userInfo.get();
    }

    public static void setUser(YdUser user) {
        userInfo.set(user);
    }
}

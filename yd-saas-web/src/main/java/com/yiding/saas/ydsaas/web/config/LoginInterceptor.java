package com.yiding.saas.ydsaas.web.config;

import com.alibaba.fastjson.JSONObject;
import com.yiding.saas.ydsaas.web.core.HttpResult;
import com.yiding.saas.ydsaas.web.jwt.JwtTokenService;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtTokenService jwtTokenService;

    /**
     * 日志
     */
    Logger log = LoggerFactory.getLogger(this.getClass());


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse response, Object object) throws Exception {
        //从 http 请求头中取出 token
        String token = httpServletRequest.getHeader("token");
        Pair<Boolean, String> resultPair = jwtTokenService.varifyToken(token);
        if (!resultPair.getKey()) {
            log.info("jwtTokenService.varifyToken result:{}", resultPair.getValue());
        }
        if (!resultPair.getKey()) {
            processFailResp(response, "未登录");
            return false;
        }
        return true;
    }

    public void processFailResp(HttpServletResponse response, String errorMsg) throws Exception {
        ServletOutputStream out = response.getOutputStream();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        HttpResult httpResult = HttpResult.loginFail(errorMsg);
        JSONObject json = (JSONObject) JSONObject.toJSON(httpResult);
        out.write(json.toJSONString().getBytes());
        out.flush();
        out.close();
    }
}
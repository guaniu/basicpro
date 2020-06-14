package com.yiding.saas.ydsaas.web.controller.api;

import com.yiding.saas.ydsaas.vo.LoginBean;
import com.yiding.saas.ydsaas.web.biz.LoginBizService;
import com.yiding.saas.ydsaas.web.core.HttpResult;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiLoginController {
    /**
     * 日志
     */
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LoginBizService loginBizService;

    @PostMapping(value = "/login")
    public HttpResult login(@RequestBody LoginBean loginBean) {
        Pair<Boolean, Object> resultPair = loginBizService.appLogin(loginBean);
        if (!resultPair.getKey()) {
            log.info("loginBizService.appLogin resp:{}", resultPair.toString());
            return HttpResult.loginFail(resultPair.getValue().toString());
        }
        return HttpResult.ok(resultPair.getValue());
    }
}

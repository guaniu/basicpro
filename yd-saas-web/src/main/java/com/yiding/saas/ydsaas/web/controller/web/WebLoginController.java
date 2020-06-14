package com.yiding.saas.ydsaas.web.controller.web;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.yiding.saas.ydsaas.vo.LoginBean;
import com.yiding.saas.ydsaas.web.biz.LoginBizService;
import com.yiding.saas.ydsaas.web.core.HttpResult;
import javafx.util.Pair;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 登录控制器
 *
 * @author Louis
 * @date Oct 29, 2018
 */
@RestController
@RequestMapping("/web")
public class WebLoginController {

    @Autowired
    private Producer producer;
    /**
     * 登录业务实现
     */
    @Autowired
    private LoginBizService loginBizService;

    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        // 生成文字验证码
        String text = producer.createText();
        // 生成图片验证码
        BufferedImage image = producer.createImage(text);
        // 保存到验证码到 session
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    /**
     * pc登录接口
     */
    @PostMapping(value = "/login")
    public HttpResult login(@RequestBody LoginBean loginBean) {
        Pair<Boolean, Object> resultPair = loginBizService.sysLogin(loginBean);
        if (!resultPair.getKey()) {
            return HttpResult.loginFail(resultPair.getValue().toString());
        }
        return HttpResult.ok(resultPair.getValue());
    }

}

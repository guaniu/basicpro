package com.yiding.saas.ydsaas.web.biz;

import com.alibaba.fastjson.JSONObject;
import com.yiding.saas.ydsaas.common.enums.CommonEnum;
import com.yiding.saas.ydsaas.common.utils.PasswordUtils;
import com.yiding.saas.ydsaas.model.SysMenu;
import com.yiding.saas.ydsaas.model.YdOrganization;
import com.yiding.saas.ydsaas.model.YdUser;
import com.yiding.saas.ydsaas.service.SysMenuService;
import com.yiding.saas.ydsaas.service.YdOrganizationService;
import com.yiding.saas.ydsaas.service.YdUserService;
import com.yiding.saas.ydsaas.vo.LoginBean;
import com.yiding.saas.ydsaas.web.jwt.JwtTokenService;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.List;

/**
 * 登录业务具体实现
 */
@Service
public class LoginBizService {
    /**
     * 日志
     */
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private YdUserService ydUserService;

    @Autowired
    private YdOrganizationService ydOrganizationService;

    /**
     * jwt
     */
    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 系统登录(PC)
     *
     * @return
     */
    public Pair<Boolean, Object> sysLogin(LoginBean loginBean) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        JSONObject rtnJson = new JSONObject();
        Pair<Boolean, Object> pair = checkUser(loginBean);
        stopWatch.stop();
        if (!pair.getKey()) {
            return pair;
        }
        YdUser user = (YdUser) pair.getValue();
        stopWatch.start();
        // 生成token
        String token = jwtTokenService.getToken(user);
        stopWatch.stop();
        JSONObject dataJson = new JSONObject();
        stopWatch.start();
        List<SysMenu> menuTree = sysMenuService.findTree(loginBean.getAccount(), 0, 3);
        YdOrganization org = ydOrganizationService.queryById(Long.valueOf(user.getUserOrgId()));
        /**新增返回组织类型*/
        dataJson.put("orgType", org.getOrgType());
        stopWatch.stop();
        log.info("login:process:");
        log.info(stopWatch.prettyPrint());
        dataJson.put("menuTree", menuTree);
        dataJson.put("token", token);
        dataJson.put("user", user);
        dataJson.put("orgType", org.getOrgType());
        rtnJson.put("data", dataJson);
        return new Pair<>(true, rtnJson);
    }


    /**
     * 移动登录
     *
     * @param loginBean
     * @return
     */
    public Pair<Boolean, Object> appLogin(LoginBean loginBean) {
        JSONObject rtnJson = new JSONObject();
        Pair<Boolean, Object> pair = checkUser(loginBean);
        if (!pair.getKey()) {
            return pair;
        }
        // 生成token
        String token = jwtTokenService.getToken((YdUser) pair.getValue());
        YdUser user = (YdUser) pair.getValue();
        YdOrganization org = ydOrganizationService.queryById(Long.valueOf(user.getUserOrgId()));
        JSONObject dataJson = new JSONObject();
        dataJson.put("token", token);
        dataJson.put("user", user);
        /**新增返回组织类型*/
        dataJson.put("orgType", org.getOrgType());
        rtnJson.put("data", dataJson);
        return new Pair<>(true, rtnJson);
    }


    /**
     * 校验用户合法性
     *
     * @param loginBean
     * @return
     */
    public Pair<Boolean, Object> checkUser(LoginBean loginBean) {
        String username = loginBean.getAccount();
        String password = loginBean.getPassword();
        YdUser ydUser = ydUserService.findByAccount(username);
        if (ydUser == null) {
            return new Pair<>(false, "用户不存在");
        }
        String enc_password = PasswordUtils.encode(password, "");
        if (!enc_password.equals(ydUser.getUserPwd())) {
            return new Pair<>(false, "用户名或密码错误");
        }
        return new Pair<>(true, ydUser);
    }
}

package com.yiding.saas.ydsaas.web.controller.web;

import com.github.pagehelper.PageInfo;
import com.yiding.saas.ydsaas.dto.YdUserDto;
import com.yiding.saas.ydsaas.model.YdUser;
import com.yiding.saas.ydsaas.vo.UserVo;
import com.yiding.saas.ydsaas.web.biz.UserBizService;
import com.yiding.saas.ydsaas.web.core.HttpResult;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户
 */
@RestController
@RequestMapping("/web/user")
public class WebUserController {

    /**
     * 日志
     */
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserBizService userBizService;

    /**
     * 新增用户
     *
     * @param ydUser
     * @return
     */
    @PostMapping("/save")
    public Object saveUser(@RequestBody YdUser ydUser) {
        Pair<Boolean, String> resultPair = userBizService.saveUser(ydUser);
        if (!resultPair.getKey()) {
            log.info("userBizService.saveUser result:{}", resultPair);
            return HttpResult.error(resultPair.getValue());
        }
        return HttpResult.ok(resultPair.getValue());
    }

    /**
     * 删除
     *
     * @param
     * @return
     */
    @PostMapping("/delete")
    public Object deleteUser(@RequestBody YdUser ydUser) {
        Pair<Boolean, String> resultPair = userBizService.deleteUser(ydUser.getId());
        if (!resultPair.getKey()) {
            log.info("userBizService.deleteUser result:{}", resultPair);
            return HttpResult.error(resultPair.getValue());
        }
        return HttpResult.ok(resultPair.getValue());
    }


    /**
     * 编辑
     *
     * @return
     */
    @PostMapping("/get")
    public Object getUser(@RequestBody YdUser ydUser) {
        YdUser user = userBizService.getYdUserById(ydUser.getId());
        return HttpResult.ok(user);
    }


    /**
     * 更新
     *
     * @param ydUser
     * @return
     */
    @PostMapping("/update")
    public Object updateUser(@RequestBody YdUser ydUser) {
        Pair<Boolean, String> resultPair = userBizService.updateUser(ydUser);
        if (!resultPair.getKey()) {
            log.info("userBizService.updateUser result:{}", resultPair);
            return HttpResult.error(resultPair.getValue());
        }
        return HttpResult.ok(resultPair.getValue());
    }

    /**
     * 列表
     *
     * @param ydUserDto
     * @return
     */
    @PostMapping("/list")
    public Object userList(@RequestBody YdUserDto ydUserDto) {
        PageInfo<UserVo> userList = userBizService.ydUserList(ydUserDto);
        return HttpResult.ok(userList);
    }
}

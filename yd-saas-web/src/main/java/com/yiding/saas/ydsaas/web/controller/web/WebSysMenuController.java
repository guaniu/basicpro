package com.yiding.saas.ydsaas.web.controller.web;

import com.yiding.saas.ydsaas.model.SysMenu;
import com.yiding.saas.ydsaas.service.SysMenuService;
import com.yiding.saas.ydsaas.web.core.HttpResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 菜单控制器
 *
 * @author Louis
 * @date Oct 29, 2018
 */
@RestController
@RequestMapping("/web/menu")
public class WebSysMenuController {

    @Autowired
    private SysMenuService sysMenuService;


    @PostMapping(value = "/save")
    public HttpResult save(@RequestBody SysMenu record, HttpServletRequest request) {
        return HttpResult.ok(sysMenuService.save(record));
    }

    @PostMapping(value = "/delete")
    public HttpResult delete(@RequestBody List<SysMenu> records) {
        return HttpResult.ok(sysMenuService.delete(records));
    }

    @GetMapping(value = "/findNavTree")
    public HttpResult findNavTree(@RequestParam String userName, String source) {
        int isApp = 0;
        if (StringUtils.isNotBlank(source) && "APP".equals(source)) {
            isApp = 1;
        }
        return HttpResult.ok(sysMenuService.findTree(userName, 1, isApp));
    }

    @PostMapping(value = "/findMenuTree")
    public HttpResult findMenuTree() {
        return HttpResult.ok(sysMenuService.findTree(null, 0, 3));
    }


}

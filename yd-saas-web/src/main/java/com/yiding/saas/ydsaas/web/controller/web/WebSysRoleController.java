package com.yiding.saas.ydsaas.web.controller.web;

import com.alibaba.fastjson.JSONObject;
import com.yiding.saas.ydsaas.common.constants.SysConstants;
import com.yiding.saas.ydsaas.dao.SysRoleMapper;
import com.yiding.saas.ydsaas.dto.YdRoleDto;
import com.yiding.saas.ydsaas.dto.YdUserOrgRefDto;
import com.yiding.saas.ydsaas.model.SysRole;
import com.yiding.saas.ydsaas.model.SysRoleMenu;
import com.yiding.saas.ydsaas.model.SysUserRole;
import com.yiding.saas.ydsaas.service.SysRoleService;
import com.yiding.saas.ydsaas.service.SysUserRoleService;
import com.yiding.saas.ydsaas.web.biz.DataAuthBizService;
import com.yiding.saas.ydsaas.web.core.HttpResult;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 角色控制器
 *
 * @author Louis
 * @date Oct 29, 2018
 */
@RestController
@RequestMapping("/web/role")
public class WebSysRoleController {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysUserRoleService sysUserRoleService;


    /**
     * 数据权限业务类
     */
    @Autowired
    private DataAuthBizService dataAuthBizService;

    @PostMapping(value = "/save")
    public HttpResult save(@RequestBody SysRole record, HttpServletRequest request) {
        SysRole role = sysRoleService.findById(record.getId());
        if (role != null) {
            if (SysConstants.ADMIN.equalsIgnoreCase(role.getName())) {
                return HttpResult.error("超级管理员不允许修改!");
            }
        }
        // 新增角色
        if ((record.getId() == null || record.getId() == 0) && !sysRoleService.findByName(record.getName()).isEmpty()) {
            return HttpResult.error("角色名已存在!");
        }
        return HttpResult.ok(sysRoleService.save(record));
    }

    @PostMapping(value = "/delete")
    public HttpResult delete(@RequestBody List<SysRole> records) {
        return HttpResult.ok(sysRoleService.delete(records));
    }


    /**
     * 角色列表 分页
     *
     * @param ydRoleDto
     * @return
     */
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody YdRoleDto ydRoleDto) {
        return HttpResult.ok(sysRoleService.findPage(ydRoleDto));
    }

    @PostMapping(value = "/findAll")
    public HttpResult findAll() {
        return HttpResult.ok(sysRoleService.findAll());
    }

    @PostMapping(value = "/findRoleMenus")
    public HttpResult findRoleMenus(@RequestBody SysRoleMenu sysRoleMenu) {
        return HttpResult.ok(sysRoleService.findRoleMenus(sysRoleMenu.getRoleId()));
    }


    /**
     * 角色-菜单-授权
     *
     * @param records
     * @return
     */
    @PostMapping(value = "/saveRoleMenus")
    public HttpResult saveRoleMenus(@RequestBody List<SysRoleMenu> records) {
        for (SysRoleMenu record : records) {
            SysRole sysRole = sysRoleMapper.selectByPrimaryKey(record.getRoleId());
            if (SysConstants.ADMIN.equalsIgnoreCase(sysRole.getName())) {
                // 如果是超级管理员，不允许修改
                return HttpResult.error("超级管理员拥有所有菜单权限，不允许修改！");
            }
        }
        int code = sysRoleService.saveRoleMenus(records);
        if (code == 1) {
            return HttpResult.ok(code, "操作成功");
        } else {
            return HttpResult.error("如需清空角色菜单权限，请删除该角色");
        }
    }

    /**
     * 用户-角色授权
     *
     * @param sysUserRole
     * @return
     */
    @PostMapping("/saveUserRole")
    public Object saveUserRole(@RequestBody SysUserRole sysUserRole) {
        Long roleId = sysUserRole.getRoleId();
        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(roleId);
        if (SysConstants.ADMIN.equalsIgnoreCase(sysRole.getName())) {
            // 如果是超级管理员，不允许修改
            return HttpResult.error("超级管理员拥有所有菜单权限，不允许修改！");
        } else {
            boolean result = sysUserRoleService.saveUserRole(sysUserRole);
            if (!result) {
                return HttpResult.error("操作失败");
            }
            return HttpResult.ok("操作成功");
        }
    }

    /**
     * 数据授权-用户
     *
     * @param ydUserOrgRefDto
     * @return
     */
    @PostMapping("/saveUserOrg")
    public Object saveRoleOrgAuth(@RequestBody YdUserOrgRefDto ydUserOrgRefDto) {
        Pair<Boolean, String> result = dataAuthBizService.save(ydUserOrgRefDto);
        if (result.getKey()) {
            return HttpResult.ok(result.getValue());
        }
        return HttpResult.error(result.getValue());
    }

    /**
     * 数据权限
     *
     * @param ydUserOrgRefDto
     * @return
     */
    @PostMapping("/getOrgIdsByUserId")
    public Object getOrgIdsByRole(@RequestBody YdUserOrgRefDto ydUserOrgRefDto) {
        List<Long> orgids = dataAuthBizService.getAuthData(ydUserOrgRefDto.getUserId());
        JSONObject rtnJson = new JSONObject();
        rtnJson.put("orgIds", orgids);
        return HttpResult.ok(rtnJson);
    }
}

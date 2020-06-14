package com.yiding.saas.ydsaas.service;

import com.yiding.saas.ydsaas.model.SysUserRole;
import org.apache.ibatis.annotations.Param;

public interface SysUserRoleService {

    int insertSelective(SysUserRole record);

    int deleteByUserId(@Param(value = "userId") Long userId);

    /**
     * 保存用户角色关系
     * @param sysUserRole
     * @return
     */
    boolean saveUserRole(SysUserRole sysUserRole);
}

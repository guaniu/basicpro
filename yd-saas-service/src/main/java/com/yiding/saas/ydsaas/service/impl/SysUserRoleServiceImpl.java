package com.yiding.saas.ydsaas.service.impl;

import com.yiding.saas.ydsaas.dao.SysUserRoleMapper;
import com.yiding.saas.ydsaas.model.SysUserRole;
import com.yiding.saas.ydsaas.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public int insertSelective(SysUserRole record) {
        return sysUserRoleMapper.insertSelective(record);
    }

    @Override
    public int deleteByUserId(Long userId) {
        return sysUserRoleMapper.deleteByUserId(userId);
    }

    @Override
    @Transactional
    public boolean saveUserRole(SysUserRole sysUserRole) {
        int result = deleteByUserId(sysUserRole.getUserId());
        sysUserRole.setCreateTime(new Date());
        int result_ = insertSelective(sysUserRole);
        if (result >= 0 && result_ > 0) {
            return true;
        }
        return false;
    }
}

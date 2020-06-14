package com.yiding.saas.ydsaas.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yiding.saas.ydsaas.common.constants.SysConstants;
import com.yiding.saas.ydsaas.common.core.MybatisPageHelper;
import com.yiding.saas.ydsaas.common.core.PageRequest;
import com.yiding.saas.ydsaas.common.core.PageResult;
import com.yiding.saas.ydsaas.dao.SysMenuMapper;
import com.yiding.saas.ydsaas.dao.SysRoleMapper;
import com.yiding.saas.ydsaas.dao.SysRoleMenuMapper;
import com.yiding.saas.ydsaas.dto.YdRoleDto;
import com.yiding.saas.ydsaas.model.SysMenu;
import com.yiding.saas.ydsaas.model.SysRole;
import com.yiding.saas.ydsaas.model.SysRoleMenu;
import com.yiding.saas.ydsaas.model.YdUser;
import com.yiding.saas.ydsaas.service.SysRoleService;
import com.yiding.saas.ydsaas.service.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public int save(SysRole record) {
        if (record.getId() == null || record.getId() == 0) {
            record.setCreateTime(new Date());
            record.setCreateBy(null);
            return sysRoleMapper.insertSelective(record);
        }
        return sysRoleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(SysRole record) {
        return sysRoleMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysRole> records) {
        for (SysRole record : records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public SysRole findById(Long id) {
        return sysRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        //Map map=pageRequest.getColumnFilter("name");
        //String name=(String) map.get("name");
        if (ServiceUtil.getColumnFilterValue(pageRequest, "name") != null) {
            return MybatisPageHelper.findPage(pageRequest, sysRoleMapper, "findPageByName", ServiceUtil.getColumnFilterValue(pageRequest, "name"));
        }
        return MybatisPageHelper.findPage(pageRequest, sysRoleMapper);
    }

    @Override
    public List<SysRole> findAll() {

        return sysRoleMapper.findAll();


    }

    public SysRoleMapper getSysRoleMapper() {
        return sysRoleMapper;
    }

    public void setSysRoleMapper(SysRoleMapper sysRoleMapper) {
        this.sysRoleMapper = sysRoleMapper;
    }

    @Override
    public List<SysMenu> findRoleMenus(Long roleId) {
        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(roleId);
        if (SysConstants.ADMIN.equalsIgnoreCase(sysRole.getName())) {
            // 如果是超级管理员，返回全部
            return sysMenuMapper.findAll();
        }
        return sysMenuMapper.findRoleMenus(roleId);
    }

    @Transactional
    @Override
    public int saveRoleMenus(List<SysRoleMenu> records) {
        if (records == null || records.isEmpty()) {
            return 0;
        }
        Long roleId = records.get(0).getRoleId();
        sysRoleMenuMapper.deleteByRoleId(roleId);
        for (SysRoleMenu record : records) {
            sysRoleMenuMapper.insertSelective(record);
        }
        return 1;
    }

    @Override
    public List<SysRole> findByName(String name) {
        return sysRoleMapper.findByName(name);
    }

    @Override
    public PageInfo<SysRole> findPage(YdRoleDto ydRoleDto) {
        PageHelper.startPage(ydRoleDto.getPageNum(),ydRoleDto.getPageSize());
        List<SysRole> list = sysRoleMapper.findByName(ydRoleDto.getName());
        PageInfo<SysRole> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

}

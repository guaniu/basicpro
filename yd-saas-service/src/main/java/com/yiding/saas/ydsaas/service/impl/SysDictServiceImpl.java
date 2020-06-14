package com.yiding.saas.ydsaas.service.impl;

import com.yiding.saas.ydsaas.common.core.PageRequest;
import com.yiding.saas.ydsaas.common.core.PageResult;
import com.yiding.saas.ydsaas.dao.SysDictMapper;
import com.yiding.saas.ydsaas.dto.SysDictDto;
import com.yiding.saas.ydsaas.model.SysDict;
import com.yiding.saas.ydsaas.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SysDictServiceImpl implements SysDictService {

    @Autowired
    private SysDictMapper sysDictMapper;

    @Override
    public List<SysDict> findByLable(String lable) {
        return sysDictMapper.findByLable(lable);
    }

    @Override
    public List<SysDict> findByType(String type) {
        return sysDictMapper.findByType(type);
    }

    @Override
    public List<SysDict> findList() {
        return sysDictMapper.findPage();
    }

    @Override
    public String findByTypeAndValue(String type, String value) {
        return sysDictMapper.findByTypeAndValue(type, value);
    }

    @Override
    public String findByTypeAndLabel(String type, String label) {
        return null;
    }

    @Override
    public List<SysDict> findByPage(SysDictDto sysDictDto) {
        return sysDictMapper.list(sysDictDto.getLabel());
    }

    @Override
    public int save(SysDict record) {
        if (record.getId() == null || record.getId() == 0) {
            Date date = new Date();
            record.setCreateTime(date);
            return sysDictMapper.insertSelective(record);
        }
        return sysDictMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(SysDict record) {
        SysDict sysDic = findById(record.getId());
        sysDic.setDelFlag(Byte.valueOf("1"));
        return sysDictMapper.updateByPrimaryKey(sysDic);
    }

    @Override
    public int delete(List<SysDict> records) {
        for (SysDict record : records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public SysDict findById(Long id) {
        return sysDictMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return null;
    }
}

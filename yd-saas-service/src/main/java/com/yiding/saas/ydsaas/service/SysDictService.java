package com.yiding.saas.ydsaas.service;

import com.yiding.saas.ydsaas.dto.SysDictDto;
import com.yiding.saas.ydsaas.model.SysDict;
import com.yiding.saas.ydsaas.service.util.CurdService;

import java.util.List;


/**
 * 字典管理
 *
 * @author Louis
 * @date Oct 29, 2018
 */
public interface SysDictService extends CurdService<SysDict> {

    /**
     * 根据名称查询
     *
     * @param lable
     * @return
     */
    List<SysDict> findByLable(String lable);

    List<SysDict> findByType(String lable);

    List<SysDict> findList();

    String findByTypeAndValue(String type, String value);

    String findByTypeAndLabel(String type, String label);


    List<SysDict> findByPage(SysDictDto sysDictDto);
}

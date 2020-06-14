package com.yiding.saas.ydsaas.dao;

import java.util.List;

import com.yiding.saas.ydsaas.model.SysDict;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysDictMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysDict record);

    int insertSelective(SysDict record);

    SysDict selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysDict record);

    int updateByPrimaryKey(SysDict record);
    
    List<SysDict> findPage();
    
    List<SysDict> findPageByLabel(@Param(value = "label") String label);

    List<SysDict> findByLable(@Param(value = "label") String label);

    List<SysDict> findByType (@Param(value = "type") String type);

    String findByTypeAndValue(@Param(value = "type") String type, @Param(value = "value") String value);

    String findByTypeAndLabel(@Param(value = "type") String type, @Param(value = "label") String label);

    List<SysDict> list(@Param(value = "label") String label);
}
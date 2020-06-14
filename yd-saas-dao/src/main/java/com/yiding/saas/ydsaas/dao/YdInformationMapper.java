package com.yiding.saas.ydsaas.dao;

import java.util.List;

import com.yiding.saas.ydsaas.model.YdInformation;

public interface YdInformationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(YdInformation record);

    int insertSelective(YdInformation record);

    YdInformation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YdInformation record);

    int updateByPrimaryKey(YdInformation record);
    
    List<YdInformation> selectAll(YdInformation record);
}
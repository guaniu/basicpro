package com.yiding.saas.ydsaas.dao;

import com.yiding.saas.ydsaas.model.YdBasicsLogistics;

public interface YdBasicsLogisticsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(YdBasicsLogistics record);

    int insertSelective(YdBasicsLogistics record);

    YdBasicsLogistics selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YdBasicsLogistics record);

    int updateByPrimaryKey(YdBasicsLogistics record);
}
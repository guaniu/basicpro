package com.yiding.saas.ydsaas.service;

import com.github.pagehelper.PageInfo;
import com.yiding.saas.ydsaas.model.YdInformation;

public interface YdInformationService {
	
	int deleteByPrimaryKey(Integer id);

    int insert(YdInformation record);

    int insertSelective(YdInformation record);

    YdInformation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YdInformation record);

    int updateByPrimaryKey(YdInformation record);
    
    PageInfo<YdInformation> selectAll(YdInformation record);

}

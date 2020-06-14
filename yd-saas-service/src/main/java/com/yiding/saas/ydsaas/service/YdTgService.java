package com.yiding.saas.ydsaas.service;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.yiding.saas.ydsaas.dto.YdWareHouseLogDto;

public interface YdTgService {
	
	 PageInfo<YdWareHouseLogDto> selectGoodsList(@Param("transportId") Integer transportId);

}

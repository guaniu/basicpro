package com.yiding.saas.ydsaas.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yiding.saas.ydsaas.dto.YdWareHouseLogDto;
import com.yiding.saas.ydsaas.model.YdTransportGoods;

public interface YdTransportGoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(YdTransportGoods record);

    YdTransportGoods selectByPrimaryKey(Integer id);

    List<YdTransportGoods> selectAll();

    int updateByPrimaryKey(YdTransportGoods record);
    
    List<YdWareHouseLogDto> selectGoodsList(@Param("transportId") Integer transportId);
}
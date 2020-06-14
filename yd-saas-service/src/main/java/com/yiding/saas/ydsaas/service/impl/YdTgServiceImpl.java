package com.yiding.saas.ydsaas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.yiding.saas.ydsaas.dao.YdTransportGoodsMapper;
import com.yiding.saas.ydsaas.dto.YdWareHouseLogDto;
import com.yiding.saas.ydsaas.service.YdTgService;

@Service
public class YdTgServiceImpl implements YdTgService {
	
	@Autowired
	private YdTransportGoodsMapper ydTransportGoodsMapper;

	@Override
	public PageInfo<YdWareHouseLogDto> selectGoodsList(Integer transportId) {
		List<YdWareHouseLogDto> list = ydTransportGoodsMapper.selectGoodsList(transportId);
		PageInfo<YdWareHouseLogDto> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

}

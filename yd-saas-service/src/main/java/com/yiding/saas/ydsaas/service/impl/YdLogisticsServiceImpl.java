package com.yiding.saas.ydsaas.service.impl;

import com.yiding.saas.ydsaas.dao.YdBasicsLogisticsMapper;
import com.yiding.saas.ydsaas.model.YdBasicsLogistics;
import com.yiding.saas.ydsaas.service.YdLogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Author BruceLee
 * @Date 2020/6/10
 */
@Service
public class YdLogisticsServiceImpl implements YdLogisticsService {
    @Autowired
    private YdBasicsLogisticsMapper ydBasicsLogisticsMapper;
    @Override
    public void save(YdBasicsLogistics ydBasicsLogistics) {
        ydBasicsLogisticsMapper.insert(ydBasicsLogistics);
    }

    @Override
    public void delete(int id) {
        ydBasicsLogisticsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(YdBasicsLogistics ydBasicsLogistics) {
        ydBasicsLogisticsMapper.updateByPrimaryKeySelective(ydBasicsLogistics);
    }

    @Override
    public YdBasicsLogistics select(int id) {
        return ydBasicsLogisticsMapper.selectByPrimaryKey(id);
    }
}

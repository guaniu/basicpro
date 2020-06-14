package com.yiding.saas.ydsaas.service;

import com.yiding.saas.ydsaas.model.YdBasicsLogistics;

import java.util.List;

/**
 * @Author BruceLee
 * @Date 2020/6/10
 */
public interface YdLogisticsService {
    void save(YdBasicsLogistics ydBasicsLogistics);

    void delete(int id);

    void update(YdBasicsLogistics ydBasicsLogistics);

    YdBasicsLogistics select(int id);
}

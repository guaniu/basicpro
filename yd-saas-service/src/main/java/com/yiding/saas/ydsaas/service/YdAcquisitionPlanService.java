package com.yiding.saas.ydsaas.service;

import com.yiding.saas.ydsaas.model.YdAcquisitionPlan;
import com.yiding.saas.ydsaas.model.YdOrganization;

import java.util.List;

/**
 * 收购计划(YdAcquisitionPlan)表服务接口
 *
 * @author makejava
 * @since 2020-06-09 15:22:34
 */
public interface YdAcquisitionPlanService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    YdAcquisitionPlan queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<YdAcquisitionPlan> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param ydAcquisitionPlan 实例对象
     * @return 实例对象
     */
    YdAcquisitionPlan insert(YdAcquisitionPlan ydAcquisitionPlan);

    /**
     * 修改数据
     *
     * @param ydAcquisitionPlan 实例对象
     * @return 实例对象
     */
    YdAcquisitionPlan update(YdAcquisitionPlan ydAcquisitionPlan);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param ydAcquisitionPlan 实例对象
     * @return 对象列表
     */
    List<YdAcquisitionPlan> queryAll(YdAcquisitionPlan ydAcquisitionPlan);

    List<YdAcquisitionPlan> list(YdAcquisitionPlan ydAcquisitionPlan);

}
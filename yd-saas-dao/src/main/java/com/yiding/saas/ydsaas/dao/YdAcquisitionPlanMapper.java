package com.yiding.saas.ydsaas.dao;

import com.yiding.saas.ydsaas.model.YdAcquisitionPlan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 收购计划(YdAcquisitionPlan)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-09 15:22:34
 */
public interface YdAcquisitionPlanMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    YdAcquisitionPlan queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<YdAcquisitionPlan> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param ydAcquisitionPlan 实例对象
     * @return 对象列表
     */
    List<YdAcquisitionPlan> queryAll(YdAcquisitionPlan ydAcquisitionPlan);

    /**
     * 新增数据
     *
     * @param ydAcquisitionPlan 实例对象
     * @return 影响行数
     */
    int insert(YdAcquisitionPlan ydAcquisitionPlan);

    /**
     * 修改数据
     *
     * @param ydAcquisitionPlan 实例对象
     * @return 影响行数
     */
    int update(YdAcquisitionPlan ydAcquisitionPlan);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<YdAcquisitionPlan> list(YdAcquisitionPlan ydAcquisitionPlan);
}
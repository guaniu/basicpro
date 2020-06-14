package com.yiding.saas.ydsaas.dao;

import com.yiding.saas.ydsaas.model.YdTobacco;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 烟叶信息(YdTobacco)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-20 09:45:42
 */
public interface YdTobaccoMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    YdTobacco queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<YdTobacco> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param ydTobacco 实例对象
     * @return 对象列表
     */
    List<YdTobacco> queryAll(YdTobacco ydTobacco);

    /**
     * 新增数据
     *
     * @param ydTobacco 实例对象
     * @return 影响行数
     */
    int insert(YdTobacco ydTobacco);

    /**
     * 修改数据
     *
     * @param ydTobacco 实例对象
     * @return 影响行数
     */
    int update(YdTobacco ydTobacco);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}
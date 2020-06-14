package com.yiding.saas.ydsaas.dao;

import com.yiding.saas.ydsaas.model.YdDistrict;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 行政区(YdDistrict)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-21 09:39:34
 */
public interface YdDistrictMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    YdDistrict queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<YdDistrict> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param ydDistrict 实例对象
     * @return 对象列表
     */
    List<YdDistrict> queryAll(YdDistrict ydDistrict);

    /**
     * 新增数据
     *
     * @param ydDistrict 实例对象
     * @return 影响行数
     */
    int insert(YdDistrict ydDistrict);

    /**
     * 修改数据
     *
     * @param ydDistrict 实例对象
     * @return 影响行数
     */
    int update(YdDistrict ydDistrict);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}
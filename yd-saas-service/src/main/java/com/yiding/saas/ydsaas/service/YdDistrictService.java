package com.yiding.saas.ydsaas.service;

import com.yiding.saas.ydsaas.model.YdDistrict;

import java.util.List;


/**
 * 行政区(YdDistrict)表服务接口
 *
 * @author makejava
 * @since 2020-05-21 09:39:34
 */
public interface YdDistrictService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    YdDistrict queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<YdDistrict> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param ydDistrict 实例对象
     * @return 实例对象
     */
    YdDistrict insert(YdDistrict ydDistrict);

    /**
     * 修改数据
     *
     * @param ydDistrict 实例对象
     * @return 实例对象
     */
    YdDistrict update(YdDistrict ydDistrict);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<YdDistrict> list(YdDistrict ydDistrict);

}
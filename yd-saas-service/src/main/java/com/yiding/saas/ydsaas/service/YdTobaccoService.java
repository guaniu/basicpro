package com.yiding.saas.ydsaas.service;

import com.yiding.saas.ydsaas.model.YdTobacco;

import java.util.List;

/**
 * 烟叶信息(YdTobacco)表服务接口
 *
 * @author makejava
 * @since 2020-05-20 09:45:42
 */
public interface YdTobaccoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    YdTobacco queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<YdTobacco> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param ydTobacco 实例对象
     * @return 实例对象
     */
    YdTobacco insert(YdTobacco ydTobacco);

    /**
     * 修改数据
     *
     * @param ydTobacco 实例对象
     * @return 实例对象
     */
    YdTobacco update(YdTobacco ydTobacco);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    List<YdTobacco> list();
}
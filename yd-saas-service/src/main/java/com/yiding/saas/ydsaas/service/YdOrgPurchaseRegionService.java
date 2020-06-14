package com.yiding.saas.ydsaas.service;

import com.yiding.saas.ydsaas.model.YdOrgPurchaseRegion;

import java.util.List;

/**
 * 组织与收购区域的关系(YdOrgPurchaseRegion)表服务接口
 *
 * @author makejava
 * @since 2020-06-01 14:29:13
 */
public interface YdOrgPurchaseRegionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    YdOrgPurchaseRegion queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<YdOrgPurchaseRegion> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param ydOrgPurchaseRegion 实例对象
     * @return 实例对象
     */
    YdOrgPurchaseRegion insert(YdOrgPurchaseRegion ydOrgPurchaseRegion);

    /**
     * 修改数据
     *
     * @param ydOrgPurchaseRegion 实例对象
     * @return 实例对象
     */
    YdOrgPurchaseRegion update(YdOrgPurchaseRegion ydOrgPurchaseRegion);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 根据组织id删除 村-组关系
     * @param orgId
     * @return
     */
    boolean deleteByOrgId(Long orgId);



    List<YdOrgPurchaseRegion> queryAll(YdOrgPurchaseRegion ydOrgPurchaseRegion);

    /**
     * 批量写入
     * @param ydOrgPurchaseRegionList
     * @return
     */
    int batchInsert(List<YdOrgPurchaseRegion> ydOrgPurchaseRegionList);

}
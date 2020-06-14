package com.yiding.saas.ydsaas.dao;

import com.yiding.saas.ydsaas.model.YdOrgPurchaseRegion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 组织与收购区域的关系(YdOrgPurchaseRegion)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-01 14:29:13
 */
public interface YdOrgPurchaseRegionMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    YdOrgPurchaseRegion queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<YdOrgPurchaseRegion> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param ydOrgPurchaseRegion 实例对象
     * @return 对象列表
     */
    List<YdOrgPurchaseRegion> queryAll(YdOrgPurchaseRegion ydOrgPurchaseRegion);

    /**
     * 新增数据
     *
     * @param ydOrgPurchaseRegion 实例对象
     * @return 影响行数
     */
    int insert(YdOrgPurchaseRegion ydOrgPurchaseRegion);

    /**
     * 修改数据
     *
     * @param ydOrgPurchaseRegion 实例对象
     * @return 影响行数
     */
    int update(YdOrgPurchaseRegion ydOrgPurchaseRegion);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 根据组织id删除村组关系
     *
     * @param orgId
     * @return
     */
    boolean deleteByOrgId(@Param("orgId") Long orgId);

    /**
     * 批量写入
     * @param ydOrgPurchaseRegionList
     * @return
     */
    int batchInsert(@Param("ydOrgPurchaseRegionList") List<YdOrgPurchaseRegion> ydOrgPurchaseRegionList);
}
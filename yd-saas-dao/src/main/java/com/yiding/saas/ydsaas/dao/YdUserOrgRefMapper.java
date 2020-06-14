package com.yiding.saas.ydsaas.dao;

import com.yiding.saas.ydsaas.model.YdUserOrgRef;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户-组织-关联关系用于控制数据权限(YdUserOrgRef)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-22 11:04:02
 */
public interface YdUserOrgRefMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    YdUserOrgRef queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<YdUserOrgRef> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param ydUserOrgRef 实例对象
     * @return 对象列表
     */
    List<YdUserOrgRef> queryAll(YdUserOrgRef ydUserOrgRef);

    /**
     * 新增数据
     *
     * @param ydUserOrgRef 实例对象
     * @return 影响行数
     */
    int insert(YdUserOrgRef ydUserOrgRef);

    /**
     * 修改数据
     *
     * @param ydUserOrgRef 实例对象
     * @return 影响行数
     */
    int update(YdUserOrgRef ydUserOrgRef);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    boolean deleteByUserIdAndType(@Param("userId") Long userId, @Param("type") String type);

    List<Long> listOrgIdsByUserId(@Param("userId") Long userId, @Param("type") String type);

}
package com.yiding.saas.ydsaas.service;

import com.yiding.saas.ydsaas.model.YdUserOrgRef;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户-组织-关联关系用于控制数据权限(YdUserOrgRef)表服务接口
 *
 * @author makejava
 * @since 2020-05-22 11:04:02
 */
public interface YdUserOrgRefService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    YdUserOrgRef queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<YdUserOrgRef> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param ydUserOrgRef 实例对象
     * @return 实例对象
     */
    YdUserOrgRef insert(YdUserOrgRef ydUserOrgRef);

    /**
     * 修改数据
     *
     * @param ydUserOrgRef 实例对象
     * @return 实例对象
     */
    YdUserOrgRef update(YdUserOrgRef ydUserOrgRef);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 根据角色id和类型删除
     *
     * @return
     */
    boolean deleteByUserIdAndType(Long userId, String type);

    List<Long> listOrgIdsByUserId(@Param("userId") Long userId, @Param("type") String type);

}
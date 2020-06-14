package com.yiding.saas.ydsaas.dao;

import com.yiding.saas.ydsaas.model.YdUser;
import com.yiding.saas.ydsaas.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户信息(YdUser)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-20 09:46:38
 */
public interface YdUserMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    YdUser queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<YdUser> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param ydUser 实例对象
     * @return 对象列表
     */
    List<YdUser> queryAll(YdUser ydUser);

    /**
     * 新增数据
     *
     * @param ydUser 实例对象
     * @return 影响行数
     */
    int insert(YdUser ydUser);

    /**
     * 修改数据
     *
     * @param ydUser 实例对象
     * @return 影响行数
     */
    int update(YdUser ydUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    YdUser findByName(@Param("userName") String userName);

    YdUser findByAccount(@Param("account") String account);

    List<UserVo> getUserList(YdUser ydUser);

    /**
     * 根据用户id查询角色名称
     * @param userId
     * @return
     */
    String findRoleNameByUserId(@Param("userId") Long userId);
    
    /**
     * 获取商户单一角色下的全部用户
     * @author chunbo
     * @param roleId
     * @param userOrgId
     * @return
     */
    List<UserVo> selectRoleUserList(@Param("roleId") int roleId,@Param("userOrgId") int userOrgId);
}
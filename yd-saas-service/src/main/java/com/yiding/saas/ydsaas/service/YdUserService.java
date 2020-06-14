package com.yiding.saas.ydsaas.service;

import com.yiding.saas.ydsaas.model.YdUser;
import com.yiding.saas.ydsaas.vo.UserVo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 用户信息(YdUser)表服务接口
 *
 * @author makejava
 * @since 2020-05-20 09:46:38
 */
public interface YdUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    YdUser queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<YdUser> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param ydUser 实例对象
     * @return 实例对象
     */
    YdUser insert(YdUser ydUser);

    /**
     * 修改数据
     *
     * @param ydUser 实例对象
     * @return 实例对象
     */
    YdUser update(YdUser ydUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);


    public List<YdUser> list(YdUser ydUser);

    YdUser findByName(String username);

    YdUser findByAccount(String username);

    List<UserVo> getUserList(YdUser ydUser);

    /**
     * 根据用户id查询角色名称
     * @param userId
     * @return
     */
    String findRoleNameByUserId(Long userId);
    
    /**
     * 获取商户单一角色下的全部用户
     * @author chunbo
     * @param roleId
     * @param userOrgId
     * @return
     */
    List<UserVo> selectRoleUserList(@Param("roleId") int roleId,@Param("userOrgId") int userOrgId);
}
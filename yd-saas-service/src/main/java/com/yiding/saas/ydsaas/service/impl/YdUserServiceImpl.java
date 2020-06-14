package com.yiding.saas.ydsaas.service.impl;

import com.yiding.saas.ydsaas.dao.YdUserMapper;
import com.yiding.saas.ydsaas.model.YdUser;
import com.yiding.saas.ydsaas.service.YdUserService;
import com.yiding.saas.ydsaas.vo.UserVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户信息(YdUser)表服务实现类
 *
 * @author makejava
 * @since 2020-05-20 09:46:38
 */
@Service("ydUserService")
public class YdUserServiceImpl implements YdUserService {
    @Resource
    private YdUserMapper ydUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public YdUser queryById(Long id) {
        return this.ydUserDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<YdUser> queryAllByLimit(int offset, int limit) {
        return this.ydUserDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param ydUser 实例对象
     * @return 实例对象
     */
    @Override
    public YdUser insert(YdUser ydUser) {
        this.ydUserDao.insert(ydUser);
        return ydUser;
    }

    /**
     * 修改数据
     *
     * @param ydUser 实例对象
     * @return 实例对象
     */
    @Override
    public YdUser update(YdUser ydUser) {
        this.ydUserDao.update(ydUser);
        return this.queryById(ydUser.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.ydUserDao.deleteById(id) > 0;
    }


    /**
     * 根据条件查询列表
     *
     * @param ydUser
     * @return
     */
    @Override
    public List<YdUser> list(YdUser ydUser) {
        return ydUserDao.queryAll(ydUser);
    }

    @Override
    public YdUser findByName(String username) {
        return ydUserDao.findByName(username);
    }

    @Override
    public YdUser findByAccount(String username) {
        return ydUserDao.findByAccount(username);
    }

    @Override
    public List<UserVo> getUserList(YdUser ydUser) {
        return ydUserDao.getUserList(ydUser);
    }

    @Override
    public String findRoleNameByUserId(Long userId) {
        return ydUserDao.findRoleNameByUserId(userId);
    }

	@Override
	public List<UserVo> selectRoleUserList(int roleId, int userOrgId) {
		return ydUserDao.selectRoleUserList(roleId, userOrgId);
	}
}
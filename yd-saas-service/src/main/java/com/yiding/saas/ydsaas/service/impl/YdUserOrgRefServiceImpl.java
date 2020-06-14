package com.yiding.saas.ydsaas.service.impl;

import com.yiding.saas.ydsaas.dao.YdUserOrgRefMapper;
import com.yiding.saas.ydsaas.model.YdUserOrgRef;
import com.yiding.saas.ydsaas.service.YdUserOrgRefService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户-组织-关联关系用于控制数据权限(YdUserOrgRef)表服务实现类
 *
 * @author makejava
 * @since 2020-05-22 11:04:02
 */
@Service("ydUserOrgRefService")
public class YdUserOrgRefServiceImpl implements YdUserOrgRefService {
    @Resource
    private YdUserOrgRefMapper ydUserOrgRefDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public YdUserOrgRef queryById(Long id) {
        return this.ydUserOrgRefDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<YdUserOrgRef> queryAllByLimit(int offset, int limit) {
        return this.ydUserOrgRefDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param ydUserOrgRef 实例对象
     * @return 实例对象
     */
    @Override
    public YdUserOrgRef insert(YdUserOrgRef ydUserOrgRef) {
        this.ydUserOrgRefDao.insert(ydUserOrgRef);
        return ydUserOrgRef;
    }

    /**
     * 修改数据
     *
     * @param ydUserOrgRef 实例对象
     * @return 实例对象
     */
    @Override
    public YdUserOrgRef update(YdUserOrgRef ydUserOrgRef) {
        this.ydUserOrgRefDao.update(ydUserOrgRef);
        return this.queryById(ydUserOrgRef.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.ydUserOrgRefDao.deleteById(id) > 0;
    }

    @Override
    public boolean deleteByUserIdAndType(Long userId, String type) {
        return ydUserOrgRefDao.deleteByUserIdAndType(userId, type);
    }

    @Override
    public List<Long> listOrgIdsByUserId(Long userId, String type) {
        return ydUserOrgRefDao.listOrgIdsByUserId(userId, type);
    }
}
package com.yiding.saas.ydsaas.service.impl;


import com.yiding.saas.ydsaas.dao.YdOrgPurchaseRegionMapper;
import com.yiding.saas.ydsaas.model.YdOrgPurchaseRegion;
import com.yiding.saas.ydsaas.service.YdOrgPurchaseRegionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 组织与收购区域的关系(YdOrgPurchaseRegion)表服务实现类
 *
 * @author makejava
 * @since 2020-06-01 14:29:13
 */
@Service("ydOrgPurchaseRegionService")
public class YdOrgPurchaseRegionServiceImpl implements YdOrgPurchaseRegionService {
    @Resource
    private YdOrgPurchaseRegionMapper ydOrgPurchaseRegionMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public YdOrgPurchaseRegion queryById(Long id) {
        return this.ydOrgPurchaseRegionMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<YdOrgPurchaseRegion> queryAllByLimit(int offset, int limit) {
        return this.ydOrgPurchaseRegionMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param ydOrgPurchaseRegion 实例对象
     * @return 实例对象
     */
    @Override
    public YdOrgPurchaseRegion insert(YdOrgPurchaseRegion ydOrgPurchaseRegion) {
        this.ydOrgPurchaseRegionMapper.insert(ydOrgPurchaseRegion);
        return ydOrgPurchaseRegion;
    }

    /**
     * 修改数据
     *
     * @param ydOrgPurchaseRegion 实例对象
     * @return 实例对象
     */
    @Override
    public YdOrgPurchaseRegion update(YdOrgPurchaseRegion ydOrgPurchaseRegion) {
        this.ydOrgPurchaseRegionMapper.update(ydOrgPurchaseRegion);
        return this.queryById(ydOrgPurchaseRegion.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.ydOrgPurchaseRegionMapper.deleteById(id) > 0;
    }

    @Override
    public boolean deleteByOrgId(Long orgId) {
        return ydOrgPurchaseRegionMapper.deleteByOrgId(orgId);
    }

    @Override
    public List<YdOrgPurchaseRegion> queryAll(YdOrgPurchaseRegion ydOrgPurchaseRegion) {
        return ydOrgPurchaseRegionMapper.queryAll(ydOrgPurchaseRegion);
    }

    @Override
    public int batchInsert(List<YdOrgPurchaseRegion> ydOrgPurchaseRegionList) {
        return ydOrgPurchaseRegionMapper.batchInsert(ydOrgPurchaseRegionList);
    }
}
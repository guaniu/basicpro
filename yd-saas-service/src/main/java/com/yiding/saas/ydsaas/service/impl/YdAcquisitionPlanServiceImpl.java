package com.yiding.saas.ydsaas.service.impl;


import com.yiding.saas.ydsaas.dao.YdAcquisitionPlanMapper;
import com.yiding.saas.ydsaas.model.YdAcquisitionPlan;
import com.yiding.saas.ydsaas.service.YdAcquisitionPlanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 收购计划(YdAcquisitionPlan)表服务实现类
 *
 * @author makejava
 * @since 2020-06-09 15:22:34
 */
@Service("ydAcquisitionPlanService")
public class YdAcquisitionPlanServiceImpl implements YdAcquisitionPlanService {
    @Resource
    private YdAcquisitionPlanMapper ydAcquisitionPlanDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public YdAcquisitionPlan queryById(Long id) {
        return this.ydAcquisitionPlanDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<YdAcquisitionPlan> queryAllByLimit(int offset, int limit) {
        return this.ydAcquisitionPlanDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param ydAcquisitionPlan 实例对象
     * @return 实例对象
     */
    @Override
    public YdAcquisitionPlan insert(YdAcquisitionPlan ydAcquisitionPlan) {
        this.ydAcquisitionPlanDao.insert(ydAcquisitionPlan);
        return ydAcquisitionPlan;
    }

    /**
     * 修改数据
     *
     * @param ydAcquisitionPlan 实例对象
     * @return 实例对象
     */
    @Override
    public YdAcquisitionPlan update(YdAcquisitionPlan ydAcquisitionPlan) {
        this.ydAcquisitionPlanDao.update(ydAcquisitionPlan);
        return this.queryById(ydAcquisitionPlan.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.ydAcquisitionPlanDao.deleteById(id) > 0;
    }

    @Override
    public List<YdAcquisitionPlan> queryAll(YdAcquisitionPlan ydAcquisitionPlan) {
        return ydAcquisitionPlanDao.queryAll(ydAcquisitionPlan);
    }

    @Override
    public List<YdAcquisitionPlan> list(YdAcquisitionPlan ydAcquisitionPlan) {
        return ydAcquisitionPlanDao.list(ydAcquisitionPlan);
    }
}
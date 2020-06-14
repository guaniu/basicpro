package com.yiding.saas.ydsaas.service.impl;


import com.yiding.saas.ydsaas.dao.YdTobaccoMapper;
import com.yiding.saas.ydsaas.model.YdTobacco;
import com.yiding.saas.ydsaas.service.YdTobaccoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 烟叶信息(YdTobacco)表服务实现类
 *
 * @author makejava
 * @since 2020-05-20 09:45:42
 */
@Service("ydTobaccoService")
public class YdTobaccoServiceImpl implements YdTobaccoService {
    @Resource
    private YdTobaccoMapper ydTobaccoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public YdTobacco queryById(Long id) {
        return this.ydTobaccoDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<YdTobacco> queryAllByLimit(int offset, int limit) {
        return this.ydTobaccoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param ydTobacco 实例对象
     * @return 实例对象
     */
    @Override
    public YdTobacco insert(YdTobacco ydTobacco) {
        this.ydTobaccoDao.insert(ydTobacco);
        return ydTobacco;
    }

    /**
     * 修改数据
     *
     * @param ydTobacco 实例对象
     * @return 实例对象
     */
    @Override
    public YdTobacco update(YdTobacco ydTobacco) {
        this.ydTobaccoDao.update(ydTobacco);
        return this.queryById(ydTobacco.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.ydTobaccoDao.deleteById(id) > 0;
    }

    @Override
    public List<YdTobacco> list() {
        return ydTobaccoDao.queryAll(new YdTobacco());
    }
}
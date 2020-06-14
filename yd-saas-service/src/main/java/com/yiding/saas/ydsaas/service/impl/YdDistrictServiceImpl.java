package com.yiding.saas.ydsaas.service.impl;

import com.yiding.saas.ydsaas.dao.YdDistrictMapper;
import com.yiding.saas.ydsaas.model.YdDistrict;
import com.yiding.saas.ydsaas.service.YdDistrictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 行政区(YdDistrict)表服务实现类
 *
 * @author makejava
 * @since 2020-05-21 09:39:34
 */
@Service("ydDistrictService")
public class YdDistrictServiceImpl implements YdDistrictService {
    @Resource
    private YdDistrictMapper ydDistrictDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public YdDistrict queryById(Integer id) {
        return this.ydDistrictDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<YdDistrict> queryAllByLimit(int offset, int limit) {
        return this.ydDistrictDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param ydDistrict 实例对象
     * @return 实例对象
     */
    @Override
    public YdDistrict insert(YdDistrict ydDistrict) {
        this.ydDistrictDao.insert(ydDistrict);
        return ydDistrict;
    }

    /**
     * 修改数据
     *
     * @param ydDistrict 实例对象
     * @return 实例对象
     */
    @Override
    public YdDistrict update(YdDistrict ydDistrict) {
        this.ydDistrictDao.update(ydDistrict);
        return this.queryById(ydDistrict.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.ydDistrictDao.deleteById(id) > 0;
    }

    @Override
    public List<YdDistrict> list(YdDistrict ydDistrict) {
        return ydDistrictDao.queryAll(ydDistrict);
    }
}
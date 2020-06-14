package com.yiding.saas.ydsaas.service.impl;


import com.yiding.saas.ydsaas.dao.YdWarnMapper;
import com.yiding.saas.ydsaas.dto.WarnDto;
import com.yiding.saas.ydsaas.model.YdWarn;
import com.yiding.saas.ydsaas.service.YdWarnService;
import com.yiding.saas.ydsaas.vo.WarnVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 告警表(YdWarn)表服务实现类
 *
 * @author makejava
 * @since 2020-06-05 10:44:42
 */
@Service("ydWarnService")
public class YdWarnServiceImpl implements YdWarnService {
    @Resource
    private YdWarnMapper ydWarnDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public YdWarn queryById(Long id) {
        return this.ydWarnDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<YdWarn> queryAllByLimit(int offset, int limit) {
        return this.ydWarnDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param ydWarn 实例对象
     * @return 实例对象
     */
    @Override
    public YdWarn insert(YdWarn ydWarn) {
        this.ydWarnDao.insert(ydWarn);
        return ydWarn;
    }

    /**
     * 修改数据
     *
     * @param ydWarn 实例对象
     * @return 实例对象
     */
    @Override
    public YdWarn update(YdWarn ydWarn) {
        this.ydWarnDao.update(ydWarn);
        return this.queryById(ydWarn.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.ydWarnDao.deleteById(id) > 0;
    }

    @Override
    public List<YdWarn> queryAll(YdWarn ydWarn) {
        return ydWarnDao.queryAll(ydWarn);
    }



    @Override
    public List<WarnVo> getListByParms(WarnDto warnDto) {
        return ydWarnDao.getListByParms(warnDto);
    }
}
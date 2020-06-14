package com.yiding.saas.ydsaas.service.impl;

import com.yiding.saas.ydsaas.dao.YdTransportLogMapper;
import com.yiding.saas.ydsaas.model.YdTransportLog;
import com.yiding.saas.ydsaas.service.YdTransportLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 运单日志表(YdTransportLog)表服务实现类
 *
 * @author makejava
 * @since 2020-05-29 13:37:51
 */
@Service("ydTransportLogService")
public class YdTransportLogServiceImpl implements YdTransportLogService {
    @Resource
    private YdTransportLogMapper ydTransportLogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public YdTransportLog queryById(Integer id) {
        return this.ydTransportLogDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<YdTransportLog> queryAllByLimit(int offset, int limit) {
        return this.ydTransportLogDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param ydTransportLog 实例对象
     * @return 实例对象
     */
    @Override
    public YdTransportLog insert(YdTransportLog ydTransportLog) {
        this.ydTransportLogDao.insert(ydTransportLog);
        return ydTransportLog;
    }

    /**
     * 修改数据
     *
     * @param ydTransportLog 实例对象
     * @return 实例对象
     */
    @Override
    public YdTransportLog update(YdTransportLog ydTransportLog) {
        this.ydTransportLogDao.update(ydTransportLog);
        return this.queryById(ydTransportLog.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.ydTransportLogDao.deleteById(id) > 0;
    }

    @Override
    public List<YdTransportLog> queryTransportLog(Integer transportId) {
        YdTransportLog ydTransportLog=new YdTransportLog();
        ydTransportLog.setTransportId(transportId);
        return ydTransportLogDao.queryAll(ydTransportLog);
    }
}
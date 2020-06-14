package com.yiding.saas.ydsaas.service;

import com.yiding.saas.ydsaas.model.YdTransportLog;

import java.util.List;

/**
 * 运单日志表(YdTransportLog)表服务接口
 *
 * @author makejava
 * @since 2020-05-29 13:37:51
 */
public interface YdTransportLogService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    YdTransportLog queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<YdTransportLog> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param ydTransportLog 实例对象
     * @return 实例对象
     */
    YdTransportLog insert(YdTransportLog ydTransportLog);

    /**
     * 修改数据
     *
     * @param ydTransportLog 实例对象
     * @return 实例对象
     */
    YdTransportLog update(YdTransportLog ydTransportLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<YdTransportLog> queryTransportLog(Integer transportId);


}
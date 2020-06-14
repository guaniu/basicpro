package com.yiding.saas.ydsaas.dao;

import com.yiding.saas.ydsaas.model.YdTransportLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 运单日志表(YdTransportLog)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-29 13:37:51
 */
public interface YdTransportLogMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    YdTransportLog queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<YdTransportLog> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param ydTransportLog 实例对象
     * @return 对象列表
     */
    List<YdTransportLog> queryAll(YdTransportLog ydTransportLog);

    /**
     * 新增数据
     *
     * @param ydTransportLog 实例对象
     * @return 影响行数
     */
    int insert(YdTransportLog ydTransportLog);

    /**
     * 修改数据
     *
     * @param ydTransportLog 实例对象
     * @return 影响行数
     */
    int update(YdTransportLog ydTransportLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}
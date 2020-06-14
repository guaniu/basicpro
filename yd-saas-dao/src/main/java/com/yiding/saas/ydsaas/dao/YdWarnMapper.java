package com.yiding.saas.ydsaas.dao;

import com.yiding.saas.ydsaas.dto.WarnDto;
import com.yiding.saas.ydsaas.model.YdWarn;
import com.yiding.saas.ydsaas.vo.WarnVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 告警表(YdWarn)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-05 10:44:42
 */
public interface YdWarnMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    YdWarn queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<YdWarn> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param ydWarn 实例对象
     * @return 对象列表
     */
    List<YdWarn> queryAll(YdWarn ydWarn);

    /**
     * 新增数据
     *
     * @param ydWarn 实例对象
     * @return 影响行数
     */
    int insert(YdWarn ydWarn);

    /**
     * 修改数据
     *
     * @param ydWarn 实例对象
     * @return 影响行数
     */
    int update(YdWarn ydWarn);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);


    List<WarnVo> getListByParms(WarnDto warnDto);
}
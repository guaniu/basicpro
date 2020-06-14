package com.yiding.saas.ydsaas.service;

import com.yiding.saas.ydsaas.dto.WarnDto;
import com.yiding.saas.ydsaas.model.YdWarn;
import com.yiding.saas.ydsaas.vo.WarnVo;

import java.util.List;

/**
 * 告警表(YdWarn)表服务接口
 *
 * @author makejava
 * @since 2020-06-05 10:44:42
 */
public interface YdWarnService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    YdWarn queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<YdWarn> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param ydWarn 实例对象
     * @return 实例对象
     */
    YdWarn insert(YdWarn ydWarn);

    /**
     * 修改数据
     *
     * @param ydWarn 实例对象
     * @return 实例对象
     */
    YdWarn update(YdWarn ydWarn);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 根据运单Id查告警信息
     * @param ydWarn
     * @return
     */
    List<YdWarn> queryAll(YdWarn ydWarn);


    List<WarnVo> getListByParms(WarnDto warnDto);

}
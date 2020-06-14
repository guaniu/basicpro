package com.yiding.saas.ydsaas.service;

import com.yiding.saas.ydsaas.model.YdOrganization;
import com.yiding.saas.ydsaas.model.YdUser;
import com.yiding.saas.ydsaas.vo.OrganizationVo;

import java.util.List;

/**
 * 组织机构(YdOrganization)表服务接口
 *
 * @author makejava
 * @since 2020-05-20 09:45:28
 */
public interface YdOrganizationService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    YdOrganization queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<YdOrganization> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param ydOrganization 实例对象
     * @return 实例对象
     */
    YdOrganization insert(YdOrganization ydOrganization);

    /**
     * 修改数据
     *
     * @param ydOrganization 实例对象
     * @return 实例对象
     */
    int update(YdOrganization ydOrganization);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    List<YdOrganization> list(YdOrganization ydOrganization);

    List<YdOrganization> listByIds(List<Long> ids);

    /**
     * 组织列表
     *
     * @param ydOrganization
     * @return
     */
    List<OrganizationVo> getOrgList(YdOrganization ydOrganization);

    List<YdOrganization> queryAll(YdOrganization ydOrganization);

    /**
     * 批量更新仓库信息
     * @param ydOrganizations
     * @return
     */
    int batchUpdateStoreHouse(List<YdOrganization> ydOrganizations);

    /**
     * 批量写入仓库信息
     * @param ydOrganizations
     * @return
     */
    int batchInsertStoreHouse(List<YdOrganization> ydOrganizations);


    /**
     * 根据组织类型查询
     * @param orgTypes
     * @return
     */
    List<YdOrganization> getOrgListByOrgType(List<String> orgTypes);
}
package com.yiding.saas.ydsaas.dao;

import com.yiding.saas.ydsaas.model.YdOrganization;
import com.yiding.saas.ydsaas.vo.OrganizationVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 组织机构(YdOrganization)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-20 09:45:28
 */
public interface YdOrganizationMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    YdOrganization queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<YdOrganization> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param ydOrganization 实例对象
     * @return 对象列表
     */
    List<YdOrganization> queryAll(YdOrganization ydOrganization);

    /**
     * 新增数据
     *
     * @param ydOrganization 实例对象
     * @return 影响行数
     */
    int insert(YdOrganization ydOrganization);

    /**
     * 修改数据
     *
     * @param ydOrganization 实例对象
     * @return 影响行数
     */
    int update(YdOrganization ydOrganization);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);


    List<YdOrganization> listByIds(@Param("ids") List<Long> ids);

    /**
     * 组织列表
     *
     * @param ydOrganization
     * @return
     */
    List<OrganizationVo> getOrgList(YdOrganization ydOrganization);

    /**
     * 批量更新仓库信息
     *
     * @param ydOrganizations
     * @return
     */
    int batchUpdateStoreHouse(@Param("ydOrganizations") List<YdOrganization> ydOrganizations);

    /**
     * 批量写入仓库
     *
     * @param ydOrganizations
     * @return
     */
    int batchInsertStoreHouse(@Param("ydOrganizations") List<YdOrganization> ydOrganizations);

    /**
     * 根据组织类型查询
     * @param orgTypes
     * @return
     */
    List<YdOrganization> getOrgListByOrgType(@Param("orgTypes") List<String> orgTypes);
}
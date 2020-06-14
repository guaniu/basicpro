package com.yiding.saas.ydsaas.service.impl;

import com.yiding.saas.ydsaas.dao.YdOrganizationMapper;
import com.yiding.saas.ydsaas.model.YdOrganization;
import com.yiding.saas.ydsaas.service.YdOrganizationService;
import com.yiding.saas.ydsaas.vo.OrganizationVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 组织机构(YdOrganization)表服务实现类
 *
 * @author makejava
 * @since 2020-05-20 09:45:29
 */
@Service("ydOrganizationService")
public class YdOrganizationServiceImpl implements YdOrganizationService {
    @Resource
    private YdOrganizationMapper ydOrganizationDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public YdOrganization queryById(Long id) {
        return this.ydOrganizationDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<YdOrganization> queryAllByLimit(int offset, int limit) {
        return this.ydOrganizationDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param ydOrganization 实例对象
     * @return 实例对象
     */
    @Override
    public YdOrganization insert(YdOrganization ydOrganization) {
        this.ydOrganizationDao.insert(ydOrganization);
        return ydOrganization;
    }

    /**
     * 修改数据
     *
     * @param ydOrganization 实例对象
     * @return 实例对象
     */
    @Override
    public int update(YdOrganization ydOrganization) {
        return this.ydOrganizationDao.update(ydOrganization);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.ydOrganizationDao.deleteById(id) > 0;
    }

    @Override
    public List<YdOrganization> list(YdOrganization ydOrganization) {
        return ydOrganizationDao.queryAll(ydOrganization);
    }

    @Override
    public List<YdOrganization> listByIds(List<Long> ids) {
        return ydOrganizationDao.listByIds(ids);
    }

    @Override
    public List<OrganizationVo> getOrgList(YdOrganization ydOrganization) {
        return ydOrganizationDao.getOrgList(ydOrganization);
    }

    @Override
    public List<YdOrganization> queryAll(YdOrganization ydOrganization) {
        return ydOrganizationDao.queryAll(ydOrganization);
    }

    /**
     * 批量修改仓库
     * @param ydOrganizations
     * @return
     */
    @Override
    public int batchUpdateStoreHouse(List<YdOrganization> ydOrganizations) {
        return ydOrganizationDao.batchUpdateStoreHouse(ydOrganizations);
    }


    /**
     * 批量写入仓库信息
     * @param ydOrganizations
     * @return
     */
    @Override
    public int batchInsertStoreHouse(List<YdOrganization> ydOrganizations) {
        return ydOrganizationDao.batchInsertStoreHouse(ydOrganizations);
    }

    @Override
    public List<YdOrganization> getOrgListByOrgType(List<String> orgTypes) {
        return ydOrganizationDao.getOrgListByOrgType(orgTypes);
    }


}
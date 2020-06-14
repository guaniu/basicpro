package com.yiding.saas.ydsaas.web.biz;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yiding.saas.ydsaas.common.enums.CommonEnum;
import com.yiding.saas.ydsaas.common.utils.ExcelUtils;
import com.yiding.saas.ydsaas.dto.VillageGroupDto;
import com.yiding.saas.ydsaas.dto.YdOrganizationDto;
import com.yiding.saas.ydsaas.model.SysDict;
import com.yiding.saas.ydsaas.model.YdDistrict;
import com.yiding.saas.ydsaas.model.YdOrgPurchaseRegion;
import com.yiding.saas.ydsaas.model.YdOrganization;
import com.yiding.saas.ydsaas.service.SysDictService;
import com.yiding.saas.ydsaas.service.YdOrgPurchaseRegionService;
import com.yiding.saas.ydsaas.service.YdOrganizationService;
import com.yiding.saas.ydsaas.vo.OrgTree;
import com.yiding.saas.ydsaas.vo.OrganizationVo;
import com.yiding.saas.ydsaas.web.util.JedisUtil;
import com.yiding.saas.ydsaas.web.util.RedisKeyUtil;
import javafx.util.Pair;
import net.bytebuddy.asm.Advice;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrgBizService {

    /**
     * 日志
     */
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private YdOrganizationService ydOrganizationService;

    @Autowired
    private YdOrgPurchaseRegionService ydOrgPurchaseRegionService;


    @Autowired
    private DistrictBizService districtBizService;

    @Autowired
    private SysDictService sysDictService;

    /**
     * 烟站
     */
    private String orgType_two = "2";
    /**
     * 仓库
     */
    private String orgType_four = "4";

    /**
     * 保存
     *
     * @param ydOrganization
     * @return
     */
    @Transactional
    public Pair<Boolean, String> save(YdOrganization ydOrganization) {
        try {
            boolean flag = checkOrgNameRepeat(ydOrganization.getOrgName());
            if (!flag) {
                return new Pair<>(false, "组织名称重复");
            }
            ydOrganization.setCreateTime(new Date());
            ydOrganization.setOrgStatus(1);
            ydOrganization.setIsDel(0);
            ydOrganizationService.insert(ydOrganization);
            //烟站
            if (ydOrganization.getOrgType().equals(orgType_two)) {
                //保存收购区域关系
                saveOrgPurchaseRegion(ydOrganization);
                //保存仓库
                List<YdOrganization> list = new ArrayList<>();
                for (YdOrganization organization : ydOrganization.getOrgStorehouses()) {
                    organization.setParentId(ydOrganization.getId());
                    organization.setOrgName(organization.getStorehouseName());
                    /**担转千克**/
                    String volume = organization.getStorehouseVolume();
                    organization.setStorehouseVolume(Long.valueOf(volume == null ? "0" : volume) * 50 + "");
                    list.add(organization);
                }
                batchInsertStoreHouse(list);
            }
            return new Pair<>(true, "保存成功");
        } catch (Exception e) {
            log.error("", e);
            return new Pair<>(false, "系统异常");
        }
    }

    /**
     * 批量修改仓库
     */
    public void batchUpdateStoreHouse(List<YdOrganization> storeHouseList) {
        try {
            if (storeHouseList != null && storeHouseList.size() > 0) {
                List<YdOrganization> list = new ArrayList<>();
                for (YdOrganization ydOrganization : storeHouseList) {
                    list.add(ydOrganization);
                    if (list.size() % 30 == 0) {
                        ydOrganizationService.batchUpdateStoreHouse(list);
                        list.clear();
                    }
                }
                ydOrganizationService.batchUpdateStoreHouse(list);
            }
        } catch (Exception e) {
            log.error("", e);
        }
    }


    /**
     * 批量新增仓库
     *
     * @param storeHouseList
     */
    public void batchInsertStoreHouse(List<YdOrganization> storeHouseList) {
        try {
            if (storeHouseList != null && storeHouseList.size() > 0) {
                List<YdOrganization> list = new ArrayList<>();
                for (YdOrganization ydOrganization : storeHouseList) {
                    list.add(ydOrganization);
                    if (list.size() % 30 == 0) {
                        ydOrganizationService.batchInsertStoreHouse(list);
                        list.clear();
                    }
                }
                ydOrganizationService.batchInsertStoreHouse(list);
            }
        } catch (Exception e) {
            log.error("", e);
        }

    }

    /**
     * 保存组织-收购区域的关系
     *
     * @param ydOrganization
     * @return
     */
    public boolean saveOrgPurchaseRegion(YdOrganization ydOrganization) {
        List<YdOrgPurchaseRegion> ydOrgPurchaseRegions = null;
        try {
            ydOrgPurchaseRegions = ydOrganization.getOrgPurchaseRegions();
            List<YdOrgPurchaseRegion> list = new ArrayList<>();
            for (YdOrgPurchaseRegion ydOrgPurchaseRegion : ydOrgPurchaseRegions) {
                ydOrgPurchaseRegion.setOrgId(ydOrganization.getId());
                list.add(ydOrgPurchaseRegion);
                if (list.size() % 30 == 0) {
                    ydOrgPurchaseRegionService.batchInsert(list);
                    list.clear();
                }
            }
            ydOrgPurchaseRegionService.batchInsert(list);
            return true;
        } catch (Exception e) {
            log.error("", e);
            return false;
        }
    }


    /**
     * 删除
     *
     * @param id
     * @return
     */
    public Pair<Boolean, String> delete(Long id) {
        YdOrganization org = null;
        try {
            org = get(id);
            org.setIsDel(1);
            ydOrganizationService.update(org);
            String org_key = RedisKeyUtil.org_key + id;
            JedisUtil.delKey(org_key);
            return new Pair<>(true, "删除成功");
        } catch (Exception e) {
            log.error("", e);
            return new Pair<>(false, "系统异常");
        }
    }

    /**
     * 编辑回显
     *
     * @param id
     * @return
     */
    public YdOrganization get(Long id) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        YdOrganization org = getYdOrganizationForRedis(id);
        if (org == null) {
            return new YdOrganization();
        }
        String volume = org.getStorehouseVolume();
        /**单位转化 千克>>担*/
        org.setStorehouseVolume(Long.valueOf(volume == null ? "0" : volume) / 50 + "");
        stopWatch.stop();
        stopWatch.start();
        Integer districtId = org.getOrgDistrictId();
        List<Integer> districtIds = new ArrayList<>();
        districtIds.add(districtId);
        districtIds = districtBizService.getParentIds(districtId, districtIds);
        stopWatch.stop();
        stopWatch.start();
        //所属区域ids
        org.setDistrictIds(districtIds);
        if (org.getOrgType().equals(orgType_two)) {
            //查询收购区域信息
            YdOrgPurchaseRegion ydOrgPurchaseRegion = new YdOrgPurchaseRegion();
            ydOrgPurchaseRegion.setOrgId(id);
            List<YdOrgPurchaseRegion> list = ydOrgPurchaseRegionService.queryAll(ydOrgPurchaseRegion);
            org.setOrgPurchaseRegions(list);
            //查看收购仓库信息
            YdOrganization ydOrganization = new YdOrganization();
            ydOrganization.setParentId(id);
            List<YdOrganization> storeHouseList = ydOrganizationService.queryAll(ydOrganization);
            for (YdOrganization organization : storeHouseList) {
                organization.setStorehouseName(organization.getOrgName());
            }
            org.setOrgStorehouses(storeHouseList);
        }
        stopWatch.stop();
        log.info("org:get:{}", stopWatch.prettyPrint());
        return org;
    }


    /**
     * 修改组织
     *
     * @param ydOrganization
     * @return
     */
    @Transactional
    public Pair<Boolean, String> update(YdOrganization ydOrganization) {
        try {
            Long orgId = ydOrganization.getId();
            //不能修改父级
            ydOrganization.setParentId(null);
            ydOrganization.setUpdateTime(new Date());
            ydOrganizationService.update(ydOrganization);
            String org_key = RedisKeyUtil.org_key + orgId;
            JedisUtil.delKey(org_key);
            //烟站
            //更新村组关系
            ydOrgPurchaseRegionService.deleteByOrgId(orgId);
            saveOrgPurchaseRegion(ydOrganization);
            //更新仓库
            List<YdOrganization> updateStoreHouseList = new ArrayList<>();
            List<YdOrganization> insertStoreHouseList = new ArrayList<>();
            for (YdOrganization organization : ydOrganization.getOrgStorehouses()) {
                if (organization.getId() == null) {
                    //新增
                    organization.setOrgName(organization.getStorehouseName());
                    organization.setParentId(orgId);
                    String volume = organization.getStorehouseVolume();
                    organization.setStorehouseVolume(Long.valueOf(volume == null ? "0" : volume) * 50 + "");
                    insertStoreHouseList.add(organization);
                } else {
                    //修改
                    organization.setOrgName(organization.getStorehouseName());
                    String volume = organization.getStorehouseVolume();
                    organization.setStorehouseVolume(Long.valueOf(volume == null ? "0" : volume) * 50 + "");
                    updateStoreHouseList.add(organization);
                }
            }
            //批量修改 批量新增 减少io次数
            batchUpdateStoreHouse(updateStoreHouseList);
            batchInsertStoreHouse(insertStoreHouseList);
            return new Pair<>(true, "修改成功");
        } catch (Exception e) {
            log.error("", e);
            return new Pair<>(false, "系统异常");
        }

    }


    /**
     * 分页查询
     *
     * @param organizationDto
     * @return
     */
    public PageInfo<OrganizationVo> list(YdOrganizationDto organizationDto) {
        int pageNum = organizationDto.getPageNum();
        int pageSize = organizationDto.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        YdOrganization ydOrganization = new YdOrganization();
        ydOrganization.setOrgStatus(organizationDto.getOrgStatus());
        ydOrganization.setOrgType(organizationDto.getOrgType());
        ydOrganization.setOrgName(organizationDto.getOrgName());
        ydOrganization.setOrgFullName(organizationDto.getOrgFullName());
        List<OrganizationVo> list = ydOrganizationService.getOrgList(ydOrganization);
        PageInfo<OrganizationVo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }


    /**
     * 组织列表 不分页
     *
     * @return
     */
    public List<YdOrganization> listNotPage() {
        return ydOrganizationService.getOrgListByOrgType(Arrays.asList("1", "2", "3"));
    }


    /**
     * 获取组织树
     *
     * @param
     * @return
     */
    public OrgTree getOrgTree() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<YdOrganization> orgList = ydOrganizationService.getOrgListByOrgType(Arrays.asList("1", "2", "3"));
        stopWatch.stop();
        log.info("ydOrganizationService.list:{}", stopWatch.prettyPrint());
        orgList = orgList.stream().sorted(Comparator.comparing(YdOrganization::getParentId)).collect(Collectors.toList());
        OrgTree orgTree = new OrgTree();
        YdOrganization top = orgList.get(0);
        orgTree.setId(0L);
        orgTree.setPid(0L);
        orgTree.setOrgCode(top.getOrgCode());
        orgTree.setOrgName(top.getOrgName());
        recursionOrgTree(orgTree, orgList);
        return orgTree;
    }

    /**
     * 递归生成组织树
     *
     * @param orgTree
     * @param list
     */
    public void recursionOrgTree(OrgTree orgTree, List<YdOrganization> list) {
        for (YdOrganization ydOrganization : list) {
            if (orgTree.getId().equals(ydOrganization.getParentId())) {
                List<OrgTree> children = orgTree.getChildren();
                if (children == null || children.size() == 0) {
                    children = new ArrayList<>();
                    orgTree.setChildren(children);
                }
                OrgTree subTree = OrgTree.convertOrgTree(ydOrganization);
                children.add(subTree);
                recursionOrgTree(subTree, list);
            }
        }
    }

    /**
     * 递归查询上级orgId
     *
     * @param orgId
     * @param pids
     * @return
     */
    public String getParentIdsByOrdId(Long orgId, List<Long> pids) {
        YdOrganization org = getYdOrganizationForRedis(orgId);
        if (org != null) {
            Long pid = org.getParentId();
            if (pid != 0) {
                pids.add(pid);
                getParentIdsByOrdId(pid, pids);
            }
        }
        pids = pids.stream().sorted().collect(Collectors.toList());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < pids.size(); i++) {
            stringBuilder.append(pids.get(i));
            if (i < pids.size() - 1) {
                stringBuilder.append("-");
            }
        }
        return stringBuilder.toString();
    }


    /**
     * 根据组织ID查询 所有父级组织ID及当前组织ID
     *
     * @param orgId
     * @return
     */
    public String getOrgIdsByOrgId(Long orgId) {
        List<Long> list = new ArrayList<>();
        list.add(orgId);
        StringBuffer stringBuffer = new StringBuffer("-");
        String orgIdsStr = getParentIdsByOrdId(orgId, list);
        stringBuffer.append(orgIdsStr).append("-");
        return stringBuffer.toString();
    }

    public List<YdOrganization> all() {
        List<SysDict> list = sysDictService.findByType("org_type");
        List<String> typeList = new ArrayList<>();
        for (SysDict sysDict : list) {
            if (!sysDict.getValue().equals("4")) {
                typeList.add(sysDict.getValue());
            }
        }
        return ydOrganizationService.getOrgListByOrgType(typeList);
    }


    /**
     * 根据组织id获取村庄-小组关系
     *
     * @param orgId
     * @return
     */
    public Map<String, Set<String>> getVillageGroupByOrgId(Long orgId) {
        Map<String, Set<String>> map = new HashMap<>();
        YdOrgPurchaseRegion ydOrgPurchaseRegion = new YdOrgPurchaseRegion();
        ydOrgPurchaseRegion.setOrgId(orgId);
        List<YdOrgPurchaseRegion> list = ydOrgPurchaseRegionService.queryAll(ydOrgPurchaseRegion);
        Set<String> villageSet = new HashSet<String>();
        for (YdOrgPurchaseRegion orgPurchaseRegion : list) {
            villageSet.add(orgPurchaseRegion.getVillageName());
        }
        Set<String> groupSet = new HashSet<>();
        for (String s : villageSet) {
            groupSet = new HashSet<>();
            for (YdOrgPurchaseRegion orgPurchaseRegion : list) {
                if (s.equals(orgPurchaseRegion.getVillageName())) {
                    groupSet.add(orgPurchaseRegion.getGroupName());
                }
            }
            map.put(s, groupSet);
        }
        return map;
    }

    /**
     * 根据组织id查询产地信息
     *
     * @param orgId
     * @return
     */
    public JSONObject getDistrictDetailByOrgId(Long orgId) {
        YdOrganization org = ydOrganizationService.queryById(orgId);
        Integer districtId = org.getOrgDistrictId();
        List<YdDistrict> list = new ArrayList<>();
        list = districtBizService.getParents(districtId, list);
        JSONObject rtnJson = new JSONObject();
        rtnJson.put("districts", list);
        rtnJson.put("village-group", getVillageGroupByOrgId(orgId));
        return rtnJson;
    }

    /**
     * 导入村庄-小组信息
     *
     * @param orgId
     * @param inputStream
     * @return
     */
    @Transactional
    public boolean uploadVillageGroupByOrgId(Long orgId, InputStream inputStream) {
        try {
            List<VillageGroupDto> result = ExcelUtils.importExcel(inputStream, 1, true, VillageGroupDto.class);
            YdOrgPurchaseRegion ydOrgPurchaseRegion = new YdOrgPurchaseRegion();
            if (result != null && result.size() > 0) {
                ydOrgPurchaseRegionService.deleteByOrgId(orgId);
            }
            YdOrganization ydOrganization = new YdOrganization();
            List<YdOrgPurchaseRegion> list = new ArrayList<>();
            ydOrganization.setId(orgId);
            for (VillageGroupDto villageGroupDto : result) {
                ydOrgPurchaseRegion = new YdOrgPurchaseRegion();
                ydOrgPurchaseRegion.setOrgId(orgId);
                ydOrgPurchaseRegion.setGroupName(villageGroupDto.getGroupName());
                ydOrgPurchaseRegion.setVillageName(villageGroupDto.getVillageName());
                list.add(ydOrgPurchaseRegion);
            }
            ydOrganization.setOrgPurchaseRegions(list);
            saveOrgPurchaseRegion(ydOrganization);
        } catch (IOException e) {
            log.error("", e);
            return false;
        }
        return true;
    }


    /**
     * 从redis中后去组织
     *
     * @param id
     * @return
     */
    private YdOrganization getYdOrganizationForRedis(Long id) {
        String org_key = RedisKeyUtil.org_key + id;
        String org_value = JedisUtil.getValue(org_key);
        if (StringUtils.isBlank(org_value)) {
            YdOrganization ydOrganization = ydOrganizationService.queryById(id);
            JedisUtil.setKey(org_key, JSONObject.toJSONString(ydOrganization), 60 * 60 * 24 * 5);
            return ydOrganization;
        }
        YdOrganization ydOrganization = JSONObject.parseObject(org_value, YdOrganization.class);
        return ydOrganization;
    }

    /**
     * 根据组织查询烟站
     *
     * @param orgId
     * @return
     */
    public List<YdOrganization> getStoreHoseByOrg(Long orgId) {
        YdOrganization ydOrganization = new YdOrganization();
        ydOrganization.setParentId(orgId);
        ydOrganization.setIsDel(0);
        ydOrganization.setOrgStatus(1);
        ydOrganization.setOrgType(orgType_four);
        List<YdOrganization> list = ydOrganizationService.queryAll(ydOrganization);
        for (YdOrganization organization : list) {
            organization.setStorehouseName(organization.getOrgName());
        }
        return list;
    }


    /**
     * 校验组织名称是否重复
     *
     * @param orgName
     * @return
     */
    private boolean checkOrgNameRepeat(String orgName) {
        YdOrganization ydOrganization = new YdOrganization();
        ydOrganization.setIsDel(0);
        ydOrganization.setOrgStatus(1);
        ydOrganization.setOrgName(orgName);
        List<YdOrganization> orgList = ydOrganizationService.queryAll(ydOrganization);
        if (orgList != null && orgList.size() > 0) {
            return false;
        }
        return true;
    }

}

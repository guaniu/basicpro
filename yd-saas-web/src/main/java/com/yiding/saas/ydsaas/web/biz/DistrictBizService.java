package com.yiding.saas.ydsaas.web.biz;

import com.alibaba.fastjson.JSONObject;
import com.yiding.saas.ydsaas.dto.YdDistrictDto;
import com.yiding.saas.ydsaas.model.YdDistrict;
import com.yiding.saas.ydsaas.service.YdDistrictService;
import com.yiding.saas.ydsaas.vo.DistrictTree;
import com.yiding.saas.ydsaas.web.util.JedisUtil;
import com.yiding.saas.ydsaas.web.util.RedisKeyUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 行政区业务接口
 */
@Service
public class DistrictBizService {

    /**
     * 日志
     */
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private YdDistrictService ydDistrictService;

    /**
     * 保存行政区
     *
     * @param ydDistrict
     * @return
     */
    public boolean save(YdDistrict ydDistrict) {
        try {
            ydDistrictService.insert(ydDistrict);
            return true;
        } catch (Exception e) {
            log.error("", e);
        }
        return false;
    }


    /**
     * 根据条件查询行政区列表
     *
     * @param ydDistrictDto
     * @return
     */
    public List<YdDistrict> list(YdDistrictDto ydDistrictDto) {
        YdDistrict ydDistrict = new YdDistrict();
        ydDistrict.setId(ydDistrictDto.getId());
        ydDistrict.setParentId(ydDistrictDto.getParentId());
        ydDistrict.setDistrictLevel(ydDistrictDto.getDistrictLevel());
        List<YdDistrict> list = ydDistrictService.list(ydDistrict);
        return list;
    }


    /**
     * 获取区域树
     *
     * @param ydDistrictDto
     * @return
     */
    public DistrictTree getDistrictTree(YdDistrictDto ydDistrictDto) {
        //权限逻辑暂时空着
        List<YdDistrict> districtList = list(new YdDistrictDto());
        DistrictTree districtTree = new DistrictTree();
        //文山州为最顶层
        YdDistrict topDistrict = new YdDistrict();
        int topPid = ydDistrictDto.getParentId() == null ? 1 : ydDistrictDto.getParentId();
        if (topPid == 0) {
            topDistrict = ydDistrictService.queryById(1);
        } else {
            topDistrict = ydDistrictService.queryById(topPid);
        }
        districtTree.setId(topPid);
        districtTree.setDistrictLevel(topDistrict.getDistrictLevel());
        districtTree.setParentId(topDistrict.getParentId());
        districtTree.setDistrictName(topDistrict.getDistrictName());
        recursionDicTree(districtTree, districtList);
        return districtTree;
    }

    /**
     * 递归生成组织树
     *
     * @param districtTree
     * @param list
     */
    public void recursionDicTree(DistrictTree districtTree, List<YdDistrict> list) {
        for (YdDistrict ydDistrict : list) {
            if (districtTree.getId().equals(ydDistrict.getParentId())) {
                List<DistrictTree> children = districtTree.getChildren();
                if (children == null || children.size() == 0) {
                    children = new ArrayList<>();
                    districtTree.setChildren(children);
                }
                DistrictTree subTree = DistrictTree.convertDicTree(ydDistrict);
                children.add(subTree);
                recursionDicTree(subTree, list);
            }
        }
    }


    /**
     * 查询父级ids
     *
     * @param districtId
     * @param list
     * @return
     */
    public List<Integer> getParentIds(Integer districtId, List<Integer> list) {
        YdDistrict district = getYdDisrictByIdForRedis(districtId);
        if (district != null) {
            Integer pid = district.getParentId();
            if (pid != 0) {
                list.add(pid);
                getParentIds(pid, list);
            }
        }
        return list.stream().sorted().collect(Collectors.toList());
    }

    /**
     * 查询父级区域
     *
     * @param districtId
     * @param list
     * @return
     */
    public List<YdDistrict> getParents(Integer districtId, List<YdDistrict> list) {
        YdDistrict district = getYdDisrictByIdForRedis(districtId);
        if (district != null) {
            Integer pid = district.getParentId();
            list.add(district);
            getParents(pid, list);
        }
        return list.stream().sorted(Comparator.comparing(YdDistrict::getParentId)).collect(Collectors.toList());
    }


    public YdDistrict getById(Integer id) {
        return ydDistrictService.queryById(id);
    }


    /**
     * 从redis中获取
     *
     * @param districtId
     * @return
     */
    private YdDistrict getYdDisrictByIdForRedis(Integer districtId) {
        String district_key = RedisKeyUtil.district_key + districtId;
        String district_value = JedisUtil.getValue(district_key);
        if (StringUtils.isBlank(district_value)) {
            YdDistrict district = ydDistrictService.queryById(districtId);
            JedisUtil.setKey(district_key, JSONObject.toJSONString(district), 60 * 60 * 24 * 5);
            return district;
        }
        YdDistrict district = JSONObject.parseObject(district_value, YdDistrict.class);
        return district;
    }

}

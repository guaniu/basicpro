package com.yiding.saas.ydsaas.web.biz;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yiding.saas.ydsaas.common.utils.DateTimeUtils;
import com.yiding.saas.ydsaas.dto.YdAcquisitionPlanDto;
import com.yiding.saas.ydsaas.model.YdAcquisitionPlan;
import com.yiding.saas.ydsaas.model.YdOrganization;
import com.yiding.saas.ydsaas.model.YdUser;
import com.yiding.saas.ydsaas.service.YdAcquisitionPlanService;
import com.yiding.saas.ydsaas.service.YdOrganizationService;
import com.yiding.saas.ydsaas.service.YdUserService;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 收购计划
 */
@Service
public class AcquisitionPlanBizService {

    /**
     * 日志
     */
    Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private YdAcquisitionPlanService ydAcquisitionPlanService;

    @Autowired
    private YdUserService ydUserService;

    @Autowired
    private YdOrganizationService ydOrganizationService;


    /**
     * 保存收购计划
     *
     * @param ydAcquisitionPlanDto
     * @return
     */
    public Pair<Boolean, String> save(YdAcquisitionPlanDto ydAcquisitionPlanDto) {
        try {
            if (!checkRepeatByOrgId(ydAcquisitionPlanDto.getStorehouseId())) {
                return new Pair<>(false, "该仓库收购计划已存在");
            }
            YdAcquisitionPlan ydAcquisitionPlan = processRedundanceData(ydAcquisitionPlanDto);
            ydAcquisitionPlan.setCreateTime(new Date());
            ydAcquisitionPlanService.insert(ydAcquisitionPlan);
        } catch (Exception e) {
            log.error("", e);
            return new Pair<>(false, "系统异常");
        }
        return new Pair<>(true, "ok");
    }


    /**
     * 删除收购计划
     *
     * @param id
     * @return
     */
    public boolean delete(Long id) {
        try {
            ydAcquisitionPlanService.deleteById(id);
        } catch (Exception e) {
            log.error("", e);
            return false;
        }
        return true;
    }


    /**
     * 编辑回显
     *
     * @param id
     * @return
     */
    public YdAcquisitionPlan get(Long id) {
        YdAcquisitionPlan ydAcquisitionPlan = ydAcquisitionPlanService.queryById(id);
        ydAcquisitionPlan.setAcquisitionWeight((Long.valueOf(ydAcquisitionPlan.getAcquisitionWeight()) / 50) + "");
        return ydAcquisitionPlan;
    }


    /**
     * 更新收购计划
     *
     * @param ydAcquisitionPlanDto
     * @return
     */
    public boolean update(YdAcquisitionPlanDto ydAcquisitionPlanDto) {
        try {
            YdAcquisitionPlan ydAcquisitionPlan = processRedundanceData(ydAcquisitionPlanDto);
            ydAcquisitionPlan.setUpdateTime(new Date());
            ydAcquisitionPlan.setId(ydAcquisitionPlanDto.getId());
            ydAcquisitionPlanService.update(ydAcquisitionPlan);
        } catch (Exception e) {
            log.error("", e);
            return false;
        }
        return true;
    }


    /**
     * 收购计划列表
     *
     * @return
     */
    public PageInfo<YdAcquisitionPlan> list(YdAcquisitionPlanDto ydAcquisitionPlanDto) {
        PageHelper.startPage(ydAcquisitionPlanDto.getPageNum(), ydAcquisitionPlanDto.getPageSize());
        YdAcquisitionPlan ydAcquisitionPlan = new YdAcquisitionPlan();
        ydAcquisitionPlan.setStartTime(DateTimeUtils.getCurrYearFirst());
        ydAcquisitionPlan.setStopTime(DateTimeUtils.getCurrYearLast());
        ydAcquisitionPlan.setSmokeStationId(ydAcquisitionPlanDto.getSmokeStationId());
        List<YdAcquisitionPlan> list = ydAcquisitionPlanService.list(ydAcquisitionPlan);
        for (YdAcquisitionPlan acquisitionPlan : list) {
            acquisitionPlan.setAcquisitionWeight((Long.valueOf(acquisitionPlan.getAcquisitionWeight()) / 50) + "");
        }
        PageInfo<YdAcquisitionPlan> planPageInfo = new PageInfo<>(list);
        return planPageInfo;
    }


    /**
     * 根据仓库id校验是否存在收购计划
     *
     * @param orgId
     * @return
     */
    public boolean checkRepeatByOrgId(Long orgId) {
        YdAcquisitionPlan ydAcquisitionPlan = new YdAcquisitionPlan();
        ydAcquisitionPlan.setStorehouseId(orgId);
        ydAcquisitionPlan.setStartTime(DateTimeUtils.getCurrYearFirst());
        ydAcquisitionPlan.setStopTime(DateTimeUtils.getCurrYearLast());
        List<YdAcquisitionPlan> list = ydAcquisitionPlanService.list(ydAcquisitionPlan);
        if (list != null && list.size() > 0) {
            return false;
        }
        return true;

    }


    /**
     * 处理冗余数据
     *
     * @param ydAcquisitionPlanDto
     */
    private YdAcquisitionPlan processRedundanceData(YdAcquisitionPlanDto ydAcquisitionPlanDto) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(DateTimeUtils.DATE_FORMAT_DAY);
        YdAcquisitionPlan ydAcquisitionPlan = new YdAcquisitionPlan();
        ydAcquisitionPlan.setSmokeStationId(ydAcquisitionPlanDto.getSmokeStationId());
        ydAcquisitionPlan.setStorehouseId(ydAcquisitionPlanDto.getStorehouseId());
        ydAcquisitionPlan.setStartTime(sdf.parse(ydAcquisitionPlanDto.getStartTime()));
        ydAcquisitionPlan.setStopTime(sdf.parse(ydAcquisitionPlanDto.getStopTime()));
        ydAcquisitionPlan.setCreateUserId(ydAcquisitionPlanDto.getCreateUserId());
        ydAcquisitionPlan.setAcquisitionWeight(ydAcquisitionPlanDto.getAcquisitionWeight());
        ydAcquisitionPlan.setSmokeStationName(ydAcquisitionPlanDto.getSmokeStationName());
        YdUser user = ydUserService.queryById(ydAcquisitionPlan.getCreateUserId());
        if (user != null) {
            ydAcquisitionPlan.setCreateUserName(user.getUserName());
        }
        /**仓库*/
        YdOrganization org = ydOrganizationService.queryById(ydAcquisitionPlan.getStorehouseId());
        if (org != null) {
            ydAcquisitionPlan.setStorehouseName(org.getOrgName());
        }
        /**烟站*/
        YdOrganization org_ = ydOrganizationService.queryById(ydAcquisitionPlan.getSmokeStationId());
        if (org_ != null) {
            ydAcquisitionPlan.setSmokeStationName(org_.getOrgName());
        }
        //担转千克
        ydAcquisitionPlan.setAcquisitionWeight((Long.valueOf(ydAcquisitionPlan.getAcquisitionWeight()) * 50) + "");
        return ydAcquisitionPlan;
    }
}

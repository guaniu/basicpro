package com.yiding.saas.ydsaas.web.controller.api;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yiding.saas.ydsaas.dto.YdOrganizationDto;
import com.yiding.saas.ydsaas.model.YdOrganization;
import com.yiding.saas.ydsaas.vo.OrganizationVo;
import com.yiding.saas.ydsaas.web.biz.OrgBizService;
import com.yiding.saas.ydsaas.web.core.HttpResult;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/org")
public class ApiOrganisationController {
    /**
     * 日志
     */
    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OrgBizService orgBizService;


    /**
     * 保存组织
     *
     * @param ydOrganization
     * @return
     */
    @PostMapping("/save")
    public Object save(@RequestBody YdOrganization ydOrganization) {
        Pair<Boolean, String> resultPair = orgBizService.save(ydOrganization);
        if (!resultPair.getKey()) {
            log.info("orgBizService.save result:{}", resultPair);
            return HttpResult.loginFail(resultPair.getValue());
        }
        return HttpResult.ok(resultPair.getValue());
    }

    /**
     * 删除组织
     *
     * @param ydOrganization
     * @return
     */
    @PostMapping("/delete")
    public Object delete(@RequestBody YdOrganization ydOrganization) {
        Pair<Boolean, String> resultPair = orgBizService.delete(ydOrganization.getId());
        if (!resultPair.getKey()) {
            log.info("orgBizService.delete result:{}", resultPair);
            return HttpResult.loginFail(resultPair.getValue());
        }
        return HttpResult.ok(resultPair.getValue());
    }

    /**
     * 编辑
     *
     * @param ydOrganization
     * @return
     */
    @PostMapping("/get")
    public Object get(@RequestBody YdOrganization ydOrganization) {
        YdOrganization org = orgBizService.get(ydOrganization.getId());
        return HttpResult.ok(org);
    }


    /**
     * 更新
     *
     * @param ydOrganization
     * @return
     */
    @PostMapping("/update")
    public Object update(@RequestBody YdOrganization ydOrganization) {
        Pair<Boolean, String> resultPair = orgBizService.update(ydOrganization);
        if (!resultPair.getKey()) {
            log.info("orgBizService.update result:{}", resultPair);
            return HttpResult.loginFail(resultPair.getValue());
        }
        return HttpResult.ok(resultPair.getValue());
    }


    /**
     * 查询
     *
     * @param ydOrganizationDto
     * @return
     */
    @PostMapping("/list")
    public Object list(@RequestBody YdOrganizationDto ydOrganizationDto) {
        PageInfo<OrganizationVo> list = orgBizService.list(ydOrganizationDto);
        return HttpResult.ok(list);
    }

    @PostMapping("/tree")
    public Object orgTree() {
        JSONObject rtnJson = new JSONObject();
        rtnJson.put("orgTree", orgBizService.getOrgTree());
        return HttpResult.ok(rtnJson);
    }

    @PostMapping("/all")
    public Object all() {
        List<YdOrganization> list = orgBizService.all();
        return HttpResult.ok(list);
    }

    /**
     * 根据组织id查询村庄-小组关系
     *
     * @param ydOrganizationDto
     * @return
     */
    @PostMapping("/getVillageGroupByOrgId")
    public Object getVillageGroupByOrgId(@RequestBody YdOrganizationDto ydOrganizationDto) {
        return orgBizService.getVillageGroupByOrgId(Long.valueOf(ydOrganizationDto.getOrgId()));
    }


    @PostMapping("/getDistrictDetailByOrgId")
    public Object getDistrictDetailByOrgId(@RequestBody YdOrganizationDto ydOrganizationDto) {
        JSONObject json = orgBizService.getDistrictDetailByOrgId(Long.valueOf(ydOrganizationDto.getOrgId()));
        return json;
    }

    /**
     * 根据组织Id查询仓库信息
     *
     * @param ydOrganizationDto
     * @return
     */
    @PostMapping("/getStoreHoseByOrg")
    public Object getStoreHoseByOrg(@RequestBody YdOrganizationDto ydOrganizationDto) {
        Integer orgId = ydOrganizationDto.getOrgId();
        Long orgid=Long.valueOf(orgId);
        return HttpResult.ok(orgBizService.getStoreHoseByOrg(orgid));
    }

    /**
     * 组织列表不分页
     * @return
     */
    @PostMapping("/orgList")
    public Object getOrgList(){
        return HttpResult.ok(orgBizService.listNotPage());
    }

}

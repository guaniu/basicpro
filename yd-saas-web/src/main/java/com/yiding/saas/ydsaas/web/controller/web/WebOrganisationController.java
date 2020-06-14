package com.yiding.saas.ydsaas.web.controller.web;

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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 组织机构
 */
@RestController
@RequestMapping("/web/org")
public class WebOrganisationController {

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
            return HttpResult.error(resultPair.getValue());
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
            return HttpResult.error(resultPair.getValue());
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
            return HttpResult.error(resultPair.getValue());
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


    /**
     * 根据组织ID上传村庄-小组信息
     *
     * @return
     */
    @PostMapping("/uploadVillageGroupByOrgId")
    public Object uploadVillageGroupByOrgId(@RequestParam("excle") MultipartFile multipartFile, Long orgId) throws Exception {
        boolean flag = orgBizService.uploadVillageGroupByOrgId(orgId, multipartFile.getInputStream());
        if (flag) {
            return HttpResult.ok("导入成功");
        }
        return HttpResult.error("导入失败");
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

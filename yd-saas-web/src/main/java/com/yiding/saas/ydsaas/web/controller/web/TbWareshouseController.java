package com.yiding.saas.ydsaas.web.controller.web;

import com.yiding.saas.ydsaas.model.*;
import com.yiding.saas.ydsaas.service.YdOrganizationService;
import com.yiding.saas.ydsaas.service.YdUserOrgRefService;
import com.yiding.saas.ydsaas.service.YdWarehouseService;
import com.yiding.saas.ydsaas.web.biz.OrgBizService;
import com.yiding.saas.ydsaas.web.core.HttpResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author BruceLee
 * @Date 2020/5/25
 */
@Api(tags = "仓库管理")
@RestController
@RequestMapping(value = "tbWarehouse")
public class TbWareshouseController {

    @Autowired
    private YdWarehouseService ydWarehouseService;
    @Autowired
    private YdUserOrgRefService ydUserOrgRefService;
    @Autowired
    private OrgBizService orgBizService;
    @Autowired
    private YdOrganizationService ydOrganizationService;

    @PostMapping(value = "save")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "orgId",value = "组织id",required = true,dataType = "int"),
//            @ApiImplicitParam(name = "tobaccoId",value = "烟叶信息id",required = true,dataType = "int"),
//            @ApiImplicitParam(name = "inOutType",value = "出/入库类型（0入库1出库2全部）",required = true,dataType = "String"),
//            @ApiImplicitParam(name = "status",value = "0 烟站仓库 1复烤厂仓库",required = true,dataType = "int"),
//            @ApiImplicitParam(name = "packageType",value = "打包类型（0烟包1烟框）",required = true,dataType = "String"),
//            @ApiImplicitParam(name = "isValid",value = "是否有效(0有效1无效)",required = true,dataType = "int"),
//            @ApiImplicitParam(name = "tobaccoStation",value = "烟站名称（发货单位）/复烤厂名称",required = true,dataType = "String"),
//            @ApiImplicitParam(name = "repetTobaccoStation",value = "复烤厂名称（收货单位）,该字段仅在烟站出库使用",required = false,dataType = "String"),
//            @ApiImplicitParam(name = "list",value = "产地和烟农信息，烟包产地入参分为address和district_ids(为地址id集合，用-隔开，烟框产地只存address)",required = true,dataType = "String"),
//    })
    public HttpResult save(@RequestBody YdWarehouseLogDO ydWarehouseLogDO){
        //仓库数据在组织表中，组织id类型为仓库的则为仓库id，本次存储在repertoryId中，sql中的org_id对应repertoryId
        //2020-6-3 上午开完会，开发组织表的程序员如此设计并告知于我
        String orgIds=orgBizService.getParentIdsByOrdId((long)ydWarehouseLogDO.getOrgId(),new ArrayList<>());
        ydWarehouseLogDO.setOrgIds(orgIds);
        YdOrganization ydOrganization= ydOrganizationService.queryById((long)ydWarehouseLogDO.getOrgId());
        ydWarehouseLogDO.setDistrictId(ydOrganization.getOrgDistrictId());
        String msg=ydWarehouseService.saveWarehouse(ydWarehouseLogDO);
        if (msg.equals("出库数大于库存数")){
            return HttpResult.ok(500,msg);
        }
        return HttpResult.ok(200,msg);
    }

    @PostMapping(value="saveRfids")
    public HttpResult saveRfids(@RequestBody YdWarehouseLogDO ydWarehouseLogDO){
        return HttpResult.ok(200, ydWarehouseService.saveRfids(ydWarehouseLogDO.getList2()));
    }

    @PostMapping(value = "findPageLogList")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userId",value = "用户id",required = true,dataType = "int"),
//            @ApiImplicitParam(name = "repertoryId",value = "仓库id，不选择时默认传0或者null",required = true,dataType = "int"),
//            @ApiImplicitParam(name = "pageNum",value = "当前页码",required = true,dataType = "int"),
//            @ApiImplicitParam(name = "pageSize",value = "分页页数",required = true,dataType = "int"),
//            @ApiImplicitParam(name = "inOutType",value = "出/入库类型（0入库1出库2全部）",required = true,dataType = "String"),
//            @ApiImplicitParam(name = "status",value = "0 烟站仓库 1复烤厂仓库",required = true,dataType = "int"),
//            @ApiImplicitParam(name = "packageType",value = "打包类型（0烟包1烟框）",required = true,dataType = "String"),
//            @ApiImplicitParam(name = "isValid",value = "是否有效(0有效1无效)",required = true,dataType = "int"),
//            @ApiImplicitParam(name = "rfid",value = "文山扣号",required = false ,dataType = "String"),
//            @ApiImplicitParam(name = "startDate",value = "开始时间",required = false ,dataType = "String"),
//            @ApiImplicitParam(name = "endDate",value = "结束时间",required = false,dataType = "String"),
//            @ApiImplicitParam(name = "tobaccoStation",value = "烟站名称/复烤厂名称",required = false,dataType = "String")
//    })
    public HttpResult findPageLogList(@RequestBody YdWarehousePageDO ydWarehousePageDO){
        if (ydWarehousePageDO.getPageNum()<=0 || ydWarehousePageDO.getPageSize()<=0){
            return HttpResult.error(500,"分页页码或分页页数小于等于0");
        }
        List<Long> list=ydUserOrgRefService.listOrgIdsByUserId(ydWarehousePageDO.getUserId(),"web");

        if (list.size()==0){
            return HttpResult.error(500,"当前用户未分配数据权限");
        }
        List<String> list1=new ArrayList<>();
        for (Long l:list){
            list1.add("-"+String.valueOf(l)+"-");
        }
        ydWarehousePageDO.setList(list1);
        YdPageDO ydPageDO= ydWarehouseService.findPageList(ydWarehousePageDO);
        return HttpResult.ok(ydPageDO);
    }

    @GetMapping(value = "warehouseDetails")
    public HttpResult warehouseDetails(Integer logId){
        return HttpResult.ok(ydWarehouseService.warehouseDetails(logId));
    }

    @PostMapping(value = "findPageWarehouseList")
    public HttpResult findPageWarehouseList(@RequestBody YdWarehousePageDO ydWarehousePageDO){
        if (ydWarehousePageDO.getPageNum()<=0 || ydWarehousePageDO.getPageSize()<=0){
            return HttpResult.error(0,"分页页码或分页页数小于等于0");
        }
        List<Long> list=ydUserOrgRefService.listOrgIdsByUserId(ydWarehousePageDO.getUserId(),"web");
        List<String> list1=new ArrayList<>();
        for (Long l:list){
            list1.add("-"+String.valueOf(l)+"-");
        }
        ydWarehousePageDO.setList(list1);
        YdPageDO ydPageDO=ydWarehouseService.findPageWarehouseList(ydWarehousePageDO);
        return HttpResult.ok(ydPageDO);
    }

    @PostMapping(value = "getRepertory")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userId",value = "用户id",required = true,dataType = "long"),
//            @ApiImplicitParam(name = "isValid",value = "是否有效(0有效1无效)",required = true,dataType = "int")})
    public HttpResult getRepertory(@RequestBody YdWarehouseLogDO ydWarehouseLogDO){
        List<Long> list=ydUserOrgRefService.listOrgIdsByUserId(ydWarehouseLogDO.getUserId(),"web");
        List<String> list1=new ArrayList<>();
        for (Long l:list){
            list1.add("-"+String.valueOf(l)+"-");
        }
        ydWarehouseLogDO.setList2(list1);
        Map map =ydWarehouseService.getRepertory(ydWarehouseLogDO);
        return HttpResult.ok(map);
    }
    @PostMapping(value = "findByRfidList")
    public HttpResult findByRfidList(@RequestBody YdWarehousePageDO ydWarehousePageDO){
        Map map=ydWarehouseService.findByRfidList(ydWarehousePageDO);
        return HttpResult.ok(map);
    }

    @PostMapping(value = "updateWarehouse")
    public HttpResult updateWarehouse(@RequestBody YdWarehouseLogDO ydWarehouseLogDO){//出入库明细表id
        Map map=ydWarehouseService.updateWarehouse(ydWarehouseLogDO);
        return HttpResult.ok(map);
    }
}

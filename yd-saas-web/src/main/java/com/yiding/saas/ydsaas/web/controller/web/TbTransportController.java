package com.yiding.saas.ydsaas.web.controller.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yiding.saas.ydsaas.common.push.JiguangPush;
import com.yiding.saas.ydsaas.common.utils.DateTimeUtils;
import com.yiding.saas.ydsaas.common.utils.PinyinUtils;
import com.yiding.saas.ydsaas.dto.TransportTobaccoDto;
import com.yiding.saas.ydsaas.dto.YdTransportDto;
import com.yiding.saas.ydsaas.dto.YdWareHouseLogDto;
import com.yiding.saas.ydsaas.model.YdDevice;
import com.yiding.saas.ydsaas.model.YdInformation;
import com.yiding.saas.ydsaas.model.YdTransport;
import com.yiding.saas.ydsaas.model.YdTransportLog;
import com.yiding.saas.ydsaas.service.IotService;
import com.yiding.saas.ydsaas.service.YdInformationService;
import com.yiding.saas.ydsaas.service.YdTgService;
import com.yiding.saas.ydsaas.service.YdTransportLogService;
import com.yiding.saas.ydsaas.service.YdTransportService;
import com.yiding.saas.ydsaas.service.YdUserService;
import com.yiding.saas.ydsaas.vo.TransportTobaccoVo;
import com.yiding.saas.ydsaas.vo.UserVo;
import com.yiding.saas.ydsaas.web.biz.DataAuthBizService;
import com.yiding.saas.ydsaas.web.biz.OrgBizService;
import com.yiding.saas.ydsaas.web.core.HttpResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javafx.util.Pair;

@Api(tags = "运单")
@RestController
@RequestMapping("transport")
public class TbTransportController {

    @Autowired
    private YdTransportService ydTransportService;
    @Autowired
    private OrgBizService orgBizService;
    @Autowired
    private YdTgService ydTgService;
    @Autowired
    private YdTransportLogService ydTransportLogService;
    @Autowired
    private DataAuthBizService dataAuthBizService;
    @Autowired
    private YdInformationService ydInformationService;
    @Autowired
    private YdUserService ydUserService;
    @Autowired
    private IotService iotService;
    @Value("${apnsProduction}")
    private boolean apnsProduction;

    @ApiOperation(value = "保存")
    @PostMapping("/save")
    public HttpResult save(@RequestBody YdTransport record) {
        record.setCreateTime(new Date());
        record.setStatus(0);
        record.setTransportState(1);
        record.setTransportNo(PinyinUtils.getTransportNo(record.getSendName()));
        String parentIds = orgBizService.getOrgIdsByOrgId(Long.valueOf(record.getOrgId()));
        record.setParentIds(parentIds);
        int ret = ydTransportService.insert(record);
        return HttpResult.ok(ret);
    }

    /**
     * 编辑
     *
     * @param record
     * @return
     */
    @PostMapping("/update")
    public Object update(@RequestBody YdTransport record) {
        record.setUpdateTime(new Date());
        String parentIds = orgBizService.getOrgIdsByOrgId(Long.valueOf(record.getOrgId()));
        record.setParentIds(parentIds);
        int ret = ydTransportService.updateByPrimaryKey(record);
        return HttpResult.ok(ret);
    }

    @ApiOperation(value = "列表")
    @PostMapping("/list")
    public HttpResult list(@RequestBody YdTransport record) {
        record.setOrgIds(dataAuthBizService.getAuthData(record.getUserId()));
        PageInfo<YdTransport> pageInfo = ydTransportService.selectAll(record);
        return HttpResult.ok(pageInfo);
    }

    @ApiOperation(value = "待装车详情")
    @GetMapping("/getStep1")
    public HttpResult getStep1(@RequestParam Integer id) {
        //运单表新增读写设备id 或者 运单-读写设备关系关系表取出装车设备id
        YdTransport ydTransport = ydTransportService.selectByPrimaryKey(id);
        String str = DateTimeUtils.secondToTime(ydTransport.getEstime());
        ydTransport.setEstimeLabel(str);
        JSONObject rtnJson = (JSONObject) JSONObject.toJSON(ydTransport);
        if (ydTransport.getTransportState() == 2) {
            //处理获取读写设备信息 暂时写死
            rtnJson.put("deviceId", 1);
            rtnJson.put("deviceName", "洪福一号");
        }
        return HttpResult.ok(rtnJson);
    }

    @ApiOperation(value = "待确认/已签收详情")
    @GetMapping("/getStep2")
    public HttpResult getStep2(@RequestParam Integer id) {
        YdTransportDto ydTransport = ydTransportService.selectTransport(id);
        // TODO   缺少烟框详情、告警
        return HttpResult.ok(ydTransport);
    }

    @ApiOperation(value = "商品列表")
    @PostMapping("/goodsList")
    public HttpResult goodsList(@RequestBody YdWareHouseLogDto record) {
        PageHelper.startPage(record.getPageNum(), record.getPageSize());
        PageInfo<YdWareHouseLogDto> pageInfo = ydTgService.selectGoodsList(record.getTransportId());
        return HttpResult.ok(pageInfo);
    }

    @ApiOperation(value = "确认发车")
    @PostMapping("/deaprt")
    public HttpResult deaprt(@RequestBody YdTransport record) {
        boolean flag = ydTransportService.deaprt(record);
        if (!flag) {
            return HttpResult.error("调用Iot设备异常");
        }
        if(flag) {
	        YdTransport obj = ydTransportService.selectByPrimaryKey(record.getId());
	        List<UserVo> userList = ydUserService.selectRoleUserList(6, obj.getReceiveId());//6.收货员角色
	        if(userList!=null && userList.size()>0) {
	        	String content = "来自车牌("+obj.getCarNo()+")";
	        	for (UserVo ydUser : userList) {
	        		JiguangPush.push(ydUser.getId().toString(), content, apnsProduction);
	        		YdInformation info = new YdInformation();
	            	info.setTransportId(record.getId());
	            	info.setUserId(ydUser.getId());
	            	info.setType(5);
	            	info.setCreateTime(new Date());
	            	info.setContent(content);
	            	ydInformationService.insertSelective(info);
				}
	        }
        }
        return HttpResult.ok("操作成功");
    }

    @ApiOperation(value = "确认收货")
    @PostMapping("/received")
    public HttpResult received(@RequestBody YdTransport record) {
        //TODO 需调用硬件
        int ret = ydTransportService.updateTransport(record);
        if(ret>0) {
	        YdTransport obj = ydTransportService.selectByPrimaryKey(record.getId());
	        List<UserVo> userList = ydUserService.selectRoleUserList(3, obj.getReceiveId());//3.仓库主管角色
	        if(userList!=null && userList.size()>0) {
	        	String content = "来自车牌("+obj.getCarNo()+")";
	        	for (UserVo ydUser : userList) {
	        		JiguangPush.push(ydUser.getId().toString(), content, apnsProduction);
	        		YdInformation info = new YdInformation();
	            	info.setTransportId(record.getId());
	            	info.setUserId(ydUser.getId());
	            	info.setType(7);
	            	info.setCreateTime(new Date());
	            	info.setContent(content);
	            	ydInformationService.insertSelective(info);
				}
	        }
        }
        return HttpResult.ok(ret);
    }

    @ApiOperation(value = "删除")
    @GetMapping("/delete")
    public HttpResult delete(@RequestParam Integer id) {
        return HttpResult.ok(ydTransportService.deleteByPrimaryKey(id));
    }

    /**
     * PC运单统计
     *
     * @param orgId
     * @return
     */
    @ApiOperation(value = "运单统计")
    @GetMapping("/selectStat")
    public HttpResult selectStat(@RequestParam String orgId) {
        return HttpResult.ok(ydTransportService.selectStat(orgId));
    }

    /**
     * PC运单详情
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "运单详情")
    @GetMapping("/getDetails")
    public HttpResult getDetails(@RequestParam Integer id) {
        JSONObject json = new JSONObject();
        YdTransportDto ydTransport = ydTransportService.selectTransport(id);
        json.put("basic", ydTransport);
        return HttpResult.ok(json);
    }

    /**
     * 运单详情-烟叶列表
     *
     * @return
     */
    @PostMapping("/getTransportTobaccoList")
    public Object getTobaccoList(@RequestBody TransportTobaccoDto transportTobaccoDto) {
        List<Long> orgIds = dataAuthBizService.getAuthData(transportTobaccoDto.getUserId());
        transportTobaccoDto.setOrgIds(orgIds);
        PageInfo<TransportTobaccoVo> pageInfo = ydTransportService.getTobaccoList(transportTobaccoDto);
        return HttpResult.ok(pageInfo);
    }

    /**
     * 运单详情-烟叶列表汇总信息
     *
     * @param transportTobaccoDto
     * @return
     */
    @PostMapping("/sumTransportTobacco")
    public Object sumTransportTobacco(@RequestBody TransportTobaccoDto transportTobaccoDto) {
        List<Long> orgIds = dataAuthBizService.getAuthData(transportTobaccoDto.getUserId());
        transportTobaccoDto.setOrgIds(orgIds);
        JSONObject rtnJson = ydTransportService.sumTransportTobacco(transportTobaccoDto);
        return HttpResult.ok(rtnJson);
    }


    /**
     * 运单日志 App 无需分页
     *
     * @param transportTobaccoDto 传运单id
     * @return
     */
    @PostMapping("/getTransportLog")
    public Object getTransportLog(@RequestBody TransportTobaccoDto transportTobaccoDto) {
        List<YdTransportLog> list = ydTransportLogService.queryTransportLog(transportTobaccoDto.getId());
        return HttpResult.ok(list);
    }


    /**
     * 先写死 二期开发
     *
     * @return
     */
    @PostMapping("/getDeviceList")
    public Object getDeviceList() {
        YdDevice ydDevice = new YdDevice();
        ydDevice.setId(1);
        ydDevice.setName("洪福一号");
        YdDevice ydDevice1 = new YdDevice();
        ydDevice1.setId(2);
        ydDevice1.setName("洪福二号");
        List<YdDevice> list = new ArrayList<>();
        list.add(ydDevice);
        list.add(ydDevice1);
        return HttpResult.ok(list);
    }

    /**
     * 开始装车
     *
     * @return
     */
    @PostMapping("/startLoading")
    public Object startLoading(@RequestBody TransportTobaccoDto transportTobaccoDto) {
        boolean flag = ydTransportService.startLoading(transportTobaccoDto);
        if (!flag) {
            return HttpResult.error("调用Iot异常");
        }
        return HttpResult.ok("开始装车");
    }


    /**
     * 结束装车
     *
     * @return
     */
    @PostMapping("/stopLoading")
    public Object stopLoading(@RequestBody TransportTobaccoDto transportTobaccoDto) {
        Pair<Boolean, JSONObject> pair = ydTransportService.stopLoading(transportTobaccoDto);
        if (!pair.getKey()) {
            return HttpResult.error("调用Iot异常");
        }
        return HttpResult.ok(pair.getValue());
    }
}

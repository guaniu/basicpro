package com.yiding.saas.ydsaas.web.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.yiding.saas.ydsaas.common.enums.CommonEnum;
import com.yiding.saas.ydsaas.model.YdTransport;
import com.yiding.saas.ydsaas.model.YdWarehouseDO;
import com.yiding.saas.ydsaas.model.YdWarehousePageDO;
import com.yiding.saas.ydsaas.model.YdWarn;
import com.yiding.saas.ydsaas.service.YdTransportService;
import com.yiding.saas.ydsaas.service.YdWarehouseService;
import com.yiding.saas.ydsaas.service.YdWarnService;
import com.yiding.saas.ydsaas.vo.WarnVo;
import com.yiding.saas.ydsaas.web.biz.DataAuthBizService;
import com.yiding.saas.ydsaas.web.config.WebSocketServer;
import com.yiding.saas.ydsaas.web.core.HttpResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * WebSocketController
 * @author fzw
 */
@RestController
@RequestMapping("/web/screen")
public class WebScreenController {
	
	@Autowired
    private YdTransportService ydTransportService;
	
	@Autowired
    private DataAuthBizService dataAuthBizService;
	
	@Autowired
    private YdWarnService ydWarnService;
	
	@Autowired
    private YdWarehouseService ydWarehouseService;

    @GetMapping("index")
    public ResponseEntity<String> index(){
        return ResponseEntity.ok("请求成功");
    }

    @GetMapping("page")
    public ModelAndView page(){
        return new ModelAndView("websocket");
    }

    @RequestMapping("/push/{toUserId}")
    public ResponseEntity<String> pushToWeb(String message, @PathVariable String toUserId) throws IOException {
        //处理数据
    	WebSocketServer.sendInfo(message,toUserId);
        return ResponseEntity.ok("MSG SEND SUCCESS");
    }
    
    /**
     * 左侧大屏数据
     */
    @PostMapping(value = "/leftScreen")
    public HttpResult leftScreen(@RequestParam Long userId) throws IOException {
        //处理数据
    	JSONObject json = new JSONObject();
		YdTransport ydTransport = new YdTransport();
    	//通过用户id获取数据权限
    	List<Long> orgIds = dataAuthBizService.getAuthData(userId);
    	ydTransport.setOrgIds(orgIds);
    	//状态20在途运单
    	ydTransport.setTransportState(20);
    	ydTransport.setCreateTimeNow(1);
        //获取用户所有能查看的烟站在途运单数据
        ydTransportService.selectAllList(ydTransport,json);
    	return HttpResult.ok(json,"左侧大屏数据获取成功");
    }
    
    /**
     * 左侧大屏数据详情
     */
    @PostMapping(value = "/leftScreenDetails")
    public HttpResult leftScreenDetails(@RequestParam Integer id) throws IOException {
        //处理数据
    	JSONObject json = new JSONObject();
    	if(id!=null && id>0){
    		ydTransportService.selectLeftScreenDetails(id,json);
    	}
    	return HttpResult.ok(json,"左侧大屏详情数据获取成功");
    }
    
    /**
     * 右侧大屏数据(仓库数据)
     * @param orgId 仓库组织id
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/rightScreen")
    public HttpResult rightScreen(@RequestParam Integer orgId) throws IOException {
        //处理数据
    	JSONObject json = new JSONObject();
    	YdWarehousePageDO ydWarehousePageDO = new YdWarehousePageDO();
    	ydWarehousePageDO.setRepertoryId(orgId);
    	ydWarehouseService.findWarehouseScreenData(ydWarehousePageDO,json);
    	return HttpResult.ok(json,"右侧大屏数据获取成功");
    }
    
    /**
     * 当日车辆管理大屏数据
     * @param orgId 复烤厂/中心库组织机构id
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/carScreen")
    public HttpResult carScreen(@RequestParam Integer orgId) throws IOException {
        //处理数据
    	JSONObject json = new JSONObject();
    	YdTransport ydTransport = new YdTransport();
    	if(orgId!=null && orgId>0){//收货方(复烤厂/中心库)组织机构id
    		ydTransport.setReceiveId(orgId);
    	}
    	ydTransport.setCreateTimeNow(1);
        //获取用户所有能查看的烟站在途运单数据
        ydTransportService.selectTodayCarList(ydTransport,json);
    	return HttpResult.ok(json,"预约车辆大屏数据获取成功");
    }
}

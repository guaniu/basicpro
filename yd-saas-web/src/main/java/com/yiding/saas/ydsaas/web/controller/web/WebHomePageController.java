package com.yiding.saas.ydsaas.web.controller.web;


import com.alibaba.fastjson.JSONObject;
import com.yiding.saas.ydsaas.dto.TransportTobaccoDto;
import com.yiding.saas.ydsaas.service.YdTransportService;
import com.yiding.saas.ydsaas.web.biz.DataAuthBizService;
import com.yiding.saas.ydsaas.web.core.HttpResult;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 首页
 *
 * @author fzw
 * @date six 3, 2020
 */
@RestController
@RequestMapping("/web/home")
public class WebHomePageController {

	@Autowired
    private YdTransportService ydTransportService;
	@Autowired
    private DataAuthBizService dataAuthBizService;

    

    /**
     * 初始化pc首页数据
     */
    @PostMapping(value = "/index")
    public HttpResult index(@RequestParam Long userId) {
    	JSONObject json = new JSONObject();
    	//通过用户id获取数据权限
    	List<Long> orgIds = dataAuthBizService.getAuthData(userId);
        //获取用户所有能查看的烟站运单数据
    	if(orgIds!=null && orgIds.size()>0){
    		json = ydTransportService.selectStatByOrgIds(orgIds);
    	}
        return HttpResult.ok(json,"首页初始化数据获取成功");
    }

}

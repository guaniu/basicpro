package com.yiding.saas.ydsaas.web.controller.api;

import com.yiding.saas.ydsaas.common.push.JiguangPush;
import com.yiding.saas.ydsaas.dto.TransportTobaccoDto;
import com.yiding.saas.ydsaas.model.YdInformation;
import com.yiding.saas.ydsaas.model.YdTransport;
import com.yiding.saas.ydsaas.model.YdWarn;
import com.yiding.saas.ydsaas.service.YdInformationService;
import com.yiding.saas.ydsaas.service.YdTransportService;
import com.yiding.saas.ydsaas.service.YdUserService;
import com.yiding.saas.ydsaas.vo.UserVo;
import com.yiding.saas.ydsaas.web.biz.WarnBizService;
import com.yiding.saas.ydsaas.web.core.HttpResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 不需要登录接口
 */
@RestController
@RequestMapping("/api/open")
public class ApiOpenController {
    @Autowired
    private YdTransportService ydTransportService;

    @Autowired
    private WarnBizService warnBizService;
    @Autowired
    private YdUserService ydUserService;
    @Autowired
    private YdInformationService ydInformationService;
    @Value("${apnsProduction}")
    private boolean apnsProduction;


    /**
     * IOT通知后台修改运单状态为待收货状态
     *
     * @param transportTobaccoDto 运单Id
     */
    @PostMapping("/pendingReceipt")
    public Object pendingReceipt(@RequestBody TransportTobaccoDto transportTobaccoDto) {
        boolean result = ydTransportService.pendingReceipt(transportTobaccoDto.getId());
        if (result) {
    	     YdTransport obj = ydTransportService.selectByPrimaryKey(transportTobaccoDto.getId());
           	 List<UserVo> list = new ArrayList<>();
           	 List<UserVo> userList1 = ydUserService.selectRoleUserList(6, obj.getReceiveId());//6.收货员角色
           	 List<UserVo> userList2 = ydUserService.selectRoleUserList(3, obj.getSendId());//3.仓库主管角色
           	 list.addAll(userList1);
           	 list.addAll(userList2);
    	        if(list!=null && list.size()>0) {
    	        	String content = "来自车牌("+obj.getCarNo()+")";
    	        	for (UserVo ydUser : list) {
    	        		JiguangPush.push(ydUser.getId().toString(), content, apnsProduction);
    	        		YdInformation info = new YdInformation();
    	            	info.setTransportId(transportTobaccoDto.getId());
    	            	info.setUserId(ydUser.getId());
    	            	info.setType(6);
    	            	info.setCreateTime(new Date());
    	            	info.setContent(content);
    	            	ydInformationService.insertSelective(info);
    				}
    	        }
            return HttpResult.ok("ok");
        }
        return HttpResult.error("更新失败");
    }


    /**
     * iot 写入告警信息
     *
     * @param ydWarn
     * @return
     */
    @PostMapping("/saveWarn")
    public Object saveWarn(@RequestBody YdWarn ydWarn) {
        boolean flag = warnBizService.save(ydWarn);
        if (flag) {
        	 YdTransport obj = ydTransportService.selectByPrimaryKey(ydWarn.getTransportId());
        	 List<UserVo> list = new ArrayList<>();
        	 List<UserVo> userList1 = ydUserService.selectRoleUserList(6, obj.getReceiveId());//6.收货员角色
        	 List<UserVo> userList2 = ydUserService.selectRoleUserList(3, obj.getSendId());//3.仓库主管角色
        	 list.addAll(userList1);
        	 list.addAll(userList2);
        	 if(list!=null && list.size()>0) {
        		 String content = "来自车牌("+obj.getCarNo()+")";
        		 for (UserVo userVo : list) {
        		   JiguangPush.push(userVo.getId().toString(), content, apnsProduction);
 	        		YdInformation info = new YdInformation();
 	            	info.setTransportId(ydWarn.getTransportId());
 	            	info.setUserId(userVo.getId());
 	            	info.setType(Integer.parseInt(ydWarn.getWarnType()));
 	            	info.setCreateTime(new Date());
 	            	info.setContent(content);
 	            	ydInformationService.insertSelective(info);
				}
        	 }
            return HttpResult.ok("写入成功");
        }
        return HttpResult.ok("写入异常");
    }

}

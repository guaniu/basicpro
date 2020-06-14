package com.yiding.saas.ydsaas.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.yiding.saas.ydsaas.common.utils.PasswordUtils;
import com.yiding.saas.ydsaas.model.YdInformation;
import com.yiding.saas.ydsaas.service.YdInformationService;
import com.yiding.saas.ydsaas.web.core.HttpResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "消息")
@RestController
@RequestMapping("/api/info")
public class ApiInformationController {
	
	@Autowired
	private YdInformationService ydInformationService;
	
	@ApiOperation(value="列表")
	@PostMapping(value="/list")
	public HttpResult list(@RequestBody YdInformation record) {
		PageInfo<YdInformation> pageInfo = ydInformationService.selectAll(record);
		return HttpResult.ok(pageInfo);
	}
	
	@ApiOperation(value="已读")
	@PostMapping(value="/read")
	public HttpResult read(@RequestBody YdInformation record) {
		int ret = ydInformationService.updateByPrimaryKeySelective(record);
		return HttpResult.ok(ret);
	}

	public static void main(String[] args) {
		String pwd= PasswordUtils.encode("111111","");
		System.out.println(pwd);
	}
}

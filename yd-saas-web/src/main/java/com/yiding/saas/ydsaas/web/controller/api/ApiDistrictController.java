package com.yiding.saas.ydsaas.web.controller.api;


import com.alibaba.fastjson.JSONObject;
import com.yiding.saas.ydsaas.dto.YdDistrictDto;
import com.yiding.saas.ydsaas.model.YdDistrict;
import com.yiding.saas.ydsaas.vo.DistrictTree;
import com.yiding.saas.ydsaas.web.biz.DistrictBizService;
import com.yiding.saas.ydsaas.web.core.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/district")
public class ApiDistrictController {
    @Autowired
    private DistrictBizService districtBizService;


    /**
     * 行政区树
     *
     * @param ydDistrictDto
     * @return
     */
    @PostMapping("/tree")
    public Object getDistrictTree(@RequestBody YdDistrictDto ydDistrictDto) {
        DistrictTree tree = districtBizService.getDistrictTree(ydDistrictDto);
        JSONObject rtnJson = new JSONObject();
        rtnJson.put("districtTree", tree);
        return HttpResult.ok(rtnJson);
    }


    /**
     * 根据id districtLevel parentId查询
     *
     * @param ydDistrictDto
     * @return
     */
    @PostMapping("/list")
    public Object getDistrictList(@RequestBody YdDistrictDto ydDistrictDto) {
        List<YdDistrict> list = districtBizService.list(ydDistrictDto);
        return HttpResult.ok(list);
    }
}

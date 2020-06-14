package com.yiding.saas.ydsaas.web.controller.api;

import com.yiding.saas.ydsaas.model.SysDict;
import com.yiding.saas.ydsaas.service.SysDictService;
import com.yiding.saas.ydsaas.web.biz.TobaccoBizService;
import com.yiding.saas.ydsaas.web.core.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 烟
 */
@RestController
@RequestMapping("/api/tobacco")
public class ApiYdTobaccoController {
    @Autowired
    private SysDictService sysDictService;

    @Autowired
    private TobaccoBizService tobaccoBizService;


    /**
     * 品种列表
     *
     * @return
     */
    @PostMapping("/listtype")
    public Object listtype() {
        return HttpResult.ok(sysDictService.findByType("tobacco_type"));
    }

    /**
     * 等级列表
     *
     * @return
     */
    @PostMapping("/listlevel")
    public Object listlevel() {
        return HttpResult.ok(tobaccoBizService.list());
    }

}

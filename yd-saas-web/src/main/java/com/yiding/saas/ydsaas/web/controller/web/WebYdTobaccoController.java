package com.yiding.saas.ydsaas.web.controller.web;

import com.yiding.saas.ydsaas.model.YdTobacco;
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
@RequestMapping("/web/tobacco")
public class WebYdTobaccoController {
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

    /**
     * 保存
     *
     * @param ydTobacco
     * @return
     */
    @PostMapping("/save")
    public Object save(@RequestBody YdTobacco ydTobacco) {
        tobaccoBizService.save(ydTobacco);
        return HttpResult.ok("ok");
    }


    /**
     * 删除
     *
     * @param ydTobacco
     * @return
     */
    @PostMapping("/delete")
    public Object delete(@RequestBody YdTobacco ydTobacco) {
        tobaccoBizService.deleteById(ydTobacco.getId());
        return HttpResult.ok("ok");
    }


    /**
     * 更新
     *
     * @param ydTobacco
     * @return
     */
    @PostMapping("/update")
    public Object update(@RequestBody YdTobacco ydTobacco) {
        tobaccoBizService.update(ydTobacco);
        return HttpResult.ok("ok");
    }


    /**
     * 编辑回显
     *
     * @param ydTobacco
     * @return
     */
    @PostMapping("/get")
    public Object get(@RequestBody YdTobacco ydTobacco) {
        return HttpResult.ok(tobaccoBizService.get(ydTobacco.getId()));
    }

}

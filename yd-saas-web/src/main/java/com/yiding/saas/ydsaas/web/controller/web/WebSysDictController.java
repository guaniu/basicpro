package com.yiding.saas.ydsaas.web.controller.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yiding.saas.ydsaas.dto.SysDictDto;
import com.yiding.saas.ydsaas.model.SysDict;
import com.yiding.saas.ydsaas.service.SysDictService;
import com.yiding.saas.ydsaas.web.core.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 字典控制器
 *
 * @author Louis
 * @date Oct 29, 2018
 */
@RestController
@RequestMapping("/web/dict")
public class WebSysDictController {

    @Autowired
    private SysDictService sysDictService;

    @PostMapping(value = "/save")
    public HttpResult save(@RequestBody SysDict record) {
        return HttpResult.ok(sysDictService.save(record));
    }


    @PostMapping(value = "/delete")
    public HttpResult delete(@RequestBody List<SysDict> records) {
        return HttpResult.ok(sysDictService.delete(records));
    }

    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody SysDictDto sysDictDto) {
        PageHelper.startPage(sysDictDto.getPageNum(), sysDictDto.getPageSize());
        List<SysDict> list = sysDictService.findByPage(sysDictDto);
        PageInfo<SysDict> pageInfo = new PageInfo<>(list);
        return HttpResult.ok(pageInfo);
    }

    @GetMapping(value = "/findByLable")
    public HttpResult findByLable(@RequestParam String lable) {
        return HttpResult.ok(sysDictService.findByLable(lable));
    }

    @PostMapping(value = "/findByType")
    public HttpResult findByType(@RequestBody SysDictDto sysDictDto) {
        return HttpResult.ok(sysDictService.findByType(sysDictDto.getType()), "查询成功");
    }

    @GetMapping(value = "/findList")
    public HttpResult findList() {
        return HttpResult.ok(sysDictService.findList());
    }
}

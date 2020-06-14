package com.yiding.saas.ydsaas.web.controller.web;

import com.yiding.saas.ydsaas.model.YdBasicsLogistics;
import com.yiding.saas.ydsaas.service.YdLogisticsService;
import com.yiding.saas.ydsaas.web.core.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author BruceLee
 * @Date 2020/6/10
 */
@RestController
@RequestMapping(value = "logistics")
public class TbLogisticsController {
    @Autowired
    private YdLogisticsService ydLogisticsService;

    @PostMapping(value = "save")
    public HttpResult save(@RequestBody YdBasicsLogistics ydBasicsLogistics){
        ydLogisticsService.save(ydBasicsLogistics);
        return HttpResult.ok(1,"保存成功");
    }
    @GetMapping(value = "delete")
    public HttpResult delete(int id){
        ydLogisticsService.delete(id);
        return HttpResult.ok(1,"删除成功");
    }
    @PostMapping(value = "update")
    public HttpResult update(@RequestBody YdBasicsLogistics ydBasicsLogistics){
        ydLogisticsService.update(ydBasicsLogistics);
        return HttpResult.ok(1,"更新成功");
    }
    @PostMapping(value = "select")
    public HttpResult select(int id){
      YdBasicsLogistics list= ydLogisticsService.select(id);
        return HttpResult.ok(list,"查询成功");
    }
}

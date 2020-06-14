package com.yiding.saas.ydsaas.web.controller.api;


import com.github.pagehelper.PageInfo;
import com.yiding.saas.ydsaas.dto.YdAcquisitionPlanDto;
import com.yiding.saas.ydsaas.model.YdAcquisitionPlan;
import com.yiding.saas.ydsaas.web.anno.ReqRespLog;
import com.yiding.saas.ydsaas.web.biz.AcquisitionPlanBizService;
import com.yiding.saas.ydsaas.web.core.HttpResult;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 收购计划
 */
@RestController
@RequestMapping("/api/acquisitionPlan")
public class ApiAcquisitionPlanController {


    /**
     * 日志
     */
    Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private AcquisitionPlanBizService acquisitionPlanBizService;


    /**
     * 新增收购计划
     *
     * @param ydAcquisitionPlanDto
     * @return
     */
    @PostMapping("/save")
    public Object save(@RequestBody YdAcquisitionPlanDto ydAcquisitionPlanDto) {
        Pair<Boolean, String> pair = acquisitionPlanBizService.save(ydAcquisitionPlanDto);
        if (!pair.getKey()) {
            return HttpResult.error(pair.getValue());
        }
        return HttpResult.ok("保存成功");
    }

    /**
     * 删除
     *
     * @param ydAcquisitionPlan
     * @return
     */
    @PostMapping("/delete")
    public Object delete(@RequestBody YdAcquisitionPlan ydAcquisitionPlan) {
        if (!acquisitionPlanBizService.delete(ydAcquisitionPlan.getId())) {
            return HttpResult.error("删除失败");
        }
        return HttpResult.ok("删除成功");
    }


    /**
     * 编辑回显
     *
     * @param ydAcquisitionPlan
     * @return
     */
    @PostMapping("/get")
    public Object get(@RequestBody YdAcquisitionPlan ydAcquisitionPlan) {
        return HttpResult.ok(acquisitionPlanBizService.get(ydAcquisitionPlan.getId()));
    }

    /**
     * 修改收购计划
     *
     * @param ydAcquisitionPlanDto
     * @return
     */
    @PostMapping("/update")
    public Object update(@RequestBody YdAcquisitionPlanDto ydAcquisitionPlanDto) {
        if (!acquisitionPlanBizService.update(ydAcquisitionPlanDto)) {
            return HttpResult.error("修改失败");
        }
        return HttpResult.ok("修改成功");
    }

    /**
     * 查看今年收购计划
     *
     * @return
     */
    @PostMapping("/list")
    @ReqRespLog
    public Object list(@RequestBody YdAcquisitionPlanDto ydAcquisitionPlanDto) {
        PageInfo<YdAcquisitionPlan> page = acquisitionPlanBizService.list(ydAcquisitionPlanDto);
        return HttpResult.ok(page);
    }


}

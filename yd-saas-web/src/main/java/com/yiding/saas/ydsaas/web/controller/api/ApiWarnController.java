package com.yiding.saas.ydsaas.web.controller.api;

import com.github.pagehelper.PageInfo;
import com.yiding.saas.ydsaas.dto.WarnDto;
import com.yiding.saas.ydsaas.model.YdWarn;
import com.yiding.saas.ydsaas.vo.WarnVo;
import com.yiding.saas.ydsaas.web.biz.WarnBizService;
import com.yiding.saas.ydsaas.web.core.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 告警
 */
@RestController
@RequestMapping("/api/warn")
public class ApiWarnController {
    /**
     * 日志
     */
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WarnBizService warnBizService;


    /**
     * 根据条件查询
     *
     * @param warnDto
     * @return
     */
    @PostMapping("/list")
    public Object list(@RequestBody WarnDto warnDto) {
        PageInfo<WarnVo> pageInfo = warnBizService.list(warnDto);
        return HttpResult.ok(pageInfo);
    }


    /**
     * 根据id处理告警
     *
     * @param ydWarn
     * @return
     */
    @PostMapping("/update")
    public Object update(@RequestBody YdWarn ydWarn) {
        warnBizService.update(ydWarn);
        return HttpResult.ok("处理完成");
    }


    /**
     * 传运单Id
     * @param warnDto
     * transportId
     * @return
     */
    @PostMapping("/listGroup")
    public Object listGroup(@RequestBody WarnDto warnDto) {
        Map<String, Object> map = warnBizService.listGroup(warnDto);
        return HttpResult.ok(map);
    }


}

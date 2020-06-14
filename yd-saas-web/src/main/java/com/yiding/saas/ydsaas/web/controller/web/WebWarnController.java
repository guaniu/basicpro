package com.yiding.saas.ydsaas.web.controller.web;

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

/**
 * 告警
 */
@RestController
@RequestMapping("/web/warn")
public class WebWarnController {
    @Autowired
    private WarnBizService warnBizService;
    /**
     * 日志
     */
    Logger log = LoggerFactory.getLogger(this.getClass());

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
}

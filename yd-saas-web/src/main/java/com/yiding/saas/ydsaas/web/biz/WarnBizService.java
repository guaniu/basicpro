package com.yiding.saas.ydsaas.web.biz;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yiding.saas.ydsaas.common.enums.CommonEnum;
import com.yiding.saas.ydsaas.dto.WarnDto;
import com.yiding.saas.ydsaas.model.YdUser;
import com.yiding.saas.ydsaas.model.YdWarn;
import com.yiding.saas.ydsaas.service.YdTransportService;
import com.yiding.saas.ydsaas.service.YdWarnService;
import com.yiding.saas.ydsaas.vo.WarnVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 告警业务处理service
 */
@Service
public class WarnBizService {

    /**
     * 日志
     */
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private YdWarnService ydWarnService;

    @Autowired
    private YdTransportService ydTransportService;

    @Autowired
    private UserBizService userBizService;


    /**
     * 写入告警
     *
     * @param ydWarn
     * @return
     */
    public boolean save(YdWarn ydWarn) {
        try {
            ydWarn.setCreateTime(new Date());
            ydWarn.setProcessState(0);
            ydWarnService.insert(ydWarn);
            return true;
        } catch (Exception e) {
            log.error("", e);
            return false;
        }
    }


    /**
     * 更新告警记录
     *
     * @param ydWarn
     * @return
     */
    public boolean update(YdWarn ydWarn) {
        try {
            ydWarn.setUpdateTime(new Date());
            ydWarn.setProcessState(1);
            Long userId = ydWarn.getProcessUserId();
            YdUser user = userBizService.getYdUserById(userId);
            if (user != null) {
                ydWarn.setProcessUserName(user.getUserName());
            }
            ydWarnService.update(ydWarn);
            return true;
        } catch (Exception e) {
            log.error("", e);
            return false;
        }
    }


    /**
     * 告警列表
     *
     * @param warnDto
     * @return
     */
    public PageInfo<WarnVo> list(WarnDto warnDto) {
        PageHelper.startPage(warnDto.getPageNum(), warnDto.getPageSize());
        List<WarnVo> list = ydWarnService.getListByParms(warnDto);
        if (list != null && list.size() > 0) {
            for (WarnVo warnVo : list) {
                warnVo.setWarnTypeName(CommonEnum.getWarnType(warnVo.getWarnType()));
                warnVo.setProcessStateName(CommonEnum.getProcessState(warnVo.getProcessState() + ""));

            }
        }
        PageInfo<WarnVo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }


    /**
     * 告警分组
     *
     * @param warnDto
     * @return
     */
    public Map<String, Object> listGroup(WarnDto warnDto) {
        List<WarnVo> list = ydWarnService.getListByParms(warnDto);
        Map<String, Object> map = new HashMap<String, Object>();
        List<WarnVo> timeOutList = new ArrayList<>();
        List<WarnVo> lowPowerList = new ArrayList<>();
        List<WarnVo> lostList = new ArrayList<>();
        List<WarnVo> removalList = new ArrayList<>();
        for (WarnVo warnVo : list) {
            String type = warnVo.getWarnType();
            if (CommonEnum.stopTimeOut.getCode().equals(type)) {
                timeOutList.add(warnVo);
            } else if (CommonEnum.lowPower.getCode().equals(type)) {
                lowPowerList.add(warnVo);
            } else if (CommonEnum.lost.getCode().equals(type)) {
                lostList.add(warnVo);
            } else if (CommonEnum.removal.getCode().equals(type)) {
                removalList.add(warnVo);
            }
        }
        map.put("timeOutList", timeOutList);
        map.put("lostList", lostList);
        map.put("lowPowerList", lowPowerList);
        map.put("removalList", removalList);
        map.put("total",list.size());
        return map;
    }
}

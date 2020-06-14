package com.yiding.saas.ydsaas.web.biz;

import com.yiding.saas.ydsaas.dto.YdUserOrgRefDto;
import com.yiding.saas.ydsaas.model.YdUser;
import com.yiding.saas.ydsaas.model.YdUserOrgRef;
import com.yiding.saas.ydsaas.service.YdUserOrgRefService;
import com.yiding.saas.ydsaas.service.YdUserService;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 数据权限
 */
@Service
public class DataAuthBizService {

    /**
     * 日志
     */
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private YdUserOrgRefService ydUserOrgRefService;

    @Autowired
    private YdUserService ydUserService;

    private static final String type = "web";

    @Transactional
    public Pair<Boolean, String> save(YdUserOrgRefDto ydUserOrgRefDto) {
        Long userId = ydUserOrgRefDto.getUserId();
        List<Long> orgIds = ydUserOrgRefDto.getOrgIds();
        if (orgIds == null || orgIds.size() == 0) {
            return new Pair<>(false, "组织结构不能为空");
        }
        try {
            //删除已有关系
            ydUserOrgRefService.deleteByUserIdAndType(userId, type);
            //建立新的关系
            for (Long orgId : orgIds) {
                YdUserOrgRef ydRoleOrgRef = new YdUserOrgRef();
                ydRoleOrgRef.setCreateTime(new Date());
                ydRoleOrgRef.setUpdateTime(new Date());
                ydRoleOrgRef.setUserId(userId);
                ydRoleOrgRef.setOrgId(orgId);
                ydRoleOrgRef.setType(type);
                ydUserOrgRefService.insert(ydRoleOrgRef);
            }
            return new Pair<>(true, "保存成功");
        } catch (Exception e) {
            log.error("", e);
            return new Pair<>(false, "系统异常");
        }
    }


    /**
     * 根据用户id 权限类型(app web)查数据权限
     *
     * @param
     * @return
     */
    public List<Long> getAuthData(Long userId) {
        List<Long> list = ydUserOrgRefService.listOrgIdsByUserId(userId, type);
        if (list != null && list.size() > 0) {
            return list;
        }
        YdUser user = ydUserService.queryById(userId);
        list.add(Long.valueOf(user.getUserOrgId()));
        return list;
    }
}

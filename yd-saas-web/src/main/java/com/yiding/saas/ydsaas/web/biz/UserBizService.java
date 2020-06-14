package com.yiding.saas.ydsaas.web.biz;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yiding.saas.ydsaas.common.constants.SysConstants;
import com.yiding.saas.ydsaas.common.utils.PasswordUtils;
import com.yiding.saas.ydsaas.dto.YdUserDto;
import com.yiding.saas.ydsaas.model.SysMenu;
import com.yiding.saas.ydsaas.model.YdOrganization;
import com.yiding.saas.ydsaas.model.YdUser;
import com.yiding.saas.ydsaas.service.*;
import com.yiding.saas.ydsaas.vo.UserVo;
import javafx.util.Pair;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserBizService {
    /**
     * 日志
     */
    Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * 用户
     */
    @Autowired
    private YdUserService ydUserService;

    @Autowired
    private YdOrganizationService ydOrganizationService;

    @Autowired
    private SysMenuService sysMenuService;


    /**
     * 保存用户
     *
     * @param ydUser
     * @return
     */

    public Pair<Boolean, String> saveUser(YdUser ydUser) {
        try {
            boolean flag = checkLoginAccountRepeat(ydUser.getUserLoginAccount());
            if (!flag) {
                return new Pair<>(false, "登录账号重复");
            }
            ydUser.setCreateTime(new Date());
            ydUser.setUserPwd(PasswordUtils.encode("111111", ""));
            ydUser.setUserStatus(1);
            ydUser.setIsDel(0);
            YdOrganization org = ydOrganizationService.queryById(Long.valueOf(ydUser.getUserOrgId()));
            if (org != null) {
                ydUser.setUserOrgName(org.getOrgName());
            }
            ydUser = ydUserService.insert(ydUser);
            return new Pair<>(true, "保存成功");
        } catch (Exception e) {
            log.error("", e);
            return new Pair<>(false, "系统异常");
        }
    }

    /**
     * 逻辑删除
     *
     * @param id
     * @return
     */
    public Pair<Boolean, String> deleteUser(Long id) {
        try {
            YdUser user = getYdUserById(id);
            user.setIsDel(1);
            ydUserService.update(user);
            return new Pair<>(true, "删除成功");
        } catch (Exception e) {
            log.error("", e);
            return new Pair<>(false, "系统异常");
        }
    }

    /**
     * 更新用户信息
     *
     * @param ydUser
     * @return
     */
    public Pair<Boolean, String> updateUser(YdUser ydUser) {
        try {
            ydUser.setUpdateTime(new Date());
            Integer orgId = ydUser.getUserOrgId();
            if (orgId != null) {
                //保证冗余字段正确
                YdOrganization org = ydOrganizationService.queryById(Long.valueOf(orgId));
                if (org != null) {
                    ydUser.setUserOrgName(org.getOrgName());
                }
            }
            //修改密码
            String pwd = ydUser.getUserPwd();
            if (StringUtils.isNotBlank(pwd)) {
                pwd = PasswordUtils.encode(pwd, "");
                ydUser.setUserPwd(pwd);
            }
            ydUserService.update(ydUser);
            return new Pair<>(true, "更新成功");
        } catch (Exception e) {
            log.error("", e);
            return new Pair<>(false, "系统异常");
        }
    }


    /**
     * 编辑 数据回显
     *
     * @param id
     * @return
     */
    public YdUser getYdUserById(Long id) {
        return ydUserService.queryById(id);
    }

    /**
     * 根据条件查询分页列表
     *
     * @param ydUserDto
     * @return
     */
    public PageInfo<UserVo> ydUserList(YdUserDto ydUserDto) {
        int pageNum = ydUserDto.getPageNum();
        int pageSize = ydUserDto.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        YdUser ydUser = new YdUser();
        ydUser.setUserOrgId(ydUserDto.getUserOrgId());
        ydUser.setUserMobile(ydUserDto.getUserMobile());
        ydUser.setUserName(ydUserDto.getUserName());
        ydUser.setUserStatus(ydUserDto.getUserStatus());
        ydUser.setUserLoginAccount(ydUserDto.getUserLoginAccount());
        List<UserVo> list = ydUserService.getUserList(ydUser);
        PageInfo<UserVo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }


    /**
     * 根据用户id判断用户是否为管理员
     *
     * @param userId
     * @return
     */
    public boolean isAdmin(Long userId) {
        String name = ydUserService.findRoleNameByUserId(userId);
        if (SysConstants.ADMIN.equals(name)) {
            return true;
        }
        return false;
    }


    /**
     * 根据登录名查询按钮权限
     *
     * @param loginAccount
     * @return
     */
    public Set<String> getBtnPermsByLoginAccount(String loginAccount) {
        List<SysMenu> list = sysMenuService.findByUser(loginAccount);
        Set<String> sets = new HashSet<>();
        for (SysMenu sysMenu : list) {
            String perms = sysMenu.getPerms();
            if (StringUtils.isNotBlank(perms)) {
                sets.add(perms);
            }
        }
        return sets;
    }


    /**
     * 校验登录账号是否重复
     *
     * @param loginAccount
     * @return
     */
    private boolean checkLoginAccountRepeat(String loginAccount) {
        YdUser ydUser = new YdUser();
        ydUser.setIsDel(0);
        ydUser.setUserStatus(1);
        ydUser.setUserLoginAccount(loginAccount);
        List<YdUser> list = ydUserService.list(ydUser);
        if (list != null && list.size() > 0) {
            return false;
        }
        return true;
    }

}

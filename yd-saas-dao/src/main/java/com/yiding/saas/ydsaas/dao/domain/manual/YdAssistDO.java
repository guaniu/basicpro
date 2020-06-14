package com.yiding.saas.ydsaas.dao.domain.manual;

import java.util.List;

/**
 * Created by 94307 on 2019/4/1.
 */
public class YdAssistDO {
    private String rolename;
    private int roleId;
    private int enterpriseId;
    private List scopeList;

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(int enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public List getScopeList() {
        return scopeList;
    }

    public void setScopeList(List scopeList) {
        this.scopeList = scopeList;
    }
}

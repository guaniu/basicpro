package com.yiding.saas.ydsaas.vo;

import com.yiding.saas.ydsaas.model.YdOrganization;

import java.io.Serializable;
import java.util.List;

/**
 * 组织结构树
 */
public class OrgTree implements Serializable {
    private static final long serialVersionUID = -7061043104745535378L;
    //id
    private Long id;
    //上级组织id
    private Long pid;
    //组织编码
    private String orgCode;
    //组织名称
    private String orgName;
    //子树
    private List<OrgTree> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public List<OrgTree> getChildren() {
        return children;
    }

    public void setChildren(List<OrgTree> children) {
        this.children = children;
    }


    /**
     * 组织模型转化为组织树模型
     *
     * @param ydOrganization
     * @return
     */
    public static OrgTree convertOrgTree(YdOrganization ydOrganization) {
        OrgTree orgTree = new OrgTree();
        orgTree.setOrgName(ydOrganization.getOrgName());
        orgTree.setId(ydOrganization.getId());
        orgTree.setPid(ydOrganization.getParentId());
        orgTree.setOrgCode(ydOrganization.getOrgCode());
        return orgTree;
    }
}

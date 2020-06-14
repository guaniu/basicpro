package com.yiding.saas.ydsaas.model;

import java.io.Serializable;

/**
 * 设备
 */
public class YdDevice implements Serializable {
    private static final long serialVersionUID = -7039215957137432363L;
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

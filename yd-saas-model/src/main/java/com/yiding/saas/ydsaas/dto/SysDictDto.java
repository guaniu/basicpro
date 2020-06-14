package com.yiding.saas.ydsaas.dto;

import java.io.Serializable;

/**
 * 字典传输层
 */
public class SysDictDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = -2188258014917408932L;
    /**
     * 标签名
     */
    private String label;

    /**
     * 类型
     */
    private String type;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

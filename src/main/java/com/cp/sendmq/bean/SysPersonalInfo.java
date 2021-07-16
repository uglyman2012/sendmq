package com.cp.sendmq.bean;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class SysPersonalInfo implements Serializable {

    private static final long serialVersionUID = -2050961555118270287L;
    /**
     * 客户名称
     */
    @ApiModelProperty(value = "客户名称", required = true)
    @NotBlank
    private String id;
    /**
     * 客户名称
     */
    @NotNull
    private String personalName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPersonalName() {
        return personalName;
    }

    public void setPersonalName(String personalName) {
        this.personalName = personalName;
    }
}

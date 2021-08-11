package com.cp.sendmq.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

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

    @ApiModelProperty(value = "过期时间", required = true)
    private LocalDateTime expireAt;

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

    public LocalDateTime getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(LocalDateTime expireAt) {
        this.expireAt = expireAt;
    }
}

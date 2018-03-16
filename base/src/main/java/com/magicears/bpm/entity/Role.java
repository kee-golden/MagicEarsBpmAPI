package com.magicears.bpm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Role {

    private Long id;

    private Date createAt;

    private Date updateAt;
    /*角色编码*/
    private String code;
    /*角色名称*/
    private String name;
    /*角色描述*/
    private String description;

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm", timezone = "GMT+8:00")
    public Date getCreateAt() {
        return createAt;
    }


    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm", timezone = "GMT+8:00")
    public Date getUpdateAt() {
        return updateAt;
    }


    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
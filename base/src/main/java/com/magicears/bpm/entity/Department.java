package com.magicears.bpm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.magicears.bpm.dingtalk.entity.ErroMessage;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by admin on 2018/3/9.
 */
public class Department{

    private Long id;

    private String name;

    private String description;

    private String code;

    private Date createAt;

    private Date updateAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @DateTimeFormat(pattern = "YYYY-MM-dd HH:mm")
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm", timezone = "GMT+8:00")
    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @DateTimeFormat(pattern = "YYYY-MM-dd HH:mm")
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
}

package com.magicears.bpm.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.magicears.bpm.entity.Role;
import com.magicears.bpm.entity.User;

import java.util.Date;
import java.util.List;

/**
 * Created by feng- on 2017/5/29.
 */
public class UserDto {

    private Long id;

    private Long departmentId;

    private String email;

    private String loginName;

    private Date loginAt;

    private String phone;

    private String realName;

    private Integer sex;

    private String roleName;

    private List<Role> roleList;

    private Date createAt;

    public UserDto() {
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.loginName = user.getLoginName();
        this.loginAt = user.getLoginAt();
        this.phone = user.getPhone();
        this.realName = user.getRealName();
        this.createAt = user.getCreateAt();
        this.sex = user.getSex();
        this.departmentId = user.getDepartmentId();
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Date getLoginAt() {
        return loginAt;
    }

    public void setLoginAt(Date loginAt) {
        this.loginAt = loginAt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss", timezone = "GMT+8:00")
    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", loginName='" + loginName + '\'' +
                ", loginAt=" + loginAt +
                ", phone='" + phone + '\'' +
                ", realName='" + realName + '\'' +
                ", sex=" + sex +
                ", departmentId=" + departmentId +
                ", roleName='" + roleName + '\'' +
                ", roleList=" + roleList +
                '}';
    }
}

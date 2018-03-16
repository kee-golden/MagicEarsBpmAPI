package com.magicears.bpm.auth.entity;



import java.util.List;

/**
 * Created by feng- on 2017/6/6.
 */
public class UserAuthInfo {

    private Long id;

    private List<PrivilegeAuthInfo> privilegeList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PrivilegeAuthInfo> getPrivilegeList() {
        return privilegeList;
    }

    public void setPrivilegeList(List<PrivilegeAuthInfo> privilegeList) {
        this.privilegeList = privilegeList;
    }

    @Override
    public String toString() {
        return "UserAuthInfo{" +
                "id=" + id +
                '}';
    }
}

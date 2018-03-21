package com.magicears.bpm.dingtalk.entity;

import java.util.List;

public class Organization extends ErroMessage{

    private List<Department> department;

    public List<Department> getDepartment() {
        return department;
    }

    public void setDepartment(List<Department> department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "department=" + department +
                '}';
    }
}

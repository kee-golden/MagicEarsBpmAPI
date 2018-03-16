package com.magicears.bpm.entity;

import java.util.Date;

public class RoleMenu {

    private Long id;

    private Date createAt;

    private Date updateAt;

    private Role role;

    private Menu privilege;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Menu getMenu() {
        return privilege;
    }

    public void setMenu(Menu privilege) {
        this.privilege = privilege;
    }


    public RoleMenu() {
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleMenu roleMenu = (RoleMenu) o;

        if (id != null ? !id.equals(roleMenu.id) : roleMenu.id != null) return false;
        if (createAt != null ? !createAt.equals(roleMenu.createAt) : roleMenu.createAt != null) return false;
        if (updateAt != null ? !updateAt.equals(roleMenu.updateAt) : roleMenu.updateAt != null) return false;
        if (role != null ? !role.equals(roleMenu.role) : roleMenu.role != null) return false;
        return privilege != null ? privilege.equals(roleMenu.privilege) : roleMenu.privilege == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (privilege != null ? privilege.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "RoleMenu{" +
                "id=" + id +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", role=" + role +
                ", privilege=" + privilege +
                '}';
    }
}
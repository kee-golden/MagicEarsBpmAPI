package com.magicears.bpm.entity;

import java.util.Date;

public class UserRole {

    private Long id;

    private Date createAt;

    private Date updateAt;

    private User User;

    private Role role;


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

    public User getUser() {
        return User;
    }

    public void setUser(User user) {
        User = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole userRole = (UserRole) o;

        if (id != null ? !id.equals(userRole.id) : userRole.id != null) return false;
        if (createAt != null ? !createAt.equals(userRole.createAt) : userRole.createAt != null) return false;
        if (updateAt != null ? !updateAt.equals(userRole.updateAt) : userRole.updateAt != null) return false;
        if (User != null ? !User.equals(userRole.User) : userRole.User != null) return false;
        return role != null ? role.equals(userRole.role) : userRole.role == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        result = 31 * result + (User != null ? User.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", User=" + User +
                ", role=" + role +
                '}';
    }
}
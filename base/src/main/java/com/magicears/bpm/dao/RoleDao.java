package com.magicears.bpm.dao;


import com.magicears.bpm.entity.Role;
import com.magicears.bpm.entity.User;
import com.magicears.bpm.entity.UserRole;
import com.magicears.bpm.helper.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface RoleDao extends BaseMapper<Role> {

    Integer deleteUserRolesByRoleId(@Param("roleId") Long roleId);

}

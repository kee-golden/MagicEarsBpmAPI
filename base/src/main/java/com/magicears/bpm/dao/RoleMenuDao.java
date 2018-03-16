package com.magicears.bpm.dao;

import com.magicears.bpm.entity.RoleMenu;
import com.magicears.bpm.helper.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * Created by admin on 2018/3/15.
 */
@Component
@Mapper
public interface RoleMenuDao extends BaseMapper<RoleMenu>{

    int deleteByRoleId(@Param("roleId") Long roleId);

    int deleteByMenuId(@Param("menuId") Long menuId);
}

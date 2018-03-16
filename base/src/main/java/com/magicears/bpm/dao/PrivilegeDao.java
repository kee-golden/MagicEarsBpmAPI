package com.magicears.bpm.dao;

import com.magicears.bpm.entity.Privilege;
import com.magicears.bpm.helper.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by admin on 2018/3/15.
 */
@Component
@Mapper
public interface PrivilegeDao extends BaseMapper<Privilege> {

    List<Privilege> findParents();

    List<Privilege> findByParentId(@Param("parentId") Long parentId);

    List<Privilege> findParentsByRoleId(@Param("roleId") Long roleId);

    List<Privilege> findByParentIdAndRoleId(@Param("parentId") Long parentId, @Param("roleId") Long roleId);

    Integer deleteByParentId(@Param("parentId") Long parentId);
}

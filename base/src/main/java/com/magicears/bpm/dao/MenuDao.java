package com.magicears.bpm.dao;


import com.magicears.bpm.entity.Menu;
import com.magicears.bpm.helper.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@Mapper
public interface MenuDao extends BaseMapper<Menu> {

    List<Menu> findAllByUserId(Long id);

    List<Menu> findParents();

    List<Menu> findByParentId(@Param("parentId") Long parentId);

    List<Menu> findParentsByRoleId(@Param("roleId") Long roleId);

    List<Menu> findByParentIdAndRoleId(@Param("parentId") Long parentId, @Param("roleId") Long roleId);

    Integer deleteByParentId(@Param("parentId") Long parentId);
}

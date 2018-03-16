package com.magicears.bpm.dao;

import com.magicears.bpm.entity.Department;
import com.magicears.bpm.helper.mybatis.BaseMapper;
import com.magicears.bpm.helper.mybatis.PageHelper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by admin on 2018/3/15.
 */
@Component
@Mapper
public interface DepartmentDao extends BaseMapper<Department>{

    List<Department> findByPage(@Param("pageHelper") PageHelper pageHelper, @Param("department") Department department);

    Integer findTotalAmount(@Param("department") Department department);
}

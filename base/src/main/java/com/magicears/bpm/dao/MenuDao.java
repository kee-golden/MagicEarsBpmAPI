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
}

package com.magicears.bpm.dao;

import com.magicears.bpm.helper.mybatis.BaseMapper;
import com.magicears.bpm.entity.Test;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by admin on 2018/3/6.
 */
@Component
@Mapper
public interface TestDao extends BaseMapper<Test> {
}

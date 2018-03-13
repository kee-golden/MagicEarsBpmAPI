package com.magicears.bpm.dao;

import com.magicears.bpm.entity.User;
import com.magicears.bpm.helper.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * Created by admin on 2018/3/8.
 */
@Component
@Mapper
public interface UserDao extends BaseMapper<User> {

    User findByUsername(@Param("userName") String userName);
}

package com.magicears.bpm.dao;

import com.magicears.bpm.auth.entity.UserAuthInfo;
import com.magicears.bpm.helper.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserAuthInfoDao extends BaseMapper<UserAuthInfo> {

    UserAuthInfo findByUserId(Long userId);
}
package com.magicears.bpm.controller;

import com.magicears.bpm.util.JWTUtil;
import com.magicears.bpm.core.mvc.ui.ResultData;
import com.magicears.bpm.dao.RoleDao;
import com.magicears.bpm.dao.UserDao;
import com.magicears.bpm.dto.UserDto;
import com.magicears.bpm.entity.User;
import com.magicears.bpm.entity.UserRole;
import com.magicears.bpm.exception.UserNotFoundException;
import com.magicears.bpm.exception.UserPasswordNotEqualException;
import com.magicears.bpm.util.MD5Util;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by admin on 2018/3/8.
 */

@RestController
public class LoginController {

    private final UserDao userDao;

    @Autowired
    public LoginController(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 登录
     *
     * @return
     */
    @RequestMapping(value = "login")
    public ResultData login(String loginName, String password) {
        User user = userDao.findByUsername(loginName);
        if (user == null) {
            throw new UserNotFoundException();
        }
        String md5Code = MD5Util.getMD5Code(password);
        if (!StringUtils.equals(md5Code, user.getPassword())) {
            throw new UserPasswordNotEqualException();
        }
        UserDto userDto = new UserDto(user);
        String sign = JWTUtil.sign(userDto, 1000 * 60 * 60 * 24);
        return ResultData.ok().putDataValue("token", sign).putDataValue("user", userDto);
    }
}

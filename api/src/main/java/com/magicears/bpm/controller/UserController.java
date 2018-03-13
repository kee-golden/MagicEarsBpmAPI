package com.magicears.bpm.controller;

import com.magicears.bpm.config.SystemConfig;
import com.magicears.bpm.core.mvc.ui.ResultData;
import com.magicears.bpm.entity.Menu;
import com.magicears.bpm.entity.User;
import com.magicears.bpm.service.UserService;
import com.magicears.bpm.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by admin on 2018/3/8.
 */
@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    /**
     * 获取所有菜单
     *
     * @param token
     * @return
     */
    @RequestMapping(path = "/menus", method = RequestMethod.GET)
    public ResultData menus(@RequestHeader(SystemConfig.TOKEN) String token) {
        User user = JWTUtil.unSign(token, User.class);
        List<Menu> menus = userService.findMenuByUser(user);
        return ResultData.ok().putDataValue("menus", menus);
    }
}

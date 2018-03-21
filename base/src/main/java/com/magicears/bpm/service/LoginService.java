package com.magicears.bpm.service;

import com.magicears.bpm.dingtalk.js.entity.AccessToken;
import com.magicears.bpm.dingtalk.js.entity.Persistent;
import com.magicears.bpm.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class LoginService {

    private final DingTalkJsService dingTalkJsService;

    public LoginService(DingTalkJsService dingTalkJsService) {
        this.dingTalkJsService = dingTalkJsService;
    }

    public User getDingTalkUser(String code){
        AccessToken accessToken = dingTalkJsService.getAccessToken();
        Persistent persistent = dingTalkJsService.getPersistent(code,accessToken.getAccess_token());
        User user = new User();
        return  user;
    }

}

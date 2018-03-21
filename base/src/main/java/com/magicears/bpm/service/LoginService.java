package com.magicears.bpm.service;

import com.magicears.bpm.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class LoginService {

    private final DingTalkService dingTalkService;

    public LoginService(DingTalkService dingTalkService) {
        this.dingTalkService = dingTalkService;
    }


    public User getDingTalkUser(String code){
        String accessToken = dingTalkService.getAccessToken();
        String tmpAuthCode = dingTalkService.getTmpAuthCode(code,accessToken);
        User user = new User();
        return  user;
    }

}

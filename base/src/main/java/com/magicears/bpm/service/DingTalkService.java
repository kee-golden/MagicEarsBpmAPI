package com.magicears.bpm.service;

import com.magicears.bpm.config.DingTalkConfig;
import com.magicears.bpm.dingtalk.entity.AccessToken;
import com.magicears.bpm.dingtalk.entity.Department;
import com.magicears.bpm.dingtalk.entity.ErroMessage;
import com.magicears.bpm.dingtalk.entity.Organization;
import com.magicears.bpm.dingtalk.url.DingTalkUrl;
import com.magicears.bpm.emuns.DingTalkErrCode;
import com.magicears.bpm.util.HttpRequestUtil;
import com.magicears.bpm.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * 钉钉服务的service
 */
@Transactional
@Service
public class DingTalkService extends ErroMessage{


    private final DingTalkConfig dingTalkConfig;

    @Autowired
    public DingTalkService(DingTalkConfig dingTalkConfig) {
        this.dingTalkConfig = dingTalkConfig;
    }

    /**
     * 获取Access_Token
     * 并且返回成功数据
     * @return
     */
    public AccessToken getAccessToken() {
        AccessToken accessToken = null;
        String url = DingTalkUrl.accessToken + "?corpid="+dingTalkConfig.getCorpId()+"&corpsecret="+dingTalkConfig.getCorpSecret();
        try {
            String tokenStr = HttpRequestUtil.doHttpGet(url);
            accessToken = JsonUtil.jsonToBean(tokenStr,AccessToken.class);
            if (!accessToken.getErrcode().equals(DingTalkErrCode.SUCCESS.getCode())) return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accessToken;
    }


    /**
     *  获取部门列表
     *  并且返回成功数据
     * @param accessToken
     * @return
     */
    public Organization getOrganization (String  accessToken) {
        Organization organization = null;
        String url = DingTalkUrl.organizationList + accessToken;
        try {
            String organizationLisStr = HttpRequestUtil.doHttpGet(url);
            System.out.print(organizationLisStr);
            organization = JsonUtil.jsonToBean(organizationLisStr, Organization.class);
            if (!organization.getErrcode().equals(DingTalkErrCode.SUCCESS.getCode())) return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return organization;
    }

    /**
     * 获取部门详情
     * @param accessToken
     * @param id
     * @return
     */
    public Department getDepartment(String accessToken, Long id) {
        Department department = null;
        String url = DingTalkUrl.departmentInfo + accessToken + "&id=" + id;
        try {
            String departmentInfoStr = HttpRequestUtil.doHttpGet(url);
            department = JsonUtil.jsonToBean(departmentInfoStr,Department.class);
            if (!department.getErrcode().equals(DingTalkErrCode.SUCCESS.getCode())) return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return department;
    }










}

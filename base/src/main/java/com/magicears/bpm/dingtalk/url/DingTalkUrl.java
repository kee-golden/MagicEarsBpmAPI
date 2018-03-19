package com.magicears.bpm.dingtalk.url;

public class DingTalkUrl {

    //获取用户授权码
    public static String snsToken = "https://oapi.dingtalk.com/sns/gettoken?appid=";

    //获取用户授权的持久授权码
    public static String tmpAuthCode = "https://oapi.dingtalk.com/sns/get_persistent_code?access_token=";

    //获取部门列表
    public static String departmentList = "https://oapi.dingtalk.com/department/list?access_token=";

    //获取部门详情
    public static String departmentInfo = "https://oapi.dingtalk.com/department/get?access_token=";

    //获取用户详情
    public static String userInfo = "https://oapi.dingtalk.com/user/get?access_token=";

    //获取部门下所以用户
    public static String userList = "https://oapi.dingtalk.com/user/simplelist?access_token=";

    //获取部门下用户详情
    public static String userInfoByDepartment = "https://oapi.dingtalk.com/user/list?access_token=";
}

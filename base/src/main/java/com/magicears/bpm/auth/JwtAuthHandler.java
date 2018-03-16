package com.magicears.bpm.auth;


import com.magicears.bpm.auth.entity.PrivilegeAuthInfo;
import com.magicears.bpm.emuns.PrivilengeType;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * Created by feng- on 2017/6/6.
 */
class RequestMatcher {

    private static final Logger log = LoggerFactory.getLogger(RequestMatcher.class);

    public static String translation(String uri) {
        if (StringUtils.isEmpty(uri)) {
            return "/";
        }
        String[] split = uri.split("/");
        StringBuilder sb = new StringBuilder("/");
        for (String s : split) {
            if (StringUtils.isEmpty(s)) {
                continue;
            }
            if (StringUtils.isNumeric(s)) {
                sb.append("#/");
            } else {
                sb.append(s).append("/");
            }
        }

        return sb.replace(sb.length() - 1, sb.length(), "").toString();
    }
}


public class JwtAuthHandler {
    private static final Logger log = LoggerFactory.getLogger(JwtAuthHandler.class);

    public static boolean handler(String uri, List<PrivilegeAuthInfo> privileges, String requestMethod) {
        String translateUri = RequestMatcher.translation(uri) + "[" + requestMethod + "]";
        boolean flag = false;
        for (PrivilegeAuthInfo privilege: privileges) {
            if ((privilege.getAction() + "[" + PrivilengeType.getMessage(privilege.getType()) + "]").equals(translateUri)){
                flag = true;
                break;
            }
        }
        //fixme 程序调试阶段试用，测试完成关闭
        if (!flag) {
            log.error("uri:{}", uri);
            log.error("translateUri:{}", translateUri);
            log.error("请将  {}  配置到系统中！并给管理员赋权。", translateUri);

        }
        return flag;
    }

}

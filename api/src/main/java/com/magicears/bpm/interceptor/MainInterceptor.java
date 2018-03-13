package com.magicears.bpm.interceptor;

import com.magicears.bpm.auth.JwtAuthHandler;
import com.magicears.bpm.auth.entity.UserAuthInfo;
import com.magicears.bpm.config.SystemConfig;
import com.magicears.bpm.dao.UserAuthInfoDao;
import com.magicears.bpm.entity.User;
import com.magicears.bpm.exception.ForbiddenException;
import com.magicears.bpm.exception.TokenExpiredException;
import com.magicears.bpm.util.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class MainInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = LoggerFactory.getLogger(MainInterceptor.class);

    private final UserAuthInfoDao userAuthInfoDao;

    @Autowired
    public MainInterceptor(UserAuthInfoDao userAuthInfoDao) {
        this.userAuthInfoDao = userAuthInfoDao;
    }
//
//    /**
//     * 在业务处理器处理请求之前被调用
//     * 如果返回false
//     * 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
//     * <p/>
//     * 如果返回true
//     * 执行下一个拦截器,直到所有的拦截器都执行完毕
//     * 再执行被拦截的Controller
//     * 然后进入拦截器链,
//     * 从最后一个拦截器往回执行所有的postHandle()
//     * 接着再从最后一个拦截器往回执行所有的afterCompletion()
//     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String contextPath = request.getContextPath();
        String method = request.getMethod();
        String requestURI = request.getRequestURI().replace(contextPath, "");
//        return true;
        String token = request.getHeader(SystemConfig.TOKEN);
        User user = JWTUtil.unSign(token, User.class);

        UserAuthInfo userAuthInfo = userAuthInfoDao.findByUserId(user.getId());
        if (userAuthInfo == null) {
            throw new TokenExpiredException();
        }

        log.info(requestURI);
        boolean hasPrivilege = JwtAuthHandler.handler(requestURI, userAuthInfo.getPrivilegeList(), method);
        if (hasPrivilege) {
            return true;
        } else {
            throw new ForbiddenException();
        }
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }


}

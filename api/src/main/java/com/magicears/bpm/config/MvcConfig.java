/**
 * Project Name:i18n
 * Package Name:cn.stackbox.demo.config
 * Date: 2017-02-25 18:06
 */
package com.magicears.bpm.config;

import com.magicears.bpm.interceptor.MainInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 配置项目拦截器
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    static private Logger log = LoggerFactory.getLogger(MvcConfig.class);


    private final MainInterceptor mainInterceptor;

    @Autowired
    public MvcConfig(MainInterceptor mainInterceptor) {
        this.mainInterceptor = mainInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(mainInterceptor).
                excludePathPatterns("/login", "/password",
                        "/auto/login", "/password/reset", "/putPassword", "/domestic/statistics",
                        "/domestic/driving-time", "/domestic/classify", "/realTimeData/location",
                        "/test", "/label", "/target", "/update-id");
    }

//    private HandlerInterceptor createLocaleChangeInterceptor() {
//        return new MainInterceptor();
//    }


}
package com.magicears.bpm.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by feng- on 2017/5/24.
 */
@Component
@ConfigurationProperties(prefix="system")
public class SystemConfig {
    private String filePath;
    public final static String TOKEN = "X-magicears-Token";


    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


}

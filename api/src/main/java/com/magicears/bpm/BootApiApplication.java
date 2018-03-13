package com.magicears.bpm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by admin on 2018/3/6.
 */

@SpringBootApplication
@ComponentScan("com.magicears.bpm")
public class BootApiApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(BootApiApplication.class, args);
    }
}

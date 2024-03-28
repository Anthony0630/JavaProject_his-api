package org.example.his.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ServletComponentScan
@EnableAsync
@ComponentScan("org.example.*")
@MapperScan("org.example.his.api.db.dao")
@EnableCaching
@EnableScheduling
public class HisApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HisApiApplication.class, args);
    }

}

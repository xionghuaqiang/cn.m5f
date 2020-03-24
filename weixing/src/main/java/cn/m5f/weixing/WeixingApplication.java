package cn.m5f.weixing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("cn.m5f")
public class WeixingApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeixingApplication.class, args);
    }

}

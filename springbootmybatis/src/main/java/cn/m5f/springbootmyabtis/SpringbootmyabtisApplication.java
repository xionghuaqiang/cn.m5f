package cn.m5f.springbootmyabtis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages ="cn.m5f.springbootmyabtis")
public class SpringbootmyabtisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootmyabtisApplication.class, args);
    }

}

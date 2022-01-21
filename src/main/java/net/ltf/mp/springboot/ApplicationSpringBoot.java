package net.ltf.mp.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//// 配置mapper扫描包 可在配置类中配置
//@MapperScan("net.ltf.mp.springboot.mapper")
@SpringBootApplication
public class ApplicationSpringBoot {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationSpringBoot.class,args);
    }

}

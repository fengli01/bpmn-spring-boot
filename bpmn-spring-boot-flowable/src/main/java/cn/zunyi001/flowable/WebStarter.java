package cn.zunyi001.flowable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Description:
 * @author: fengli
 * @date: 2020/7/24 10:20 上午
 */
@SpringBootApplication
@MapperScan("cn.zunyi001.flowable.mapper")
public class WebStarter {

    public static void main(String[] args) {
        SpringApplication.run(WebStarter.class, args);
    }
}

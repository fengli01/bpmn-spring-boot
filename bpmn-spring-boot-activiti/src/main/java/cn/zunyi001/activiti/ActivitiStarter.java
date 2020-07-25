package cn.zunyi001.activiti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * @Description:
 * @author: fengli
 * @date: 2020/7/25 6:15 下午
 */
@SpringBootApplication(exclude = {
        SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class
})
public class ActivitiStarter {

    public static void main(String[] args) {
        SpringApplication.run(ActivitiStarter.class, args);
    }
}

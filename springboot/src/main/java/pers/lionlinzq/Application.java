package pers.lionlinzq;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author lin
 */
// @NacosPropertySource(dataId = "springboot", groupId = "test_group",autoRefreshed = true)
@MapperScan(basePackages = {"pers.lionlinzq.mapper"})
@SpringBootApplication(scanBasePackages = {"pers.lionlinzq"})
@Slf4j
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

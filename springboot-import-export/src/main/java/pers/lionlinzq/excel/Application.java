package pers.lionlinzq.excel;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@SpringBootApplication
public class Application {

    @Autowired(required = false) // 如果没有配置 datasource，注入不会报错
    private HikariDataSource dataSource;


    public static void main(String[] args) {
        ConcurrentHashMap<Integer,Integer> map = new ConcurrentHashMap<>();
        SpringApplication.run(Application.class);
    }

    @PostConstruct
    public void logStartupInfo() {
        if (dataSource != null) {
            log.info("======================== HikariCP Configuration ========================");
            log.info("Maximum Pool Size: {}", dataSource.getMaximumPoolSize());
            log.info("JDBC URL: {}", dataSource.getJdbcUrl());
            log.info("Username: {}", dataSource.getUsername());
            log.info("=======================================================================");
        } else {
            log.info("======================== HikariCP Configuration ========================");
            log.info("HikariDataSource is not configured.");
            log.info("=======================================================================");
        }
    }

}

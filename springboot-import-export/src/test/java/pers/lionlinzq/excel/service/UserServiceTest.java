package pers.lionlinzq.excel.service;

import com.github.javafaker.Faker;
import com.github.yitter.idgen.YitIdHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.lionlinzq.excel.entity.User;
import pers.lionlinzq.excel.entity.Users;
import pers.lionlinzq.excel.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Slf4j
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository; // 使用真实的 UserRepository


    @Test
    public void testQuery() {

        User alice01 = userRepository.findByUsernameAndPassword("ian_clark", "ian@example.com");
        log.info("user:{}", alice01);
    }

    @Test
    public void test() {
        Faker faker = new Faker(Locale.CHINA);
        List<User> userList = new ArrayList<>();
        // 记录构建实体的开始时间
        long entityStartTime = System.currentTimeMillis();
        LocalDate startDate = LocalDate.of(2019, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 1);

        for (int i = 0; i < 1000; i++) {
            User user = new User();
            user.setId(YitIdHelper.nextId());
            String name = faker.name().name();
            user.setUsername(name);
            user.setPassword(faker.internet().password());
            user.setEmail(faker.internet().safeEmailAddress(name+faker.random().hex()));
            user.setPhone(faker.phoneNumber().cellPhone());
            user.setStatus(faker.random().nextInt(3));

            // 生成随机日期
            LocalDateTime randomDate = faker.date().between(
                    Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
            ).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            user.setCreatedAt(randomDate);
            user.setAddress(faker.address().fullAddress());
            userList.add(user);
        }

        // 记录构建实体的结束时间
        long entityEndTime = System.currentTimeMillis();  // 或者使用 Instant.now()

        // 计算构建实体的耗时
        long entityDuration = entityEndTime - entityStartTime;
        log.info("构建实体耗时: {} 毫秒", entityDuration);

        // 记录插入操作的开始时间
        long startTime = System.currentTimeMillis();  // 或者使用 Instant.now()

        // 执行批量插入
        userService.batchSave(userList);

        // 记录插入操作的结束时间
        long endTime = System.currentTimeMillis();  // 或者使用 Instant.now()

        // 计算并打印插入操作的耗时
        long duration = endTime - startTime;
        log.info("插入操作耗时: {} 毫秒", duration);
    }

    @Autowired
    UsersService usersService;

    @Test
    public void testSaveUserByMp() {
        Faker faker = new Faker(Locale.CHINA);
        List<Users> userList = new ArrayList<>();
        log.info("执行前数据量:{}",usersService.count());
        // 记录构建实体的开始时间
        long entityStartTime = System.currentTimeMillis();
        LocalDate startDate = LocalDate.of(2019, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 1);

        for (int i = 0; i < 1000; i++) {
            Users user = new Users();
            user.setId(YitIdHelper.nextId());
            String name = faker.name().name();
            user.setUsername(name);
            user.setPassword(faker.internet().password());
            user.setEmail(faker.internet().safeEmailAddress(name+faker.random().hex()));
            user.setPhone(faker.phoneNumber().cellPhone());
            user.setStatus(faker.random().nextInt(3));

            // 生成随机日期
            LocalDateTime randomDate = faker.date().between(
                    Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
            ).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            user.setCreatedAt(randomDate);
            user.setAddress(faker.address().fullAddress());
            userList.add(user);
        }

        // 记录构建实体的结束时间
        long entityEndTime = System.currentTimeMillis();  // 或者使用 Instant.now()

        // 计算构建实体的耗时
        long entityDuration = entityEndTime - entityStartTime;
        log.info("构建实体耗时: {} 毫秒", entityDuration);

        // 记录插入操作的开始时间
        long startTime = System.currentTimeMillis();  // 或者使用 Instant.now()

        // 执行批量插入
        usersService.saveBatch(userList);

        // 记录插入操作的结束时间
        long endTime = System.currentTimeMillis();  // 或者使用 Instant.now()

        // 计算并打印插入操作的耗时
        long duration = endTime - startTime;
        log.info("插入操作耗时: {} 毫秒", duration);

        log.info("执行后数据量:{}",usersService.count());
    }

}

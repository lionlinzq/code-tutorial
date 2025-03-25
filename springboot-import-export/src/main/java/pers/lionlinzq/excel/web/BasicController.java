package pers.lionlinzq.excel.web;

import com.github.javafaker.Faker;
import com.github.yitter.idgen.YitIdHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pers.lionlinzq.excel.entity.User;
import pers.lionlinzq.excel.entity.Users;
import pers.lionlinzq.excel.repository.UserRepository;
import pers.lionlinzq.excel.service.UserService;
import pers.lionlinzq.excel.service.UsersService;
import pers.lionlinzq.excel.utils.ExportUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RestController
@RequestMapping("/basic")
public class BasicController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/userList")
    public List<User> userList() {
        List<User> allUsers = userService.getAllUsers();
        return allUsers;
    }


    @GetMapping("/export")
    public void export(HttpServletResponse response) {
        List<User> allUsers = userService.getAllUsers();
        ExportUtils.export(allUsers, Arrays.asList("id", "名字", "密码", "邮箱", "电话", "状态", "创建时间"), response);
    }

    @GetMapping("/importExcel")
    public void importExcel(@RequestParam(value = "file", required = true) MultipartFile file) throws IOException {
        long start = System.currentTimeMillis();
        log.info("开始导入。。。");
        List<User> userList = ExportUtils.upload(file.getInputStream());
        userService.saveUserBatch(userList);
        log.info("完成导入,花费时间:{}s", (System.currentTimeMillis() - start) / 1000);
    }

    @Autowired
    UsersService usersService;

    @RequestMapping("/addUserByMp")
    public void addUserByMp(@RequestParam Long size) {
        log.info("进行测试轮数:{}", size);
        Faker faker = new Faker(Locale.CHINA);
        List<Users> userList = new ArrayList<>();
        log.info("执行前数据量:{}", usersService.count());
        // 记录构建实体的开始时间
        long entityStartTime = System.currentTimeMillis();
        LocalDate startDate = LocalDate.of(2019, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 1);

        for (int i = 0; i < 1000 * size; i++) {
            Users user = new Users();
            user.setId(YitIdHelper.nextId());
            String name = faker.name().name();
            user.setUsername(name);
            user.setPassword(faker.internet().password());
            user.setEmail(faker.internet().safeEmailAddress(name + faker.random().hex()));
            user.setPhone(faker.phoneNumber().cellPhone());
            user.setStatus(faker.random().nextInt(3));

            // 生成随机日期
            LocalDateTime randomDate = faker.date().between(Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant())).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
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

        log.info("执行后数据量:{}", usersService.count());

    }


    @RequestMapping("/addUserByJPA")
    public void addUserByJPA(@RequestParam Long size) {
        log.info("进行测试轮数:{}", size);
        Faker faker = new Faker(Locale.CHINA);
        List<User> userList = new ArrayList<>();
        log.info("执行前数据量:{}", userRepository.count());
        // 记录构建实体的开始时间
        long entityStartTime = System.currentTimeMillis();
        LocalDate startDate = LocalDate.of(2019, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 1);

        for (int i = 0; i < 1000 * size; i++) {
            User user = new User();
            user.setId(YitIdHelper.nextId());
            String name = faker.name().name();
            user.setUsername(name);
            user.setPassword(faker.internet().password());
            user.setEmail(faker.internet().safeEmailAddress(name + faker.random().hex()));
            user.setPhone(faker.phoneNumber().cellPhone());
            user.setStatus(faker.random().nextInt(3));

            // 生成随机日期
            LocalDateTime randomDate = faker.date().between(Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant())).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
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

        log.info("执行后数据量:{}", usersService.count());

    }


    @ResponseBody
    @RequestMapping("/addUser")
    public Users addUser() {
        Faker faker = new Faker(Locale.CHINA);
        log.info("执行前数据量:{}", userRepository.count());
        // 记录构建实体的开始时间
        long entityStartTime = System.currentTimeMillis();
        LocalDate startDate = LocalDate.of(2019, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 1);
        ConcurrentHashMap<Integer,Integer> map = new ConcurrentHashMap();
        map.put(1,2);
        Users user = new Users();
        user.setId(YitIdHelper.nextId());
        String name = faker.name().name();
        user.setUsername(name);
        user.setPassword(faker.internet().password());
        user.setEmail(faker.internet().safeEmailAddress(name + faker.random().hex()));
        user.setPhone(faker.phoneNumber().cellPhone());
        user.setStatus(faker.random().nextInt(3));

        // 生成随机日期
        LocalDateTime randomDate = faker.date().between(Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant())).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        user.setCreatedAt(randomDate);
        user.setAddress(faker.address().fullAddress());


        // 记录构建实体的结束时间
        long entityEndTime = System.currentTimeMillis();  // 或者使用 Instant.now()

        // 计算构建实体的耗时
        long entityDuration = entityEndTime - entityStartTime;
        log.info("构建实体耗时: {} 毫秒", entityDuration);

        // 记录插入操作的开始时间
        long startTime = System.currentTimeMillis();  // 或者使用 Instant.now()

        // 执行批量插入
        usersService.save(user);

        // 记录插入操作的结束时间
        long endTime = System.currentTimeMillis();  // 或者使用 Instant.now()

        // 计算并打印插入操作的耗时
        long duration = endTime - startTime;
        log.info("插入操作耗时: {} 毫秒", duration);

        log.info("执行后数据量:{}", usersService.count());
        return user;
    }


    @RequestMapping("/addUserByJPANoBatch")
    public void addUserByJPANoBatch(@RequestParam Long size) {
        log.info("进行测试轮数:{}", size);
        Faker faker = new Faker(Locale.CHINA);
        List<User> userList = new ArrayList<>();
        log.info("执行前数据量:{}", userRepository.count());
        // 记录构建实体的开始时间
        long entityStartTime = System.currentTimeMillis();
        LocalDate startDate = LocalDate.of(2019, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 1);

        for (int i = 0; i < 1000 * size; i++) {
            User user = new User();
            user.setId(YitIdHelper.nextId());
            String name = faker.name().name();
            user.setUsername(name);
            user.setPassword(faker.internet().password());
            user.setEmail(faker.internet().safeEmailAddress(name + faker.random().hex()));
            user.setPhone(faker.phoneNumber().cellPhone());
            user.setStatus(faker.random().nextInt(3));

            // 生成随机日期
            LocalDateTime randomDate = faker.date().between(Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant())).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
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
        userService.saveUserBatch(userList);

        // 记录插入操作的结束时间
        long endTime = System.currentTimeMillis();  // 或者使用 Instant.now()

        // 计算并打印插入操作的耗时
        long duration = endTime - startTime;
        log.info("插入操作耗时: {} 毫秒", duration);

        log.info("执行后数据量:{}", usersService.count());

    }

    @GetMapping("/addUser2")
    public void addUser2(){
        log.info("执行一堆操作。。。。");

    }
}

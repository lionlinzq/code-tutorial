package pers.lionlinzq.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.lionlinzq.domain.User;
import pers.lionlinzq.service.UserService;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * 测试控制器
 *
 * @author lin
 * @date 2024/02/26
 */
@RestController
@Slf4j
@RequestMapping("/testController")
public class TestController {

    @Autowired
    UserService userService;


    @GetMapping("/testSave")
    public void testSave() {
        User user = new User();
        Random random = new Random();
        boolean flag = random.nextBoolean();
        if (flag){
            user.setId(random.nextLong());
        }
        user.setName(random.nextInt() + "张三");
        user.setAge(random.nextInt(50));
        user.setEmail(random.nextInt() + "zhangsan@qq.com");
        user.setCreateTime(LocalDateTime.now());
        log.info("接受用户参数:{}", user);
        userService.addUser(user);
    }

}

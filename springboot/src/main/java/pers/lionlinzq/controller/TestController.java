package pers.lionlinzq.controller;

import cn.monitor4all.logRecord.annotation.OperationLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pers.lionlinzq.domain.User;
import pers.lionlinzq.service.UserService;

/**
 * 测试控制器
 *
 * @author lin
 * @date 2024/02/26
 */
@RestController("/testController")
public class TestController {

    @Autowired
    UserService userService;


    @OperationLog(bizId = "#user.id", bizType = "'保存'")
    @PostMapping("/testSave")
    public void testSave(@RequestBody User user) {
        userService.save(user);
        System.out.println("testSave");
    }

}

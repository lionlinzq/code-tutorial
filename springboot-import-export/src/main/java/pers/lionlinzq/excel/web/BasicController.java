package pers.lionlinzq.excel.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pers.lionlinzq.excel.entity.User;
import pers.lionlinzq.excel.service.UserService;
import pers.lionlinzq.excel.utils.ExportUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/basic")
public class BasicController {

    @Autowired
    UserService userService;

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


}

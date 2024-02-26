package pers.lionlinzq.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pers.lionlinzq.Application;
import pers.lionlinzq.domain.User;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Slf4j
@SpringBootTest(classes = Application.class) // 启动类
@ExtendWith(SpringExtension.class)
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userServiceImplUnderTest;

    @Test
    public void testList1() {
        // Setup
        final Wrapper<User> queryWrapper = null;
        final User user = new User();
        user.setId(0L);
        user.setName("name");
        user.setAge(0);
        user.setEmail("email");
        final List<User> expectedResult = Arrays.asList(user);

        // Run the test
        final List<User> result = userServiceImplUnderTest.list(queryWrapper);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testList2() {
        List<User> list = userServiceImplUnderTest.list();
        log.info("查询数据是:{}", list);
    }
}

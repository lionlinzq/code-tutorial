package pers.lionlinzq.excel.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.lionlinzq.excel.entity.User;
import pers.lionlinzq.excel.repository.UserRepository;
import pers.lionlinzq.excel.service.UserService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository; // 使用真实的 UserRepository

    private User testUser;

    @BeforeEach
    void setUp() {
        // 清空数据库以确保干净的状态
        userRepository.deleteAll();

        List<User> users = Arrays.asList(
                new User(1L, "alice01", "alice@example.com", "1234567890", "password123", "ACTIVE", LocalDateTime.now().minusDays(10)),
                new User(2L, "bob_smith", "bob@example.com", "0987654321", "password456", "INACTIVE", LocalDateTime.now().minusDays(8)),
                new User(3L, "charlie_wang", "charlie@example.com", "1122334455", "mySecret789", "ACTIVE", LocalDateTime.now().minusDays(6)),
                new User(4L, "diana_lee", "diana@example.com", "2233445566", "helloWorld2024", "SUSPENDED", LocalDateTime.now().minusDays(5)),
                new User(5L, "edward_jones", "edward@example.com", "3344556677", "edwardPass", "ACTIVE", LocalDateTime.now().minusDays(4)),
                new User(6L, "fiona_brown", "fiona@example.com", "4455667788", "secure123", "INACTIVE", LocalDateTime.now().minusDays(3)),
                new User(7L, "george_miller", "george@example.com", "5566778899", "george123", "ACTIVE", LocalDateTime.now().minusDays(2)),
                new User(8L, "hannah_white", "hannah@example.com", "6677889900", "passHannah", "SUSPENDED", LocalDateTime.now().minusDays(1)),
                new User(9L, "ian_clark", "ian@example.com", "7788990011", "ianClark007", "ACTIVE", LocalDateTime.now()),
                new User(10L, "julia_king", "julia@example.com", "8899001122", "juliasPass!", "INACTIVE", LocalDateTime.now().plusDays(1))
        );

        // 保存这些用户数据
        userRepository.saveAll(users);
    }

    @Test
    void testCreateAndRetrieveUser() {
        // 保存用户
        User createdUser = userService.createUser(testUser);
        assertThat(createdUser).isNotNull();
        assertThat(createdUser.getId()).isNotNull(); // 检查自动生成的 ID
        assertThat(createdUser.getUsername()).isEqualTo("realUser");

        // 检索所有用户并验证
        List<User> users = userService.getAllUsers();
        assertThat(users.size()).isEqualTo(1);
        assertThat(users.get(0).getUsername()).isEqualTo("realUser");
    }

    @Test
    public void testQuery(){

        User alice01 = userRepository.findByUsernameAndPassword("ian_clark", "ian@example.com");
        log.info("user:{}",alice01);
    }


}

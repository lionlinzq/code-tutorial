package pers.lionlinzq.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pers.lionlinzq.domain.User;
import pers.lionlinzq.mapper.UserMapper;

import java.time.LocalDateTime;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserMapper mockUserMapper;

    @InjectMocks
    private UserServiceImpl userServiceImplUnderTest;

    @Test
    void testAddUser() {
        User user = new User();
        user.setName("zhangsan");
        user.setAge(18);
        user.setId(new Random().nextLong());
        userServiceImplUnderTest.addUser(user);
    }
}

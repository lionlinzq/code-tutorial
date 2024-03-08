package pers.lionlinzq.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.lionlinzq.annotation.OperationLog;
import pers.lionlinzq.context.LogRecordContext;
import pers.lionlinzq.domain.User;
import pers.lionlinzq.mapper.UserMapper;
import pers.lionlinzq.service.UserService;

import java.time.LocalDateTime;

/**
 * @author lin
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2024-01-31 14:42:19
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(User user) {
        if (user.getId() == null) {
            // userMapper.insert(user);
            log.info("新增用户:{}", user);
        }else {
            // User oldUser = userMapper.selectById(user);
            User oldUser = new User(1L,"张三",20,"zhangsan@qq.com", LocalDateTime.now());
            log.info("修改用户:{}", oldUser);
            LogRecordContext.putVariable("oldUser", oldUser);
            oldUser.setName(user.getName());
            oldUser.setAge(user.getAge());
            oldUser.setEmail(user.getEmail());
            // userMapper.updateById(oldUser);
        }
    }
}





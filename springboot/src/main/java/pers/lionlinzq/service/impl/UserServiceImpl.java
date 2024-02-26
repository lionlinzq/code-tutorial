package pers.lionlinzq.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.lionlinzq.domain.User;
import pers.lionlinzq.mapper.UserMapper;
import pers.lionlinzq.service.UserService;

/**
 * @author lin
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2024-01-31 14:42:19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

}





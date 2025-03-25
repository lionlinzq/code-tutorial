package pers.lionlinzq.excel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.lionlinzq.excel.entity.Users;
import pers.lionlinzq.excel.mapper.UsersMapper;
import pers.lionlinzq.excel.service.UsersService;


/**
* @author lzq
* @description 针对表【users(用户表，存储用户的基本信息)】的数据库操作Service实现
* @createDate 2024-11-14 15:49:00
*/
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>
    implements UsersService {

}





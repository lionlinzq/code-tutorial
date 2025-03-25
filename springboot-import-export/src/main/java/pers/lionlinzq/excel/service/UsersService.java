package pers.lionlinzq.excel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Repository;
import pers.lionlinzq.excel.entity.Users;

/**
* @author lzq
* @description 针对表【users(用户表，存储用户的基本信息)】的数据库操作Service
* @createDate 2024-11-14 15:49:00
*/
@Repository
public interface UsersService extends IService<Users> {

}

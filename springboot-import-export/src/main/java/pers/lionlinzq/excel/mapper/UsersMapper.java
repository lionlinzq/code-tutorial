package pers.lionlinzq.excel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.lionlinzq.excel.entity.Users;

/**
* @author lzq
* @description 针对表【users(用户表，存储用户的基本信息)】的数据库操作Mapper
* @createDate 2024-11-14 15:49:00
* @Entity generator.entity.Users
*/
@Mapper
public interface UsersMapper extends BaseMapper<Users> {

}





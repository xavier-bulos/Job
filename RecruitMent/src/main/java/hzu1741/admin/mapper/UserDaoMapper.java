package hzu1741.admin.mapper;

import hzu1741.admin.dao.UserDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDaoMapper
{
    @Select("select * from user where user_name = #{userName}")
    UserDao selectByName(@Param("userName") String userName);

    @Select("select * from user where user_pwd = #{password}")
    UserDao selectByPassword(@Param("password") String userName);

    @Select("select * from user where user_phone = #{phone}")
    UserDao selectByPhone(@Param("phone") String phone);

    @Select("select user_name from user where user_name = #{userName}")
    String getName(@Param("userName") String userName);

    @Select("select user_phone from user where user_phone = #{phone}")
    String getPhone(@Param("phone") String phone);

    int insert(UserDao record);
    List<UserDao> selectAll();

}
package hzu1741.admin.service;


import hzu1741.admin.dao.UserDao;
import org.apache.ibatis.annotations.Param;

public interface UserService
{
    UserDao selectByName(String userName);
    UserDao selectByPassword(String password);

    UserDao selectByPhone(String phone);
    int insert(UserDao record);

    String getName(@Param("userName") String userName);
    String getPhone(@Param("phone") String phone);
}

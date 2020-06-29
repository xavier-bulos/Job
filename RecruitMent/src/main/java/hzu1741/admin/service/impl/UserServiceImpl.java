package hzu1741.admin.service.impl;

import hzu1741.admin.dao.UserDao;
import hzu1741.admin.mapper.UserDaoMapper;
import hzu1741.admin.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDaoMapper userDaoMapper;

    @Override
    public UserDao selectByName(String userName) {
        return userDaoMapper.selectByName(userName);
    }

    @Override
    public UserDao selectByPassword(String password) {
        return userDaoMapper.selectByPassword(password);
    }

    @Override
    public UserDao selectByPhone(String phone) {
        return userDaoMapper.selectByPhone(phone);
    }

    @Override
    public int insert(UserDao record) {
        return userDaoMapper.insert(record);
    }

    @Override
    public String getName(String userName) {
        return userDaoMapper.getName(userName);
    }

    @Override
    public String getPhone(String phone) {
        return userDaoMapper.getPhone(phone);
    }
}

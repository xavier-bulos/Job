package hzu1741.admin.service.impl;

import hzu1741.admin.dao.LoginLogDao;
import hzu1741.admin.mapper.LoginLogDaoMapper;
import hzu1741.admin.service.LoginLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginLogServiceImpl implements LoginLogService {
    @Resource
    LoginLogDaoMapper loginLogDaoMapper;

    @Override
    public int insert(LoginLogDao record) {
        return loginLogDaoMapper.insert(record);
    }
}

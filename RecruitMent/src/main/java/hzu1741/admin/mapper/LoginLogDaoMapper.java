package hzu1741.admin.mapper;

import hzu1741.admin.dao.LoginLogDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoginLogDaoMapper {
    int insert(LoginLogDao record);

    List<LoginLogDao> selectAll();
}
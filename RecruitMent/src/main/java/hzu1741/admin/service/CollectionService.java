package hzu1741.admin.service;

import hzu1741.admin.dao.CollectionDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectionService {

    String getName(@Param("userName") String userName);

    List<CollectionDao> selectByName(String userName);

    int deleteByPrimaryKey(String userName,String jobId);

    int insert(CollectionDao record);

    List<CollectionDao> selectAll();
}

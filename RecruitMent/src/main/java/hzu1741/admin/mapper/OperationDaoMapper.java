package hzu1741.admin.mapper;

import hzu1741.admin.dao.OperationDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OperationDaoMapper {
    int insert(OperationDao record);

    List<OperationDao> selectAll();
}
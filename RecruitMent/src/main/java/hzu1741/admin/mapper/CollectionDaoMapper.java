package hzu1741.admin.mapper;

import hzu1741.admin.dao.CollectionDao;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CollectionDaoMapper {

    List<CollectionDao> selectByName(@Param("userName") String userName);

    String getName(@Param("userName") String userName);

    @Delete("delete from user_collection where user_name = #{userName} and job_id=#{jobId}")
    int deleteByPrimaryKey(@Param("userName")String userName,@Param("jobId")String jobId);

    int insert(CollectionDao record);

    List<CollectionDao> selectAll();
}
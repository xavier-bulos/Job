package hzu1741.admin.mapper;

import hzu1741.admin.dao.JobDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface JobDaoMapper {


    List<JobDao> selectAll(@Param("place") String place,@Param("pay") String pay,
                           @Param("state") String state,@Param("word") String word);

    List<JobDao> selectByPrimaryKey(String title);

    @Select("select * from job where job_id = #{Id}")
    JobDao selectById(@Param("Id") String jobId);

    @Select("select * from job where job_title like #{keyword} and work_place like #{place}")
    List<JobDao> selectByword(@Param("keyword") String keyword,@Param("place") String place);

    //据地点查询
    @Select("select * from job where work_place like #{place} ")
    List<JobDao> selectByPlace(@Param("place") String place);

    //****************************************************************************


}
package hzu1741.admin.service;

import hzu1741.admin.dao.JobDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobService {
    JobDao selectById(String jobId);

    List<JobDao> selectAll(String place,String pay,String state, String word);

    List<JobDao> selectByPlace(String place);

    List<JobDao> selectByPrimaryKey(String title);

    List<JobDao> selectByword(String keyword, String place);


}

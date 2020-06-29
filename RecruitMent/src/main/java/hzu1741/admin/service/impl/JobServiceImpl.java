package hzu1741.admin.service.impl;

import hzu1741.admin.dao.JobDao;
import hzu1741.admin.mapper.JobDaoMapper;
import hzu1741.admin.service.JobService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Resource
    JobDaoMapper jobDaoMapper;

    @Override
    public List<JobDao> selectByPrimaryKey(String title) {
        return jobDaoMapper.selectByPrimaryKey(title);
    }

    @Override
    public List<JobDao> selectByword(String keyword, String place) {
        return jobDaoMapper.selectByword(keyword,place);
    }

    @Override
    public JobDao selectById(String jobId) {
        return jobDaoMapper.selectById(jobId);
    }

    @Override
    public List<JobDao> selectAll(String place, String pay, String state, String word) {
        return jobDaoMapper.selectAll(place,pay,state,word);
    }

    @Override
    public List<JobDao> selectByPlace(String place) {
        return jobDaoMapper.selectByPlace(place);
    }



}

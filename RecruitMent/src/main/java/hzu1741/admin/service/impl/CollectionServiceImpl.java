package hzu1741.admin.service.impl;

import hzu1741.admin.dao.CollectionDao;
import hzu1741.admin.mapper.CollectionDaoMapper;
import hzu1741.admin.service.CollectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Resource
    CollectionDaoMapper collectionDaoMapper;

    @Override
    public String getName(String userName) {
        return collectionDaoMapper.getName(userName);
    }

    @Override
    public List<CollectionDao> selectByName(String userName) {
        return collectionDaoMapper.selectByName(userName);
    }

    @Override
    public int deleteByPrimaryKey(String userName, String jobId) {
        return collectionDaoMapper.deleteByPrimaryKey(userName,jobId);
    }

    @Override
    public int insert(CollectionDao record) {
        return collectionDaoMapper.insert(record);
    }

    @Override
    public List<CollectionDao> selectAll() {
        return collectionDaoMapper.selectAll();
    }
}

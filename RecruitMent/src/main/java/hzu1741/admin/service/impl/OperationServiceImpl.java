package hzu1741.admin.service.impl;

import hzu1741.admin.dao.OperationDao;
import hzu1741.admin.mapper.OperationDaoMapper;
import hzu1741.admin.service.OperationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OperationServiceImpl implements OperationService {

    @Resource
    OperationDaoMapper operationDaoMapper;

    @Override
    public int insert(OperationDao record) {
        return operationDaoMapper.insert(record);
    }
}

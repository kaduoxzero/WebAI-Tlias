package com.kaduox.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kaduox.mapper.OperateLogMapper;
import com.kaduox.pojo.OperateLog;
import com.kaduox.pojo.PageResult;
import com.kaduox.service.OperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OperateLogServiceImpl implements OperateLogService {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    public PageResult page(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<OperateLog> list = operateLogMapper.list();
        Page<OperateLog> p = (Page<OperateLog>) list;

        return new PageResult(p.getTotal(), p.getResult());
    }
}
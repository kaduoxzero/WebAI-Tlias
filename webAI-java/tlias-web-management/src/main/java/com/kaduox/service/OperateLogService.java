package com.kaduox.service;

import com.kaduox.pojo.PageResult;

public interface OperateLogService {
    PageResult page(Integer page, Integer pageSize);
}
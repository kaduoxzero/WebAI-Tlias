package com.kaduox.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kaduox.pojo.Clazz;
import com.kaduox.pojo.ClazzQueryParam;
import com.kaduox.pojo.PageResult;

import java.util.List;

/**
 * 班级Service
 */
public interface ClazzService  {
    PageResult<Clazz> getList(ClazzQueryParam clazzQueryParam);

    void deleteByIds(List<Integer> idList);

    void save(Clazz clazz);

    Clazz getById(Integer id);

    void update(Clazz clazz);

    List<Clazz> list();
}
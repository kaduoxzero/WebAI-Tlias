package com.kaduox.mapper;

import com.kaduox.pojo.Clazz;
import com.kaduox.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

@Mapper
public interface ClazzMapper {
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    void deleteByIds(List<Integer> ids);

    void insert(Clazz clazz);

    Clazz getById(Integer id);

    void update(Clazz clazz);

    List<Clazz> clazzList();
}
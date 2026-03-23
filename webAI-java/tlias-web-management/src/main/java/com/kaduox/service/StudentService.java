package com.kaduox.service;

import com.kaduox.pojo.PageResult;
import com.kaduox.pojo.Student;
import com.kaduox.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    PageResult<Student> page(StudentQueryParam studentQueryParam);

    void save(Student student);

    void update(Student student);

    void handleViolation(Integer id, Integer score);

    void deleteByIds(List<Integer> ids);
    
    Student getById(Integer id);

}

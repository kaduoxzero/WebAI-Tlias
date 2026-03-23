package com.kaduox.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kaduox.mapper.StudentMapper;
import com.kaduox.pojo.PageResult;
import com.kaduox.pojo.Student;
import com.kaduox.pojo.StudentQueryParam;
import com.kaduox.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired // 建议习惯性放在类顶部
    private StudentMapper studentMapper;

    @Override
    public void save(Student student) {
        studentMapper.insert(student);
    }

    @Override
    public void update(Student student) {
        studentMapper.update(student);
    }

    @Override
    public void handleViolation(Integer id, Integer score) {
        Student student = new Student();
        student.setId(id);
        student.setViolationScore(score);

        student.setViolationCount(1);

        studentMapper.handleViolation(student);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(List<Integer> ids) {
        studentMapper.deleteByIds(ids);
    }

    @Override
    public Student getById(Integer id) {
        return studentMapper.getById(id);
    }

    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam) {
        // 1. 开启分页
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());

        // 2. 执行查询
        List<Student> list = studentMapper.list(studentQueryParam);

        // 3. 强转并封装结果
        Page<Student> studentPage = (Page<Student>) list;
        return new PageResult<Student>(studentPage.getTotal(), studentPage.getResult());
    }
}
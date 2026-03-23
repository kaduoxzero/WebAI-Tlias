package com.kaduox.service.impl;

import com.kaduox.mapper.DeptMapper;
import com.kaduox.pojo.Dept;
import com.kaduox.pojo.Result;
import com.kaduox.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        deptMapper.deletebyId(id);
    }

    @Override
    public void addDept(Dept dept) {

        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.insert(dept);
    }


    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }
    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}

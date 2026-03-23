package com.kaduox.service;

import com.kaduox.pojo.Dept;

import java.util.List;

public interface DeptService {

    List<Dept> findAll();

    void deleteById(Integer id);

    void addDept(Dept dept);

    Dept getById(Integer id);

    void update(Dept dept);
}

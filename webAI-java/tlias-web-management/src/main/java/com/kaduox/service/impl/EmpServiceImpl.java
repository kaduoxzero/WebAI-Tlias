package com.kaduox.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kaduox.mapper.EmpExprMapper;
import com.kaduox.mapper.EmpMapper;
import com.kaduox.pojo.*;
import com.kaduox.service.EmpLogService;
import com.kaduox.service.EmpService;
import com.kaduox.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class EmpServiceImpl implements EmpService{

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;

    //分页查询
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {

        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());

        List<Emp> list = empMapper.list(empQueryParam);

        Page<Emp> empPage = (Page<Emp>) list;

        return new PageResult<Emp>(empPage.getTotal(), empPage.getResult());
    }

    @Transactional(rollbackFor = {Exception.class})//事务管理
    @Override
    public void save(Emp emp) throws Exception {
        try {
            //添加时间
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            //批量插入员工工作经历数据
            List<EmpExpr> empExprList = emp.getExprList();
            if(!CollectionUtils.isEmpty(empExprList)){
                empExprList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });
                empExprMapper.insertBatch(empExprList);
            }
        } finally {
            EmpLog empLog = new EmpLog(null,LocalDateTime.now(),"新增员工:"+emp);
            empLogService.insertLog(empLog);
        }


    }

    @Transactional
    @Override
    public void deleteByIds(List<Integer> ids) {
        //1. 根据ID批量删除员工基本信息
        empMapper.deleteByIds(ids);

        //2. 根据员工的ID批量删除员工的工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    @Transactional
    @Override
    public void update(Emp emp) {
        //1. 根据ID更新员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        //2. 根据员工ID删除员工的工作经历信息 【删除老的】
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));

        //3. 新增员工的工作经历数据 【新增新的】
        Integer empId = emp.getId();
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(empId));
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public List<Emp> list() {
        List<Emp> empList = empMapper.empList();
        return empList;
    }

    @Override
    public LoginInfo login(Emp emp) {
        // 1. 查询数据库，确认用户是否存在
        Emp empLogin = empMapper.getUsernameAndPassword(emp);

        if(empLogin != null) {
            // 2. 登录成功，准备要存入 Token 的数据
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("id", empLogin.getId());
            dataMap.put("username", empLogin.getUsername());

            // 3. 调用工具类，把数据“封印”进 JWT 字符串
            String jwt = JwtUtils.generateJwt(dataMap);

            // 4. 把 Token 和用户信息一起封装成对象返回给前端
            LoginInfo loginInfo = new LoginInfo(empLogin.getId(), empLogin.getUsername(), empLogin.getName(), jwt);
            return loginInfo;
        }
        return null;
    }


}

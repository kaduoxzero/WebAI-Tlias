package com.kaduox.service;

import com.kaduox.pojo.Emp;
import com.kaduox.pojo.EmpQueryParam;
import com.kaduox.pojo.LoginInfo;
import com.kaduox.pojo.PageResult;

import java.util.List;


public interface EmpService {


    /**
     * 分页查询员工数据
     *  page 页码
     *  pageSize 每页记录数
     */

    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /**
     * 新增员工数据
     */
    void save(Emp emp) throws Exception;

    void deleteByIds(List<Integer> ids);

    Emp getInfo(Integer id);

    void update(Emp emp);

    List<Emp> list();

    /**
     * 登录
     */
    LoginInfo login(Emp emp);
}

package com.kaduox.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kaduox.pojo.Clazz;
import com.kaduox.mapper.ClazzMapper;
import com.kaduox.pojo.ClazzQueryParam;
import com.kaduox.pojo.Emp;
import com.kaduox.pojo.PageResult;
import com.kaduox.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 班级 Service 实现
 */
@Slf4j
@Service
public class ClazzServiceImpl implements ClazzService {


    @Autowired
    private ClazzMapper clazzMapper;

    /**
     * 分页查询班级数据
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public PageResult<Clazz> getList(ClazzQueryParam clazzQueryParam) {
        //获取分页参数
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
        //获取列表数据
        List<Clazz> list = clazzMapper.list(clazzQueryParam);
        //获取分页结果
        Page<Clazz> clazzPage = (Page<Clazz>) list;
        //返回结果
        return new PageResult<Clazz>(clazzPage.getTotal(),clazzPage.getResult());
    }


    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void deleteByIds(List<Integer> idList) {
        clazzMapper.deleteByIds(idList);
    }

    //事务管理
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void save(Clazz clazz){
        //添加时间
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDate.now());
        clazzMapper.insert(clazz);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Clazz getById(Integer id) {
        log.info("查询班级, id: {}", id);
        Clazz clazz = clazzMapper.getById(id);
        log.info("查询结果: {}", clazz);
        return clazzMapper.getById(id);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDate.now());
        clazzMapper.update(clazz);
        log.info("更新班级, 参数: {}", clazz);

    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public List<Clazz> list() {
        List<Clazz> clazzList = clazzMapper.list( null);
        return clazzList;
    }

}

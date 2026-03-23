package com.kaduox.controller;

import com.kaduox.anno.LogOperation;
import com.kaduox.pojo.Clazz;
import com.kaduox.pojo.ClazzQueryParam;
import com.kaduox.pojo.PageResult;
import com.kaduox.pojo.Result;
import com.kaduox.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 班级管理控制器
 */
@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam){
        log.info("查询员工列表, 参数: {}", clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.getList(clazzQueryParam);
        return Result.success(pageResult);
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable String ids){
        log.info("批量删除班级, ids: {}", ids);
        List<Integer> idList = Arrays.asList(ids.split(",")).stream().map(id -> Integer.parseInt(id)).toList();
        clazzService.deleteByIds(idList);
        return Result.success();
    }

    @LogOperation
    @PostMapping
    public Result save(@RequestBody Clazz clazz){
        clazzService.save(clazz);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("查询班级, id: {}", id);
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }

    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("更新班级, 参数: {}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list(){
        log.info("查询班级列表");
        List<Clazz> clazzList = clazzService.list();
        return Result.success(clazzList);
    }

}
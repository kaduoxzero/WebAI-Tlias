package com.kaduox.controller;

import com.kaduox.pojo.Emp;
import com.kaduox.pojo.EmpQueryParam;
import com.kaduox.pojo.PageResult;
import com.kaduox.pojo.Result;
import com.kaduox.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping
    public Result page(EmpQueryParam empQueryParam){
        log.info("查询员工列表, 参数: {}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    @PostMapping
    public  Result save(@RequestBody Emp emp) throws Exception{
        log.info("新增员工, 参数: {}", emp);
        empService.save(emp);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(@RequestParam List< Integer> ids){
        log.info("删除员工, ids: {}", Arrays.asList(ids));
        empService.deleteByIds(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("查询员工, id: {}", id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("更新员工, 参数: {}", emp);
        empService.update(emp);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list(){
        log.info("查询员工列表");
        List<Emp> empList = empService.list();
        return Result.success(empList);
    }




}

package com.kaduox.controller;

import com.kaduox.pojo.PageResult;
import com.kaduox.pojo.Result;
import com.kaduox.pojo.Student;
import com.kaduox.pojo.StudentQueryParam;
import com.kaduox.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // 分页查询
    @GetMapping
    public Result page(StudentQueryParam studentQueryParam){
        log.info("查询学员列表, 参数: {}", studentQueryParam);
        PageResult<Student> studentList = studentService.page(studentQueryParam);
        return Result.success(studentList);
    }

    // 新增学员
    @PostMapping
    public Result save(@RequestBody Student student){
        log.info("新增学员, 参数: {}", student);
        studentService.save(student);
        return Result.success();
    }

    // 修改学员
    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("修改学员信息, 参数: {}", student);
        studentService.update(student);
        return Result.success();
    }

    // 违纪处理
    @PutMapping("/violation/{id}/{score}")
    public Result handleViolation(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("学员违纪处理, id: {}, 分数: {}", id, score);
        studentService.handleViolation(id, score);
        return Result.success();
    }

    // 批量删除
    @DeleteMapping("/{ids}")
    public Result deleteByIds(@PathVariable List<Integer> ids){
        log.info("批量删除学员, ids: {}", ids);
        studentService.deleteByIds(ids);
        return Result.success();
    }

    // 根据ID查询详情
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("查询学员详情, id: {}", id);
        Student student = studentService.getById(id);
        return Result.success(student);
    }
}
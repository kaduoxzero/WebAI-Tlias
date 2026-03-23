package com.kaduox.controller;

import com.kaduox.pojo.PageResult;
import com.kaduox.pojo.Result;
import com.kaduox.service.OperateLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/log")
public class OperateLogController {

    @Autowired
    private OperateLogService operateLogService;

    // 对应前端日志页面的分页查询请求
    @GetMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("分页查询操作日志: 页码={}, 每页大小={}", page, pageSize);
        PageResult pageBean = operateLogService.page(page, pageSize);
        return Result.success(pageBean);
    }
}
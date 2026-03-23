package com.kaduox.exception;

import com.kaduox.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理静态资源未找到异常 (Spring Boot 3 特有)
     * 过滤 favicon.ico 和 chrome-extension 产生的噪音
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public Result handleResourceNotFoundException(NoResourceFoundException e) {
        String resourcePath = e.getResourcePath();

        // 针对 favicon 和 插件请求，仅记录 debug 级别日志，且不视为系统错误
        if (resourcePath.contains("favicon.ico") || resourcePath.contains("chrome-extension")) {
            log.debug("忽略的资源请求: {}", resourcePath);
            return Result.error("Resource not found"); // 返回 404 语义的错误
        }

        log.warn("静态资源不存在: {}", resourcePath);
        return Result.error("请求的资源不存在");
    }

    /**
     * 处理数据库唯一键冲突
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        // ... (保持之前的解析逻辑)
        String msg = e.getMostSpecificCause().getMessage();
        if (msg != null && msg.contains("Duplicate entry")) {
            return parseDuplicateMessage(msg);
        }
        return Result.error("数据重复，请检查");
    }

    /**
     * 兜底异常处理
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("系统运行异常: ", e);
        return Result.error("对不起，系统繁忙，请联系管理员");
    }

    private Result parseDuplicateMessage(String msg) {
        Pattern pattern = Pattern.compile("Duplicate entry '(.*?)' for key '(.*?)'");
        Matcher matcher = pattern.matcher(msg);
        if (matcher.find()) {
            String value = matcher.group(1);
            String key = matcher.group(2);
            if (key.contains("uk_name")) return Result.error("名称 '" + value + "' 已存在");
            return Result.error("字段值 '" + value + "' 重复");
        }
        return Result.error("数据已存在");
    }
}
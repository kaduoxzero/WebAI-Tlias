package com.kaduox.interceptor;

import com.kaduox.utils.CurrentHolder;
import com.kaduox.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 获取请求url
        String url = request.getRequestURL().toString();

        // 2. 判断请求url中是否包含login，如果包含，说明是登录操作，放行
        if (url.contains("login")) {
            log.info("登录请求, 直接放行");
            return true;
        }

        // 3. 获取请求头中的令牌（token）
        String jwt = request.getHeader("token");

        // 4. 判断令牌是否存在
        if (!StringUtils.hasLength(jwt)) {
            log.info("获取到jwt令牌为空, 返回401错误");
            response.setStatus(401); // 设置状态码为未授权
            return false;
        }

        // 5. 解析token
        try {
            // 解析获取 Claims 对象
            Claims claims = JwtUtils.parseJWT(jwt);

            // 从 claims 中获取登录时存入的 "id"
            // 注意：这里的 "id" 必须和你登录生成 Token 时 dataMap.put("id", ...) 里的 Key 一致
            Integer empId = (Integer) claims.get("id");

            // 【核心修复】：将解析出来的用户ID存入 ThreadLocal 中
            // 这样后面的 AOP 切面通过 CurrentHolder.getCurrentId() 就能拿到值了
            CurrentHolder.setCurrentId(empId);

            log.info("令牌合法, 用户ID: {}, 放行", empId);
        } catch (Exception e) {
            log.error("解析令牌失败: {}, 返回401错误", e.getMessage());
            response.setStatus(401);
            return false;
        }

        // 6. 放行
        return true;
    }

    /**
     * 在整个请求结束（视图渲染完毕）后执行
     * 主要用于资源清理
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 【核心优化】：请求结束后清理 ThreadLocal
        // 防止由于线程复用导致的数据错乱和内存泄漏
        CurrentHolder.remove();
        log.info("清理 ThreadLocal 变量");
    }
}
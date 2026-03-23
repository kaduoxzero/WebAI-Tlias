# Tlias 智能教务管理系统 (Tlias Management System)

[![Java](https://img.shields.io/badge/Java-11-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/SpringBoot-2.7.x-green.svg)](https://spring.io/projects/spring-boot)
[![Vue](https://img.shields.io/badge/Vue-3.x-brightgreen.svg)](https://vuejs.org/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)](https://www.mysql.com/)

## 📖 项目简介
Tlias 是一款专为中小型教育机构设计的教务管理系统。项目采用**前后端分离**架构，涵盖了教务管理中核心的人事管理、部门配置、数据统计等功能。

该项目是我在学习 Java Web 开发过程中的核心练手项目，完整实现了从底层数据库设计到前端页面交互的全过程。

## 🌟 核心功能
- **安全登录**：集成 JWT (JSON Web Token) 实现用户身份校验与登录拦截。
- **部门管理**：支持部门结构的增删改查。
- **员工管理**：
    - 完整的分页查询与多条件筛选。
    - 员工信息的动态维护（新增、修改、禁用）。
    - 批量删除功能。
- **文件存储**：集成**阿里云 OSS**，实现员工头像与资料的云端异步上传。
- **操作日志**：基于 **Spring AOP** 实现自定义注解，全自动记录用户的关键操作行为。
- **数据可视化**：前端集成 Echarts 简单展示部门人员分布（可选功能）。

## 🛠️ 技术栈

### 后端 (webAI-java)
- **核心框架**：Spring Boot 2.7
- **持久层**：MyBatis / MyBatis-Plus
- **数据库**：MySQL 8.0
- **工具类**：Lombok, Maven
- **安全校验**：JWT, 拦截器 (Interceptor)
- **切面编程**：Spring AOP
- **第三方服务**：阿里云 OSS SDK

### 前端 (webAI-vue)
- **框架**：Vue 3
- **组件库**：Element Plus
- **网络请求**：Axios
- **路由管理**：Vue Router

## 📂 项目结构
```text
.
├── webAI-java          # 后端 Spring Boot 源码
│   ├── tlias-parent    # 父工程
│   ├── tlias-utils     # 通用工具类 (JWT, OSS等)
│   ├── tlias-pojo      # 实体类
│   └── tlias-web-management # 业务逻辑核心模块
├── webAI-vue           # 前端 Vue 源码
└── README.md           # 项目说明文档
```
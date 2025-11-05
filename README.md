# Studying

基于 Spring Boot 3.5.7（JDK 17）搭建的模块化脚手架，参考了 [RuoYi-Vue-Plus](https://github.com/dromara/RuoYi-Vue-Plus) 的工程结构与编码风格，集成了 MyBatis-Plus、Redisson 以及兼容 AWS S3 协议的对象存储能力。

## 项目结构

```
studying
├─ studying-admin                         # 后台管理端（端口 8080）
│  ├─ src/main/java/com/jenson/studying    # 启动入口、Servlet 初始化
│  ├─ src/main/java/com/jenson/studying/web
│  │  └─ controller/monitor/HealthController.java
│  └─ src/main/resources                   # 配置文件 & 国际化
│     ├─ application.yml                   # 总配置（默认启用 dev）
│     ├─ application-dev.yml               # 开发环境配置
│     ├─ application-prod.yml              # 生产环境配置
│     ├─ i18n/messages.properties          # 国际化资源
│     └─ logback.xml                       # 日志配置
├─ studying-framework                     # 框架层，聚合通用能力
│  └─ pom.xml                              # 引入 common 模块
├─ studying-common                        # 公共组件层
│  ├─ studying-common-bom                  # 依赖版本管理 BOM
│  ├─ studying-common-core                 # 核心常量、基础响应（AjaxResult 等）
│  ├─ studying-common-datasource           # 数据源 & MyBatis-Plus 配置
│  ├─ studying-common-log                  # 日志扩展
│  ├─ studying-common-oss                  # OSS 客户端封装（S3 协议）
│  ├─ studying-common-redis                # Redisson 自动配置
│  ├─ studying-common-security             # 安全模块预留
│  ├─ studying-common-sensitive            # 数据脱敏模块预留
│  ├─ studying-common-web                  # Web 层通用配置（LocaleResolver 等）
│  └─ ...                                  # 其他 common 子模块占位
├─ studying-modules                       # 业务模块层
│  └─ studying-system                      # 系统核心模块（示例用户表）
├─ script                                 # 脚本、Docker、SQL 占位
│  ├─ bin/.gitkeep
│  ├─ docker/.gitkeep
│  └─ sql/.gitkeep
├─ .editorconfig                          # 编辑器统一规范
├─ pom.xml                                # 根 POM，聚合所有模块
└─ README.md                              # 项目说明
```

## 模块亮点

- **studying-common-bom**：统一管理三方依赖版本（MyBatis-Plus 3.5.14、Redisson 3.51.0、AWS SDK 2.25.47、MySQL 驱动 8.4.0 等）。
- **studying-common-core**：提供 `AjaxResult`、`BaseController`、`HttpStatus` 等基础设施，便于统一返回风格。
- **studying-common-datasource**：自动装配 MyBatis-Plus 插件（含 MySQL 分页拦截器）。
- **studying-common-redis**：基于 `redisson.*` 配置项自动创建 `RedissonClient`，默认关闭，可按需打开。
- **studying-common-oss**：封装 AWS SDK S3 客户端，提供 `OssService` 生成对象访问地址。
- **studying-framework**：聚合常用 common 依赖，对上层模块只暴露一个依赖入口。
- **studying-system**：示例系统模块，内含 `SysUser` 实体及 `SysUserMapper`。
- **studying-admin**：入口应用，采用 RuoYi 风格的分层结构与响应规范。

## 本地运行

### 环境要求

- JDK 17
- Maven 3.9+
- MySQL 5.7/8.0（更新 `application-*.yml` 中的连接信息）
- 可选：Redis（启用 Redisson 时）
- 可选：兼容 S3 协议的对象存储（如 MinIO）

### 构建全量模块

```bash
mvn clean install
```

### 启动后台管理（默认 dev 配置）

```bash
cd studying-admin
mvn spring-boot:run
```

应用启动后监听 **http://localhost:8080**，示例接口：

- `GET /monitor/health` —— 返回健康状态、时间戳
- `GET /monitor/health/oss-url?objectKey=<key>` —— 生成示例对象访问地址（返回 `AjaxResult`）

### 打包运行

```bash
cd studying-admin
mvn clean package
java -jar target/studying-admin-0.0.1-SNAPSHOT.jar
```

## 配置说明

- `application.yml`：启用指定 Profile、设置消息国际化、暴露健康检查端点。
- `application-dev.yml`：MySQL、Redis、OSS 默认开发配置（Redisson 默认关闭）。
- `application-prod.yml`：生产环境占位配置，支持通过环境变量覆盖凭证。
- `logback.xml`：定义控制台与滚动文件日志输出。

## 编码规范

- 所有返回类统一使用 `AjaxResult`，控制器可继承 `BaseController` 获得 `success/error` 快捷方法。
- MyBatis-Plus 默认开启驼峰映射，实体类示例遵循 `Sys` 前缀命名。
- 国际化资源位于 `src/main/resources/i18n`，默认语种为简体中文，可通过 `Accept-Language` 切换。

## 后续扩展

- 在 `studying-common` 目录下补充更多公共能力（如缓存、消息队列、监控告警等）。
- 在 `studying-modules` 中拆分业务子系统，结合 `studying-framework` 快速装配到 `studying-admin`。
- 若需要进一步贴合 RuoYi-Vue-Plus，可在此基础上补充权限体系、租户、审计日志等高级特性。

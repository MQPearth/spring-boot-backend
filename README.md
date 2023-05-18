## Spring-Boot-Backend

> Demo

* SpringBoot 3
* SpringCloud
* MyBatis-Plus
* Redisson
* Nacos 2.2.2
* SkyWalking

### 模块

* parent
* base 禁止依赖二方库, 只能依赖三方库
* user-po user数据库对应的实体类及相关常量
* mp-boot-starter 只允许被 *-web 的模块依赖
* api-boot-starter 只允许被 *-api 的模块依赖
* service-boot-starter 只允许被 *-web 的模块依赖

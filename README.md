## Spring-Boot-Backend

> Cloud Native Demo

* SpringBoot3
* SpringCloud Alibaba
* MyBatis-Plus
* Redisson
* Nacos
* SkyWalking
* Docker
* ELK
* Kubernetes
* Prometheus
* Grafana
* Jenkins

### 模块

* parent
* base 禁止依赖二方库, 只能依赖三方库
* user-po user数据库对应的实体类及相关常量
* mp-boot-starter 只允许被 *-web 的模块依赖
* api-boot-starter 只允许被 *-api 的模块依赖
* service-boot-starter 只允许被 *-web 的模块依赖
* 子模块禁止定义版本号, 由一个模块统一定义所有版本号. 避免依赖混乱
* 每一个模块更建议使用一个仓库单独管理, 做权限控制. 对外的接口将jar包deploy到项目的私仓. 避免开发随意改动基础库代码

### 数据库

* 类型值, 比如审核状态之类的, 建议设置100->200, 保证一定的步长, 如果后续有业务变动, 增加了中间的状态, 新状态值可以是110/120,
  语义更清晰



[教程地址](https://blog.22xcode.com/article/537114869967618049)

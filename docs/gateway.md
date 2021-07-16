##spring cloud之gateway服务模块——*分布式路由网关*
spring cloud gateway提供了一种简单有效的方式来对请求进行路由。
并提供了路由(*route*)，断言(*predicate*)，过滤(*filter*)等功能，
同时完成对路由的动态管理功能。

###gateway架构设计
分布式路由网关主要包含了gateway-admin以及gateway-web两类服务，
gateway-admin负责对路由的管理、通知，gateway-web负责对请求的处理，
是一种manager-executor的关系模式。

```puml
skinparam monochrome true
node admin

node web1
node web2

admin -down-> web1 :指令
admin -down-> web2 :指令

web1 -up-> admin :路由加载请求
web2 -up-> admin :路由加载请求
```

###gateway启动
gateway-admin和gateway-web启动的先后顺序没有要求，
gateway-admin服务在启动成功后会将最新的路由信息同步至所有的gateway-web服务当中
gateway-web服务启动后会请求gateway-admin获取最新的路由信息


ps:gateway服务模块整体基于nacos服务注册中心启动的，使用前请保证nacos服务的可用
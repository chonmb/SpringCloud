# SpringCloud

Spring Cloud 微服务开发
> spring cloud 微服务开发学习

## 模块介绍

|模块|模块名|开发状态|备注|
|----|----|-----|-----|
|数据库|mysql|-||
|缓存|redis|-||
|消息队列|rabbitMQ|-||
|注册与配置中心|nacos|-|服务注册、配置中心|
|服务监控|monitor|已完成|链路追踪、服务监控|
|权限管理|auth|未开发||
|网关|gateway|已完成|[详细文档](./docs/gateway.md)|

## Gateway

分布式网关路由

路由模块基于`spring-cloud-gateway`开发，能够实现对网关路由的分布式部署以及动态更新，该模块主要由`admin`和`web`两类服务形成`manager-executor`的关系模式。
## spring cloud项目组件——rabbitmq

### RabbitMQ简介
AMQP，即Advanced Message Queuing Protocol，高级消息队列协议，
是应用层协议的一个开放标准，为面向消息的中间件设计。
消息中间件主要用于组件之间的解耦，消息的发送者无需知道消息使用者的存在，反之亦然。
AMQP的主要特征是面向消息、队列、路由（包括点对点和发布/订阅）、可靠性、安全。
RabbitMQ是一个开源的AMQP实现，服务器端用Erlang语言编写，支持多种客户端，
如：Python、Ruby、.NET、Java、JMS、C、PHP、ActionScript、XMPP、STOMP等，支持AJAX。
用于在分布式系统中存储转发消息，在易用性、扩展性、高可用性等方面表现不俗。

### rabbitmq组件功能
使用RabbitMQ作为服务的中间件进行开发前，不可避免的需要了解ConnectionFactory,Connection,Channel,Exchange,Queue等
相关对象的功能和定义进行学习，同时在开发过程中需要注意对一些对象的状态进行记录维护。
rabbitmq组件在RabbitMQ提供的API基础上做了一层封装，完成对RabbitMQ一些对象的生命周期进行维护，
开发者只需要简单的一些配置操作就可以完成对RabbitMQ的使用，减少RabbitMQ的开发使用成本。

### rabbitmq组件的使用
+ rabbitmq的配置  
开发者需要在配置清单中对rabbitmq的基本信息进行配置  

```yaml
spring:
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest
```

+ rabbitmq组件开启  
开发者需配置开启rabbitmq组件服务

```java
@SpringBootApplication
@EnableSpringCloudRabbitmq
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
```

+ rabbitmq的使用  
发送消息  
开发者在返回类型为*String*的方法上添加注解@*SendSingleRabbitmqMessage*/@*SendPublicRabbitmqMessage*
这样消息就会发送到指定的rabbitmq队列上
```java
public class SendMessage {
    @SendPublishRabbitmqMessage(channel=CommonConstant.GATEWAY_RABBITMQ_NAME)
    public String send(String message) {
        return message;
    }
}
```
接收消息  
开发者需要实现*BaseObserver*并将对象实例托管到IOC容器当中,这样当对应的channel接收到信息时，就会执行相应的业务逻辑
```java
public class RouteOperationEvent extends BasePublicMessageObserver {
    @Override
    public void execute(String message) {
        //业务逻辑
    }

    @Override
    public String getChannelName() {
        return channelName;
    }
}
```


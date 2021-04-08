# dubbo-springboot-zk1-direct
dubbo的直接调用模式
SpringBoot与Dubbo整合的几种方式
https://blog.csdn.net/J080624/article/details/84033579

总代码来源：https://github.com/JanusJ/SpringBoot
zookeeper使用的是：
E:\中-java\0_0学习\学习：dubbo\code-RPC-dubbo\apache-zookeeper-3.7.0-bin


第三种：SpringBoot整合Dubbo和Zookeeper升级版
https://blog.csdn.net/J080624/article/details/83900551

分布式架构与Dubbo基础入门与实践一文中初步介绍了分布式架构并使用xml配置方式进行了Dubbo和Zookeeper实践。分布式应用简单入门及SpringBoot整合Dubbo+Zookeeper一文中使用SpringBoot整合了Dubbo和Zookeeper但是并未抽取公共API项目。本文是上述两个项目的升级版。

第二种：分布式架构与Dubbo基础入门与实践
https://blog.csdn.net/J080624/article/details/83823713

第一种：分布式应用简单入门及SpringBoot整合Dubbo+Zookeeper
https://blog.csdn.net/J080624/article/details/80996235


详细篇：
######################################################################
第三种：SpringBoot整合Dubbo和Zookeeper升级版
https://blog.csdn.net/J080624/article/details/83900551

分布式架构与Dubbo基础入门与实践一文中初步介绍了分布式架构并使用xml配置方式进行了Dubbo和Zookeeper实践。分布式应用简单入门及SpringBoot整合Dubbo+Zookeeper一文中使用SpringBoot整合了Dubbo和Zookeeper但是并未抽取公共API项目。本文是上述两个项目的升级版。

spring的xml改为springboot方式：

Dubbo的@Service注解暴露服务
在主程序启动类中使用@EnableDubbo注解以开启基于注解的dubbo功能
由于消费者是一个web工程，使用浏览器发送请求进行测试
http://localhost:8081/initOrder?uid=1

########################
本机：
dubbo-admin和dubbo-monitor-simple是基于第二种方式部署
代码来源：E:\中-java\0_0学习\学习：dubbo\code-RPC-dubbo2\From-SpringBoot-JanusJ\
springboot+dubbo+zookeeper-new
消费者：boot-order-service-consumer
生产者：boot-user-service-provider
生产者：boot-user-service-provider-20881
生产者：boot-user-service-provider-20882
公共接口：gmall-interface

启动消费者：boot-order-service-consumer，BootOrderServiceConsumerApplication
启动生产者：boot-user-service-provider，BootUserServiceProviderApplication
启动生产者：boot-user-service-provider-20881，BootUserServiceProvider20881Application
启动生产者：boot-user-service-provider-20882，BootUserServiceProvider20882Application
已经使用了MyDubboConfig 消费者和第一个生产者的 application.properties、consumer.xml、dubbo.properties、provider.xml都注释掉
只留下消费者的端口配置application.properties 是 server.port=8888

dubbo权重可以设定，一样的时候就可以平均调用
@Service(weight=200)


消费者是web：使用浏览器测试：重复调用即可
#http://localhost:8081/initOrder?uid=1
#被nexus占用，
#server.port=8888
http://localhost:8888/initOrder?uid=1

dubbo-admin为；注册中心
http://localhost:7001/
用户：root
密码：root
search中搜索：com.web.gmall.service.UserService 查看生产者
* 查看所有
http://localhost:7001/governance/services?keyword=*


dubbo-monitor为；监控中心
http://localhost:8080/services.html
查看图示调用过程：
http://localhost:8080/charts.html?service=com.web.gmall.service.UserService




######################################################################
第二种：分布式架构与Dubbo基础入门与实践
https://blog.csdn.net/J080624/article/details/83823713


##################################
dubbo-admin中间件部分：
dubbo本身并不是一个服务软件。它其实就是一个jar(2.6之后为jar,之前为war需要在Tomcat下运行)包能够帮你的java程序连接到zookeeper，并利用zookeeper消费、提供服务。所以你不用在Linux上启动什么dubbo服务。

源码：
https://github.com/apache/dubbo
中国代理：
https://codechina.csdn.net/mirrors/apache/incubator-dubbo?utm_source=csdn_github_accelerator

安装步骤如下：
进入Dubbo的GitHub项目项目地址；
在最下方Dubbo eco system中点击进入Dubbo-OPS；
Download ZIP并解压到本地

文章时间是2018年，现在是2021年
代码对不上，需要切换到分支 2.6.x
就能在此页面中找到 Dubbo ops
点击进入，跟文章中也不一样，需要切换分支：master
最终代码：
分支：master
https://github.com/apache/dubbo-admin.git
中国代理：
https://codechina.csdn.net/mirrors/apache/incubator-dubbo-ops.git


进入目录，修改dubbo-admin配置
修改 src\main\resources\application.properties 指定zookeeper地址：
dubbo.registry.address=zookeeper://127.0.0.1:2181
打包dubbo-admin mvn clean package -Dmaven.test.skip=true
使用命令运行jar : java -jar dubbo-admin-0.0.1-SNAPSHOT.jar
浏览器访问(http://localhost:7001/)默认用户名密码root/root

自己是：
E:\中-java\0_0学习\学习：dubbo\code-RPC-dubbo2\incubator-dubbo-ops-master
\dubbo-admin
\src\main\resources\application.properties
使用默认：
dubbo.registry.address=zookeeper://127.0.0.1:2181
端口使用默认：server.port=7001
使用idea，进行修改和启动 ，打包后生成：dubbo-admin-0.0.1-SNAPSHOT.jar
启动： com.alibaba.dubboadmin.DubboAdminApplication
http://localhost:7001/
用户：root
密码：root

##################################
代码部分：
代码：
来源：https://github.com/JanusJ/SpringBoot
中的 From-SpringBoot-JanusJ/
springboot+dubbo+zookeeper-new
接口：/gmall-interface
消费者：/order-service-consumer
生产者：/user-service-provider

中间件zookeeper：
使用的zk为：E:\中-java\0_0学习\学习：dubbo\code-RPC-dubbo\apache-zookeeper-3.7.0-bin\conf
zookeeper最近的版本中有个内嵌的管理控制台是通过jetty启动，也会占用8080 端口。
在zoo.cfg中增加admin.serverPort=没有被占用的端口号  admin.serverPort=9999

pom引用：
<!-- 注册中心使用的是zookeeper，引入操作zookeeper的客户端端 -->
dubbo 2.6以前的版本引入zkclient操作zookeeper ，dubbo 2.6及以后的版本引入curator操作zookeeper。
下面两个zk客户端根据dubbo版本2选1即可：
<dependency>
	<groupId>com.101tec</groupId>
	<artifactId>zkclient</artifactId>
	<version>0.10</version>
</dependency>
<!-- curator-framework -->
<dependency>
	<groupId>org.apache.curator</groupId>
	<artifactId>curator-framework</artifactId>
	<version>2.12.0</version>
</dependency>
————————————————

生产者运行：user-service-provider/MainApplication

消费者运行：order-service-consumer/MainApplication
重复调用消费者就能看到全部流程
生产者的调用是每个实现一次调用

在保证Zookeeper服务端，Dubbo Admin和服务器提供者启动并正常运行前提下，运行该消费者主类，将会从服务提供者那里获取数据！

##################################
【6】安装Monitor监控中心
文中用的是：dubbo-monitor-simple-2.0.0


本机修改；
idea中直接运行和操作：dubbo-monitor-simple
E:\中-java\0_0学习\学习：dubbo\code-RPC-dubbo2\incubator-dubbo-ops-master\dubbo-monitor-simple\src\main\resources\conf
文件：dubbo.properties
确认：这里注意windows的zk默认的是8080，前面直接把他改为9999
	// Zookeeper注册中心地址
	dubbo.registry.address=zookeeper://127.0.0.1:2181
	//监控中心于其他服务通信端口
	dubbo.protocol.port=7070
	//监控中心web页面访问端口
	dubbo.jetty.port=8080
	
打包跳过检查
clean package -Dmaven.test.skip=true -Dmaven.javadoc.skip=true

运行：com.alibaba.dubbo.monitor.simple
MonitorStarter

页面：
http://localhost:8080/services.html


详细：
################
Simple Monitor 挂掉不会影响到 Consumer 和 Provider 之间的调用，所以用于生产环境不会有风险。

Simple Monitor 采用磁盘存储统计信息，请注意安装机器的磁盘限制，如果要集群，建议用mount共享磁盘。
————————————————
① 安全并运行Monitor

和安装Dubbo Admin时步骤类似，进入incubator-dubbo-ops-master\dubbo-monitor-simple目录下，使用maven命令进行打包：

mvn clean package -Dmaven.test.skip=true
————————————————
这里运行jar前，需要查看一下配置文件。将dubbo-monitor-simple-2.0.0-assembly.tar.gz复制并解压到指定文件夹下，查看其conf目录下的配置文件：
修改：E:\中-java\0_0学习\学习：dubbo\code-RPC-dubbo2\incubator-dubbo-ops-master\dubbo-monitor-simple\src\main\resources\conf
文件：dubbo.properties
// Zookeeper注册中心地址
dubbo.registry.address=zookeeper://127.0.0.1:2181
//监控中心于其他服务通信端口
dubbo.protocol.port=7070
//监控中心web页面访问端口
dubbo.jetty.port=8080
之后，进入dubbo-monitor-simple-2.0.0\assembly.bin目录，运行start.bat。
进入浏览器查看监控中心：
localhost:8080/services.html
此时监控中心还没有消费者。
————————————————

② 修改服务提供者/消费者配置
在consumer.xml中添加如下配置：
<dubbo:monitor protocol="registry"></dubbo:monitor>
在provider.xml中如下配置：
<dubbo:monitor address="127.0.0.1:7070"></dubbo:monitor>
这两种有什么区别？参考官方文档：
————————————————
重新启动provider和consumer，提供者向消费者索取服务再次查看Monitor，将会发现com.alibaba.dubbo.monitor.MonitorService对应的consumer有两个：provider和consumer都是MonitorService的消费者！
————————————————






######################################################################
第一种：
分布式应用简单入门及SpringBoot整合Dubbo+Zookeeper
https://blog.csdn.net/J080624/article/details/80996235


来源：https://github.com/JanusJ/SpringBoot
中的 From-SpringBoot-JanusJ/
springboot+dubbo+zookeeper
使用的zk为：E:\中-java\0_0学习\学习：dubbo\code-RPC-dubbo\apache-zookeeper-3.7.0-bin\conf

####################################
启动生产者：启动springboot项目：provider-ticket 项目中的 ProviderApplication
	用dubbo协议在20880端口暴露服务
	默认启动使用的是：8080 端口，不能出现占用情况，否则不能启动
	zookeeper最近的版本中有个内嵌的管理控制台是通过jetty启动，也会占用8080 端口。
	在zoo.cfg中增加admin.serverPort=没有被占用的端口号  admin.serverPort=9999
	
####################################
启动消费者：运行class的test方法：，不需要启动 ConsumerApplication
consumer-user 项目中的 ConsumerApplicationTests

注意，Dubbo是面向接口的，impl包及实现类不需要！
// dubbo： 远程引用，按照全类名从注册中心寻找
@Reference
TicketService ticketService;

保持服务提供者正常启动状态，然后服务消费者测试结果如下：
即，通过Zookeeper，服务消费者获取了另一个工程-服务提供者提供的服务！

一个工程引用另一个工程的服务，其实就是远程调用–RPC，常见的如WebService。Dubbo就是基于RPC远程调用实现服务之间的通信！！！

但是本篇博文只是简单实践，实际应用中不可能在消费者下面建立服务提供者同级目录并拷贝接口的。通常是将服务接口抽离到公共接口项目中，消费者和服务提供者依赖该接口项目。





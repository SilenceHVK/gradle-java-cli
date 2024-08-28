# gradle-java-cli

🔥🪛 A Gradle Java project scaffolding（一个 gradle java项目脚手架），基于 JDK 21 + Gradle 8.5。

```
.
├── LICENSE
├── README.md
├── application
│   ├── build.gradle  // gradle 子项目配置
│   └── src
│       └── main
│           ├── generated
│           ├── java
│           │   └── me
│           │       └── hvkcoder
│           │           └── application
│           │               └── NettyServer.java
│           └── resources
│               └── application.properties
├── bin
│   └── service.sh          // 应用服务启动脚本
├── build.gradle           // gradle 父级配置
├── dependencies.gradle   //  gradle 依赖
└── settings.gradle
```

# 使用方法
1. 子项目 build.gradle 配置
```groovy
ext {
	application = [
		name     : "netty-server",  // 服务名称
		manifestVersion: '1.0',	// 版本号
		mainClass: "me.hvkcoder.application.NettyServer" // 服务主类
	]
}
```
2. 在子项目的根目录下执行打包指令
```bash
gradle clean buildJar
```
3. 打包后的目录在 build/子项目名称 下，如 build/application
```
.
├── bin
│   └── service.sh   // 项目启动脚本
├── conf
│   └── application.properties  // 项目配置文件
├── lib  		       // 项目依赖的第三方 jar 包
│   ├── lombok-1.18.30.jar
│   ├── netty-all-4.1.112.Final.jar
│   ├── netty-buffer-4.1.112.Final.jar
│   ├── netty-codec-4.1.112.Final.jar
│   ├── netty-codec-dns-4.1.112.Final.jar
│   ├── netty-codec-haproxy-4.1.112.Final.jar
│   ├── netty-codec-http-4.1.112.Final.jar
│   ├── netty-codec-http2-4.1.112.Final.jar
│   ├── netty-codec-memcache-4.1.112.Final.jar
│   ├── netty-codec-mqtt-4.1.112.Final.jar
│   ├── netty-codec-redis-4.1.112.Final.jar
│   ├── netty-codec-smtp-4.1.112.Final.jar
│   ├── netty-codec-socks-4.1.112.Final.jar
│   ├── netty-codec-stomp-4.1.112.Final.jar
│   ├── netty-codec-xml-4.1.112.Final.jar
│   ├── netty-common-4.1.112.Final.jar
│   ├── netty-handler-4.1.112.Final.jar
│   ├── netty-handler-proxy-4.1.112.Final.jar
│   ├── netty-handler-ssl-ocsp-4.1.112.Final.jar
│   ├── netty-resolver-4.1.112.Final.jar
│   ├── netty-resolver-dns-4.1.112.Final.jar
│   ├── netty-resolver-dns-classes-macos-4.1.112.Final.jar
│   ├── netty-transport-4.1.112.Final.jar
│   ├── netty-transport-classes-epoll-4.1.112.Final.jar
│   ├── netty-transport-classes-kqueue-4.1.112.Final.jar
│   ├── netty-transport-native-unix-common-4.1.112.Final.jar
│   ├── netty-transport-rxtx-4.1.112.Final.jar
│   ├── netty-transport-sctp-4.1.112.Final.jar
│   └── netty-transport-udt-4.1.112.Final.jar
└── netty-server-1.0.jar  // 项目 Jar 包，不包含第三方依赖
```
4. 通过 项目启动脚本 启动项目

```bash
./bin/service.sh
USAGE: ./bin/service.sh [-daemon] [-name servicename] [-loggc] [start|stop|restart]

-daemon 表示以守护进程的方式启动
```

# 项目简介
闪灵 Sharine 是一款主要由个人开发并维护的基于 Web 的前后端分离架构短视频平台。用户可在平台自由浏览视频，根据兴趣选择分区，查看分区热度以及用户个人最常浏览分区排行榜。用户也可以与视频进行点赞，收藏，评论等交互动作。用户注册后可以拥有专属的订阅频道，在关注的 UP 主更新时订阅频道也会第一时间收到更新消息，登录后的用户也可以自由进行视频投稿。

建议前往 **docs** 查看完整文档  
Demo 视频地址：
# 线上体验

>线路屏蔽海外！！！  
部分视频无法浏览属正常现象，测试数据中的链接来源于互联网，并不保证其链接有效性
> 
http://ipv4.rinkore.com:15001  
测试账号 3401286177@qq.com  
测试密码 mikkoayaka  
当然您也可以选择注册一个账号体验一下  
# 自行部署
## 环境准备
> 本项目提供了测试用数据集，请访问 Github 仓库路径 **Sharine/server/src/main/resources/Sharine.sql** 进行下载
- JDK 17 及以上(测试版本：Oracle JDK 17)
- 支持 JDBC 的 SQL 数据库环境(测试版本：MySQL 5.7.26)
- Redis 数据库环境(测试版本：Redis for Windows 5.0.14)
## 开始对接
### 后端部署
  请修改后端相关配置文件，完成以下对接：
- SQL 数据库
- Redis 数据库
- SMTP 邮箱服务
- 七牛云 API  


  首先请前往 Github 自动构建页面 下载最新的服务端 jar 包。
  配置文件修改方式有以下两种：
1. 直接使用压缩软件打开服务端 jar 包，前往 BOOT-INF/classes/ 路径下修改 application.yml 以及该路径下 config 文件夹中的线上环境配置，修改完毕后保存。
2. 在服务端 jar 包同目录下创建 config 文件夹，再自行创建 application.yml 以及 application-prod.yml 配置文件
   下面是配置文件参考示例：
```yaml
# application.yml
server:
# 后端 HTTP 端口，修改端口以后需要自行修改前端相关配置，详见 前端部署
  port: 15000
spring:
  profiles:
# 指定应用环境配置文件，例如 prod 则指定使用 config/application-prod.yml
    active: prod
```
```yaml
# application-prod.yml
spring:
  # 数据库配置，请按实际环境修改
  datasource:
    type: com.zaxxer.hikari.HikariDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    # 数据库连接 URL
    url: jdbc:mysql://localhost:3306/sharine?serverTimezone=Asia/Shanghai&allowMultiQueries=true
    # 数据库用户
    username: root
    # 数据库密码
    password: root
    # 连接池相关配置，无特殊需要可默认不做更改
    hikari:
      minimum-idle: 5
      # 空闲连接存活最大时间，默认600000（10分钟）
      idle-timeout: 180000
      # 连接池最大连接数，默认是10
      maximum-pool-size: 10
      # 此属性控制从池返回的连接的默认自动提交行为,默认值：true
      auto-commit: true
      # 连接池名称
      pool-name: MyHikariCP
      # 此属性控制池中连接的最长生命周期，值0表示无限生命周期，默认1800000即30分钟
      max-lifetime: 1800000
      # 数据库连接超时时间,默认30秒，即30000
      connection-timeout: 30000
      connection-test-query: SELECT 1
  jpa:
    show-sql: false
    hibernate:
      ddl-auto: update
  data:
    # Redis 数据库相关配置
    redis:
      host: localhost
      port: 6379
      password: root
      # SMTP 邮箱配置
  mail:
    host: smtp.163.com
    username: 用户名
    password: 密码
    default-encoding: UTF-8
    port: 587
    properties:
      mail:
        smtp:
          auth: true
          socketFactory:
            class: javax.net.ssl.SSLSocketFactory
# 七牛云相关配置
qiniu:
  access-key: key
  secret-key: key
  bucket: sharine
  domain-of-bucket: s336zn7wo.hn-bkt.clouddn.com
# 闪灵后端应用配置
application:
  # 用户订阅频道最大容量，超过容量则按先进先出规则删除最早数据
  subscribe-channel-size: 128
  # 每IP每日有效播放量统计(防止反复刷播放)
  cache-view-video-size: 256
  # 每IP每分钟请求限制
  query-per-minute-limit: 1200
```


修改完毕配置文件以后，在服务端同目录下创建服务端启动脚本，可参考以下内容：
```shell
# start.bat
# 如果没有配置 Java 环境变量，请修改 Java 为 java.exe 的具体路径
Java -Xms8G -Xmx8G -jar server.jar
pause
```
编写完成后即可双击启动后端服务器。  
### 前端部署
请首先前往 https://nodejs.org/en 安装 Node.js 环境，并从 项目仓库 拉取 Sharine/web 前端项目文件，修改项目根目录 .env 文件中的环境变量，改为后端服务器 URL。
#### 测试环境
1. 运行 npm install 补全缺失依赖
2. 运行 npm run dev 运行前端
#### 生产环境
1. 运行 npm install 补全缺失依赖
2. 运行 npm run build 打包前端项目文件为 dist 文件夹
3. 下载 nginx 后自行修改相关配置，将前端打包后得到的 dist 文件夹解压到 nginx 配置文件 http.server.location.root 指定的目录下，并运行 nginx
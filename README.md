# Geapi平台

# 上线项目
1. 用阿里云服务器
2. nacos云服务器部署
   a. 将nacos包上传置服务器并运行
   b. 需要java和maven环境
```
运行
sh startup.sh -m standalone 单机部署

关闭
sh shutdown.sh
```
## 安装java环境
1. 安装教程：https://blog.csdn.net/qq_43329216/article/details/118385502
## 安装maven
1.安装教程：https://blog.csdn.net/zh_chong/article/details/107744424

## 部署后端Geapi-backend
1. 先部署服务器的数据库导入sql语句
2. 开放服务器3306端口，数据库权限运行所有ip访问
3. 修改dubbo配置host地址为服务器==内网==地址，向nacos注册服务
```yml
配置为
dubbo:
  registry:
    address: nacos://服务区内网地址:8848
外网地址不能会导致服务不能注册
```
4. 修改cookie的作用域
```yml
server:
  address: 0.0.0.0
  port: 9092
  servlet:
    context-path: /api
    # cookie 30 天过期
    session:
      cookie:
        max-age: 2592000
        domain: 服务器外网IP
```
5. 修改SDK中的网关IP为服务器地址
5. 启动端口
```
java -jar Geapi-backend-0.0.1-SNAPSHOT.jar 
```

## 打包前端项目Geapi-frontend
1. 修改requestConfig.ts文件中的配置
```ts
  baseURL: 'http://后端公网ip地址:9092',
```
2. 修改openApi中的地址生成新的service文件（应该不需要这步）
3. config/config.ts中添加
```ts
  exportStatic: {},
```
4. 打包 build
## 使用宝塔PHP项目部署前端
1. 建立PHP项目
2. 删除PHP项目的文件夹
3. 导入我们打包好的dist文件夹
4. 设置nginx配置（一定要配置，否着前端不能正常访问到后端）
```
      location / {
        # 用于配合 browserHistory使用
        try_files $uri $uri/index.html /index.html;
  }
    
        location /api {
            rewrite ^/api/(.*) /$1 break;
            # 后台服务地址
            proxy_pass http://后端ip地址/api;
            proxy_set_header   X-Forwarded-Proto $scheme;
            proxy_set_header   Host              $http_host;
            proxy_set_header   X-Real-IP         $remote_addr;
        }
```
5. 重新启动项目


## 打包Geapi-gateway项目
1. 修改配置中IP地址为服务器地址，和dubbo中的nacos地址
```yml
dubbo:
  application:
    name: dubbo-springboot-demo-provider
  protocol:
    name: dubbo
    port: -1
  registry:
    id: nacos-registry
    address: nacos://服务内网地址:8848
```
2. 修改模拟接口地址为服务器地址
```
    private static final String INTERFACE_HOST = "http://服务器地址外网:8123";
```
3. 打包
4. 放开服务器端口
5. 安照上线后端接口流程上线
```
java -jar Geapi-gateway-0.0.1-SNAPSHOT.jar
```
## 打包Geapi-interface项目
1. 修改配置IP地址
2. 如果模拟接口有连接数据库也需要把数据库放在服务器上
3. 开发端口
4. 安照上线后端接口流程上线
```
java -jar Geapi-interface-0.0.1-SNAPSHOT.jar
```



## linux Screen保持程序后台运行
https://blog.csdn.net/Pan_peter/article/details/128875714

## 结束

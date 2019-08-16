# 赏云后台管理系统

### 类型
> *** 权限管理系统 ***

### 技术栈
> 1. 后端: SpringBoot + Shiro + Mybatis
> 4. 前端: Vue
> 2. 数据库： mysql
> 3. 服务器: tomcat

### 描述
这是我学习shiro权限管理的第一个练手项目，比较简单，如果你也想要学习shiro框架，这个项目正好适合你。

### 展示
* 首页: http://localhost:8080

### 项目部署
1.项目配置
    * [MySQL](https://dev.mysql.com/downloads/mysql/)
    * [JDK1.8或以上](http://www.oracle.com/technetwork/java/javase/overview/index.html)
    * [Maven](https://maven.apache.org/download.cgi)
2. 编辑 application.properties 文件，修改要连接的数据库用户名密码
   ```
   spring.datasource.username=root
   spring.datasource.password=root
   ```
3. 创建数据库
   ```
   create database permission_manager;
   ```
4. 选择使用数据库
   ```
   use blog;
   ```
5. 数据库导入myblog-master/sql下的数据库文件
   * db.sql
6.运行前端
   * front_page/index.html
   
### 项目截图

### 联系交流
- EMail: [carl36528@gmail.com](http://carl36528@gmail.com)
- 我的网址: [itmewdata.com](https://itnewdata.com)

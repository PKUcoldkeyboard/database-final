# database-final

## 概述

数据库系统期末项目——图书销售系统，基于Spring boot + Vue.js

## 系统功能描述

- 前台销售系统功能
  - 图书展示和分类检索：图书分类展示、图书搜索、图书详细信息浏览
  - 会员事务：会员注册、登陆、信息更改、注销
  - 订单事务：购物车管理、订单填写和查询、订单取消
  - 留言与反馈事务：用户售后评价、用户留言、功能和需求反馈
- 后台应用系统功能
  - 图书管理和更新：图书库存更新、增加、信息修改、销售统计
  - 订单管理：订单统计、查询、状态跟踪
  - 普通管理业务：销售记录浏览和修改、订单状态处理、订单浏览
  - 用户管理：用户查询统计、用户消费情况统计、用户删除，留言回复和反馈处理
  
## 数据库表

- 图书表bms_book
- 折扣表bms_book_discount
- 满减表bms_book_full_reduction
- 类别表bms_category
- 评价表bms_comment
- 回复表bms_comment_reply
- 回复内容表bms_comment_reply_content
- 评价内容表bms_comment_content
- 购物车oms_cart_item
- 订单表oms_order
- 权限表role
- 用户邮箱表ums_user_email
- 用户信息表ums_user_info
- 收货用户信息表ums_user_receive
- 用户统计表ums_user_statistics
- 用户表user
- 用户权限关系表user_role

## 系统组成说明

- db-final-web部分

src --源码目录

├─api --axios网络请求定义

├─assets --静态图片资源文件

│ └─404_images

├─components --通用组件封装

├─plugins --插件，包括ant-design-vue、element和axios

├─router --vue-router：路由器，用于地址解析和页面跳转

├─store --vuex：状态管理

├─utils --工具类，包括：cookie工具类、token工具类、request工具类、日期处理工具类、格式验证工具类

└─views --前端页面

 ├─findPwd --找回密码页面

 ├─layout --通用页面加载框架

 ├─login --登录页面

 ├─register --注册页面

  ├─index.vue --首页

  ├─specials.vue --每日特价页

  ├─cart.vue --购物车页

  ├─contact.vue --联系我们页

  ├─a-steps.vue  --下单流程页

  ├─order.vue  --订单页

  ├─...

- db-final部分

├─common --常数变量、响应结果封装、响应码枚举、消息队列枚举

├─component --消息队列接收器和发送器、Redis缓存切面（防止Redis宕机影响正常业务）、日志切面（用于打印日志进行debug）

├─config --配置类：跨域请求配置、消息队列配置、ElasticSearch配置、定时任务配置、安全与认证配置

├─controller --控制器：用于处理web请求

├─dao --数据访问对象，用于封装操作Mysql数据库中数据的方法。

├─dto --数据传输对象，封装请求体的数据实体。

├─elasticsearch --用于封装操作ElasticSearch数据库中数据的方法。

├─entity --实体类：对应的是数据库中的一张表，对属性进行封装

├─exception --异常处理类：对系统中发生的异常进行统一处理

├─filter --过滤器：用于拦截请求进行验证

├─service --业务层：业务模块的逻辑应用接口。

│ └─impl --service接口的实现类：用于业务实现

└─util --工具类：包括jwt工具类、邮件工具类、分页工具类


## 开发环境

- 操作系统：Windows 10 Enterprise
- 开发工具：Eclipse 2020
- Java SDK：Open JDK 1.8.261
- 数据库：MySQL 8.0、Redis、ElasticSearch

## 部署环境

- 操作系统：Linux Ubuntu Server 18.04 X64

## 项目管理工具

- 项目构建：Maven
- 代码管理：Git
- 镜像管理：Docker Registry

## 后台主要技术栈

- 核心框架：Spring Boot
- ORM框架：Mybatis
- 数据库连接池：HikariCP
- 数据库缓存：Redis
- 消息中间件：RabbitMQ
- 全文检索引擎：ElasticSearch
- 分布式文件系统：Minio
- 反向代理+负载均衡：Nginx

## 前后端分离

- 前端框架：NodeJs + Vue + Axios
- 前端模板：Ant Design Vue + ElementUI

## 持续集成

- Github + Jenkins

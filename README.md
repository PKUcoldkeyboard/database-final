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

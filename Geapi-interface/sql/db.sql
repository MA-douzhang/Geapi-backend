-- 创建库
create database if not exists interface_api;

-- 切换库
use interface_api;

-- 用户表
create table if not exists love_words
(
    id           bigint auto_increment comment 'id' primary key,
    loveWord varchar(512)                           not null comment '情话'
    ) comment '情话' collate = utf8mb4_unicode_ci;

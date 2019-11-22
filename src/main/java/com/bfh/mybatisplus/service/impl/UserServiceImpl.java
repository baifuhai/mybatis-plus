package com.bfh.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfh.mybatisplus.entity.User;
import com.bfh.mybatisplus.mapper.UserMapper;
import com.bfh.mybatisplus.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 不用再进行mapper的注入
 *
 * EmployeeServiceImpl 继承了 ServiceImpl
 * 1. 在ServiceImpl中已经完成Mapper对象的注入，直接在EmployeeServiceImpl中进行使用.
 * 2. 在ServiceImpl中也帮我们提供了常用的CRUD方法，基本的一些CRUD方法在Service中不需要我们自己定义.
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}

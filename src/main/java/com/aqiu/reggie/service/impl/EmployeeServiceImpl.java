package com.aqiu.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aqiu.reggie.entity.Employee;
import com.aqiu.reggie.mapper.EmployeeMapper;
import com.aqiu.reggie.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper,Employee> implements EmployeeService{
}

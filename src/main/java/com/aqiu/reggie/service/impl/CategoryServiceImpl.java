package com.aqiu.reggie.service.impl;

import com.aqiu.reggie.entity.Category;
import com.aqiu.reggie.mapper.CategoryMapper;
import com.aqiu.reggie.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>Powered by Aqiu On 2023--02--20--11--34--32
 *
 * @author Aqiu
 * @version 1.0
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}

package com.aqiu.reggie.service.impl;

import com.aqiu.reggie.common.CustomException;
import com.aqiu.reggie.entity.Category;
import com.aqiu.reggie.entity.Dish;
import com.aqiu.reggie.entity.Setmeal;
import com.aqiu.reggie.mapper.CategoryMapper;
import com.aqiu.reggie.service.CategoryService;
import com.aqiu.reggie.service.DishService;
import com.aqiu.reggie.service.SetmealService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>Powered by Aqiu On 2023--02--20--11--34--32
 *
 * @author Aqiu
 * @version 1.0
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {


    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    /**
     * 根据id删除分类，删除之前判断是否关联菜品菜单
     * @param ids
     */
    @Override
    public void remove(Long ids) {

        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();

        dishLambdaQueryWrapper.eq(Dish::getCategoryId,ids);

        int count = dishService.count(dishLambdaQueryWrapper);

        if(count>0){
            throw new CustomException("当前分类下关联了菜品，不能删除");
        }

        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();

        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,ids);

        int count2 = setmealService.count(setmealLambdaQueryWrapper);

        if(count2 > 0){
            throw new CustomException("当前分类下关联了套餐，不能删除");
        }

        super.removeById(ids);

    }
}

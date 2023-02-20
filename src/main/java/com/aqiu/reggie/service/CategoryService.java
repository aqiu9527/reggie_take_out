package com.aqiu.reggie.service;

import com.aqiu.reggie.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>Powered by Aqiu On 2023--02--20--11--34--11
 *
 * @author Aqiu
 * @version 1.0
 */
public interface CategoryService extends IService<Category> {
    public void remove(Long ids);
}

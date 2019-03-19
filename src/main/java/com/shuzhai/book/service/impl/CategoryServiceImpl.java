package com.shuzhai.book.service.impl;

import com.shuzhai.book.common.bean.Category;
import com.shuzhai.book.common.utils.ServerResponse;
import com.shuzhai.book.dao.ICategoryMapper;
import com.shuzhai.book.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryMapper categoryMapper;

    @Override
    public ServerResponse<List<Category>> findAllCategory() {
        List<Category> categories = categoryMapper.selectAll();
        if (categories != null) {
            return ServerResponse.createBySuccess(categories);
        }
        return ServerResponse.createByErrorMessage("系统错误");
    }
}

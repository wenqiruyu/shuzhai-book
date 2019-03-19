package com.shuzhai.book.web.controller;

import com.shuzhai.book.common.bean.Category;
import com.shuzhai.book.common.utils.ServerResponse;
import com.shuzhai.book.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping(value = "/getCategory")
    @CrossOrigin
    public ServerResponse<List<Category>> getCategory(){
        return categoryService.findAllCategory();
    }
}
